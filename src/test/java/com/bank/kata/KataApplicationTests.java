package com.bank.kata;

import com.bank.kata.domain.Compte;
import com.bank.kata.domain.Operation;
import com.bank.kata.domain.enumeration.TypeOperation;
import com.bank.kata.service.CompteService;
import com.bank.kata.service.impl.CompteServiceImpl;
import com.bank.kata.utils.OperationPrinter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KataApplicationTests {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	CompteServiceImpl compteService;

	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		compteService = new CompteServiceImpl();
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	public void print_empty_statement() throws IOException {

		OperationPrinter statementPrinter = new OperationPrinter();

		statementPrinter.print(new ArrayList<>());
		outContent.flush();
		String allWrittenLines = new String(outContent.toByteArray());

		assertEquals("DATE | OPERATION | AMOUNT | BALANCE\r\n", allWrittenLines);
	}

	@Test
	public void print_full_statement() throws IOException {

		LocalDateTime time = LocalDateTime.now();

		Operation operation1 = new Operation(TypeOperation.DEPOSIT, time, new BigDecimal(100), new BigDecimal(100));
		Operation operation2 = new Operation(TypeOperation.WITHDRAWAL, time, new BigDecimal(50), new BigDecimal(50));
		Operation operation3 = new Operation(TypeOperation.DEPOSIT, time, new BigDecimal(100), new BigDecimal(150));

		List<Operation> operationList = Arrays.asList(operation1,operation2,operation3);
		OperationPrinter statementPrinter = new OperationPrinter();

		statementPrinter.print(operationList);
		outContent.flush();
		String allWrittenLines = new String(outContent.toByteArray());

		assertEquals("DATE | OPERATION | AMOUNT | BALANCE\r\n" +
				""+ time.format(formatter) +" | DEPOSIT | 100.00 | 100.00\r\n" +
				""+ time.format(formatter) +" | WITHDRAWAL | 50.00 | 50.00\r\n" +
				""+ time.format(formatter) +" | DEPOSIT | 100.00 | 150.00\r\n", allWrittenLines);
	}

	@Test(expected = RuntimeException.class)
	public void fail_withdrawal(){
		compteService.withdrawalFromAccount(new BigDecimal(100));
	}


	@Test
	public void deposit(){
		BigDecimal newBalance;
		newBalance = compteService.depositToAccount(new BigDecimal(100));
		newBalance = compteService.depositToAccount(new BigDecimal(150));
		newBalance = compteService.depositToAccount(new BigDecimal(300));

		assertEquals(new BigDecimal(550),newBalance);
	}

	@Test
	public void successful_withdrawal(){

		BigDecimal newBalance;
		newBalance = compteService.depositToAccount(new BigDecimal(100));

		newBalance = compteService.withdrawalFromAccount(new BigDecimal(50));

		assertEquals(new BigDecimal(50),newBalance);
	}



}
