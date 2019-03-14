package com.example.spring.jta.bank.exceptions;

public class NotEnouthFoundsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public NotEnouthFoundsException(String msg) {
		super(msg);
	}
}
