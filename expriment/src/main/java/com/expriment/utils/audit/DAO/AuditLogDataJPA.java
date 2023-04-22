package com.expriment.utils.audit.DAO;

import com.expriment.utils.audit.entity.AuditLogData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogDataJPA extends JpaRepository<AuditLogData,Integer> {

}
