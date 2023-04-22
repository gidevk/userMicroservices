package com.expriment.utils.audit.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.Serializable;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmsMailPayload extends RootPayload implements Serializable {

	private static final long serialVersionUID = -3173976006217369965L;
	
	private String message;
	private String mailTo;
	private String fileName;
	private File file;
	private String subject;
	private String completeFilePath;
	
	private String completeFilePath1;
	private String fileName1;
	private File file1;
	
	private String leadId;
	private String mobileNumber;
	private String smsTrigger;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCompleteFilePath1() {
		return completeFilePath1;
	}
	public void setCompleteFilePath1(String completeFilePath1) {
		this.completeFilePath1 = completeFilePath1;
	}
	public String getFileName1() {
		return fileName1;
	}
	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}
	public File getFile1() {
		return file1;
	}
	public void setFile1(File file1) {
		this.file1 = file1;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCompleteFilePath() {
		return completeFilePath;
	}
	public void setCompleteFilePath(String completeFilePath) {
		this.completeFilePath = completeFilePath;
	}
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getSmsTrigger() {
		return smsTrigger;
	}
	public void setSmsTrigger(String smsTrigger) {
		this.smsTrigger = smsTrigger;
	}

}
