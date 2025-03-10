package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Expense;
import edu.neumont.csc150.models.Income;
import edu.neumont.csc150.models.Transaction;
import edu.neumont.csc150.models.TransactionLog;
import edu.neumont.csc150.views.AppUI;
import edu.neumont.csc150.views.LogViewUI;

import java.time.YearMonth;
import java.util.List;

public class LogViewController {


    public static void viewCollection(TransactionLog txnLog) {
        List<YearMonth> dates = txnLog.getPossibleDates();
        int dateIndex = 0;
        boolean doContinueViewing = true;
        AppUI.displayHR();
        while (doContinueViewing) {
            // get all CURRENT variables and display them
            YearMonth currentDate = dates.get(dateIndex);
            List<Transaction> currentTxns = txnLog.getTransactionsFromYearMonth(currentDate);
            LogViewUI.displayCurrentTransactions(currentDate, currentTxns);

            //get command from user
            switch(LogViewUI.getLogViewInput()) {
                case "next" -> {
                    dateIndex += 1;
                    if (dateIndex >= dates.size()) {
                        dateIndex = 0;
                    }
                }
                case "previous" -> {
                    dateIndex -= 1;
                    if (dateIndex < 0) {
                        dateIndex = dates.size() - 1;
                    }
                }
                case "sort" -> {
                    String sortChoice = AppUI.getSortInput();
                    txnLog.setSortType(sortChoice);
                    txnLog.sort();
                }
                case "balance" -> {
                    LogViewUI.displayBalance(calculateCurrentBalance(currentTxns));
                }
                case "goals" -> {
                    // TODO implement goals and put a command to view them here
                }
                case "quit" -> doContinueViewing = false;
            }
        }
    }

    public static float calculateCurrentBalance(List<Transaction> currentTxns) {
        float balance = 0;
        for (Transaction t: currentTxns) {
            if (t instanceof Income) {
                balance += t.getAmount();
            } else if (t instanceof Expense) {
                balance -= t.getAmount();
            }
        }
        return balance;
    }
}
