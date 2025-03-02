package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Transaction;
import edu.neumont.csc150.models.TransactionLog;
import edu.neumont.csc150.models.enums.TransactionCategory;

import java.time.LocalDate;

public class TestController {
    private final TransactionLog transactionLog = new TransactionLog();

    public void run() {
        //testSort();
        SaveController.run();
    }

    public void testSort() {
        Transaction t1 = new Transaction();
        Transaction t2 = new Transaction();
        Transaction t3 = new Transaction();
        Transaction t4 = new Transaction();

        t1.setProperties("Burger (Transaction 1)", 200, LocalDate.parse("2024-12-20"), TransactionCategory.NEEDS, "");
        t2.setProperties("Burger (Transaction 2)", 200, LocalDate.parse("2024-12-10"), TransactionCategory.NEEDS, "");
        t3.setProperties("Burger (Transaction 3)", 200, LocalDate.parse("2024-12-30"), TransactionCategory.NEEDS, "");
        t4.setProperties("Burger (Transaction 4)", 200, LocalDate.parse("2024-10-23"), TransactionCategory.NEEDS, "");

        transactionLog.add(t1);
        transactionLog.add(t2);
        transactionLog.add(t3);
        transactionLog.add(t4);

        transactionLog.sortByDate();

        for (Transaction t : transactionLog) {
            System.out.println(t.getName());
            System.out.println(t.getDate());
            System.out.println();
        }
    }
}
