package edu.neumont.csc150.models;

import java.util.ArrayList;

public class TransactionLog extends ArrayList<Transaction>{
    private String name;
    private int txnIndex = 0;

    public int getTxnIndex() {
        return this.txnIndex;
    }
    public void setTxnIndex(int txnIndex) {
        this.txnIndex = txnIndex;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // my own methods on top of the ArrayList class

    //region NAVIGATION
    public Transaction viewTxn() {
      return this.get(getTxnIndex());
    }

    public void nextTxn() {
        // loop below array size if necessary
        int newIndex = getTxnIndex() + 1;
        if (newIndex >= this.size()) {
            newIndex = 0;
        }

        setTxnIndex(newIndex);
    }

    public void previousTxn() {
        // loop above array size if necessary
        int newIndex = getTxnIndex() - 1;
        if (newIndex < 0) {
            newIndex = this.size() - 1;
        }

        setTxnIndex(newIndex);
    }
    //endregion

    /**
     * Sorts the ArrayList by date, earliest come sfirst
     */
    public void sortByDate() {
        super.sort(new TransactionDateComparer());
    }

    /**
     * Copies all transactions from another TransactionLog
     */
    public void copyFrom(TransactionLog other) {
        this.addAll(other);
    }

    /**
     * Remove a transaction from the log
     * Set txnIndex to previous after deletion, or set to 0 if there are none left
     * @param index the index of the element to be removed
     * @return Removed transaction
     */
    @Override
    public Transaction remove(int index) {
        Transaction removedTxn = super.remove(index);
        // set to previous after deletion or to 0 if none left
        this.setTxnIndex(Math.max(index-1, 0));
        return removedTxn;
    }
}
