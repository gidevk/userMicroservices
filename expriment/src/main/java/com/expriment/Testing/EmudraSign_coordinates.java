package com.expriment.Testing;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EmudraSign_coordinates implements Serializable {
    /*"lly": 35,
            "llx": 35,
            "urx": 322,
            "ury": 60*/
    @JsonProperty("1")
    List<Coordinates> coordinates =new ArrayList<>();

    public List<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }
}

