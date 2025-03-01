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
}
