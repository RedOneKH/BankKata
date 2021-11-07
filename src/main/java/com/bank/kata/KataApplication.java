package com.bank.kata;


import com.bank.kata.service.impl.CompteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class KataApplication implements CommandLineRunner {

	@Autowired
	public CompteServiceImpl compteService;

	public static void main(String[] args) {
		SpringApplication.run(KataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		compteService.depositToAccount(BigDecimal.valueOf(12.2));
		compteService.depositToAccount(BigDecimal.valueOf(120.25));
		compteService.depositToAccount(BigDecimal.valueOf(182.75));
		compteService.depositToAccount(BigDecimal.valueOf(6.25));
		compteService.depositToAccount(BigDecimal.valueOf(122.25));

		compteService.findOperations();
	}
}
