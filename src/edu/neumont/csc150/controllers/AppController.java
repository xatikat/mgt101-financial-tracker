package edu.neumont.csc150.controllers;

import edu.neumont.csc150.views.TrackerUI;

public class AppController {
    public void run() {
        boolean doContinue = true;
        do {
            switch(TrackerUI.displayMainMenu()) {
                case 1:
                    // Add income / expense page
                    TransactionController.addTransaction();
                    break;
                case 2:
                    // Individual transaction view
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
