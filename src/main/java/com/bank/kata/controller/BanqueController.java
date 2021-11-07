package com.bank.kata.controller;

import com.bank.kata.domain.Operation;
import com.bank.kata.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class BanqueController {

    @Autowired
    CompteService compteService;

    @PutMapping("/deposit")
    public ResponseEntity<?> depositToAccount(@RequestParam("amount") BigDecimal amount){

        BigDecimal newBalance = compteService.depositToAccount(amount);

        return new ResponseEntity<>("SUCCESS : new Balance : " + newBalance,HttpStatus.OK);
    }

    @PutMapping("/withdrawal")
    public ResponseEntity<?> withdrawalFromAccount(@RequestParam("amount") BigDecimal amount){
        try {
            BigDecimal newBalance = compteService.withdrawalFromAccount(amount);
            return new ResponseEntity<>("SUCCESS : new Balance : " + newBalance,HttpStatus.OK);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.PRECONDITION_FAILED);
        }

    }

    @GetMapping("/historique")
    public ResponseEntity<?> getHistoriques(){

        List<Operation> operations = compteService.findOperations();

        return new ResponseEntity<>(operations, HttpStatus.OK);

    }
}
