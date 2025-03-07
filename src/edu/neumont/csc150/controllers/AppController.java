package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.*;
import edu.neumont.csc150.views.*;

public class AppController {
    private static final TransactionLog txnLog = new TransactionLog();
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

    /**
     * Lets user log in given they enter only a username
     * Makes sure user wants to create new save folder if user has not been created
     * @return Username user used to log in
     */
    private static String login() {
        while (true) {
            String username = AppUI.getUsernameInput();
            if (!SaveController.doesSaveExist(username)) {
                if (AppUI.getUserContinueIfUncreated()) {
                    return username;
                }
            } else {
                return username;
            }
        }
    }

    /**
     * Loads data to class-level Library with SaveManager given a username
     * @param username Username to load data from
     */
    private static void loadData(String username) {
        //txnLog.setName(username);
        TransactionLog loadData = SaveController.loadState(username);
        if (loadData != null) {
            txnLog.copyFrom(loadData);
        }
    }
}
