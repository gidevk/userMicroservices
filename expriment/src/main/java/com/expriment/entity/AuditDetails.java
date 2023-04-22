package com.expriment.entity;

import com.expriment.utils.ProjectConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "tbl_audit_details", catalog = ProjectConstants.DB.Exp_User)
public class AuditDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "rec_no")
	private Long recNo;
	
	@Column(name="lead_id")
	private String leadId;
	
	@Column(name = "api_name")
	private String apiName;
	
	@Column(name = "request_time")
	private Date requestTime;
	
	@Column(name = "response_time")
	private Date responseTime;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "no_of_attempts")
	private String noOfAttempts;
	
	@Column(name = "request_file_name")
	private String requestFileName;
	
	@Column(name = "response_file_name")
	private String responseFileName;
	
	@Column(name = "request_url")
	private String requestUrl;
	
	@Column(name = "mobile_number")
	private Long mobileNumber;
	
	@Column(name="email_id")
	private String emailId;
	
	
	@Column(name="conversation_id")
	private String conversationId;
	
	
	
	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public Long getRecNo() {
		return recNo;
	}

	public void setRecNo(Long recNo) {
		this.recNo = recNo;
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

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
