package com.example.spring.jta.audit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.jta.audit.model.AuditLog;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {

}
