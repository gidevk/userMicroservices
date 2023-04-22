/**
 * 
 */
package com.expriment.utils.audit.entity;

import com.expriment.utils.ProjectConstants;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Indradev kumar
 *
 */

@Entity
@Table(name = "exp_tcl_constants", catalog = ProjectConstants.DB.Exp_User)
public class TclConstant implements Serializable {


	private static final long serialVersionUID = -5333945627977164059L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sl_no")
	Long slNo;
	
	@Column(name = "constant_name")
	String constantName;
	
	@Column(name = "constant_value_app")
	String appConstantValue;
	
	@Column(name = "constant_value_web")
	String webConstantValue;
	
	@Column(name = "api_name")
	String apiName;

	public Long getSlNo() {
		return slNo;
	}

	public void setSlNo(Long slNo) {
		this.slNo = slNo;
	}

	public String getConstantName() {
		return constantName;
	}

	public void setConstantName(String constantName) {
		this.constantName = constantName;
	}

	public String getAppConstantValue() {
		return appConstantValue;
	}

	public void setAppConstantValue(String appConstantValue) {
		this.appConstantValue = appConstantValue;
	}

	public String getWebConstantValue() {
		return webConstantValue;
	}

	public void setWebConstantValue(String webConstantValue) {
		this.webConstantValue = webConstantValue;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

}
