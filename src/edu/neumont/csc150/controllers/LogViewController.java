package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.TransactionLog;
import edu.neumont.csc150.views.AppUI;
import edu.neumont.csc150.views.LogViewUI;

public class LogViewController {
    public static void viewCollection(TransactionLog txnLog) {
        boolean doContinueViewing = true;
        AppUI.displayHR();
        while (doContinueViewing) {
            LogViewUI.displayLog(txnLog);
            switch(LogViewUI.getLogViewInput()) {
                case "goto" -> {
                    txnLog.setTxnIndex(AppUI.getGotoInput(txnLog.size())-1);
                TransactionViewController.viewTransactionView(txnLog);
                }
                case "sort" -> {
                    //TODO implement sorts
                }
                case "balance" -> {
                    //TODO implement balance screen
                    //CollectionViewUI.displayCollectionPrice(txnLog.calculateCollectionPrice());
                }
                case "goals" -> {
                    // TODO implement goals and put a command to view them here
                }
                case "quit" -> doContinueViewing = false;
            }
        }
    }
}
