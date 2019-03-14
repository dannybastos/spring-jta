package com.example.spring.jta.bank.exceptions;

public class BankAccountNotException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public BankAccountNotException(String msg) {
		super(msg);
	}
}
