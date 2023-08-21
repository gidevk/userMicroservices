package com.expriment.entity.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.expriment.utils.audit.entity.vo.RootResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankRequestDetailsResponse extends RootResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String bankName;
	private String bankCode;
	private String ifscCode;
	private String accountNumber;
	private String confirmAccountNumber;
	private String accountType;
	private String cardType;
	private Boolean nameMatch;

	// Validate Bank Response fields
	private String customerNameInBank;
	private String finalDecision;
	private String paymentErrorDescription;
	private String nameMatchPercentage;

	public String getCustomerNameInBank() {
		return customerNameInBank;
	}

	public void setCustomerNameInBank(String customerNameInBank) {
		this.customerNameInBank = customerNameInBank;
	}

	public String getFinalDecision() {
		return finalDecision;
	}

	public void setFinalDecision(String finalDecision) {
		this.finalDecision = finalDecision;
	}

	public String getPaymentErrorDescription() {
		return paymentErrorDescription;
	}

	public void setPaymentErrorDescription(String paymentErrorDescription) {
		this.paymentErrorDescription = paymentErrorDescription;
	}

	public String getNameMatchPercentage() {
		return nameMatchPercentage;
	}

	public void setNameMatchPercentage(String nameMatchPercentage) {
		this.nameMatchPercentage = nameMatchPercentage;
	}

	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getConfirmAccountNumber() {
		return confirmAccountNumber;
	}
	public void setConfirmAccountNumber(String confirmAccountNumber) {
		this.confirmAccountNumber = confirmAccountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public Boolean getNameMatch() {
		return nameMatch;
	}
	public void setNameMatch(Boolean nameMatch) {
		this.nameMatch = nameMatch;
	}
}
