package com.example.spring.jta.bank.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.spring.jta.bank.exceptions.BankAccountNotException;
import com.example.spring.jta.bank.model.BankAccount;
import com.example.spring.jta.bank.repo.BankAccountRepository;

@Service
public class BankAccountService {

	private BankAccountRepository bankAccountRepository;
	private final Logger log = LoggerFactory.getLogger(getClass());

	public BankAccountService(BankAccountRepository bankAccountRepository) {
		super();
		this.bankAccountRepository = bankAccountRepository;
	}

	public void transfer(Integer fromAccountId, Integer toAccountId, Float amount) {
		Optional<BankAccount> fromAccount = bankAccountRepository.findById(fromAccountId);
		Optional<BankAccount> toAccount = bankAccountRepository.findById(toAccountId);
		if (!(fromAccount.isPresent() && toAccount.isPresent())) {
			throw new BankAccountNotException("fromAccount or toAccount is empty");			
		}
		fromAccount.get().updateBalance(amount * (-1));
		toAccount.get().updateBalance(amount);
		bankAccountRepository.save(fromAccount.get());
		bankAccountRepository.save(toAccount.get());
		log.info("transfer from:{} => to:{}", fromAccount, toAccount);
	}

	public BankAccount save(BankAccount bankAccount) {
		return bankAccountRepository.save(bankAccount);
	}

	public Optional<BankAccount> findById(Integer id) {
		return bankAccountRepository.findById(id);
	}
}
