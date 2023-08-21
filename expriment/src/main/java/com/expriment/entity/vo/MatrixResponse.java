package com.expriment.entity.vo;

import java.io.Serializable;

/*
 * @author Indradev.kuamr
 */
public class MatrixResponse implements Serializable {

    private static final long serialVersionUID = 7728792181690166680L;
    String status;
    String message;

    Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
