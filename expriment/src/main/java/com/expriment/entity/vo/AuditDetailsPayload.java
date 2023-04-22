
package com.expriment.entity.vo;

import com.expriment.utils.audit.entity.vo.RootPayload;

import java.io.Serializable;
import java.util.Date;


/**
 * @author vikash singh
 *
 */
public class AuditDetailsPayload extends RootPayload implements Serializable {

	
	private static final long serialVersionUID = 8640965136944151531L;

	private String apiName;
	private Date requestTime;
	private Date responseTime;
	private String status;
	private String noOfAttempts;
	private String requestFileName;
	private String responseFileName;
	private String requestUrl;
	private String payload;
	private String response;
	private String mobileNumber;
	private String emailId;
	private String leadId;
	private String conversationId;
	
	
	
	
	public String getConversationId() {
		return conversationId;
	}
	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNoOfAttempts() {
		return noOfAttempts;
	}
	public void setNoOfAttempts(String noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}
	public String getRequestFileName() {
		return requestFileName;
	}
	public void setRequestFileName(String requestFileName) {
		this.requestFileName = requestFileName;
	}
	public String getResponseFileName() {
		return responseFileName;
	}
	public void setResponseFileName(String responseFileName) {
		this.responseFileName = responseFileName;
	}
	
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
}
