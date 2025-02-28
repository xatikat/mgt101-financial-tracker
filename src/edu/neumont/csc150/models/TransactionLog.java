package edu.neumont.csc150.models;

import java.util.ArrayList;

public class TransactionLog extends ArrayList<Transaction>{
    // my own methods on top of the ArrayList class

    // sorts earliest first
    public void sortByDate() {
        super.sort(new TransactionDateComparer());
    }
}
