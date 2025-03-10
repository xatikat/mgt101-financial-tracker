package edu.neumont.csc150.models.comparators;

import edu.neumont.csc150.models.Expense;
import edu.neumont.csc150.models.Income;
import edu.neumont.csc150.models.Transaction;

import java.util.Comparator;

public abstract class TransactionTypeComparator implements Comparator<Transaction> {

    /**
     * Sorts by Transaction type first (Income then Expense)
     * @param t1 the first object to be compared.
     * @param t2 the second object to be compared.
     * @return -1 if t1 is before, 0 if same, 1 if t1 is after
     */
    @Override
    public int compare(Transaction t1, Transaction t2) {
        // before = -1
        // same = 0
        // after = 1

        if ((t1 instanceof Income && t2 instanceof Income) || (t1 instanceof Expense && t2 instanceof Expense)) {
            return 0;
        } else if (t1 instanceof Income) {
            return -1;
        } else {
            return 1;
        }
    }
}
