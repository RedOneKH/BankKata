package com.bank.kata.domain;

import com.bank.kata.domain.enumeration.TypeOperation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Operation implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    private String reference;
	
	private TypeOperation typeOperation;
	
	private LocalDateTime dateOperation;
	
	private BigDecimal amount;
	
	private BigDecimal balance;

	public Operation(TypeOperation typeOperation, LocalDateTime dateOperation, BigDecimal amount, BigDecimal balance) {
		this.reference = UUID.randomUUID().toString();
		this.typeOperation = typeOperation;
		this.dateOperation = dateOperation;
		this.amount = amount;
		this.balance = balance;
	}

	public TypeOperation getTypeOperation() {
		return typeOperation;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public void setTypeOperation(TypeOperation typeOperation) {
		this.typeOperation = typeOperation;
	}

	public LocalDateTime getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(LocalDateTime dateOperation) {
		this.dateOperation = dateOperation;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Operation [reference=" + reference + ", typeOperation=" + typeOperation + ", dateOperation=" + dateOperation
				+ ", amount=" + amount + ", balance=" + balance + "]";
	}

	

}
