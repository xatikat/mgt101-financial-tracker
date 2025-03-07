package edu.neumont.csc150.views;

import edu.neumont.csc150.models.Transaction;

public class TransactionViewUI {
    public static void displayTransaction(Transaction txn, int index, int size) {
        Console.writeLn("Current Transaction (" + (index+1) + " of " + size + "):", Console.TextColor.YELLOW);
        Console.writeLn(txn.toString(), Console.TextColor.YELLOW);
    }

    /**
     * Gets command from user for individual Transaction view
     * @return
     */
    public static String getTransactionViewInput() {
        while(true) {
            String choiceS = Console.getStringInput("Enter a command (type help for available commands):", false, Console.TextColor.BLUE).toLowerCase();
            switch (choiceS) {
                case "next", "n" -> {return "next";}
                case "previous", "prev", "p" -> {return "previous";}
                case "edit", "e" -> {return "edit";}
                case "delete", "del", "d" -> {return "delete";}
                case "goto", "g" -> {return "goto";}
                case "quit", "q" -> {return "quit";}
                case "help", "h" -> displayAvailableCommands();
                default -> displayInvalidCommand();
            }
        }
    }

    public static void displayAvailableCommands() {
        Console.writeLn("Available Commands:", Console.TextColor.BLUE);
        Console.writeLn("help (h)\t\t-\tdisplays available commands", Console.TextColor.PURPLE);
        Console.writeLn("next (n)\t\t-\tgoes to the next transaction", Console.TextColor.PURPLE);
        Console.writeLn("previous (p)\t-\tgoes to the previous transaction", Console.TextColor.PURPLE);
        Console.writeLn("edit (e)\t\t-\tedit current transaction", Console.TextColor.PURPLE);
        Console.writeLn("delete (d)\t\t-\tdelete current transaction", Console.TextColor.PURPLE);
        Console.writeLn("goto (g)\t\t-\tgo to a specific transaction in the list", Console.TextColor.PURPLE);
        Console.writeLn("quit (q)\t\t-\tgo back to the main menu", Console.TextColor.PURPLE);
    }

    public static void displayInvalidCommand() {
        Console.writeLn("Invalid command! Please enter a valid command.", Console.TextColor.RED);
    }

    public static void displayNoTxnError() {
        Console.writeLn("There are no transactions to be viewed.", Console.TextColor.RED);
    }

    /**
     * Allows user to confirm the deletion of a transaction in the Log
     * @return True if they chose to delete, false if not
     */
    public static boolean confirmDeletion() {
        while (true) {
            String choice = Console.getStringInput("Are you sure you want to delete this release? (Y/N)", false, Console.TextColor.BLUE).toLowerCase();
            switch(choice) {
                case "y", "yes" -> {return true;}
                case "n", "no" -> {return false;}
                default -> Console.writeLn("That is not a valid choice, please enter yes or no.", Console.TextColor.RED);
            }
        }
    }
}
