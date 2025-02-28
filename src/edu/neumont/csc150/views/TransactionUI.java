package edu.neumont.csc150.views;

import edu.neumont.csc150.models.enums.TransactionCategory;

import java.time.LocalDate;

public class TransactionUI {

    public static String displayLoginScreen() {
        return Console.getStringInput("""
                Please enter your username:
                """).toLowerCase();
    }

    public static String getTxnTypeInput() {
        return Console.getStringInput("Would you like to add an income or an expense?", false, Console.TextColor.BLUE).toLowerCase();
    }

    public static void displayIncorrectTxnType() {
        Console.writeLn("Please enter a valid transaction type! (Income / Expense)", Console.TextColor.RED);
    }

    public static String getTxnNameInput() {
        return Console.getStringInput("Enter the name of the transaction:", false, Console.TextColor.BLUE);
    }

    public static int getTxnAmountInput() {
        return Console.getIntInput("Enter the amount of the transaction:", 0, Integer.MAX_VALUE, Console.TextColor.BLUE);
    }

    public static LocalDate getTxnDateInput() {
        LocalDate minDate = LocalDate.parse("2000-01-01");
        LocalDate maxDate = LocalDate.parse("9999-12-31");
        return Console.getDateInputInline("Enter the transaction date", minDate, maxDate, Console.TextColor.BLUE);
    }

    public static String getTxnCatInput() {
        return Console.getStringInput("Enter transaction category (Type list for a list of valid categories):", false, Console.TextColor.BLUE).toLowerCase();
    }

    public static void displayValidCategories() {
        // take all enum values and just toString them
        TransactionCategory[] enumValues = TransactionCategory.values();
        Console.writeLn("Valid categories: ", Console.TextColor.BLUE);
        for (TransactionCategory enumValue : enumValues) {
            Console.writeLn(enumValue.toString(), Console.TextColor.PURPLE);
        }
    }

    public static void displayInvalidCategory() {
        Console.writeLn("Please enter a valid category!", Console.TextColor.RED);
    }

    public static String getTxnDescInput() {
        return Console.getStringInput("Enter the description of the transaction (or leave blank):", true, Console.TextColor.BLUE);
    }
}
