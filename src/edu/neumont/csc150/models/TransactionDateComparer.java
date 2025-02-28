package edu.neumont.csc150.models;

import java.util.Comparator;

public class TransactionDateComparer implements Comparator<Transaction> {

    /**
     * Compares two transactions
     * Sort Order: Dates by latest, then alphabetically
     * @param t1 the first object to be compared.
     * @param t2 the second object to be compared.
     * @return 0 if both have same date and name, -1 if t1 comes before t2, 1 if t1 comes after t2
     */
    @Override
    public int compare(Transaction t1, Transaction t2) {
        // Same (0)?
        // Before (-1)?
        // After (1)?

        int dateCompare = t1.getDate().compareTo(t2.getDate())*-1;
        if (dateCompare == 0) {
            return t1.getName().compareTo(t2.getName());
        } else {
            return dateCompare;
        }
    }
}
