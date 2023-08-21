package com.expriment.entity.vo;

public class RequestObject {

    private String pan_id;
    private String passport_no;
    private String voter_id;
    private String driving_licence_no;
    private String dob;


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
}
