package com.example.spring.jta.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.spring.jta.audit.service.AuditLogService;
import com.example.spring.jta.bank.exceptions.NotEnouthFoundsException;
import com.example.spring.jta.bank.model.BankAccount;
import com.example.spring.jta.bank.service.BankAccountService;

@Service
public class TransferService {

	private BankAccountService bankAccountService;
	private AuditLogService auditLogService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public TransferService(BankAccountService bankAccountService, AuditLogService auditLogService) {
		this.bankAccountService = bankAccountService;
		this.auditLogService = auditLogService;
	}
	
	@Transactional
	public void transfer(Integer fromAccountId, Integer toAccountId, Float amount) {
		bankAccountService.transfer(fromAccountId, toAccountId, amount);
		log.info("bankAccount ok");
		auditLogService.logEvent(fromAccountId, toAccountId, amount);
		log.info("auditEvent ok");
		Optional<BankAccount> bankAccount = bankAccountService.findById(fromAccountId);
		bankAccount.ifPresent( bank -> {
			if (bank.getBalance() < 0) {
				throw new NotEnouthFoundsException("Not enouth funds.!");
			}			
		});
	}
	
}
