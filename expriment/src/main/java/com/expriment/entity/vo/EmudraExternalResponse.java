package com.expriment.entity.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmudraExternalResponse implements Serializable {

    String signed_file_content;

    public String getSigned_file_content() {
        return signed_file_content;
    }

    public void setSigned_file_content(String signed_file_content) {
        this.signed_file_content = signed_file_content;
    }
}
