package com.onmylevel.banking.service;

import com.onmylevel.banking.entity.AuditLog;
import com.onmylevel.banking.repository.AuditLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditService {

    private static final Logger log = LoggerFactory.getLogger(AuditService.class);
    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    @Transactional
    public void log(String entityType, Long entityId, String action, String details) {
        try {
            AuditLog auditLog = new AuditLog(entityType, entityId, action, details);
            auditLogRepository.save(auditLog);
            log.info("Audit: {} {} {}", action, entityType, entityId);
        } catch (Exception e) {
            log.error("Failed to save audit log", e);
        }
    }
}
