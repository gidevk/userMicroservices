package com.expriment.entity;

import com.expriment.utils.ProjectConstants;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tbl_digi_details", catalog = ProjectConstants.DB.Exp_User)
public class DigilockerDetails {

    @Id
    @Column(name="lead_id")
    private Long leadId;

    @Column(name="digi_match")
    private Boolean digiMatch;

    @Column(name="digi_status")
    private String digiStatus;

    @Column(name="digi_customer_name")
    private String digiCustomerName;

    @Column(name="dob")
    private String dob;

    @Column(name="gender")
    private String gender;

    @Column(name="country")
    private String country;

    @Column(name="pincode")
    private String pinCode;

    @Column(name="dist")
    private String dist;

    @Column(name="house")
    private String house;

    @Column(name="landmark")
    private String landMark;

    @Column(name="locality") //loc
    private String locality;

    @Column(name="state")
    private String state;

    @Column(name="street")
    private String street;

    @Column(name="village_town_city") //vtc
    private String villageTownCity;

    @Column(name="digi_photograph")
    private String digiPhotograph;

    @Column(name="response")
    private String response;

    @Column(name="updated_date")
    private Date updatedDate;

    @Column(name="created_date")
    private Date createdDate;

 public Long getLeadId() {
  return leadId;
 }

 public void setLeadId(Long leadId) {
  this.leadId = leadId;
 }

 public Boolean getDigiMatch() {
  return digiMatch;
 }

 public void setDigiMatch(Boolean digiMatch) {
  this.digiMatch = digiMatch;
 }

 public String getDigiStatus() {
  return digiStatus;
 }

 public void setDigiStatus(String digiStatus) {
  this.digiStatus = digiStatus;
 }

 public String getDigiCustomerName() {
  return digiCustomerName;
 }

 public void setDigiCustomerName(String digiCustomerName) {
  this.digiCustomerName = digiCustomerName;
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

 public String getCountry() {
  return country;
 }

 public void setCountry(String country) {
  this.country = country;
 }

 public String getPinCode() {
  return pinCode;
 }

 public void setPinCode(String pinCode) {
  this.pinCode = pinCode;
 }

 public String getDist() {
  return dist;
 }

 public void setDist(String dist) {
  this.dist = dist;
 }

 public String getHouse() {
  return house;
 }

 public void setHouse(String house) {
  this.house = house;
 }

 public String getLandMark() {
  return landMark;
 }

 public void setLandMark(String landMark) {
  this.landMark = landMark;
 }

 public String getLocality() {
  return locality;
 }

 public void setLocality(String locality) {
  this.locality = locality;
 }

 public String getState() {
  return state;
 }

 public void setState(String state) {
  this.state = state;
 }

 public String getStreet() {
  return street;
 }

 public void setStreet(String street) {
  this.street = street;
 }

 public String getVillageTownCity() {
  return villageTownCity;
 }

 public void setVillageTownCity(String villageTownCity) {
  this.villageTownCity = villageTownCity;
 }

 public String getDigiPhotograph() {
  return digiPhotograph;
 }

 public void setDigiPhotograph(String digiPhotograph) {
  this.digiPhotograph = digiPhotograph;
 }

 public String getResponse() {
  return response;
 }

 public void setResponse(String response) {
  this.response = response;
 }

 public Date getUpdatedDate() {
  return updatedDate;
 }

 public void setUpdatedDate(Date updatedDate) {
  this.updatedDate = updatedDate;
 }

 public Date getCreatedDate() {
  return createdDate;
 }

 public void setCreatedDate(Date createdDate) {
  this.createdDate = createdDate;
 }
}