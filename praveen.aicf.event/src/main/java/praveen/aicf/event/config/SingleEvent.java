package praveen.aicf.event.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.gson.Gson;

import praveen.aicf.event.models.AICFEvent;
import praveen.aicf.event.models.EventCategory;
import praveen.aicf.event.models.Organizer;

public class SingleEvent {
	
	private static Gson gson=new Gson();
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
		WebClient webClient = new WebClient();
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		
	    HtmlPage page = webClient.getPage("http://aicf.in/calendar/15th-delhi-gm-international-open-2017/");
	    webClient.waitForBackgroundJavaScript(100);
	    AICFEvent a1=new AICFEvent();
	    
	    HtmlElement title=(HtmlElement) page.getFirstByXPath("//h2[@class='tribe-events-single-event-title summary entry-title']");
	    a1.setTitle(title.asText());
	    //HtmlElement venue=(HtmlElement) page.getFirstByXPath("//div[@class='tribe-events-meta-group tribe-events-meta-group-venue']");
	    //a1.setAddress(((HtmlAnchor)venue.getFirstByXPath("//a[@class='tribe-events-gmap']")).getHrefAttribute());
	    
	    HtmlElement details=(HtmlElement)page.getFirstByXPath("//div[@class='tribe-events-meta-group tribe-events-meta-group-details']");
	    a1.setStart(new Date(((HtmlElement) details.getFirstByXPath("//abbr[@class='tribe-events-abbr updated published dtstart']")).asText()));
	    a1.setEnd(new Date(((HtmlElement) details.getFirstByXPath("//abbr[@class='tribe-events-abbr dtend']")).asText()));
	    a1.setPrizeMoney(((HtmlElement) details.getFirstByXPath("//dd[@class='tribe-events-event-cost']")).asText());
	    
	    List<HtmlAnchor> eventCategoryAICF=(List<HtmlAnchor>) details.getByXPath("//dd[@class='tribe-events-event-categories']//a");
	    List<EventCategory> eventCategoryList=new ArrayList<>();
	    for(HtmlAnchor an:eventCategoryAICF){
	    	EventCategory e1=new EventCategory();
	    	e1.setEvent(an.asText());
	    	e1.setLink(an.getHrefAttribute());
	    	eventCategoryList.add(e1);
	    }
	    a1.setEventCategoryList(eventCategoryList);
	    
	    HtmlElement organizer=(HtmlElement)page.getFirstByXPath("//div[@class='tribe-events-meta-group tribe-events-meta-group-organizer']");
	    Organizer o1=new Organizer();
	    o1.setName(((HtmlElement) organizer.getFirstByXPath("//dd[@class='fn org']")).asText());
	    o1.setPhone(((HtmlElement) organizer.getFirstByXPath("//dd[@class='tel']")).asText());
	    o1.setEmail(((HtmlElement) organizer.getFirstByXPath("//dd[@class='email']")).asText());
	    o1.setWebsite(((HtmlAnchor) organizer.getFirstByXPath("//dd[@class='url']//a")).getHrefAttribute());
	    
	    a1.setOrganizer(o1);
	    System.out.println(gson.toJson(a1));
	    //System.out.println(venue.asXml());
	}

}