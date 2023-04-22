package com.expriment.utils.audit.entity;

import com.expriment.utils.audit.entity.vo.RootResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SmsMailResponse extends RootResponse implements Serializable {

	
	private static final long serialVersionUID = -5880787062328246060L;
	
	private String mobileNumber;
	private String leadId;
	private Boolean isSendSms;
	private Boolean isSendMail;
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	public Boolean getIsSendSms() {
		return isSendSms;
	}
	public void setIsSendSms(Boolean isSendSms) {
		this.isSendSms = isSendSms;
	}
	public Boolean getIsSendMail() {
		return isSendMail;
	}
	public void setIsSendMail(Boolean isSendMail) {
		this.isSendMail = isSendMail;
	}
	
	

}

