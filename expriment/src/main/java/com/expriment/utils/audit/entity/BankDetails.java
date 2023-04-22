package com.expriment.utils.audit.entity;


import com.expriment.utils.ProjectConstants;
import com.expriment.utils.audit.AuditDetailsInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "exp_bank_details", catalog = ProjectConstants.DB.Exp_User)
public class BankDetails implements Serializable /*AuditDetailsInfo*/ {
    private static final long serialVersionUID = 5703625717571546600L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name="bank_id",unique = true, nullable = false)
    private Integer bankId;

//    @ManyToOne
    @Column(name="cp_id")
    private Integer cpId;

    @Column(name="bank_name")
    private String bankName;

    @Column(name="account_holder_name")
    private String accountHolderName;

    @Column(name="account_number")
    private Long accountNumber;

    @Column(name="Ifsc_code")
    private String ifscCode;

    @Column(name="mobile_no")
    private String mobileNo;

    @Column(name="email_id")
    private String emailId;

    @Column(name="status")
    private String status;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="updated_date")
    private Date updatedDate;

   /* @JsonIgnore
    @Transient
    @Override
    public String getLogDeatil() {
        StringBuilder builder = new StringBuilder();
        builder.append("BankDetails { bankId=");
        builder.append(bankId);
        builder.append(", cpId=");
        builder.append(cpId);
        builder.append(", bank_name=");
        builder.append(bankName);
        builder.append(", account_number=");
        builder.append(accountNumber);
        builder.append(", Ifsc_code=");
        builder.append(ifscCode);
        builder.append(", account_holder_name=");
        builder.append(accountHolderName);
        builder.append(", mobile_no=");
        builder.append(mobileNo);
        builder.append(", email_id=");
        builder.append(emailId);
        builder.append(", status=");
        builder.append(status);
        builder.append(", created_date=");
        builder.append(createdDate);
        builder.append(", updated_date=");
        builder.append(updatedDate);




        builder.append(" } ");
        return builder.toString();
    }*/
    /*
    * {
    "cpId": 5,
    "bankName": "State Bank Of India",
    "accountHolderName": "Indradev kumar",
    "accountNumber": 123456789,
    "ifscCode": "SBIN0009242",
    "mobileNO": "97543215"
}*/
    @Override
    public String toString() {
        return "BankDetails{" +
                "bankId=" + bankId +
                ", cpId=" + cpId +
                ", bankName='" + bankName + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", accountNumber=" + accountNumber +
                ", ifscCode='" + ifscCode + '\'' +
                ", response='" + mobileNo + '\'' +
                ", emailId='" + emailId + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

   /* @JsonIgnore
    @Transient
    @Override
    public String getEntityId() {
        return String.valueOf(bankId);
    }

    @JsonIgnore
    @Transient
    @Override
    public String getEntityName() {
        return "exp_bank_details";
    }
*/}
