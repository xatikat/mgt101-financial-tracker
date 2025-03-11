package edu.neumont.csc150.models;

import edu.neumont.csc150.models.enums.TransactionCategory;

import java.time.LocalDate;

public abstract class Transaction implements Comparable<Transaction> {
    private String name;
    private float amount;
    private LocalDate date;
    private TransactionCategory category;
    private String description;

    public String getName() {
        return name;
    }
    private void setName(String name) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
    }

    public float getAmount() {
        return amount;
    }
    private void setAmount(float amount) {
        if (amount > 0) {
            this.amount = amount;
        }
    }

    public LocalDate getDate() {
        return date;
    }
    private void setDate(LocalDate date) {
        this.date = date;
    }

    public TransactionCategory getCategory() {
        return category;
    }
    private void setCategory(TransactionCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }
    private void setDescription(String description) {
        if (description != null && !description.isBlank()) {
            this.description = description;
        } else {
            this.description = "";
        }
    }


    public void setProperties(String name, float amount, LocalDate date, TransactionCategory category, String description) {
        setName(name);
        setAmount(amount);
        setDate(date);
        setCategory(category);
        setDescription(description);
    }

    /**
     * Returns formatted amount as $
     * @return String of formatted amount
     */
    public String getAmountString() {
        return String.format("$%,.2f", getAmount());
    }

    /**
     * Compares this transaction to another transaction by type (Income first, then Expense)
     * @param o the object to be compared.
     * @return 0 if they are the same type, -1 if this txn is before, 1 if this txn is after
     */
    @Override
    public int compareTo(Transaction o) {
        if ((this instanceof Income && o instanceof Income) || (this instanceof Expense && o instanceof Expense)) {
            return 0;
        } else if (this instanceof Income) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name:\t\t\t\t").append(getName()).append("\n");
        sb.append("Amount:\t\t\t\t").append(getAmountString()).append("\n");
        sb.append("Date:\t\t\t\t").append(getDate()).append("\n");
        sb.append("Category:\t\t\t").append(getCategory()).append("\n");
        sb.append("Description:\t\t").append(getDescription()).append("\n");
        return sb.toString();
    }

    public String toShortString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDate()).append(" - ");
        if (this instanceof Expense) {
            sb.append("(").append(getAmountString()).append(")");
        } else {
            sb.append(getAmountString());
        }
        sb.append(" - ").append(getCategory());
        sb.append(" - ").append(getName());
        return sb.toString();
    }
}
