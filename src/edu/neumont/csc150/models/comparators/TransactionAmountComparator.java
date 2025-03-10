package edu.neumont.csc150.models.comparators;

import edu.neumont.csc150.models.Transaction;

import java.util.Comparator;

public class TransactionAmountComparator implements Comparator<Transaction> {

    /**
     * Sorts by Transaction type first, then by amount
     * @param t1 the first object to be compared.
     * @param t2 the second object to be compared.
     * @return -1 if t1 is before, 1 if t1 is after, 0 if same
     */
    @Override
    public int compare(Transaction t1, Transaction t2) {
        // before = -1
        // same = 0
        // after = 1

        int typeCompare = t1.compareTo(t2);
        if (typeCompare == 0) {
            int amountCompare = t1.getAmount() > t2.getAmount() ? -1 : 1;
            amountCompare = t1.getAmount() == t2.getAmount() ? 0 : amountCompare;
            return amountCompare;
        } else {
            return typeCompare;
        }
    }
}
