package edu.neumont.csc150.views;

import edu.neumont.csc150.models.Transaction;

public class TransactionViewUI {
    public static void displayTransaction(Transaction txn, int index, int size) {
        Console.writeLn("Current Transaction (" + (index+1) + " of " + size + "):", Console.TextColor.YELLOW);
        Console.writeLn(txn.toString(), Console.TextColor.YELLOW);
    }

    public static String getTransactionViewInput() {
        while(true) {
            String choiceS = Console.getStringInput("Enter a command (type help for available commands):", false, Console.TextColor.BLUE).toLowerCase();
            switch (choiceS) {
                case "next", "n" -> {return "n";}
                case "previous", "prev", "p" -> {return "p";}
                case "edit", "e" -> {return "e";}
                // TODO confirm deletion
                case "delete", "del", "d" -> {return "d";}
                case "quit", "q" -> {return "q";}
                case "help", "h" -> displayAvailableCommands();
                default -> displayInvalidCommand();
            };
        }
    }

    public static void displayAvailableCommands() {
        Console.writeLn("Available Commands:", Console.TextColor.BLUE);
        Console.writeLn("help (h)\t\t-\tdisplays available commands", Console.TextColor.PURPLE);
        Console.writeLn("next (n)\t\t-\tgoes to the next transaction", Console.TextColor.PURPLE);
        Console.writeLn("previous (p)\t-\tgoes to the previous transaction", Console.TextColor.PURPLE);
        Console.writeLn("edit (e)\t\t-\tedit current transaction", Console.TextColor.PURPLE);
        Console.writeLn("delete (d)\t\t-\tdelete current transaction", Console.TextColor.PURPLE);
        Console.writeLn("quit (q)\t\t-\tgo back to the main menu", Console.TextColor.PURPLE);
    }

    public static void displayInvalidCommand() {
        Console.writeLn("Invalid command! Please enter a valid command.", Console.TextColor.RED);
    }

    public static void displayNoTxnError() {
        Console.writeLn("There are no transactions to be viewed.", Console.TextColor.RED);
    }
}
