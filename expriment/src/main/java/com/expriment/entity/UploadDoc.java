package com.expriment.entity;

import com.expriment.utils.ProjectConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="tbl_upload_doc", catalog = ProjectConstants.DB.Exp_User)
public class UploadDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="doc_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long docId;
	
	@Column(name="lead_id")
	private String leadId;

	@Column(name="pl_lead_id")
	private String plleadId;
	
	@Column(name="applicant_nature")
	private String applicantNature;

	@Column(name="applicant_type")
	private String applicantType;

	@Column(name="doc_upload_base64")
	private String docUploadBase64;

	@Column(name="doc_upload_error_message")
	private String docUploadErrorMessage;

	@Column(name="doc_upload_logtxnid")
	private String docUploadLogtxnid;

	@Column(name="doc_upload_name")
	private String docUploadName;

	@Column(name="doc_upload_objectid")
	private String docUploadObjectid;

	@Column(name="doc_upload_ret_status")
	private String docUploadRetStatus;

	@Column(name="doc_upload_type")
	private String docUploadType;

	@Column(name="loan_type")
	private String loanType;

	@Column(name="webtop_no")
	private String webtopNo;
	
	@Column(name = "doc_retry_count")
	private Integer docRetryCount;
	
	@Column(name = "doc_uploaded_to_jocata_status")
	private String docUploadedToJocataStatus;

	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;

	@Column(name="base64_formatted_data")
	private String base64FormattedData;

	public String getBase64FormattedData() {
		return base64FormattedData;
	}

	public void setBase64FormattedData(String base64FormattedData) {
		this.base64FormattedData = base64FormattedData;
	}
	
	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getApplicantNature() {
		return this.applicantNature;
	}

	public void setApplicantNature(String applicantNature) {
		this.applicantNature = applicantNature;
	}

	public String getApplicantType() {
		return this.applicantType;
	}

	public void setApplicantType(String applicantType) {
		this.applicantType = applicantType;
	}

	public String getDocUploadBase64() {
		return this.docUploadBase64;
	}

	public void setDocUploadBase64(String docUploadBase64) {
		this.docUploadBase64 = docUploadBase64;
	}

	public String getDocUploadErrorMessage() {
		return this.docUploadErrorMessage;
	}

	public void setDocUploadErrorMessage(String docUploadErrorMessage) {
		this.docUploadErrorMessage = docUploadErrorMessage;
	}

	public String getDocUploadLogtxnid() {
		return this.docUploadLogtxnid;
	}

	public void setDocUploadLogtxnid(String docUploadLogtxnid) {
		this.docUploadLogtxnid = docUploadLogtxnid;
	}

	public String getDocUploadName() {
		return this.docUploadName;
	}

	public void setDocUploadName(String docUploadName) {
		this.docUploadName = docUploadName;
	}

	public String getDocUploadObjectid() {
		return this.docUploadObjectid;
	}

	public void setDocUploadObjectid(String docUploadObjectid) {
		this.docUploadObjectid = docUploadObjectid;
	}

	public String getDocUploadRetStatus() {
		return this.docUploadRetStatus;
	}

	public void setDocUploadRetStatus(String docUploadRetStatus) {
		this.docUploadRetStatus = docUploadRetStatus;
	}

	public String getDocUploadType() {
		return this.docUploadType;
	}

	public void setDocUploadType(String docUploadType) {
		this.docUploadType = docUploadType;
	}

	public String getLoanType() {
		return this.loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getWebtopNo() {
		return this.webtopNo;
	}

	public void setWebtopNo(String webtopNo) {
		this.webtopNo = webtopNo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public Integer getDocRetryCount() {
		return docRetryCount;
	}

	public void setDocRetryCount(Integer docRetryCount) {
		this.docRetryCount = docRetryCount;
	}

	public String getDocUploadedToJocataStatus() {
		return docUploadedToJocataStatus;
	}

	public void setDocUploadedToJocataStatus(String docUploadedToJocataStatus) {
		this.docUploadedToJocataStatus = docUploadedToJocataStatus;
	}

	public String getPlleadId() {
		return plleadId;
	}

	public void setPlleadId(String plleadId) {
		this.plleadId = plleadId;
	}
	
}