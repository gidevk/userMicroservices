package com.expriment.Testing;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmudraRequest implements Serializable {

    private static final long serialVersionUID = 186136054180864645L;

    /*
    * "
    "sign_coordinates": {
        "1": [
            {
                "lly": 35,
                "llx": 35,
                "urx": 322,
                "ury": 60
            }
        ]
    },reason": "Testing",---
    "display_on_page": "custom",--
    "key_store_name": "TCFSL",---
    "file_content_string":     "",---
    "name": "Prod DLG",---
    "location": "Mumbai" ----*/
    String name;
    String key_store_name;
    String reason;
    String location;
    String file_content_string;
    String display_on_page;
    EmudraSign_coordinates sign_coordinates;

    public EmudraSign_coordinates getSign_coordinates() {
        return sign_coordinates;
    }

    public void setSign_coordinates(EmudraSign_coordinates sign_coordinates) {
        this.sign_coordinates = sign_coordinates;
    }

    public String getDisplay_on_page() {
        return display_on_page;
    }

    public void setDisplay_on_page(String display_on_page) {
        this.display_on_page = display_on_page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey_store_name() {
        return key_store_name;
    }

    public void setKey_store_name(String key_store_name) {
        this.key_store_name = key_store_name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFile_content_string() {
        return file_content_string;
    }

    public void setFile_content_string(String file_content_string) {
        this.file_content_string = file_content_string;
    }
}
