package edu.neumont.csc150.views;

import edu.neumont.csc150.models.TransactionLog;

public class LogViewUI {
    /**
     * Displays a TransactionLog as a whole
     * @param txnLog TransactionLog to be displayed
     */
    public static void displayLog(TransactionLog txnLog) {
        Console.writeLn("Transaction Log:", Console.TextColor.PURPLE);
        Console.writeLn(txnLog.toString(), Console.TextColor.YELLOW);
    }

    /**
     * Gets a command from the user
     * @return Chosen command
     */
    public static String getLogViewInput() {
        while(true) {
            String choiceS = Console.getStringInput("Enter a command (type help for available commands):", false, Console.TextColor.BLUE).toLowerCase();
            switch (choiceS) {
                case "goto", "g" -> {return "goto";}
                case "sort", "s" -> {return "sort";}
                case "balance", "b" -> {return "balance";}
                case "quit", "q" -> {return "quit";}
                case "help", "h" -> displayAvailableCommands();
                default -> displayInvalidCommand();
            }
        }
    }

    public static void displayAvailableCommands() {
        Console.writeLn("Available Commands:", Console.TextColor.PURPLE);
        // selection thing here if enough time, would require to remove toString from collection but is ok
        // would be cool!
        Console.writeLn("""
                        goto (g)\t\t-\t\tallows the user to go to a specific transaction in the log
                        sort (s)\t\t-\t\tsorts the transactions in different ways
                        balance (p)\t\t-\t\tcalculates the total balance of the log
                        quit (q)\t\t-\t\tgoes back to the main menu
                        help (h)\t\t-\t\tdisplays this command list
                        """, Console.TextColor.YELLOW);
    }

    public static void displayInvalidCommand() {
        Console.writeLn("Invalid command! Please enter a valid command.", Console.TextColor.RED);
    }
}
