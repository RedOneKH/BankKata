package com.bank.kata.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Compte implements Serializable {
	
    private static final long serialVersionUID = 1L;

	private String rib;

	private LocalDateTime dateCreation;

	private BigDecimal balance = new BigDecimal(0);
	
	private List<Operation> operations = new ArrayList<>();

	public void addOperation(Operation operation) {
		operations.add(0,operation);
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<Operation> getOperations() {
		return Collections.unmodifiableList(operations);
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	@Override
	public String toString() {
		return "Compte [rib=" + rib + ", dateCreation=" + dateCreation + ", balance=" + balance
				+ ", operations=" + operations + "]";
	}
	
}
