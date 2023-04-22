package com.expriment.Testing;

import java.io.Serializable;

public class DocTypeData implements Serializable {
    private static final long serialVersionUID = -2910951028591437502L;
    String doc;
    String docType;

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }
}
