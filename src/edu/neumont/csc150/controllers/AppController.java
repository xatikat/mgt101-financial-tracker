package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.*;
import edu.neumont.csc150.views.*;
import edu.neumont.csc150.controllers.UserController;

import java.io.FileNotFoundException;

public class AppController {
    private static final TransactionLog txnLog = new TransactionLog();

    public void run() {
        // let user login and attempt to load data
        loadData(login());

        boolean doContinue = true;
        do {
            AppUI.displayHR();
            switch(AppUI.displayMainMenu()) {
                case 1 -> {
                    // Add income / expense page
                    txnLog.add(TransactionController.createTxn());
                    txnLog.sortByDate();
                }
                case 2 -> {
                    // Individual transaction view
                    if (!txnLog.isEmpty()) {
                        TransactionViewController.viewTransactionView(txnLog);
                    } else {
                        TransactionViewUI.displayNoTxnError();
                    }
                }
                case 3 -> {
                    // Transaction history page
                    LogViewController.viewCollection(txnLog);
                }
                case 4 -> {
                    // Current balances page
                }
                case 5 -> {
                    // Goals page
                }
                default -> {
                    // Quit
                    doContinue = false;
                }
            }
            // save after every main menu action is completed
            SaveController.saveState(txnLog.getName(), txnLog);
        }
        while (doContinue);
    }

    /**
     * Loads data to class-level Library with SaveManager given a username
     * @param username Username to load data from
     */
    private static void loadData(String username) {
        txnLog.setName(username);
        if (SaveController.doesSaveExist(username)) {
            TransactionLog loadData = SaveController.loadState(username);
            if (!loadData.isEmpty()) {
                txnLog.copyFrom(loadData);
            }
        }
    }

    /**
     * Lets user log in given they enter only a username
     * Makes sure user wants to create new save folder if user has not been created
     * @return Username user used to log in
     */
    private static String login() {
        while (true) {
            // get username and check if it already exists
            // if it does, get password and check both creds against database
            // return username if creds exist, otherwise we will display login error
            String username = AppUI.getUsernameInput();
            if (UserController.checkUsername(username)) {
                String password = AppUI.getPasswordInput();
                if (UserController.checkCredentials(username, password)) {
                    return username;
                } else {
                    AppUI.displayLoginError();
                }
            }
            // if it doesn't, confirm user wants to continue creating
            // otherwise loop over again (like if they typo username)
            else {
                if (AppUI.getUserContinueIfUncreated()) {
                    String password = AppUI.getPasswordInput();
                    UserController.addCredsToFile(username, password);
                    return username;
                }
            }
        }
    }
}
