package edu.neumont.csc150.views;

public class AppUI {
    public static int displayMainMenu() {
        return Console.getIntInput("""
                1. Add Income / Expense
                2. View Specific Transaction
                3. View Transaction History (By Month)
                4. View Current Balances
                5. View Financial Goals
                6. Quit
                """, 1, 6, Console.TextColor.BLUE);
    }

    public static void displayHR() {
        Console.writeLn("\n===================================\n", Console.TextColor.CYAN);
    }

    public static int getGotoInput(int collSize) {
        int choice = Console.getIntInput("Enter the transaction to go to:", 1, collSize, Console.TextColor.BLUE);
        Console.writeLn("");
        return choice;
    }

    //region LOGIN UI

    public static String getUsernameInput() {
        String username = Console.getStringInput("Enter your username:", false, Console.TextColor.BLUE);
        Console.writeLn("");
        return username;
    }

    public static boolean getUserContinueIfUncreated() {
        boolean choice = Console.getBooleanInput("This user does not exist yet, would you like to create a save for it?", "yes", "no", Console.TextColor.BLUE);
        Console.writeLn("");
        return choice;
    }

    public static String getPasswordInput() {
        String username = Console.getStringInput("Enter your password:", false, Console.TextColor.BLUE);
        Console.writeLn("");
        return username;
    }

    public static void displayLoginError() {
        Console.writeLn("Username or password is incorrect! Please try again.\n", Console.TextColor.RED);
    }

    //endregion

    //region SORTING UI

    public static String getSortInput() {
        while (true) {
            String choiceS = Console.getStringInput("Enter a sort type (list for available sort types", false, Console.TextColor.BLUE).toLowerCase();
            switch (choiceS) {
                case "date", "d" -> {return "date";}
                case "amount", "a" -> {return "amount";}
                case "category", "c" -> {return "category";}
                case "name", "n" -> {return "name";}
                case "list", "help" -> displayAvailableSorts();
                default -> displayInvalidSort();
            }
        }
    }

    public static void displayAvailableSorts() {
        Console.writeLn("Available Sorts:", Console.TextColor.BLUE);
        Console.writeLn("""
                date\t\t-\t\tsorts by type (income then expense), then by date (newest first), then alphabetically
                amount\t\t-\t\tsorts by type (income then expense), then by amount (highest first)
                category\t-\t\tsorts by type (income then expense), then by category, then by date (newest first)
                name\t\t-\t\tsorts by type (income then expense), then by name
                """, Console.TextColor.PURPLE);
    }

    public static void displayInvalidSort() {
        Console.writeLn("Invalid sort type! Please enter a valid sort.", Console.TextColor.RED);
    }

    //endregion
}
