package com.example.spring.jta.audit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AuditLog {

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private Integer fromAccountId;
	@Column
	private Integer toAccountId;
	@Column(scale = 9, precision = 2)
	private Float amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(Integer fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public Integer getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(Integer toAccountId) {
		this.toAccountId = toAccountId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public AuditLog() {	}

	public AuditLog(Integer fromAccountId, Integer toAccountId, Float amount) {
		super();
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return String.format("AuditLog [id=%s, fromAccount=%s, toAccount=%s, amount=%s]", id, fromAccountId,
				toAccountId, amount);
	}

}
