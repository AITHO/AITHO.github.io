package org.aitho.weather;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;

public class WeatherClient {
	
	public static WeatherResponse getWeather(String date, String city) throws Exception {
		String apiKey = "API_KEY";
		return sendGet(date, city, apiKey);
	}
	
	private static WeatherResponse sendGet(String date, String city, String apiKey) throws Exception {
		WeatherResponse weatherResponse = null;
		String url = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apiKey;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		if(con.getResponseCode() != 200) {
			return weatherResponse;
		}

		String json = convertStreamToString(con.getInputStream());
		weatherResponse = new Gson().fromJson(json, WeatherResponse.class);
		return weatherResponse;

	}
	
	static String convertStreamToString(java.io.InputStream is) {
		Scanner scanner = new Scanner(is);
		scanner.useDelimiter("\\A");
		String output = scanner.hasNext() ? scanner.next() : "";
		scanner.close();
	    return output;
	}
	
}

