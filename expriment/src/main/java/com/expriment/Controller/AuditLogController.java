package com.expriment.Controller;

import com.expriment.utils.audit.DAO.AuditLogDataJPA;
import com.expriment.utils.audit.entity.AuditLogData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/audit-log")
public class AuditLogController {

    private final AuditLogDataJPA auditLogDataJPA;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(AuditLogController.class);

    public AuditLogController(AuditLogDataJPA auditLogDataJPA, ObjectMapper objectMapper) {
        this.auditLogDataJPA = auditLogDataJPA;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{cpId}")
    public ResponseEntity<?> getAuditLogDataById(@PathVariable("cpId") Integer cpId) {
        try {
            Optional<AuditLogData> opt = Optional.ofNullable(auditLogDataJPA.findByCpId(cpId));

            logger.info("Pin code match result: {}", objectMapper.writeValueAsString(opt));

            if (opt.isPresent()) {
                return ResponseEntity.ok(opt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Audit Log Data not found for ID: " + cpId);
            }
        } catch (JsonProcessingException e) {
            logger.error("Error processing JSON for Audit Log Data ID: {}", cpId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error processing request. Please try again later.");
        } catch (Exception e) {
            logger.error("Unexpected error occurred for Audit Log Data ID: {}", cpId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("An unexpected error occurred. Please contact support.");
        }
    }
}
