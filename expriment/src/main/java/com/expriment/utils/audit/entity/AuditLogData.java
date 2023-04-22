package com.expriment.utils.audit.entity;


import com.expriment.utils.ProjectConstants;
import com.expriment.utils.audit.AuditDetailsInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "exp_audit_log", catalog = ProjectConstants.DB.Exp_User)
public class AuditLogData implements Serializable, AuditDetailsInfo {
    private static final long serialVersionUID = -4566252697233776968L;
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY, generator = "seq")
    @Column(name="al_id",unique = true, nullable = false)
    private Integer auditLogId;

//    @ManyToOne
    @Column(name="cp_id")
    private Integer cpId;

    @Column(name="status")
    private String status;

    @Column(name="service_name")
    private String serviceName;

    @Column(name="request")
    private String request;

    @Column(name="response")
    private String response;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="updated_date")
    private Date updatedDate;

    @Override
    public String toString() {
        return "AuditLogData{" +
                "auditLogId=" + auditLogId +
                ", cpId=" + cpId +
                ", status='" + status + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    public Integer getAuditLogId() {
        return auditLogId;
    }

    public void setAuditLogId(Integer auditLogId) {
        this.auditLogId = auditLogId;
    }

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
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

    @JsonIgnore
    @Transient
    @Override
    public String getEntityId() {
        return String.valueOf(auditLogId);
    }

    @JsonIgnore
    @Transient
    @Override
    public String getLogDeatil() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuditLogData { alId=");
        builder.append(auditLogId);
        builder.append(", cpId=");
        builder.append(cpId);
        builder.append(", status=");
        builder.append(status);
        builder.append(", service_name=");
        builder.append(serviceName);
        builder.append(", request=");
        builder.append(request);
        builder.append(", response=");
        builder.append(response);
        builder.append(", created_date=");
        builder.append(createdDate);
        builder.append(",updated_date=");
        builder.append(updatedDate);
        builder.append(" } ");
        return builder.toString();

    }

    @Override
    public String getEntityName() {
        return "exp_audit_log";
    }
}
