package praveen.aicf.event.config;

import java.io.File;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class EventsURLPostData {

	public static void main(String[] args) throws Exception {
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
		writer.write("year=2017");
		writer.flush();
		
		int responseCode=conn.getResponseCode();
		System.out.println(responseCode);
		if(responseCode==200 || true){
			String response=IOUtils.toString(conn.getInputStream(),"UTF-8");
			File f=new File("./src/main/resources/aicf_calendar_event_data.html");
			FileUtils.writeStringToFile(f, response);
		}
		
	}

}
