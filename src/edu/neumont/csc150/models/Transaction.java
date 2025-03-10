package edu.neumont.csc150.models;

import edu.neumont.csc150.models.enums.TransactionCategory;

import java.time.LocalDate;

public abstract class Transaction implements Comparable<Transaction> {
    private String name;
    private int amount;
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

    public int getAmount() {
        return amount;
    }
    private void setAmount(int amount) {
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


    public void setProperties(String name, int amount, LocalDate date, TransactionCategory category, String description) {
        setName(name);
        setAmount(amount);
        setDate(date);
        setCategory(category);
        setDescription(description);
    }

    /**
     * Sorts between
     * @param o the object to be compared.
     * @return
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
        sb.append("Amount:\t\t\t\t").append(getAmount()).append("\n");
        sb.append("Date:\t\t\t\t").append(getDate()).append("\n");
        sb.append("Category:\t\t\t").append(getCategory()).append("\n");
        sb.append("Description:\t\t").append(getDescription()).append("\n");
        return sb.toString();
    }

    public String toShortString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDate()).append(" - ");
        if (this instanceof Expense) {
            sb.append("(").append(getAmount()).append(")");
        } else {
            sb.append(getAmount());
        }
        sb.append(" - ");
        sb.append(getName());
        return sb.toString();
    }
}
