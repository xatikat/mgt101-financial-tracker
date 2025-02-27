package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.*;
import edu.neumont.csc150.models.enums.TransactionCategory;
import edu.neumont.csc150.views.TrackerUI;

import java.time.LocalDate;

public class TransactionController {

    public static void addTransaction() {
        // getting type
        boolean isValidType = true;
        Transaction transaction = null;
        do {
            switch (TrackerUI.getTransactionType()) {
                case "income":
                    transaction = new Income();
                    isValidType = false;
                    break;
                case "expense":
                    transaction = new Expense();
                    isValidType = false;
                    break;
                default:
                    TrackerUI.displayIncorrectTransactionType();
                    break;
            };
        } while (isValidType);

        //
        String name = TrackerUI.getTransactionName();
        int amount = TrackerUI.getTransactionAmount();
        LocalDate date = TrackerUI.getTransactionDate();
        TransactionCategory category = getTransactionCategory();

        transaction.setProperties(name, amount, date, category, "");
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
