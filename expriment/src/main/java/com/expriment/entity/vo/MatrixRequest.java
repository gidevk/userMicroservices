package com.expriment.entity.vo;

/*
 * @author Indradev.kuamr
 */
public class MatrixRequest {

    Integer batch_id;
    String DOB;
    String Pan_number;
    String passport_no;
    String voter_id;
    String driving_licence_no;
    String data_flag;
    String unique_identifier;

    public Integer getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(Integer batch_id) {
        this.batch_id = batch_id;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPan_number() {
        return Pan_number;
    }

    public void setPan_number(String pan_number) {
        Pan_number = pan_number;
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

    public String getData_flag() {
        return data_flag;
    }

    public void setData_flag(String data_flag) {
        this.data_flag = data_flag;
    }

    public String getUnique_identifier() {
        return unique_identifier;
    }

    public void setUnique_identifier(String unique_identifier) {
        this.unique_identifier = unique_identifier;
    }
}
