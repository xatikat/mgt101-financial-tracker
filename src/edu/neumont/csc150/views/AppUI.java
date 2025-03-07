package edu.neumont.csc150.views;

public class AppUI {
    public static int displayMainMenu() {
        return Console.getIntInput("""
                1. Add Income / Expense
                2. View Specific Transaction
                3. View Transaction History (By Month)
                4. View Current Balances
                5. View Financial Goals
                6. Save Data
                7. Quit
                """, 1, 8, Console.TextColor.BLUE);
    }

    public static void displayHR() {
        Console.writeLn("\n===================================\n", Console.TextColor.CYAN);
    }

    public static int getGotoInput(int collSize) {
        int choice = Console.getIntInput("Enter the transaction to go to:", 1, collSize, Console.TextColor.BLUE);
        Console.writeLn("");
        return choice;
    }

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
}
