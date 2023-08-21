package com.expriment.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class DigiLockerKYCDtlsResponse implements java.io.Serializable {
    private static final long serialVersionUID = 998645321888L;

    private String digiLockerAddress;
    private String digiLockerPincode;
    private String digiLockerCity;
    private String digiLockerState;
    private String imageinBase64;
    private String digiLockerCustomerName;
//    private CallStatus status;
    private String retStatus;

//    public CallStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(CallStatus status) {
//        this.status = status;
//    }

    public String getRetStatus() {
        return retStatus;
    }

    public void setRetStatus(String retStatus) {
        this.retStatus = retStatus;
    }

    public String getDigiLockerAddress() {
        return digiLockerAddress;
    }

    public void setDigiLockerAddress(String digiLockerAddress) {
        this.digiLockerAddress = digiLockerAddress;
    }

    public String getDigiLockerPincode() {
        return digiLockerPincode;
    }

    public void setDigiLockerPincode(String digiLockerPincode) {
        this.digiLockerPincode = digiLockerPincode;
    }

    public String getDigiLockerCity() {
        return digiLockerCity;
    }

    public void setDigiLockerCity(String digiLockerCity) {
        this.digiLockerCity = digiLockerCity;
    }

    public String getDigiLockerState() {
        return digiLockerState;
    }

    public void setDigiLockerState(String digiLockerState) {
        this.digiLockerState = digiLockerState;
    }

    public String getImageinBase64() {
        return imageinBase64;
    }

    public void setImageinBase64(String imageinBase64) {
        this.imageinBase64 = imageinBase64;
    }

    public String getDigiLockerCustomerName() {
        return digiLockerCustomerName;
    }

    public void setDigiLockerCustomerName(String digiLockerCustomerName) {
        this.digiLockerCustomerName = digiLockerCustomerName;
    }
}

