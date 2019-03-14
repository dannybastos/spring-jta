package com.example.spring.jta;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.spring.jta.bank.model.BankAccount;
import com.example.spring.jta.bank.service.BankAccountService;

@Component
public class StartDB implements ApplicationListener<ContextRefreshedEvent> {

	private BankAccountService bankAccountService;
	
	public StartDB(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		BankAccount bankAccount1 = new BankAccount();
		bankAccount1.setId(1010);
		bankAccount1.setNumber(1010l);
		bankAccount1.setBalance(100f);
		
		BankAccount bankAccount2 = new BankAccount();
		bankAccount2.setId(1020);
		bankAccount2.setNumber(1020l);
		bankAccount2.setBalance(200f);
		
		bankAccountService.save(bankAccount1);
		bankAccountService.save(bankAccount2);
	}

}
