package edu.neumont.csc150.models.comparators;

import edu.neumont.csc150.models.Transaction;

import java.util.Comparator;

public class TransactionDateComparer implements Comparator<Transaction> {

    /**
     * Compares two transactions
     * Sort Order: By type (Income then Expense), then date (by latest), then alphabetically
     * @param t1 the first object to be compared.
     * @param t2 the second object to be compared.
     * @return 0 if both have same date and name, -1 if t1 comes before t2, 1 if t1 comes after t2
     */
    @Override
    public int compare(Transaction t1, Transaction t2) {
        // Same (0)?
        // Before (-1)?
        // After (1)?

        int typeCompare = t1.compareTo(t2);
        if (typeCompare == 0) {
            int dateCompare = t1.getDate().compareTo(t2.getDate());
            if (dateCompare == 0) {
                return t1.getName().compareToIgnoreCase(t2.getName());
            }
            return dateCompare*-1;
        }
        return typeCompare;
    }
}
