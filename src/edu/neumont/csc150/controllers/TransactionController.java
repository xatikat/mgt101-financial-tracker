package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.*;
import edu.neumont.csc150.models.enums.TransactionCategory;
import edu.neumont.csc150.views.TransactionUI;

import java.time.LocalDate;

public class TransactionController {

    /**
     * Creates a transaction using user inputs
     * @return Created transaction with inputted properties
     */
    public static Transaction createTxn() {
        Transaction transaction = getTxnType();

        //create transaction
        String name = TransactionUI.getTxnNameInput();
        int amount = TransactionUI.getTxnAmountInput();
        LocalDate date = TransactionUI.getTxnDateInput();
        TransactionCategory category = getTxnCat();
        String description = TransactionUI.getTxnDescInput();
        transaction.setProperties(name, amount, date, category, description);

        return transaction;
    }

    /**
     * Gets the transaction type the user wants from an input
     * @return New Transaction object of user-specified type
     */
    private static Transaction getTxnType() {
        // getting type
        do {
            switch (TransactionUI.getTxnTypeInput()) {
                case "income" -> {
                    return new Income();
                }
                case "expense" -> {
                    return new Expense();
                }
                default -> TransactionUI.displayIncorrectTxnType();
            }
        } while (true);
    }

    private static TransactionCategory getTxnCat() {
        do {
            String categoryS = TransactionUI.getTxnCatInput();
            try {
                return TransactionCategory.valueOf(categoryS.toUpperCase());
            } catch (IllegalArgumentException e) {
                // check if user wanted to list categories
                if (categoryS.equals("list")) {
                    TransactionUI.displayValidCategories();
                } else {
                    // invalid category
                    TransactionUI.displayInvalidCategory();
                }
            }
        } while (true);
    }

    // TODO updating / editing existing txns :p
    public static Transaction editTxn(Transaction txn) {
        boolean isEditing = true;

        // get old values
        String name = txn.getName();
        int amount = txn.getAmount();
        LocalDate date = txn.getDate();
        TransactionCategory category = txn.getCategory();
        String description = txn.getDescription();

        while (isEditing) {
            // choose which value to edit
            switch (TransactionUI.getTxnEditValueInput()) {
                case "name" -> {
                    TransactionUI.displayPreviousValue(name);
                    name = TransactionUI.getTxnNameInput();
                }
                case "amount" -> {
                    TransactionUI.displayPreviousValue(String.valueOf(amount));
                    amount = TransactionUI.getTxnAmountInput();
                }
                case "date" -> {
                    TransactionUI.displayPreviousValue(date.toString());
                    date = TransactionUI.getTxnDateInput();
                }
                case "category" -> {
                    TransactionUI.displayPreviousValue(category.toString());
                    category = getTxnCat();
                }
                case "description" -> {
                    TransactionUI.displayPreviousValue(description);
                    description = TransactionUI.getTxnDescInput();
                }
                case "quit" -> isEditing = false;
            }
        }

        txn.setProperties(name, amount, date, category, description);
        return txn;
    }
}
