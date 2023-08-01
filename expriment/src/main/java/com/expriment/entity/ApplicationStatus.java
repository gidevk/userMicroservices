package com.expriment.entity;

import com.expriment.utils.ProjectConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_application_status", catalog = ProjectConstants.DB.Exp_User)
public class ApplicationStatus implements Serializable {
	private static final long serialVersionUID = 455745121946519L;
	
	@Id
	@Column(name = "lead_id")
	private String leadId;
	
	@Column(name = "resultix_pixel_placement")
	private String resultixPixelPlacement;
	
	@Column(name = "approval_status")
	private String approvalStatus;
	
	@Column(name = "kyc_status")
	private String kycStatus;
	
	@Column(name = "nach_status")
	private String nachStatus; //NACHStatus
	
	@Column(name = "agreement_status")
	private String agreementStatus;
	
	@Column(name = "activation_status")
	private String activationStatus;

	@Column(name = "drop_off_page")
	private String dropOffPage;
	
	@Column(name = "next_page")
	private String nextPage;
	
	@Column(name = "doorstep_pickup")
	private String doorStepPickUp;
	
	@Column(name = "match_fail")
	private String matchFail;
	
	@Column(name="created_date")
	private Date createDate;

	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name = "vault_url")
	private String vaultUrl;

	@Column(name="stageupdated_date_time")
	private Long stageUpdatedDateTime;
	
	@Column(name = "drop_off_status")
	private Boolean dropOffStatus;
	
	@Column(name="offer_identifier")
	private String offerIdentifier;
	
	@Column(name="mismatch")
	private String mismatch;
	
	@Column(name="drop_off_time")
	private Date dropOffTime;
	
	@Column(name="drop_off_reason")
	private String dropOffReason;
	
	@Column(name="appl_stage")
	private String applStage;
	
	@Column(name="appl_stage_status")
	private String applStageStatus;
	
	@Column(name="appl_source")
	private String applSource;

	@Column(name="kyc_start_date_time")
	private Date kycStartDateTime;

	@Column(name="kyc_end_date_time")
	private Date kycEndDateTime;

	@Column(name="nach_start_date_time")
	private Date nachStartDateTime;

	@Column(name="nach_end_date_time")
	private Date nachEndDateTime;


	@Column(name="appl_start_date_time")
	private Date applStartDateTime;

	@Column(name="appl_end_date_time")
	private Date applEndDateTime;

	@Column(name="aggrement_date_time")
	private Date aggrementDateTime;

	@Column(name ="ckyc_name_match_score")
	private double ckycNameMatchScore;

	@Column(name ="okyc_name_match_score")
	private double okycNameMatchScore;

	public double getCkycNameMatchScore() {
		return ckycNameMatchScore;
	}

	public void setCkycNameMatchScore(double ckycNameMatchScore) {
		this.ckycNameMatchScore = ckycNameMatchScore;
	}

	public double getOkycNameMatchScore() {
		return okycNameMatchScore;
	}

	public void setOkycNameMatchScore(double okycNameMatchScore) {
		this.okycNameMatchScore = okycNameMatchScore;
	}

	public Date getKycStartDateTime() {
		return kycStartDateTime;
	}

	public void setKycStartDateTime(Date kycStartDateTime) {
		this.kycStartDateTime = kycStartDateTime;
	}

	public Date getKycEndDateTime() {
		return kycEndDateTime;
	}

	public void setKycEndDateTime(Date kycEndDateTime) {
		this.kycEndDateTime = kycEndDateTime;
	}

	public Date getNachStartDateTime() {
		return nachStartDateTime;
	}

	public void setNachStartDateTime(Date nachStartDateTime) {
		this.nachStartDateTime = nachStartDateTime;
	}

	public Date getNachEndDateTime() {
		return nachEndDateTime;
	}

	public void setNachEndDateTime(Date nachEndDateTime) {
		this.nachEndDateTime = nachEndDateTime;
	}

	public Date getApplStartDateTime() {
		return applStartDateTime;
	}

	public void setApplStartDateTime(Date applStartDateTime) {
		this.applStartDateTime = applStartDateTime;
	}

	public Date getApplEndDateTime() {
		return applEndDateTime;
	}

	public void setApplEndDateTime(Date applEndDateTime) {
		this.applEndDateTime = applEndDateTime;
	}

	public Date getAggrementDateTime() {
		return aggrementDateTime;
	}

	public void setAggrementDateTime(Date aggrementDateTime) {
		this.aggrementDateTime = aggrementDateTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getResultixPixelPlacement() {
		return resultixPixelPlacement;
	}

	public void setResultixPixelPlacement(String resultixPixelPlacement) {
		this.resultixPixelPlacement = resultixPixelPlacement;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(String kycStatus) {
		this.kycStatus = kycStatus;
	}

	public String getNachStatus() {
		return nachStatus;
	}

	public void setNachStatus(String nachStatus) {
		this.nachStatus = nachStatus;
	}

	public String getAgreementStatus() {
		return agreementStatus;
	}

	public void setAgreementStatus(String agreementStatus) {
		this.agreementStatus = agreementStatus;
	}

	public String getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(String activationStatus) {
		this.activationStatus = activationStatus;
	}

	public String getDropOffPage() {
		return dropOffPage;
	}

	public void setDropOffPage(String dropOffPage) {
		this.dropOffPage = dropOffPage;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getDoorStepPickUp() {
		return doorStepPickUp;
	}

	public void setDoorStepPickUp(String doorStepPickUp) {
		this.doorStepPickUp = doorStepPickUp;
	}

	public String getMatchFail() {
		return matchFail;
	}

	public void setMatchFail(String matchFail) {
		this.matchFail = matchFail;
	}

	public String getVaultUrl() {
		return vaultUrl;
	}

	public void setVaultUrl(String vaultUrl) {
		this.vaultUrl = vaultUrl;
	}

	public Long getStageUpdatedDateTime() {
		return stageUpdatedDateTime;
	}

	public void setStageUpdatedDateTime(Long stageUpdatedDateTime) {
		this.stageUpdatedDateTime = stageUpdatedDateTime;
	}

	public Boolean getDropOffStatus() {
		return dropOffStatus;
	}

	public void setDropOffStatus(Boolean dropOffStatus) {
		this.dropOffStatus = dropOffStatus;
	}

	public String getOfferIdentifier() {
		return offerIdentifier;
	}

	public void setOfferIdentifier(String offerIdentifier) {
		this.offerIdentifier = offerIdentifier;
	}

	public String getMismatch() {
		return mismatch;
	}

	public void setMismatch(String mismatch) {
		this.mismatch = mismatch;
	}

	public String getApplSource() {
		return applSource;
	}

	public void setApplSource(String applSource) {
		this.applSource = applSource;
	}

	public Date getDropOffTime() {
		return dropOffTime;
	}

	public void setDropOffTime(Date dropOffTime) {
		this.dropOffTime = dropOffTime;
	}

	public String getDropOffReason() {
		return dropOffReason;
	}

	public void setDropOffReason(String dropOffReason) {
		this.dropOffReason = dropOffReason;
	}

	public String getApplStage() {
		return applStage;
	}

	public void setApplStage(String applStage) {
		this.applStage = applStage;
	}

	public String getApplStageStatus() {
		return applStageStatus;
	}

	public void setApplStageStatus(String applStageStatus) {
		this.applStageStatus = applStageStatus;
	}

}

