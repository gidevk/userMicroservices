package com.expriment.utils.audit.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RootPayload implements java.io.Serializable {
	private static final long serialVersionUID = 95123655152888L;

	private String stage;
	private String insertDataFlag;

	public String getInsertDataFlag() {
		return insertDataFlag;
	}
	public void setInsertDataFlag(String insertDataFlag) {
		this.insertDataFlag = insertDataFlag;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
}
