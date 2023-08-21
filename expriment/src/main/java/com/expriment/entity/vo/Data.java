package com.expriment.entity.vo;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

    private static final long serialVersionUID = 575398545006922854L;
    private int BatchID;
    private List<EmandateInfo> emandateData;

    public int getBatchID() {
        return BatchID;
    }

    public void setBatchID(int batchID) {
        BatchID = batchID;
    }

    public List<EmandateInfo> getEmandateData() {
        return emandateData;
    }

    public void setEmandateData(List<EmandateInfo> emandateData) {
        this.emandateData = emandateData;
    }
}
