package com.expriment.entity.vo;

import java.io.Serializable;

public class EnquiryAPIResp implements Serializable {

    private static final long serialVersionUID = 97845126541288L;

    private String status;
    private String existing_customer;
    private String unique_identifier;
    private String message;
    private RequestObject request_object;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExisting_customer() {
        return existing_customer;
    }

    public void setExisting_customer(String existing_customer) {
        this.existing_customer = existing_customer;
    }

    public String getUnique_identifier() {
        return unique_identifier;
    }

    public void setUnique_identifier(String unique_identifier) {
        this.unique_identifier = unique_identifier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestObject getRequest_object() {
        return request_object;
    }

    public void setRequest_object(RequestObject request_object) {
        this.request_object = request_object;
    }
}
