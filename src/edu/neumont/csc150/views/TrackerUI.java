package edu.neumont.csc150.views;

import edu.neumont.csc150.models.enums.TransactionCategory;

import java.time.LocalDate;

public class TrackerUI {

    public static String displayLoginScreen() {
        return Console.getStringInput("""
                Please enter your username:
                """).toLowerCase();
    }

    public static int displayMainMenu() {
        return Console.getIntInput("""
                1. Add Income / Expense
                2. View Transaction History (By Month)
                3. View Specific Transaction
                4. View Current Balances
                5. View Financial Goals
                6. Save Data
                7. Quit
                """, 1, 8, Console.TextColor.BLUE);
    }

    public static String getTransactionType() {
        return Console.getStringInput("Would you like to add an income or an expense?", false, Console.TextColor.BLUE).toLowerCase();
    }

    public static void displayIncorrectTransactionType() {
        Console.writeLn("Please enter a valid transaction type! (Income / Expense)", Console.TextColor.RED);
    }

    public static String getTransactionName() {
        return Console.getStringInput("Enter the name of the transaction:", false, Console.TextColor.BLUE);
    }

    public static int getTransactionAmount() {
        return Console.getIntInput("Enter the amount of the transaction:", 0, Integer.MAX_VALUE, Console.TextColor.BLUE);
    }

    public static LocalDate getTransactionDate() {
        LocalDate minDate = LocalDate.parse("2000-01-01");
        LocalDate maxDate = LocalDate.parse("9999-12-31");
        return Console.getDateInputInline("Enter the transaction date", minDate, maxDate, Console.TextColor.BLUE);
    }

    public static String getTransactionCategory() {
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
}
