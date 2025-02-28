package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.*;
import edu.neumont.csc150.models.enums.TransactionCategory;
import edu.neumont.csc150.views.TrackerUI;

import java.time.LocalDate;

public class TransactionController {

    public static Transaction createTransaction() {
        Transaction transaction = getTransactionType();

        //create transaction
        String name = TrackerUI.getTransactionName();
        int amount = TrackerUI.getTransactionAmount();
        LocalDate date = TrackerUI.getTransactionDate();
        TransactionCategory category = getTransactionCategory();
        //TODO description setting
        transaction.setProperties(name, amount, date, category, "");

        return transaction;
    }

    private static Transaction getTransactionType() {
        // getting type
        do {
            switch (TrackerUI.getTransactionType()) {
                case "income" -> {
                    return new Income();
                }
                case "expense" -> {
                    return new Expense();
                }
                default -> TrackerUI.displayIncorrectTransactionType();
            };
        } while (true);
    }

    private static TransactionCategory getTransactionCategory() {
        do {
            String categoryS = TrackerUI.getTransactionCategory();
            try {
                return TransactionCategory.valueOf(categoryS.toUpperCase());
            } catch (IllegalArgumentException e) {
                // check if user wanted to list categories
                if (categoryS.equals("list")) {
                    TrackerUI.displayValidCategories();
                } else {
                    // invalid category
                    TrackerUI.displayInvalidCategory();
                }
            }
        } while (true);
    }

    public static void updateTransaction() {}

    public static void deleteTransaction() {}
}
