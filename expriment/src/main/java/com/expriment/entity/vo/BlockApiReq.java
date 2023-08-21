package com.expriment.entity.vo;

import java.io.Serializable;

/*
 * @author Indradev.kuamr
 */
public class BlockApiReq implements Serializable {

    private static final long serialVersionUID = 1960913898926310725L;
    private String unique_identifier;
    private String tenure;
    private String source_system;
    private String repayment_mode;
    private String product_type;
    private String micr;
    private String mandate_start_date;
    private String mandate_end_date;
    private String loan_amount;
    private String legal_entity;
    private String ifsc;
    private String emi_amount;
    private String customer_type;
    private String customer_name;
    private String company_code;
    private String bank_name;
    private String account_type;
    private String account_number;
    private String account_holder_name;
    private String contract_number;
    private String los_id;
    private String web_flag;
    private String other_detail;

    // Constructors, getters, setters, and other methods can be added here.


    public String getUnique_identifier() {
        return unique_identifier;
    }

    public void setUnique_identifier(String unique_identifier) {
        this.unique_identifier = unique_identifier;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getSource_system() {
        return source_system;
    }

    public void setSource_system(String source_system) {
        this.source_system = source_system;
    }

    public String getRepayment_mode() {
        return repayment_mode;
    }

    public void setRepayment_mode(String repayment_mode) {
        this.repayment_mode = repayment_mode;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getMicr() {
        return micr;
    }

    public void setMicr(String micr) {
        this.micr = micr;
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

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getEmi_amount() {
        return emi_amount;
    }

    public void setEmi_amount(String emi_amount) {
        this.emi_amount = emi_amount;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCompany_code() {
        return company_code;
    }

    public void setCompany_code(String company_code) {
        this.company_code = company_code;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public String getContract_number() {
        return contract_number;
    }

    public void setContract_number(String contract_number) {
        this.contract_number = contract_number;
    }

    public String getLos_id() {
        return los_id;
    }

    public void setLos_id(String los_id) {
        this.los_id = los_id;
    }

    public String getWeb_flag() {
        return web_flag;
    }

    public void setWeb_flag(String web_flag) {
        this.web_flag = web_flag;
    }

    public String getOther_detail() {
        return other_detail;
    }

    public void setOther_detail(String other_detail) {
        this.other_detail = other_detail;
    }
}
