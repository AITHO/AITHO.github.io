package org.aitho.request;

import java.util.HashMap;
import java.util.Map;

public class AgentRequest {
	private String responseId;
	private QueryResult queryResult;
	private OriginalDetectIntentRequest originalDetectIntentRequest;
	private String session;
	
	public String getResponseId() {
		return responseId;
	}
	public QueryResult getQueryResult() {
		return queryResult;
	}
	public OriginalDetectIntentRequest getOriginalDetectIntentRequest() {
		return originalDetectIntentRequest;
	}
	public String getSession() {
		return session;
	}
}