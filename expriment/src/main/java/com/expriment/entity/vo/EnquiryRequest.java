package com.expriment.entity.vo;

import java.io.Serializable;

/*
 * @author Indradev.kuamr
 */
public class EnquiryRequest implements Serializable {

    private static final long serialVersionUID = -4169524597595026140L;
    private String source_system;
    private String pan_id;
    private String passport_no;
    private String voter_id;
    private String driving_licence_no;
    private String dob;
    private String emi_amount;
    private String tenure;
    private String filler_1;
    private String filler_2;
    private String filler_3;
    private String filler_4;
    private String filler_5;

    public String getSource_system() {
        return source_system;
    }

    public void setSource_system(String source_system) {
        this.source_system = source_system;
    }

    public String getPan_id() {
        return pan_id;
    }

    public void setPan_id(String pan_id) {
        this.pan_id = pan_id;
    }

    public String getPassport_no() {
        return passport_no;
    }

    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }

    public String getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(String voter_id) {
        this.voter_id = voter_id;
    }

    public String getDriving_licence_no() {
        return driving_licence_no;
    }

    public void setDriving_licence_no(String driving_licence_no) {
        this.driving_licence_no = driving_licence_no;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmi_amount() {
        return emi_amount;
    }

    public void setEmi_amount(String emi_amount) {
        this.emi_amount = emi_amount;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getFiller_1() {
        return filler_1;
    }

    public void setFiller_1(String filler_1) {
        this.filler_1 = filler_1;
    }

    public String getFiller_2() {
        return filler_2;
    }

    public void setFiller_2(String filler_2) {
        this.filler_2 = filler_2;
    }

    public String getFiller_3() {
        return filler_3;
    }

    public void setFiller_3(String filler_3) {
        this.filler_3 = filler_3;
    }

    public String getFiller_4() {
        return filler_4;
    }

    public void setFiller_4(String filler_4) {
        this.filler_4 = filler_4;
    }

    public String getFiller_5() {
        return filler_5;
    }

    public void setFiller_5(String filler_5) {
        this.filler_5 = filler_5;
    }
}

