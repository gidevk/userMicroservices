package com.expriment.utils.audit.DAO;

import com.expriment.utils.audit.entity.AuditLogData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogDataJPA extends JpaRepository<AuditLogData,Integer> {

    AuditLogData findByCpId(Integer cp_id);
}