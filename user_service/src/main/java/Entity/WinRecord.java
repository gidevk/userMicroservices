package Entity;

import com.expriment.utils.ProjectConstants;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "win_record", catalog = ProjectConstants.DB.Exp_User)
public class WinRecord implements Serializable {

    private static final long serialVersionUID = 6617310698389222577L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private int winId;

    @Column(name="user_id")
    private int cpId;

    @Column(name="batting_amount")
    private float battingAmount;

    @Column(name="prise_amount")
    private float prise_amount;

    @Column(name="period_no")
    private int periodNumber;

    public int getWinId() {
        return winId;
    }

    public void setWinId(int winId) {
        this.winId = winId;
    }

    public int getCpId() {
        return cpId;
    }

    public void setCpId(int cpId) {
        this.cpId = cpId;
    }

    public float getBattingAmount() {
        return battingAmount;
    }

    public void setBattingAmount(float battingAmount) {
        this.battingAmount = battingAmount;
    }

    public float getPrise_amount() {
        return prise_amount;
    }

    public void setPrise_amount(float prise_amount) {
        this.prise_amount = prise_amount;
    }

    public int getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(int periodNumber) {
        this.periodNumber = periodNumber;
    }
}
