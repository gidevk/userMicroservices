package com.expriment.entity.vo;

import java.io.Serializable;

public class NameMatchKarzaRequest implements Serializable {

    private String customerHash;
    private String name;
    private String plLeadId;
    private String plWebtopId;

    public String getCustomerHash() {
        return customerHash;
    }

    public void setCustomerHash(String customerHash) {
        this.customerHash = customerHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
