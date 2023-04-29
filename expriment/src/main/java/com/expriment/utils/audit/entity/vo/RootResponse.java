package com.expriment.utils.audit.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootResponse implements java.io.Serializable {
	private static final long serialVersionUID = 998645321888L;
	
	private String leadId;
	private String retStatus;
	private String sysErrorCode;
	private String sysErrorMessage;
	private String CustomerHash;
	private String emiCardNumber;
	private String opportunityId;
    private  String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	public String getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(String opportunityId) {
		this.opportunityId = opportunityId;
	}

	public String getRetStatus() {
		return retStatus;
	}
	public void setRetStatus(String retStatus) {
		this.retStatus = retStatus;
	}
	public String getSysErrorCode() {
		return sysErrorCode;
	}
	public void setSysErrorCode(String sysErrorCode) {
		this.sysErrorCode = sysErrorCode;
	}
	public String getSysErrorMessage() {
		return sysErrorMessage;
	}
	public void setSysErrorMessage(String sysErrorMessage) {
		this.sysErrorMessage = sysErrorMessage;
	}
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	public String getCustomerHash() {
		return CustomerHash;
	}
	public void setCustomerHash(String customerHash) {
		this.CustomerHash = customerHash;
	}
	public String getEmiCardNumber() {
		return emiCardNumber;
	}
	public void setEmiCardNumber(String emiCardNumber) {
		this.emiCardNumber = emiCardNumber;
	}
}
