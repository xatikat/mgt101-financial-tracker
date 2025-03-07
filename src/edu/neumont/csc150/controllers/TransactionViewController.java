package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Transaction;
import edu.neumont.csc150.models.TransactionLog;
import edu.neumont.csc150.views.AppUI;
import edu.neumont.csc150.views.TransactionViewUI;

public class TransactionViewController {
    public static void viewTransactionView(TransactionLog txnLog) {
        boolean doContinueViewing = true;
        do {
            viewTransaction(txnLog);
            switch (TransactionViewUI.getTransactionViewInput()) {
                case "next" -> txnLog.nextTxn();
                case "previous" -> txnLog.previousTxn();
                case "edit" -> {
                    //edits current txn
                    Transaction newTxn = TransactionController.editTxn(txnLog.get(txnLog.getTxnIndex()));
                    txnLog.set(txnLog.getTxnIndex(), newTxn);
                    txnLog.sortByDate();
                    SaveController.saveState(txnLog.getName(), txnLog);
                }
                case "delete" -> {
                    //deletes current txn
                    if (TransactionViewUI.confirmDeletion()) {
                        txnLog.remove(txnLog.getTxnIndex());
                    }
                }
                case "goto" -> {
                    //goto command
                    txnLog.setTxnIndex(AppUI.getGotoInput(txnLog.size())-1);
                }
                case "quit" -> doContinueViewing = false;
            }
            // in case there are no more left after being deleted
            if (txnLog.isEmpty()) {
                TransactionViewUI.displayNoTxnError();
                doContinueViewing = false;
            }
        } while(doContinueViewing);
    }

    public static void viewTransaction(TransactionLog txnLog) {
        TransactionViewUI.displayTransaction(txnLog.viewTxn(), txnLog.getTxnIndex(), txnLog.size());
    }
}
