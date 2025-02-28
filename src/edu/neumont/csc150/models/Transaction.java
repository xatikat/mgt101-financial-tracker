package edu.neumont.csc150.models;

import edu.neumont.csc150.models.enums.TransactionCategory;

import java.time.LocalDate;

public class Transaction implements Comparable<Transaction> {
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

    @Override
    public int compareTo(Transaction o) {
        int dateCompare = getDate().compareTo(o.getDate())*-1;
        if (dateCompare == 0) {
            return getName().compareTo(o.getName());
        } else {
            return dateCompare;
        }
    }
}
