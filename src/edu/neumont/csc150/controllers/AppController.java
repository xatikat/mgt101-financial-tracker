package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.*;
import edu.neumont.csc150.views.*;

public class AppController {
    private final TransactionLog txnLog = new TransactionLog();
    //TODO remove this once users are created
    private final String username = "default";

    public void run() {
        // load save from username
        if (SaveController.doesSaveExist(username)) {
            TransactionLog saveData = SaveController.loadState(username);
            if (!saveData.isEmpty()) {
                txnLog.addAll(saveData);
                txnLog.sortByDate();
            }
        }

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
                    if (!txnLog.isEmpty()) {
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
                    SaveController.saveState(username, txnLog);
                }
                default -> {
                    // Quit
                    SaveController.saveState(username, txnLog);
                    doContinue = false;
                }
            }
        }
        while (doContinue);
    }
}
