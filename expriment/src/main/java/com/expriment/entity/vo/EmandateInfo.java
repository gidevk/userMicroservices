package com.expriment.entity.vo;

import java.io.Serializable;

public class EmandateInfo implements Serializable {

    private static final long serialVersionUID = 1467766095415523709L;
    private String SysX_uniqueid;
    private String customer_name;
    private String product;
    private String company_code;
    private String micr;
    private String ifsc;
    private String account_type;
    private String customer_account_number;
    private String mandate_start_date;
    private String mandate_end_date;
    private String repayment_mode;
    private String emi;
    private String loan_amount;
    private String legal_entity;
    private String available_limit;
    private String systemx_mrn;
    private String mandate_status;
    private String contract_number;
    private boolean loan_status;
    private String account_holder_name;
    private String maximum_cap_amt;
    private String Pan_number;
    private String UMRN;
    private String Tenure;
    private String customer_id;
    private String customer_type;
    private String Maturity_date;
    private String loan_start_dt;
    private String Mandate_bank_name;
    private String Mandate_registration_date;
    private String remark;

    public String getSysX_uniqueid() {
        return SysX_uniqueid;
    }

    public void setSysX_uniqueid(String sysX_uniqueid) {
        SysX_uniqueid = sysX_uniqueid;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getMicr() {
        return micr;
    }

    public void setMicr(String micr) {
        this.micr = micr;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getCustomer_account_number() {
        return customer_account_number;
    }

    public void setCustomer_account_number(String customer_account_number) {
        this.customer_account_number = customer_account_number;
    }

    public String getMandate_start_date() {
        return mandate_start_date;
    }

    public void setMandate_start_date(String mandate_start_date) {
        this.mandate_start_date = mandate_start_date;
    }

    public String getMandate_end_date() {
        return mandate_end_date;
    }

    public void setMandate_end_date(String mandate_end_date) {
        this.mandate_end_date = mandate_end_date;
    }

    public String getRepayment_mode() {
        return repayment_mode;
    }

    public void setRepayment_mode(String repayment_mode) {
        this.repayment_mode = repayment_mode;
    }

    public String getEmi() {
        return emi;
    }

    public void setEmi(String emi) {
        this.emi = emi;
    }

    public String getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getLegal_entity() {
        return legal_entity;
    }

    public void setLegal_entity(String legal_entity) {
        this.legal_entity = legal_entity;
    }

    public String getAvailable_limit() {
        return available_limit;
    }

    public void setAvailable_limit(String available_limit) {
        this.available_limit = available_limit;
    }

    public String getSystemx_mrn() {
        return systemx_mrn;
    }

    public void setSystemx_mrn(String systemx_mrn) {
        this.systemx_mrn = systemx_mrn;
    }

    public String getMandate_status() {
        return mandate_status;
    }

    public void setMandate_status(String mandate_status) {
        this.mandate_status = mandate_status;
    }

    public String getContract_number() {
        return contract_number;
    }

    public void setContract_number(String contract_number) {
        this.contract_number = contract_number;
    }

    public boolean isLoan_status() {
        return loan_status;
    }

    public void setLoan_status(boolean loan_status) {
        this.loan_status = loan_status;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public String getMaximum_cap_amt() {
        return maximum_cap_amt;
    }

    public void setMaximum_cap_amt(String maximum_cap_amt) {
        this.maximum_cap_amt = maximum_cap_amt;
    }

    public String getPan_number() {
        return Pan_number;
    }

    public void setPan_number(String pan_number) {
        Pan_number = pan_number;
    }

    public String getUMRN() {
        return UMRN;
    }

    public void setUMRN(String UMRN) {
        this.UMRN = UMRN;
    }

    public String getTenure() {
        return Tenure;
    }

    public void setTenure(String tenure) {
        Tenure = tenure;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getMaturity_date() {
        return Maturity_date;
    }

    public void setMaturity_date(String maturity_date) {
        Maturity_date = maturity_date;
    }

    public String getLoan_start_dt() {
        return loan_start_dt;
    }

    public void setLoan_start_dt(String loan_start_dt) {
        this.loan_start_dt = loan_start_dt;
    }

    public String getMandate_bank_name() {
        return Mandate_bank_name;
    }

    public void setMandate_bank_name(String mandate_bank_name) {
        Mandate_bank_name = mandate_bank_name;
    }

    public String getMandate_registration_date() {
        return Mandate_registration_date;
    }

    public void setMandate_registration_date(String mandate_registration_date) {
        Mandate_registration_date = mandate_registration_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
