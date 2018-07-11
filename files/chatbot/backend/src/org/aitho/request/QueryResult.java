package org.aitho.request;

import java.util.HashMap;

public class QueryResult {
	private String queryText;
	private String action;
	private HashMap<String, String> parameters;
	private boolean allRequiredParamsPresent;
	private String fulfillmentText;
	private FulfillmentMessages[] fulfillmentMessages;
	private OutputContexts[] outputContexts;
	private Intent intent;
	private float intentDetectionConfidence;
	
	private HashMap<String, String> diagnosticInfo;
	private String languageCode;
	public String getQueryText() {
		return queryText;
	}
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public HashMap<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}
	public boolean isAllRequiredParamsPresent() {
		return allRequiredParamsPresent;
	}
	public void setAllRequiredParamsPresent(boolean allRequiredParamsPresent) {
		this.allRequiredParamsPresent = allRequiredParamsPresent;
	}
	public String getFulfillmentText() {
		return fulfillmentText;
	}
	public void setFulfillmentText(String fulfillmentText) {
		this.fulfillmentText = fulfillmentText;
	}
	public FulfillmentMessages[] getFulfillmentMessages() {
		return fulfillmentMessages;
	}
	public void setFulfillmentMessages(FulfillmentMessages[] fulfillmentMessages) {
		this.fulfillmentMessages = fulfillmentMessages;
	}
	public OutputContexts[] getOutputContexts() {
		return outputContexts;
	}
	public void setOutputContexts(OutputContexts[] outputContexts) {
		this.outputContexts = outputContexts;
	}
	public Intent getIntent() {
		return intent;
	}
	public void setIntent(Intent intent) {
		this.intent = intent;
	}
	public float getIntentDetectionConfidence() {
		return intentDetectionConfidence;
	}
	public void setIntentDetectionConfidence(float intentDetectionConfidence) {
		this.intentDetectionConfidence = intentDetectionConfidence;
	}
	public HashMap<String, String> getDiagnosticInfo() {
		return diagnosticInfo;
	}
	public void setDiagnosticInfo(HashMap<String, String> diagnosticInfo) {
		this.diagnosticInfo = diagnosticInfo;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
		
}
