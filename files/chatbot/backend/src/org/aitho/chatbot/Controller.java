package org.aitho.chatbot;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.aitho.request.AgentRequest;
import org.aitho.response.AgentResponse;
import org.aitho.weather.WeatherClient;
import org.aitho.weather.WeatherResponse;

import com.google.gson.Gson;

public class Controller {
	public static void handle(AgentRequest agentRequest, HttpServletResponse response) throws Exception {
		AgentResponse agentResponse = new AgentResponse();
		if(agentRequest.getQueryResult().getIntent().getDisplayName().equalsIgnoreCase("get-data-meteo-splitted")) {
			String date = agentRequest.getQueryResult().getParameters().get("date");
			String city = agentRequest.getQueryResult().getParameters().get("city");
			if(date == null || date.isEmpty()) {
				agentResponse.setFulfillmentText("Non non riuscito a capire la data");
				sendResponse(agentResponse, response);
				return;
			}
			
			if(city == null || city.isEmpty()) {
				agentResponse.setFulfillmentText("Non non riuscito a capire la citta'");
				sendResponse(agentResponse, response);
				return;
			}
			WeatherResponse weatherResponse = WeatherClient.getWeather(date, city);
			String outputMessage = weatherResponse.getWeather()[0].get("main")+": "+weatherResponse.getWeather()[0].get("description");
			agentResponse.setFulfillmentText(outputMessage);
			sendResponse(agentResponse, response);
		}
	} 
	
	private static void sendResponse(AgentResponse agentResponse, HttpServletResponse response) throws IOException {
		response.getWriter().append(new Gson().toJson(agentResponse));
	}
}
