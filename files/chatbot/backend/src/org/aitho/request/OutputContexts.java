package org.aitho.request;

import java.util.HashMap;

public class OutputContexts {
	private String name;
	private int lifespanCount;
	private HashMap<String, String> parameters;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLifespanCount() {
		return lifespanCount;
	}
	public void setLifespanCount(int lifespanCount) {
		this.lifespanCount = lifespanCount;
	}
	public HashMap<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}
	
}
