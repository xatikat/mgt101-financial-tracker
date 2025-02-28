package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Transaction;
import edu.neumont.csc150.models.TransactionLog;
import edu.neumont.csc150.views.TransactionViewUI;

public class TransactionViewController {
    public static void run(TransactionLog txnLog) {
        boolean doContinueViewing = true;
        do {
            viewTransaction(txnLog);
            switch (TransactionViewUI.getTransactionViewInput()) {
                case "n" -> txnLog.nextTxn();
                case "p" -> txnLog.previousTxn();
                case "e" -> {
                    //todo edit current txn
                    Transaction newTxn = TransactionController.editTxn(txnLog.get(txnLog.getTxnIndex()));
                    txnLog.set(txnLog.getTxnIndex(), newTxn);
                }
                case "d" -> {
                    //todo delete current txn
                    // todo test with many transactions
                    txnLog.remove(txnLog.getTxnIndex());
                }
                case "q" -> doContinueViewing = false;
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
