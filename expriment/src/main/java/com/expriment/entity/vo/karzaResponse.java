package com.expriment.entity.vo;

import java.io.Serializable;

public class karzaResponse implements Serializable {
    private double score;
    private String customerHash;
    private String plLeadId;
    private String plWebtopId;
    private String errorCode;
    private String errorReason;
    private String errorMessage;
    /* "response": {
                            "score":0.98,
                            "customerHash":"string",
                           "plLeadId":"string",
                           "plWebtopId":"string",
                           "errorCode": "string",
                           "errorReason": "string",
                           "errorMessage": "string"
    },*/

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getCustomerHash() {
        return customerHash;
    }

    public void setCustomerHash(String customerHash) {
        this.customerHash = customerHash;
    }

    public String getPlLeadId() {
        return plLeadId;
    }

    public void setPlLeadId(String plLeadId) {
        this.plLeadId = plLeadId;
    }

    public String getPlWebtopId() {
        return plWebtopId;
    }

    public void setPlWebtopId(String plWebtopId) {
        this.plWebtopId = plWebtopId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
