package com.expriment.Testing;

import java.io.Serializable;

public class Coordinates implements Serializable {

    Integer lly;
    Integer urx;
    Integer ury;
    Integer llx;

    @Override
    public String toString() {
        return "Coordinates{" +
                "lly=" + lly +
                ", urx=" + urx +
                ", ury=" + ury +
                ", llx=" + llx +
                '}';
    }

    public Integer getLly() {
        return lly;
    }

    public void setLly(Integer lly) {
        this.lly = lly;
    }

    public Integer getUrx() {
        return urx;
    }

    public void setUrx(Integer urx) {
        this.urx = urx;
    }

    public Integer getUry() {
        return ury;
    }

    public void setUry(Integer ury) {
        this.ury = ury;
    }

    public Integer getLlx() {
        return llx;
    }

    public void setLlx(Integer llx) {
        this.llx = llx;
    }
}
