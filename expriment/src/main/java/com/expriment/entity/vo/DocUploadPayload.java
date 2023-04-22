package com.expriment.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocUploadPayload implements Serializable{

	private static final long serialVersionUID = 6278047654991799340L;
	
	private String webtopNo;
	private String applicantType;
	private String applicantNature;
	private String docUploadType;
	private String docUploadName;
	private String loanType;
	private String base64;
	private Long docId;
	private String MobileNo;

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}
	
	public Long getDocId() {
		return docId;
	}
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	public String getWebtopNo() {
		return webtopNo;
	}
	public void setWebtopNo(String webtopNo) {
		this.webtopNo = webtopNo;
	}
	public String getApplicantType() {
		return applicantType;
	}
	public void setApplicantType(String applicantType) {
		this.applicantType = applicantType;
	}
	public String getApplicantNature() {
		return applicantNature;
	}
	public void setApplicantNature(String applicantNature) {
		this.applicantNature = applicantNature;
	}
	public String getDocUploadType() {
		return docUploadType;
	}
	public void setDocUploadType(String docUploadType) {
		this.docUploadType = docUploadType;
	}
	public String getDocUploadName() {
		return docUploadName;
	}
	public void setDocUploadName(String docUploadName) {
		this.docUploadName = docUploadName;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	
	

}