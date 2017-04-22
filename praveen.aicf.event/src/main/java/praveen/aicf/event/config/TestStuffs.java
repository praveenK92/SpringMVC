package praveen.aicf.event.config;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import praveen.aicf.event.models.AICFEvent;

public class TestStuffs {
	private static Gson gson=new Gson();
	public static void main(String[] args) throws Exception {
		Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		App app=new App();
		AICFEvent a=app.getAICFEvent("http://aicf.in/calendar/15th-delhi-gm-international-open-2017", "Pikachu!");
		System.out.println(gson.toJson(a));
	}

}
