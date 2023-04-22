package com.expriment.entity;

import com.expriment.utils.ProjectConstants;
import sun.util.calendar.BaseCalendar;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.zip.DataFormatException;


@Entity
@Table(name = "user_details_tbl", catalog = ProjectConstants.DB.Exp_User)
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 4091451048691357611L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="user_id")
    private Integer cpId;

    @Column(name="user_fname")
    private String userFirstName;

    @Column(name="user_lname")
    private String userLastName;

    @Column(name="user_father_name")
    private String userFatherName;

    @Column(name="user_mobile")
    private String userMobile;

    @Column(name="dob")
    private String dob;

    @Column(name="sex")
    private String gender;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="update_date")
    private Date updateDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }
    public int getCpId() {
        return cpId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserFatherName() {
        return userFatherName;
    }

    public void setUserFatherName(String userFatherName) {
        this.userFatherName = userFatherName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
