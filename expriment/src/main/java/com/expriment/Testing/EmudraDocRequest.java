package com.expriment.Testing;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmudraDocRequest implements Serializable {

    private static final long serialVersionUID = -7056194520309834156L;

    List<DocTypeData> docTypeData;
    String leadId;
    String CustomerHash;
    String opportunityId;
    String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<DocTypeData> getDocTypeData() {
        return docTypeData;
    }

    public void setDocTypeData(List<DocTypeData> docTypeData) {
        this.docTypeData = docTypeData;
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getCustomerHash() {
        return CustomerHash;
    }

    public void setCustomerHash(String customerHash) {
        this.CustomerHash = customerHash;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }
}
