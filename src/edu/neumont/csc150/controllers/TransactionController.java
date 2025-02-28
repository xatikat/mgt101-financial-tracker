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

        return txn;
    }
}
