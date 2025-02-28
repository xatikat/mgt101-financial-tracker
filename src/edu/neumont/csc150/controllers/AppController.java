package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.*;
import edu.neumont.csc150.views.*;

public class AppController {
    private final TransactionLog txnLog = new TransactionLog();

    public void run() {
        boolean doContinue = true;
        do {
            switch(AppUI.displayMainMenu()) {
                case 1 -> {
                    // Add income / expense page
                    txnLog.add(TransactionController.createTxn());
                    txnLog.sortByDate();
                }
                case 2 -> {
                    // Individual transaction view
                    //TODO
                    if (txnLog.size() > 0) {
                        TransactionViewController.run(txnLog);
                    } else {
                        TransactionViewUI.displayNoTxnError();
                    }
                }
                case 3 -> {
                    // Transaction history page
                }
                case 4 -> {
                    // Current balances page
                }
                case 5 -> {
                    // Goals page
                }
                case 6 -> {
                    // Save Data
                }
                default -> {
                    // Quit
                    doContinue = false;
                }
            }
        }
        while (doContinue);
    }
}
