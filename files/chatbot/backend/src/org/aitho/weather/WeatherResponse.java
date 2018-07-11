package org.aitho.weather;

import java.util.HashMap;

public class WeatherResponse {
	private HashMap<String, String> coord;
	private HashMap<String, String>[] weather;
	private String base;
	private HashMap<String, String> main;
	private String visibility;
	private HashMap<String, String> wind;
	private HashMap<String, String> clouds;
	private String dt;
	private HashMap<String, String> sys;
	private String id;
	private String name;
	private String cod;
	public HashMap<String, String> getCoord() {
		return coord;
	}
	public void setCoord(HashMap<String, String> coord) {
		this.coord = coord;
	}
	public HashMap<String, String>[] getWeather() {
		return weather;
	}
	public void setWeather(HashMap<String, String>[] weather) {
		this.weather = weather;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public HashMap<String, String> getMain() {
		return main;
	}
	public void setMain(HashMap<String, String> main) {
		this.main = main;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public HashMap<String, String> getWind() {
		return wind;
	}
	public void setWind(HashMap<String, String> wind) {
		this.wind = wind;
	}
	public HashMap<String, String> getClouds() {
		return clouds;
	}
	public void setClouds(HashMap<String, String> clouds) {
		this.clouds = clouds;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public HashMap<String, String> getSys() {
		return sys;
	}
	public void setSys(HashMap<String, String> sys) {
		this.sys = sys;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	
	
}