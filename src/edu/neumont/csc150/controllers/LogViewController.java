package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.*;
import edu.neumont.csc150.views.AppUI;
import edu.neumont.csc150.views.LogViewUI;

import java.time.YearMonth;
import java.util.List;

public class LogViewController {
    public static void viewCollection(TransactionLog txnLog, List<Goal> goalLog) {
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
                    LogViewUI.displayGoalHeader();
                    for (Goal goal : goalLog) {
                        LogViewUI.displayGoal(goal, calculateCurrentGoal(goal, currentTxns));
                    }
                    LogViewUI.displayEnterBar();
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

    public static float calculateCurrentGoal(Goal goal, List<Transaction> currentTxns) {
        float spendingTotal = 0;
        if (goal.getGoalCategory() != null) {
            // if the goal has a category, only count category-specific spending
            for (Transaction t: currentTxns) {
                if (t.getCategory() == goal.getGoalCategory() && t instanceof Expense) {
                    spendingTotal += t.getAmount();
                }
            }
        } else {
            // for if the goal has no category, then it applies to all spending
            for (Transaction t: currentTxns) {
                if (t instanceof Expense) {
                    spendingTotal += t.getAmount();
                }
            }
        }
        return spendingTotal;
    }
}
