package com.example.spring.jta.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankAccount {

	@Id
	private Integer id;
	@Column
	private Long number;
	@Column(scale=9, precision=2)
	private Float balance;
	
	public Float updateBalance(Float amount) {
		balance = this.balance + amount;
		return balance;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return String.format("BankAccount [id=%s, number=%s, balance=%s]", id, number, balance);
	}	
}
