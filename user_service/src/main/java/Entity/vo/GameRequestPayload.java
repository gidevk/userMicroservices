package Entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public
class GameRequestPayload implements Serializable {
    private static final long serialVersionUID = 7120218839018151262L;

    private String projectCode;
    private float battingAmount;
    private int cpId;
    private int colourCode;
    private int resultColourCode;
    private int periodNumber;

    public int getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(int periodNumber) {
        this.periodNumber = periodNumber;
    }

    public int getResultColourCode() {
        return resultColourCode;
    }

    public void setResultColourCode(int resultColourCode) {
        this.resultColourCode = resultColourCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public float getBattingAmount() {
        return battingAmount;
    }

    public void setBattingAmount(float battingAmount) {
        this.battingAmount = battingAmount;
    }

    public int getCpId() {
        return cpId;
    }

    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    public int getColourCode() {
        return colourCode;
    }

    public void setColourCode(int colourCode) {
        this.colourCode = colourCode;
    }
}
