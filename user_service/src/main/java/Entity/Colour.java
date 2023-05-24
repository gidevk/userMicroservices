package Entity;

import com.expriment.utils.ProjectConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity

@Table(name = "tbl_colour", catalog = ProjectConstants.DB.Exp_User)
public class Colour implements Serializable {

    private static final long serialVersionUID = -1094067595546592834L;
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        @Column(name = "id")
        private int colourId;

        @Column(name="colour_code")
        private int colourCode;

        @Column(name="project_name")
        private String porjectName;

        @Column(name="period_no")
        private String periodNo;

        @Column(name="batting_amount")
        private float battingAmount;

        @Column(name="project_code")
        private float projectCode;

        @Column(name="created_data")
        private Date createdData;

        @Column(name="updated_date")
        private Date updatedDate;

    public float getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(float projectCode) {
        this.projectCode = projectCode;
    }

    public float getBattingAmount() {
        return battingAmount;
    }

    public void setBattingAmount(float battingAmount) {
        this.battingAmount = battingAmount;
    }

    public int getColourId() {
        return colourId;
    }

    public void setColourId(int colourId) {
        this.colourId = colourId;
    }

    public int getColourCode() {
        return colourCode;
    }

    public void setColourCode(int colourCode) {
        this.colourCode = colourCode;
    }

    public String getPorjectName() {
        return porjectName;
    }

    public void setPorjectName(String porjectName) {
        this.porjectName = porjectName;
    }

    public String getPeriodNo() {
        return periodNo;
    }

    public void setPeriodNo(String periodNo) {
        this.periodNo = periodNo;
    }

    public Date getCreatedData() {
        return createdData;
    }

    public void setCreatedData(Date createdData) {
        this.createdData = createdData;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
