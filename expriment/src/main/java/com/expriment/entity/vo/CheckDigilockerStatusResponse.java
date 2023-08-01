package com.expriment.entity.vo;

public class CheckDigilockerStatusResponse {

    private Boolean digiLockerFlag;
    private String digiLockerUrl;
    private Boolean ckycFlag;
    private String ckycUrl;

    public Boolean getDigiLockerFlag() {
        return digiLockerFlag;
    }

    public void setDigiLockerFlag(Boolean digiLockerFlag) {
        this.digiLockerFlag = digiLockerFlag;
    }

    public String getDigiLockerUrl() {
        return digiLockerUrl;
    }

    public void setDigiLockerUrl(String digiLockerUrl) {
        this.digiLockerUrl = digiLockerUrl;
    }

    public Boolean getCkycFlag() {
        return ckycFlag;
    }

    public void setCkycFlag(Boolean ckycFlag) {
        this.ckycFlag = ckycFlag;
    }

    public String getCkycUrl() {
        return ckycUrl;
    }

    public void setCkycUrl(String ckycUrl) {
        this.ckycUrl = ckycUrl;
    }
}
