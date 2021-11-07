package com.bank.kata.service.impl;

import com.bank.kata.domain.Compte;
import com.bank.kata.domain.Operation;
import com.bank.kata.domain.enumeration.TypeOperation;
import com.bank.kata.service.CompteService;
import com.bank.kata.utils.OperationPrinter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompteServiceImpl implements CompteService {

	Compte compte = new Compte();
	OperationPrinter statementPrinter = new OperationPrinter();

	@Override
	public BigDecimal depositToAccount(BigDecimal amount) {
		return createTransaction(TypeOperation.DEPOSIT,amount);
	}

	@Override
	public BigDecimal withdrawalFromAccount(BigDecimal amount) {

		if(compte.getBalance().compareTo(amount) < 0 ) throw new RuntimeException("Insufficient Balance");

		return createTransaction(TypeOperation.WITHDRAWAL,amount);
		
	}

	private BigDecimal createTransaction(TypeOperation operationType, BigDecimal amount) {

		BigDecimal newBalance = operationType == TypeOperation.WITHDRAWAL ? compte.getBalance().add(amount.negate()) : compte.getBalance().add(amount);

		Operation operation = new Operation(operationType, LocalDateTime.now(), amount, newBalance);
		compte.setBalance(newBalance);
		compte.addOperation(operation);

		return newBalance;
	}

	@Override
	public List<Operation> findOperations() {
		List<Operation> ops = compte.getOperations();
		statementPrinter.print(ops);
		return ops;
	}

}
