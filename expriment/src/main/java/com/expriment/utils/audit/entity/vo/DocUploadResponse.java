package com.expriment.utils.audit.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocUploadResponse extends RootResponse implements java.io.Serializable {
	private static final long serialVersionUID = 63049181786146143L;

	private String LogTxnID;
	private String RetStatus;
	private String ErrorMessage;
	private String ObjectID;
	
	public String getLogTxnID() {
		return LogTxnID;
	}
	public void setLogTxnID(String logTxnID) {
		LogTxnID = logTxnID;
	}
	public String getRetStatus() {
		return RetStatus;
	}
	public void setRetStatus(String retStatus) {
		RetStatus = retStatus;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public String getObjectID() {
		return ObjectID;
	}
	public void setObjectID(String objectID) {
		ObjectID = objectID;
	}
}