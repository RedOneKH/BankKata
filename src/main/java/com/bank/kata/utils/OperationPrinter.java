package com.bank.kata.utils;

import com.bank.kata.domain.Operation;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OperationPrinter {

    public static final String STATEMENT_HEADER = "DATE | OPERATION | AMOUNT | BALANCE";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DECIMAL_PATTERN = "#.00";

    public void print(List<Operation> transactionList) {
        printHeader();
        printStatementLines(transactionList);
    }

    private void printHeader() {
        System.out.println(STATEMENT_HEADER);
    }

    private void printStatementLines(List<Operation> operations) {
        operations
                .stream()
                .forEach(op -> statementLine(op));
    }

    private void statementLine(Operation operation) {
        System.out.println(formatDate(operation.getDateOperation()) +
                " | " +
                operation.getTypeOperation() +
                " | " +
                formatDecimal(operation.getAmount()) +
                " | " +
                formatDecimal(operation.getBalance()));
    }

    private String formatDecimal(BigDecimal amount) {
        DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_PATTERN);
        return decimalFormat.format(amount);
    }

    private String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return date.format(formatter);
    }
}
