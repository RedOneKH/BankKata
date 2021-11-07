package com.bank.kata;

import com.bank.kata.service.impl.CompteServiceImpl;
import com.bank.kata.utils.OperationPrinter;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestSteps  {

    CompteServiceImpl compteService = new CompteServiceImpl();
    private BigDecimal balance;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    OperationPrinter statementPrinter = new OperationPrinter();


    @When("bank client wants to save money")
    public void bank_client_wants_to_save_money() {
    }

    @Then("make a deposit in my account of {double}")
    public void make_a_deposit_in_my_account_of(double amount) {
        balance = compteService.depositToAccount(new BigDecimal(amount));
    }

    @When("bank client wants to retrieve some or all of his savings")
    public void bank_client_wants_to_retrieve_some_or_all_of_his_savings() {
    }

    @Then("make a withdrawal from my account of {double}")
    public void make_a_withdrawal_from_my_account_of(double amount) {
        compteService.depositToAccount(BigDecimal.valueOf(100));
        balance = compteService.withdrawalFromAccount(new BigDecimal(amount));
    }

    @Then("the client gets balance of {double}")
    public void the_client_gets_balance_of(double amount) {
        assertEquals(balance,new BigDecimal(amount));
    }


    @When("bank client wants to check his operations")
    public void bank_client_wants_to_check_his_operations() {
        System.setOut(new PrintStream(outContent));
        compteService = new CompteServiceImpl();
    }

    @Then("wants to see the history \\(operation, date, amount, balance) of my operations")
    public void wants_to_see_the_history_operation_date_amount_balance_of_my_operations() throws IOException {
        OperationPrinter statementPrinter = new OperationPrinter();
        statementPrinter.print(new ArrayList<>());
        outContent.flush();
    }
    @Then("the client gets response (.+)$")
    public void the_client_gets_response(String res) {
        String allWrittenLines = new String(outContent.toByteArray());

        assertEquals(res+"\r\n", allWrittenLines);
    }
}
