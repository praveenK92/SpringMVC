package praveen.aicf.event.config;

import java.io.File;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.google.gson.Gson;

import praveen.aicf.event.html.AICFEventData;
import praveen.aicf.event.models.AICFEvent;
import praveen.aicf.event.models.EventCategory;
import praveen.aicf.event.models.Organizer;

/**
 * Hello world!
 *
 */
public class App {
	private static WebClient webClient=new WebClient();
	private static Gson gson=new Gson();
	
	public static String getAICFCalendarData(int year) throws Exception{
		String x="http://aicf.in/wp-content/plugins/aicf-events-calendar/views/year-ajax.php";
		URL u1=new URL(x);
		HttpURLConnection conn=(HttpURLConnection)u1.openConnection();
		conn.setRequestMethod("POST");
		
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Host", "aicf.in");
		conn.setRequestProperty("Origin", "http://aicf.in");
		conn.setRequestProperty("Referer", "http://aicf.in/calendar/");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write("year="+year);
		writer.flush();
		
		int responseCode=conn.getResponseCode();
		
		if(responseCode==200){
			StringWebResponse response=new StringWebResponse("<html><head><title>Test</title></head><body></body></html>",
					conn.getURL());
			String res1=IOUtils.toString(conn.getInputStream());
			System.out.println(response);
			File f=new File("./src/main/resources/aicf_calendar_event_data.html");
			FileUtils.writeStringToFile(f, res1);
			return res1;
		}else return null;
	}
	
	public static void main(String[] args) throws Exception{
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		
		Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		
		HtmlPage page=webClient.getPage("http://aicf.in/calendar/");
		webClient.waitForBackgroundJavaScript(2*1000);
		
		/*String response=getAICFCalendarData(2017);
		if(response==null)return;
		HtmlPage page=HTMLParser.parseHtml(new WebResponse(response), webClient.getCurrentWindow());*/
		
		List<HtmlAnchor> eventList=(List<HtmlAnchor>)
				page.getByXPath("//div[@class='col-xs-4 table-event-name']//a");
		List<HtmlElement> eventCodeList=(List<HtmlElement>) page.
				getByXPath("//div[@class='col-xs-2 text-center table-event-code']");
		
		
		System.out.println(eventList.size());
		List<AICFEventData> eventDataList=new ArrayList<>();
		for(int i=0;i<eventList.size();i++){
			AICFEventData aicfEvent=new AICFEventData();
			aicfEvent.setaHref(eventList.get(i).getHrefAttribute());
			aicfEvent.setEventCode(eventCodeList.get(i).asText());
			eventDataList.add(aicfEvent);
		}
		List<AICFEvent> aicfEventList=getAllAICFEventsData(eventDataList);
		File f=new File("./src/main/resources/aicf_event_data.json");
		FileUtils.writeStringToFile(f, gson.toJson(aicfEventList));
	}
	public static List<AICFEvent> getAllAICFEventsData(List<AICFEventData> eventList){
		List<AICFEvent> aicfEventList=new ArrayList<>();
		int i=1;
		for(AICFEventData event:eventList){
			System.out.println(i+". "+event.getaHref()+" "+event.getEventCode());
			i++;
			try {
				AICFEvent a=getAICFEvent(event.getaHref(),event.getEventCode());
				if(a!=null)aicfEventList.add(a);
			}catch (Exception e){
				e.printStackTrace();
			}
			//if(i==3)break;
		}
		if(aicfEventList.size()<1)return null;
		else return aicfEventList;
	}
	public static AICFEvent getAICFEvent(String href,String eventCode) throws Exception{
		HtmlPage page = webClient.getPage(href);
	    webClient.waitForBackgroundJavaScript(100);
		
	    AICFEvent a1=new AICFEvent();
	    a1.setEventCode(eventCode);
	    
	    HtmlAnchor anchorTemp=(HtmlAnchor)
	    		page.getFirstByXPath("//div[@class='tribe-events-cal-links']"+
	    				"//a[@class='tribe-events-button prospectus-single-event']");
	    
	    a1.setDownload(anchorTemp!=null?anchorTemp.getHrefAttribute():null);
	    
	    HtmlElement title=(HtmlElement) page.getFirstByXPath("//h2[@class='tribe-events-single-event-title summary entry-title']");
	    a1.setTitle(title.asText());
	    
	    HtmlElement details=(HtmlElement)page.
	    		getFirstByXPath("//div[@class='tribe-events-meta-group tribe-events-meta-group-details']");
	    
	    HtmlElement temp=(HtmlElement)details.
	    		getFirstByXPath("//abbr[@class='tribe-events-abbr updated published dtstart']");
	    if(temp!=null && !temp.asText().equals("")){
	    	a1.setStart(new Date(temp.asText()));
	    }
	    
	    temp=(HtmlElement)details.
	    		getFirstByXPath("//abbr[@class='tribe-events-abbr dtend']");
	    if(temp!=null && !temp.asText().equals("")){
	    	a1.setEnd(new Date(temp.asText()));
	    }
	    
	    temp=(HtmlElement) details.getFirstByXPath("//dd[@class='tribe-events-event-cost']");
	    a1.setPrizeMoney(temp!=null?temp.asText():"-");
	    
	    //EventCategory Data here
	    List<HtmlAnchor> eventCategoryAICF=(List<HtmlAnchor>)
	    		details.getByXPath("//dd[@class='tribe-events-event-categories']//a");
	    List<EventCategory> eventCategoryList=new ArrayList<>();
	    for(HtmlAnchor an:eventCategoryAICF){
	    	EventCategory e1=new EventCategory();
	    	e1.setEvent(an.asText());
	    	e1.setLink(an.getHrefAttribute());
	    	eventCategoryList.add(e1);
	    }
	    a1.setEventCategoryList(eventCategoryList);
	    
	    //Get Organizer data here
	    HtmlElement organizer=(HtmlElement)page.
	    		getFirstByXPath("//div[@class='tribe-events-meta-group tribe-events-meta-group-organizer']");
	    if(organizer!=null){
	    	Organizer o1=new Organizer();
		    o1.setName(((HtmlElement) organizer.getFirstByXPath("//dd[@class='fn org']")).asText());
		    
		    temp=(HtmlElement) organizer.getFirstByXPath("//dd[@class='tel']");
		    o1.setPhone(temp!=null?temp.asText():null);
		    
		    temp=(HtmlElement) organizer.getFirstByXPath("//dd[@class='email']");
		    o1.setEmail(temp!=null?temp.asText():null);
		    temp=(HtmlElement) organizer.getFirstByXPath("//dd[@class='url']//a");
		    o1.setWebsite(temp!=null?temp.asText():null);
		    
		    a1.setOrganizer(o1);
	    }
	    
	    return a1;
	}
}
