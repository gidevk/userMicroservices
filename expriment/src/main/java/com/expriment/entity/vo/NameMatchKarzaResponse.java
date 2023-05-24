package com.expriment.entity.vo;

import java.io.Serializable;

public class NameMatchKarzaResponse implements Serializable {

    private String retStatus;
    private karzaResponse response;
    private String sysErrorMessage;
    private String errorMessage;
    private String sysErrorCode;


  /*    "retStatus": "FAILURE",
                "response": {
                             "score":0.98,
                           "customerHash":"string",
                           "plLeadId":"string",
                           "plWebtopId":"string",
                           "errorCode": "string",
                           "errorReason": "string",
                           "errorMessage": "string"
    },
                "sysErrorMessage": "Functional error occurred at SFDC end",
                "errorMessage": "Required Fields missing: plLeadId,plWebtopId,plOpportunityId,.",
                "sysErrorCode": "ERRSFDC200"*/

    public String getRetStatus() {
        return retStatus;
    }

    public void setRetStatus(String retStatus) {
        this.retStatus = retStatus;
    }

    public karzaResponse getResponse() {
        return response;
    }

    public void setResponse(karzaResponse response) {
        this.response = response;
    }

    public String getSysErrorMessage() {
        return sysErrorMessage;
    }

    public void setSysErrorMessage(String sysErrorMessage) {
        this.sysErrorMessage = sysErrorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSysErrorCode() {
        return sysErrorCode;
    }

    public void setSysErrorCode(String sysErrorCode) {
        this.sysErrorCode = sysErrorCode;
    }
}
