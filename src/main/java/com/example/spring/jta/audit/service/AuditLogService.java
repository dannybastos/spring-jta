package com.example.spring.jta.audit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.spring.jta.audit.model.AuditLog;
import com.example.spring.jta.audit.repo.AuditLogRepository;

@Service
public class AuditLogService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private AuditLogRepository auditLogRepository;
	
	public AuditLogService(AuditLogRepository auditLogRepository) {
		this.auditLogRepository = auditLogRepository;
	}
	
	public void logEvent(Integer fromAccountId, Integer toAccountId, Float amount) {
		AuditLog auditLog = new AuditLog(fromAccountId, toAccountId, amount);
		auditLogRepository.save(auditLog);
		log.info("logEvent - {}", auditLog);
	}
}
