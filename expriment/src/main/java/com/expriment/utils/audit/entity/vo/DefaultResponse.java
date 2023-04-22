package com.expriment.utils.audit.entity.vo;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class DefaultResponse implements Serializable {

    private String message;

    private String status;

    private int statusCode;

    private boolean containsErrors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isContainsErrors() {
        return containsErrors;
    }

    public void setContainsErrors(boolean containsErrors) {
        this.containsErrors = containsErrors;
    }

//    public HttpStatus extractHttpStatus() {
//        if (this.statusCode < 0 || this.statusCode == 200)
//            return HttpStatus.OK;
//
//        return HttpStatus.resolve(this.statusCode);
//    }
}
