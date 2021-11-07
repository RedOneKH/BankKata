package com.bank.kata;


import com.bank.kata.service.impl.CompteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KataApplication implements CommandLineRunner {

	@Autowired
	public CompteServiceImpl compteService;

	public static void main(String[] args) {
		SpringApplication.run(KataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*compteService.depositToAccount(12.25);
		compteService.depositToAccount(120.25);
		compteService.depositToAccount(182.75);
		compteService.depositToAccount(6.25);
		compteService.depositToAccount(122.25);*/

		compteService.findOperations();
	}
}
