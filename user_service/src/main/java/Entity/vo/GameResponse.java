package Entity.vo;

public class GameResponse {
    float priseAmount;
    String projectCode;
    float battingAmount;
    int cpId;
    int colourCode;
    int resultColourCode;
    int periodNumber;

    public float getPriseAmount() {
        return priseAmount;
    }

    public void setPriseAmount(float priseAmount) {
        this.priseAmount = priseAmount;
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

    public int getResultColourCode() {
        return resultColourCode;
    }

    public void setResultColourCode(int resultColourCode) {
        this.resultColourCode = resultColourCode;
    }

    public int getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(int periodNumber) {
        this.periodNumber = periodNumber;
    }
}
