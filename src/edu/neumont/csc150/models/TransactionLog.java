package edu.neumont.csc150.models;

import edu.neumont.csc150.models.comparators.*;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionLog extends ArrayList<Transaction>{
    private final static List<String> SORT_TYPES = List.of(new String[]{"amount", "date", "category", "name"});
    private String name;
    private int txnIndex;
    private String sortType;

    public TransactionLog() {
        setTxnIndex(0);
        setSortType("date");
    }

    public int getTxnIndex() {
        return this.txnIndex;
    }
    public void setTxnIndex(int txnIndex) {
        this.txnIndex = txnIndex;
    }

    public String getSortType() {
        return this.sortType;
    }
    public void setSortType(String sortType) {
        if (SORT_TYPES.contains(sortType.toLowerCase())) {
            this.sortType = sortType;
        }
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
     * Sorts TransactionLog by currently stored sortType
     */
    public void sort() {
        switch (getSortType()) {
            case "amount" -> super.sort(new TransactionAmountComparator());
            case "date" -> super.sort(new TransactionDateComparer());
            case "category" -> super.sort(new TransactionCategoryComparator());
            default -> super.sort(new TransactionNameComparator());
        }
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

    /**
     * Get all possible YearMonth combos from current TransactionLog
     * @return YearMonth ArrayList of combos existing in current log
     */
    public List<YearMonth> getPossibleDates() {
        List<YearMonth> possibleDates = new ArrayList<>();
        for (Transaction t: this) {
            Month month = t.getDate().getMonth();
            int year = t.getDate().getYear();
            YearMonth possibleDate = YearMonth.of(year, month);
            if (!possibleDates.contains(possibleDate)) {
                possibleDates.add(possibleDate);
            }
        }
        // sort whole list of possible dates (newest first) before returning
        // so that we return the latest transactions first
        possibleDates.sort(YearMonth::compareTo);
        return possibleDates.reversed();
    }

    /**
     * Gets all existing transactions under a specific YearMonth
     * @return Transaction ArrayList of transactions with given YearMonth
     */
    public List<Transaction> getTransactionsFromYearMonth(YearMonth yearMonth) {
        List<Transaction> txns = new ArrayList<>();
        for (Transaction t : this) {
            Month month = t.getDate().getMonth();
            int year = t.getDate().getYear();
            if (Objects.equals(yearMonth, YearMonth.of(year, month))) {
                txns.add(t);
            }
        }
        return txns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Transaction t: this.reversed()) {
            sb.append(t.toShortString()).append('\n');
        }
        return sb.toString();
    }
}
