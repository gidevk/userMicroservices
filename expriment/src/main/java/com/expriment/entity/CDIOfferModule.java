package com.expriment.entity;

import com.expriment.utils.ProjectConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="tbl_cdi_offer_module", catalog= ProjectConstants.DB.Exp_User)
public class CDIOfferModule implements Serializable {

	private static final long serialVersionUID = 8910808572967300443L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="gcid")
	private String gcid;

	@Column(name="ucic")
	private String ucic;

	@Column(name="mobile_no")
	private Long mobileNo;

	@Column(name="first_name")
	private String firstName;

	@Column(name="middle_name")
	private String MiddleName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="father_name")
	private String fatherName;

	@Column(name="customer_type")
	private String customerType;

	@Column(name="data_source")
	private String dataSource;
	
	@Column(name="base_product_contract_id")
	private String baseProductContractID;

	@Column(name="base_contract_status")
	private String baseContractStatus;

	@Column(name="morse_risk_category")
	private String morseRiskCategory;

	@Column(name="kyc_required_flag")
	private String kycRequiredFlag;

	@Column(name="last_kyc_date")
	private Date lastKycDate;

	@Column(name="product_type")
	private String productType;

	@Column(name="company_code")
	private Long companyCode;

	@Column(name="legal_entity")
	private String legalEntity;
	
	
	@Column(name ="cus_entr_dob")
	private Date customerEnteredDob;
	
	@Column(name="ofr_upl_dob")
	private Date offerUploadedDob;

	@Column(name="gender")
	private String gender;

	@Column(name="pin_code")
	private Long pinCode;
	
	@Column(name="city")
	private String city;

	@Column(name="offer_can_be_used_for_products")
	private String offerCanBeUsedForWhichProducts;

	@Column(name="member_reference")
	private String memberReference;

	@Column(name="tenure")
	private Long tenure;
	
	@Column(name="online_nach_exist")
	private String onlineNachExist;
	
	@Column(name="base_contract_bank_account_no")
	private String baseContractBankAccountNo;

	@Column(name="base_contract_ifsc_code")
	private String baseContractIfscCode;

	@Column(name="base_contract_bank_name")
	private String baseContractBankName;

	@Column(name="account_holder_name")
	private String accountHolderName;

	@Column(name="unique_identifier")
	private String uniqueIdentifier;

	@Column(name="bank_code")
	private String bankCode;

	@Column(name="micr")
	private String micr;

	@Column(name="account_type")
	private String accountType;

	@Column(name="mandate_start_date")
	private Date mandateStartDate;
	
	@Column(name="mandate_end_date")
	private Date mandateEndDate;

	@Column(name="repayment_mode")
	private String repaymentMode;

	@Column(name="bankaccount_first_name")
	private String bankAccountFirstName;

	@Column(name="bankaccount_middle_name")
	private String bankAccountMiddleName;

	@Column(name="bankaccount_last_name")
	private String bankAccountLastName;

	@Column(name="fathers_name")
	private String fathersName;

	@Column(name="mothers_name")
	private String mothersName;

	@Column(name="marital_status")
	private String maritalStatus;

	@Column(name="salaried")
	private String salaried;

	@Column(name="non_salaried")
	private String nonSalaried;
	
	@Column(name="emi_card_number")
	private String emiCardNumber;

	@Column(name="emi_card_status")
	private String emiCardStatus;

	@Column(name="emi_card_issue_date")
	private Date emiCardIssueDate;

	@Column(name="emi_card_expiry_date")
	private Date emiCardExpiryDate;

	@Column(name="emi_card_authorisation_date")
	private Date emiCardAuthorisationDate;

	@Column(name="emi_card_block_date")
	private Date emiCardBlockDate;

	@Column(name="available_offer_amount")
	private String availableOfferAmount;
	
	@Column(name="max_offer_amount")
	private String maxOfferAmount;
	 
	@Column(name="utilized_amount")
	private Double utilizedAmount;
	
	@Column(name="net_financed_amount")
	private Double netFinancedAmount;
	
	@Column(name="tot_amount_paid_back")
	private Double totAmountPaidBack;
	
	@Column(name="max_offer_tenure")
	private String maxOfferTenure;
	
	@Column(name="ref_ccid")
	private String refCcid;

	@Column(name="ref_dob")
	private Date refDOB;
	
	@Column(name="ref_address")
	private String refAddress;

	@Column(name="ref_city")
	private String refCity;

	@Column(name="ref_state")
	private String refState;

	@Column(name="ref_landmark")
	private String refLandmark;

	@Column(name="ref_address_type")
	private String refAddressType;
	
	@Column(name="ref_accomodation_type")
	private String refAccomodationType;

	@Column(name="ref_pincode")
	private Long refPincode;

	@Column(name="ref_mobile")
	private String refMobile;

	@Column(name="ref_landline")
	private String refLandline;

	@Column(name="ref_emailid")
	private String refEmailid;

	@Column(name="ref_lob")
	private String refLob;

	@Column(name="ref_name")
	private String refName;

	@Column(name="ref_score")
	private String refScore;

	@Column(name="final_income_comb")
	private String finalIncomeComb;
	
	@Column(name="branch_city")
	private String branchCity;

	@Column(name="mobile_cibil")
	private String mobileCibil;

	@Column(name="email_cibil")
	private String emailCibil;

	@Column(name="employee_name")
	private String employeeName;

	@Column(name="office_email_id")
	private String officeEmailID;

	@Column(name="approval_type")
	private String approvalType;
	
	@Column(name="pan")
	private String pan;
	
	@Column(name="webtop_id")
	private String webTopId;
	
	@Column(name="opportunity_id")
	private String opportunityId;
	
	@Column(name="lead_id")
	private Long leadId;
	
	@Column(name="stage")
	private String stage;
	
	@Column(name="created_date")
	private Date createDate;
	
	@Column(name="updated_date")
	private Date updatedDate;	
	
	@Column(name="email_address")
	private String emailAddress;
	
	@Column(name="customer_name")
	private String customerName;

	/*@Column(name="customer_hash")
	private String customerHash; */
	
	@Column(name="customer_hash_new")
	private String customerHashNew;
	
	@Column(name="customer_pin_code")
	private String customerPincode;
	
	@Column(name="sanctionletter_status")
	private Boolean sanctionLetterStatus;
	
	@Column(name="offer_amount_updated")
	private Boolean offerAmountUpdated;
	
	@Column(name="max_emi_amount")
	private String maxEmiAmount;
	
	@Column(name="campaign_name")
	private String campaignName;
	
	@Column(name="campaign_type")
	private String campaignType;
	
	@Column(name="stp_nstp_flag")
	private String stpNstpFlag;
	
	@Column(name="pl_offer_amount")
	private String plOfferAmount;
	
	@Column(name="pl_offer_tenure")
	private String plOfferTenure;
	
	@Column(name="pl_max_emi_amount")
	private String plMaxEmiAmount;
	
	@Column(name="imputed_income")
	private String imputedIncome;
	
	@Column(name="tcp_hash")
	private String tcpHash;
	
	@Column(name="offer_identifier")
	private String offerIdentifier;
	
	@Column(name="existing_customer")
	private String existingCustomer;
	
	@Column(name="low_risk")
	private String lowRisk;
	
	@Column(name="pl_lead_id")
	private Long plLeadId;
	
	@Column(name="pl_webtop_id")
	private String plWebTopId;
	
	@Column(name="pl_opportunity_id")
	private String plOpportunityId;
	
	@Column(name="pl_kyc_required_flag")
	private String plKycRequiredFlag; 
	
	@Column(name="pl_online_nach_exist")
	private String plOnlineNachExist;
	
	@Column(name="pl_company_code")
	private Long plCompanyCode;
	
	@Column(name="pl_office_email_id")
	private String plOfficeEmailID;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="emp_type")
	private String employeeType;
	
	@Column(name="existing_customer_KYC")
	private String existingCustomerKYC;
	
	@Column(name="primary_campaign_source")
	private String primaryCampaignSource;

	@Column(name="offer_release_pl")
	private String offerReleasePl;
	
	@Column(name="offer_block_pl")
	private String offerBlockPl;
	
	@Column(name="longitude")
	private String longitude;
	
	@Column(name="latitude")
	private String latitude;
	
	@Column(name="okyc_client_id")
	private String okycClientId;
	
	@Column(name="customer_hash")
	private String customerHash;
	
	@Column(name="pl_created_date")
	private Date plCreateDate;
	
	@Column(name="pl_updated_date")
	private Date plUpdatedDate;
	
	@Column(name="pl_offer_expiry_date")
	private Date plOfferExpiryDate;
	
	@Column(name="pl_offer_upload_date")
	private Date plOfferUploadDate;
	
	@Column(name="cd_offer_expiry_date")
	private Date cdOfferExpiryDate;
	
	@Column(name="cd_offer_upload_date")
	private Date cdOfferUploadDate;
	
	@Column(name="flag_FI_Waiver")
	private String flagFIWaiver;

	@Column(name ="PAN_4Digits")
	private String pan4digits;
	
	public String getPan4digits() {
		return pan4digits;
	}

	public void setPan4digits(String pan4digits) {
		this.pan4digits = pan4digits;
	}

	public String getFlagFIWaiver() { return flagFIWaiver; }

	public void setFlagFIWaiver(String flagFIWaiver) { this.flagFIWaiver = flagFIWaiver; }

	public Date getCustomerEnteredDob() {
		return customerEnteredDob;
	}

	public void setCustomerEnteredDob(Date customerEnteredDob) {
		this.customerEnteredDob = customerEnteredDob;
	}

	public Date getOfferUploadedDob() {
		return offerUploadedDob;
	}

	public void setOfferUploadedDob(Date offerUploadedDob) {
		this.offerUploadedDob = offerUploadedDob;
	}

	public Date getCdOfferExpiryDate() {
		return cdOfferExpiryDate;
	}

	public void setCdOfferExpiryDate(Date cdOfferExpiryDate) {
		this.cdOfferExpiryDate = cdOfferExpiryDate;
	}

	public Date getCdOfferUploadDate() {
		return cdOfferUploadDate;
	}

	public void setCdOfferUploadDate(Date cdOfferUploadDate) {
		this.cdOfferUploadDate = cdOfferUploadDate;
	}

	public Date getPlOfferExpiryDate() {
		return plOfferExpiryDate;
	}

	public void setPlOfferExpiryDate(Date plOfferExpiryDate) {
		this.plOfferExpiryDate = plOfferExpiryDate;
	}

	public Date getPlOfferUploadDate() {
		return plOfferUploadDate;
	}

	public void setPlOfferUploadDate(Date plOfferUploadDate) {
		this.plOfferUploadDate = plOfferUploadDate;
	}

	public Date getPlCreateDate() {
		return plCreateDate;
	}

	public void setPlCreateDate(Date plCreateDate) {
		this.plCreateDate = plCreateDate;
	}

	public Date getPlUpdatedDate() {
		return plUpdatedDate;
	}

	public void setPlUpdatedDate(Date plUpdatedDate) {
		this.plUpdatedDate = plUpdatedDate;
	}

	public String getCustomerHash() {
		return customerHash;
	}

	public void setCustomerHash(String customerHash) {
		this.customerHash = customerHash;
	}

	public String getGcid() {
		return gcid;
	}

	public void setGcid(String gcid) {
		this.gcid = gcid;
	}

	public String getUcic() {
		return ucic;
	}

	public void setUcic(String ucic) {
		this.ucic = ucic;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getBaseProductContractID() {
		return baseProductContractID;
	}

	public void setBaseProductContractID(String baseProductContractID) {
		this.baseProductContractID = baseProductContractID;
	}

	public String getBaseContractStatus() {
		return baseContractStatus;
	}

	public void setBaseContractStatus(String baseContractStatus) {
		this.baseContractStatus = baseContractStatus;
	}

	public String getMorseRiskCategory() {
		return morseRiskCategory;
	}

	public void setMorseRiskCategory(String morseRiskCategory) {
		this.morseRiskCategory = morseRiskCategory;
	}

	public String getKycRequiredFlag() {
		return kycRequiredFlag;
	}

	public void setKycRequiredFlag(String kycRequiredFlag) {
		this.kycRequiredFlag = kycRequiredFlag;
	}

	public Date getLastKycDate() {
		return lastKycDate;
	}

	public void setLastKycDate(Date lastKycDate) {
		this.lastKycDate = lastKycDate;
	}

	public String getBaseContractBankAccountNo() {
		return baseContractBankAccountNo;
	}

	public void setBaseContractBankAccountNo(String baseContractBankAccountNo) {
		this.baseContractBankAccountNo = baseContractBankAccountNo;
	}

	public String getBaseContractIfscCode() {
		return baseContractIfscCode;
	}

	public void setBaseContractIfscCode(String baseContractIfscCode) {
		this.baseContractIfscCode = baseContractIfscCode;
	}

	public String getBaseContractBankName() {
		return baseContractBankName;
	}

	public void setBaseContractBankName(String baseContractBankName) {
		this.baseContractBankName = baseContractBankName;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Long getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(Long companyCode) {
		this.companyCode = companyCode;
	}

	public String getLegalEntity() {
		return legalEntity;
	}

	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getMicr() {
		return micr;
	}

	public void setMicr(String micr) {
		this.micr = micr;
	}

	public Date getMandateStartDate() {
		return mandateStartDate;
	}

	public void setMandateStartDate(Date mandateStartDate) {
		this.mandateStartDate = mandateStartDate;
	}

	public Date getMandateEndDate() {
		return mandateEndDate;
	}

	public void setMandateEndDate(Date mandateEndDate) {
		this.mandateEndDate = mandateEndDate;
	}

	public String getRepaymentMode() {
		return repaymentMode;
	}

	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}

	public String getBankAccountFirstName() {
		return bankAccountFirstName;
	}

	public void setBankAccountFirstName(String bankAccountFirstName) {
		this.bankAccountFirstName = bankAccountFirstName;
	}

	public String getBankAccountMiddleName() {
		return bankAccountMiddleName;
	}

	public void setBankAccountMiddleName(String bankAccountMiddleName) {
		this.bankAccountMiddleName = bankAccountMiddleName;
	}

	public String getBankAccountLastName() {
		return bankAccountLastName;
	}

	public void setBankAccountLastName(String bankAccountLastName) {
		this.bankAccountLastName = bankAccountLastName;
	}



	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSalaried() {
		return salaried;
	}

	public void setSalaried(String salaried) {
		this.salaried = salaried;
	}

	public String getNonSalaried() {
		return nonSalaried;
	}

	public void setNonSalaried(String nonSalaried) {
		this.nonSalaried = nonSalaried;
	}

	public String getOfferCanBeUsedForWhichProducts() {
		return offerCanBeUsedForWhichProducts;
	}

	public void setOfferCanBeUsedForWhichProducts(String offerCanBeUsedForWhichProducts) {
		this.offerCanBeUsedForWhichProducts = offerCanBeUsedForWhichProducts;
	}

	public String getEmiCardNumber() {
		return emiCardNumber;
	}

	public void setEmiCardNumber(String emiCardNumber) {
		this.emiCardNumber = emiCardNumber;
	}

	public String getEmiCardStatus() {
		return emiCardStatus;
	}

	public void setEmiCardStatus(String emiCardStatus) {
		this.emiCardStatus = emiCardStatus;
	}

	public Date getEmiCardIssueDate() {
		return emiCardIssueDate;
	}

	public void setEmiCardIssueDate(Date emiCardIssueDate) {
		this.emiCardIssueDate = emiCardIssueDate;
	}

	public Date getEmiCardExpiryDate() {
		return emiCardExpiryDate;
	}

	public void setEmiCardExpiryDate(Date emiCardExpiryDate) {
		this.emiCardExpiryDate = emiCardExpiryDate;
	}

	public Date getEmiCardAuthorisationDate() {
		return emiCardAuthorisationDate;
	}

	public void setEmiCardAuthorisationDate(Date emiCardAuthorisationDate) {
		this.emiCardAuthorisationDate = emiCardAuthorisationDate;
	}

	public Date getEmiCardBlockDate() {
		return emiCardBlockDate;
	}

	public void setEmiCardBlockDate(Date emiCardBlockDate) {
		this.emiCardBlockDate = emiCardBlockDate;
	}

	public String getMaxOfferAmount() {
		return maxOfferAmount;
	}

	public void setMaxOfferAmount(String maxOfferAmount) {
		this.maxOfferAmount = maxOfferAmount;
	}

	public String getAvailableOfferAmount() {
		return availableOfferAmount;
	}

	public void setAvailableOfferAmount(String availableOfferAmount) {
		this.availableOfferAmount = availableOfferAmount;
	}

	public String getMaxOfferTenure() {
		return maxOfferTenure;
	}

	public void setMaxOfferTenure(String maxOfferTenure) {
		this.maxOfferTenure = maxOfferTenure;
	}

	public String getMemberReference() {
		return memberReference;
	}

	public void setMemberReference(String memberReference) {
		this.memberReference = memberReference;
	}

	public String getFinalIncomeComb() {
		return finalIncomeComb;
	}

	public void setFinalIncomeComb(String finalIncomeComb) {
		this.finalIncomeComb = finalIncomeComb;
	}

	public Long getTenure() {
		return tenure;
	}

	public void setTenure(Long tenure) {
		this.tenure = tenure;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public String getMobileCibil() {
		return mobileCibil;
	}

	public void setMobileCibil(String mobileCibil) {
		this.mobileCibil = mobileCibil;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getOfficeEmailID() {
		return officeEmailID;
	}

	public void setOfficeEmailID(String officeEmailID) {
		this.officeEmailID = officeEmailID;
	}

	public String getOnlineNachExist() {
		return onlineNachExist;
	}

	public void setOnlineNachExist(String onlineNachExist) {
		this.onlineNachExist = onlineNachExist;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getEmailCibil() {
		return emailCibil;
	}

	public void setEmailCibil(String emailCibil) {
		this.emailCibil = emailCibil;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getRefCcid() {
		return refCcid;
	}

	public void setRefCcid(String refCcid) {
		this.refCcid = refCcid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRefDOB() {
		return refDOB;
	}

	public void setRefDOB(Date refDOB) {
		this.refDOB = refDOB;
	}

	public String getRefAddress() {
		return refAddress;
	}

	public void setRefAddress(String refAddress) {
		this.refAddress = refAddress;
	}

	public String getRefCity() {
		return refCity;
	}

	public void setRefCity(String refCity) {
		this.refCity = refCity;
	}

	public String getRefState() {
		return refState;
	}

	public void setRefState(String refState) {
		this.refState = refState;
	}

	public String getRefLandmark() {
		return refLandmark;
	}

	public void setRefLandmark(String refLandmark) {
		this.refLandmark = refLandmark;
	}

	public String getRefAddressType() {
		return refAddressType;
	}

	public void setRefAddressType(String refAddressType) {
		this.refAddressType = refAddressType;
	}

	public String getRefAccomodationType() {
		return refAccomodationType;
	}

	public void setRefAccomodationType(String refAccomodationType) {
		this.refAccomodationType = refAccomodationType;
	}

	public Long getRefPincode() {
		return refPincode;
	}

	public void setRefPincode(Long refPincode) {
		this.refPincode = refPincode;
	}

	public String getRefMobile() {
		return refMobile;
	}

	public void setRefMobile(String refMobile) {
		this.refMobile = refMobile;
	}

	public String getRefLandline() {
		return refLandline;
	}

	public void setRefLandline(String refLandline) {
		this.refLandline = refLandline;
	}

	public String getRefEmailid() {
		return refEmailid;
	}

	public void setRefEmailid(String refEmailid) {
		this.refEmailid = refEmailid;
	}

	public String getRefLob() {
		return refLob;
	}

	public void setRefLob(String refLob) {
		this.refLob = refLob;
	}

	public String getRefScore() {
		return refScore;
	}

	public void setRefScore(String refScore) {
		this.refScore = refScore;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public Long getLeadId() {
		return leadId;
	}

	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}

	public String getWebTopId() {
		return webTopId;
	}

	public void setWebTopId(String webTopId) {
		this.webTopId = webTopId;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
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

	public String getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(String opportunityId) {
		this.opportunityId = opportunityId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Double getUtilizedAmount() {
		return utilizedAmount;
	}

	public void setUtilizedAmount(Double utilizedAmount) {
		this.utilizedAmount = utilizedAmount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerPincode() {
		return customerPincode;
	}

	public void setCustomerPincode(String customerPincode) {
		this.customerPincode = customerPincode;
	}
	
	public String getMaxEmiAmount() {
		return maxEmiAmount;
	}

	public void setMaxEmiAmount(String maxEmiAmount) {
		this.maxEmiAmount = maxEmiAmount;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getStpNstpFlag() {
		return stpNstpFlag;
	}

	public void setStpNstpFlag(String stpNstpFlag) {
		this.stpNstpFlag = stpNstpFlag;
	}

	public String getPlOfferAmount() {
		return plOfferAmount;
	}

	public void setPlOfferAmount(String plOfferAmount) {
		this.plOfferAmount = plOfferAmount;
	}

	public String getPlOfferTenure() {
		return plOfferTenure;
	}

	public void setPlOfferTenure(String plOfferTenure) {
		this.plOfferTenure = plOfferTenure;
	}

	public String getPlMaxEmiAmount() {
		return plMaxEmiAmount;
	}

	public void setPlMaxEmiAmount(String plMaxEmiAmount) {
		this.plMaxEmiAmount = plMaxEmiAmount;
	}

	public String getImputedIncome() {
		return imputedIncome;
	}

	public void setImputedIncome(String imputedIncome) {
		this.imputedIncome = imputedIncome;
	}

	public Double getNetFinancedAmount() {
		return netFinancedAmount;
	}

	public void setNetFinancedAmount(Double netFinancedAmount) {
		this.netFinancedAmount = netFinancedAmount;
	}

	public Double getTotAmountPaidBack() {
		return totAmountPaidBack;
	}

	public void setTotAmountPaidBack(Double totAmountPaidBack) {
		this.totAmountPaidBack = totAmountPaidBack;
	}

	public String getTcpHash() {
		return tcpHash;
	}

	public void setTcpHash(String tcpHash) {
		this.tcpHash = tcpHash;
	}

	public String getOfferIdentifier() {
		return offerIdentifier;
	}

	public void setOfferIdentifier(String offerIdentifier) {
		this.offerIdentifier = offerIdentifier;
	}

	public String getExistingCustomer() {
		return existingCustomer;
	}

	public void setExistingCustomer(String existingCustomer) {
		this.existingCustomer = existingCustomer;
	}

	public String getLowRisk() {
		return lowRisk;
	}

	public void setLowRisk(String lowRisk) {
		this.lowRisk = lowRisk;
	}

	public Long getPlLeadId() {
		return plLeadId;
	}

	public void setPlLeadId(Long plLeadId) {
		this.plLeadId = plLeadId;
	}

	public String getPlWebTopId() {
		return plWebTopId;
	}

	public void setPlWebTopId(String plWebTopId) {
		this.plWebTopId = plWebTopId;
	}

	public String getPlOpportunityId() {
		return plOpportunityId;
	}

	public void setPlOpportunityId(String plOpportunityId) {
		this.plOpportunityId = plOpportunityId;
	}

	public Boolean getSanctionLetterStatus() {
		return sanctionLetterStatus;
	}

	public void setSanctionLetterStatus(Boolean sanctionLetterStatus) {
		this.sanctionLetterStatus = sanctionLetterStatus;
	}

	public Boolean getOfferAmountUpdated() {
		return offerAmountUpdated;
	}

	public void setOfferAmountUpdated(Boolean offerAmountUpdated) {
		this.offerAmountUpdated = offerAmountUpdated;
	}

	public Long getPlCompanyCode() {
		return plCompanyCode;
	}

	public void setPlCompanyCode(Long plCompanyCode) {
		this.plCompanyCode = plCompanyCode;
	}

	public String getPlOfficeEmailID() {
		return plOfficeEmailID;
	}

	public void setPlOfficeEmailID(String plOfficeEmailID) {
		this.plOfficeEmailID = plOfficeEmailID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPlKycRequiredFlag() {
		return plKycRequiredFlag;
	}

	public void setPlKycRequiredFlag(String plKycRequiredFlag) {
		this.plKycRequiredFlag = plKycRequiredFlag;
	}

	public String getPlOnlineNachExist() {
		return plOnlineNachExist;
	}

	public void setPlOnlineNachExist(String plOnlineNachExist) {
		this.plOnlineNachExist = plOnlineNachExist;
	}

	public String getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(String campaignType) {
		this.campaignType = campaignType;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getExistingCustomerKYC() {
		return existingCustomerKYC;
	}

	public void setExistingCustomerKYC(String existingCustomerKYC) {
		this.existingCustomerKYC = existingCustomerKYC;
	}

	public String getOfferReleasePl() {
		return offerReleasePl;
	}

	public void setOfferReleasePl(String offerReleasePl) {
		this.offerReleasePl = offerReleasePl;
	}

	public String getPrimaryCampaignSource() {
		return primaryCampaignSource;
	}

	public void setPrimaryCampaignSource(String primaryCampaignSource) {
		this.primaryCampaignSource = primaryCampaignSource;
	}
	
	public String getCustomerHashNew() {
		return customerHashNew;
	}

	public void setCustomerHashNew(String customerHashNew) {
		this.customerHashNew = customerHashNew;
	}

	public String getOfferBlockPl() {
		return offerBlockPl;
	}

	public void setOfferBlockPl(String offerBlockPl) {
		this.offerBlockPl = offerBlockPl;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getOkycClientId() {
		return okycClientId;
	}

	public void setOkycClientId(String okycClientId) {
		this.okycClientId = okycClientId;
	}

	@Override
	public String toString() {
		return "CDIOfferModule{" +
				"id=" + id +
				", gcid='" + gcid + '\'' +
				", ucic='" + ucic + '\'' +
				", mobileNo=" + mobileNo +
				", firstName='" + firstName + '\'' +
				", MiddleName='" + MiddleName + '\'' +
				", lastName='" + lastName + '\'' +
				", fatherName='" + fatherName + '\'' +
				", customerType='" + customerType + '\'' +
				", dataSource='" + dataSource + '\'' +
				", baseProductContractID='" + baseProductContractID + '\'' +
				", baseContractStatus='" + baseContractStatus + '\'' +
				", morseRiskCategory='" + morseRiskCategory + '\'' +
				", kycRequiredFlag='" + kycRequiredFlag + '\'' +
				", lastKycDate=" + lastKycDate +
				", productType='" + productType + '\'' +
				", companyCode=" + companyCode +
				", legalEntity='" + legalEntity + '\'' +
				", customerEnteredDob=" + customerEnteredDob +
				", offerUploadedDob=" + offerUploadedDob +
				", gender='" + gender + '\'' +
				", pinCode=" + pinCode +
				", city='" + city + '\'' +
				", offerCanBeUsedForWhichProducts='" + offerCanBeUsedForWhichProducts + '\'' +
				", memberReference='" + memberReference + '\'' +
				", tenure=" + tenure +
				", onlineNachExist='" + onlineNachExist + '\'' +
				", baseContractBankAccountNo='" + baseContractBankAccountNo + '\'' +
				", baseContractIfscCode='" + baseContractIfscCode + '\'' +
				", baseContractBankName='" + baseContractBankName + '\'' +
				", accountHolderName='" + accountHolderName + '\'' +
				", uniqueIdentifier='" + uniqueIdentifier + '\'' +
				", bankCode='" + bankCode + '\'' +
				", micr='" + micr + '\'' +
				", accountType='" + accountType + '\'' +
				", mandateStartDate=" + mandateStartDate +
				", mandateEndDate=" + mandateEndDate +
				", repaymentMode='" + repaymentMode + '\'' +
				", bankAccountFirstName='" + bankAccountFirstName + '\'' +
				", bankAccountMiddleName='" + bankAccountMiddleName + '\'' +
				", bankAccountLastName='" + bankAccountLastName + '\'' +
				", fathersName='" + fathersName + '\'' +
				", mothersName='" + mothersName + '\'' +
				", maritalStatus='" + maritalStatus + '\'' +
				", salaried='" + salaried + '\'' +
				", nonSalaried='" + nonSalaried + '\'' +
				", emiCardNumber='" + emiCardNumber + '\'' +
				", emiCardStatus='" + emiCardStatus + '\'' +
				", emiCardIssueDate=" + emiCardIssueDate +
				", emiCardExpiryDate=" + emiCardExpiryDate +
				", emiCardAuthorisationDate=" + emiCardAuthorisationDate +
				", emiCardBlockDate=" + emiCardBlockDate +
				", availableOfferAmount='" + availableOfferAmount + '\'' +
				", maxOfferAmount='" + maxOfferAmount + '\'' +
				", utilizedAmount=" + utilizedAmount +
				", netFinancedAmount=" + netFinancedAmount +
				", totAmountPaidBack=" + totAmountPaidBack +
				", maxOfferTenure='" + maxOfferTenure + '\'' +
				", refCcid='" + refCcid + '\'' +
				", refDOB=" + refDOB +
				", refAddress='" + refAddress + '\'' +
				", refCity='" + refCity + '\'' +
				", refState='" + refState + '\'' +
				", refLandmark='" + refLandmark + '\'' +
				", refAddressType='" + refAddressType + '\'' +
				", refAccomodationType='" + refAccomodationType + '\'' +
				", refPincode=" + refPincode +
				", refMobile='" + refMobile + '\'' +
				", refLandline='" + refLandline + '\'' +
				", refEmailid='" + refEmailid + '\'' +
				", refLob='" + refLob + '\'' +
				", refName='" + refName + '\'' +
				", refScore='" + refScore + '\'' +
				", finalIncomeComb='" + finalIncomeComb + '\'' +
				", branchCity='" + branchCity + '\'' +
				", mobileCibil='" + mobileCibil + '\'' +
				", emailCibil='" + emailCibil + '\'' +
				", employeeName='" + employeeName + '\'' +
				", officeEmailID='" + officeEmailID + '\'' +
				", approvalType='" + approvalType + '\'' +
				", pan='" + pan + '\'' +
				", webTopId='" + webTopId + '\'' +
				", opportunityId='" + opportunityId + '\'' +
				", leadId=" + leadId +
				", stage='" + stage + '\'' +
				", createDate=" + createDate +
				", updatedDate=" + updatedDate +
				", emailAddress='" + emailAddress + '\'' +
				", customerName='" + customerName + '\'' +
				", customerHashNew='" + customerHashNew + '\'' +
				", customerPincode='" + customerPincode + '\'' +
				", sanctionLetterStatus=" + sanctionLetterStatus +
				", offerAmountUpdated=" + offerAmountUpdated +
				", maxEmiAmount='" + maxEmiAmount + '\'' +
				", campaignName='" + campaignName + '\'' +
				", campaignType='" + campaignType + '\'' +
				", stpNstpFlag='" + stpNstpFlag + '\'' +
				", plOfferAmount='" + plOfferAmount + '\'' +
				", plOfferTenure='" + plOfferTenure + '\'' +
				", plMaxEmiAmount='" + plMaxEmiAmount + '\'' +
				", imputedIncome='" + imputedIncome + '\'' +
				", tcpHash='" + tcpHash + '\'' +
				", offerIdentifier='" + offerIdentifier + '\'' +
				", existingCustomer='" + existingCustomer + '\'' +
				", lowRisk='" + lowRisk + '\'' +
				", plLeadId=" + plLeadId +
				", plWebTopId='" + plWebTopId + '\'' +
				", plOpportunityId='" + plOpportunityId + '\'' +
				", plKycRequiredFlag='" + plKycRequiredFlag + '\'' +
				", plOnlineNachExist='" + plOnlineNachExist + '\'' +
				", plCompanyCode=" + plCompanyCode +
				", plOfficeEmailID='" + plOfficeEmailID + '\'' +
				", companyName='" + companyName + '\'' +
				", employeeType='" + employeeType + '\'' +
				", existingCustomerKYC='" + existingCustomerKYC + '\'' +
				", primaryCampaignSource='" + primaryCampaignSource + '\'' +
				", offerReleasePl='" + offerReleasePl + '\'' +
				", offerBlockPl='" + offerBlockPl + '\'' +
				", longitude='" + longitude + '\'' +
				", latitude='" + latitude + '\'' +
				", okycClientId='" + okycClientId + '\'' +
				", customerHash='" + customerHash + '\'' +
				", plCreateDate=" + plCreateDate +
				", plUpdatedDate=" + plUpdatedDate +
				", plOfferExpiryDate=" + plOfferExpiryDate +
				", plOfferUploadDate=" + plOfferUploadDate +
				", cdOfferExpiryDate=" + cdOfferExpiryDate +
				", cdOfferUploadDate=" + cdOfferUploadDate +
				'}';
	}
}

