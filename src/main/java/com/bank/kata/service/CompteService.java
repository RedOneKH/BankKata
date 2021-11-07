package com.bank.kata.service;

import com.bank.kata.domain.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface CompteService {
	
	BigDecimal depositToAccount(BigDecimal amount);
	
	BigDecimal withdrawalFromAccount(BigDecimal amount);

	List<Operation> findOperations();

}
