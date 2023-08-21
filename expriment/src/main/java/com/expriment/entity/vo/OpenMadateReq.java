package com.expriment.entity.vo;

/*
 * @author Indradev.kuamr
 */
public class OpenMadateReq{
    private EnquiryRequest enquiryRequest;
    private String leadId;

    public EnquiryRequest getEnquiryRequest() {
        return enquiryRequest;
    }

    public void setEnquiryRequest(EnquiryRequest enquiryRequest) {
        this.enquiryRequest = enquiryRequest;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }
}