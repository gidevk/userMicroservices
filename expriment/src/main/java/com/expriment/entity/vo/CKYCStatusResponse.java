package com.expriment.entity.vo;


import com.expriment.utils.audit.entity.vo.RootResponse;

public class CKYCStatusResponse extends RootResponse {
    private boolean cKycDecision;
    private String url;
    private String rejectionCode;
    private String rejectionReason;

    public void setcKycDecision(boolean cKycDecision) {
        this.cKycDecision = cKycDecision;
    }

    public boolean getcKycDecision() {
        return cKycDecision;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setRejectionCode(String rejectionCode) {
        this.rejectionCode = rejectionCode;
    }

    public String getRejectionCode() {
        return rejectionCode;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }
}
