package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Transaction;
import edu.neumont.csc150.models.TransactionLog;
import edu.neumont.csc150.views.TrackerUI;

public class AppController {
    private final TransactionLog transactionLog = new TransactionLog();

    public void run() {
        boolean doContinue = true;
        do {
            switch(TrackerUI.displayMainMenu()) {
                case 1:
                    // Add income / expense page
                    transactionLog.add(TransactionController.createTransaction());
                    transactionLog.sortByDate();
                    break;
                case 2:
                    // Individual transaction view
                    //TODO
                    for (Transaction t : transactionLog) {
                        System.out.println(t.getName());
                        System.out.println(t.getDate());
                        System.out.println();
                    }
                    break;
                case 3:
                    // Transaction history page
                    break;
                case 4:
                    // Current balances page
                    break;
                case 5:
                    // Goals page
                    break;
                case 6:
                    // Save Data
                    break;
                default:
                    // Quit
                    doContinue = false;
                    break;
            }
        }
        while (doContinue);
    }
}
