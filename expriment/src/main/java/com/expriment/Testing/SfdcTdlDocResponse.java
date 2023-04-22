package com.expriment.Testing;

import com.expriment.utils.ProjectConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//
@Entity
@Table(name="tbl_sfdc_tdl_doc", catalog = ProjectConstants.DB.Exp_User)
public class SfdcTdlDocResponse implements Serializable {
	private static final long serialVersionUID = -7701801077770701884L;

//	@Id
//	@Column(name="id")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Long id;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="lead_id") //primery key
	private Integer leadId;

	@Column(name="customer_hash")
	private String customerHash;

	@Column(name="sfdc_doc")
	private String sfdcDoc;

	@Column(name="tdl_doc_tnc")
	private String tdlDocTnc;

	@Column(name="tdl_doc_loan_agr")
	private String tdlDocLoanAgr;

	@Column(name="esign_doc")
	private String esignDoc;

	@Column(name="created_date")
	private Date createdDate;

	@Column(name="emudra_Status")
	private String emudraStatus;

	@Column(name="updated_date")
	private Date updatedDate;

	public String getCustomerHash() {
		return customerHash;
	}

	public void setCustomerHash(String customerHash) {
		this.customerHash = customerHash;
	}

	public String getTdlDocTnc() {
		return tdlDocTnc;
	}

	public void setTdlDocTnc(String tdlDocTnc) {
		this.tdlDocTnc = tdlDocTnc;
	}

	public String getTdlDocLoanAgr() {
		return tdlDocLoanAgr;
	}

	public void setTdlDocLoanAgr(String tdlDocLoanAgr) {
		this.tdlDocLoanAgr = tdlDocLoanAgr;
	}

	public String getEmudraStatus() {
		return emudraStatus;
	}

	public void setEmudraStatus(String emudraStatus) {
		this.emudraStatus = emudraStatus;
	}

	public String getSfdcDoc() {
		return sfdcDoc;
	}

	public Integer getLeadId() {
		return leadId;
	}

	public void setLeadId(Integer leadId) {
		this.leadId = leadId;
	}

	public void setSfdcDoc(String sfdcDoc) {
		this.sfdcDoc = sfdcDoc;
	}


	public String getEsignDoc() {
		return esignDoc;
	}

	public void setEsignDoc(String esignDoc) {
		this.esignDoc = esignDoc;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}