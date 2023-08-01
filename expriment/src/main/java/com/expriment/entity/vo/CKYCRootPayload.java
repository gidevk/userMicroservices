package com.expriment.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CKYCRootPayload implements java.io.Serializable {

	private static final long serialVersionUID = 95123655152888L;
	
	private String appId;
	private String stage;
	private String mobileNumber;
	private String customerHash;
	private String appLeadId;
	private String empId;
	private String applSource;
	private String deviceType;
	private String deviceSubType;
	private String schemeId;
	private String userId;
	private String acceptConsent;
	private String consentSource;
	private Boolean isCREJourney;
	private String deviceOS;
	private String channel;
	private Boolean isOkycRequired;
	private Boolean isFromVMZero;
	private String city;
	private String logoutReason;
	private String ipAddress;
	
	
	
	
	public String getCustomerHash() {
		return customerHash;
	}
	public void setCustomerHash(String customerHashNew) {
		this.customerHash = customerHashNew;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAppLeadId() {
		return appLeadId;
	}
	public void setAppLeadId(String appLeadId) {
		this.appLeadId = appLeadId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getApplSource() {
		return applSource;
	}
	public void setApplSource(String applSource) {
		this.applSource = applSource;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceSubType() {
		return deviceSubType;
	}
	public void setDeviceSubType(String deviceSubType) {
		this.deviceSubType = deviceSubType;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAcceptConsent() {
		return acceptConsent;
	}
	public void setAcceptConsent(String acceptConsent) {
		this.acceptConsent = acceptConsent;
	}
	public String getConsentSource() {
		return consentSource;
	}
	public void setConsentSource(String consentSource) {
		this.consentSource = consentSource;
	}
	public Boolean getIsCREJourney() {
		return isCREJourney;
	}
	public void setIsCREJourney(Boolean isCREJourney) {
		this.isCREJourney = isCREJourney;
	}
	public String getDeviceOS() {
		return deviceOS;
	}
	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Boolean getIsOkycRequired() {
		return isOkycRequired;
	}
	public void setIsOkycRequired(Boolean isOkycRequired) {
		this.isOkycRequired = isOkycRequired;
	}
	public Boolean getIsFromVMZero() {
		return isFromVMZero;
	}
	public void setIsFromVMZero(Boolean isFromVMZero) {
		this.isFromVMZero = isFromVMZero;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLogoutReason() {
		return logoutReason;
	}
	public void setLogoutReason(String logoutReason) {
		this.logoutReason = logoutReason;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
