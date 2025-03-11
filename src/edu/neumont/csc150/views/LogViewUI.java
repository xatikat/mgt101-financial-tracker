package edu.neumont.csc150.views;

import edu.neumont.csc150.models.Goal;
import edu.neumont.csc150.models.Transaction;

import java.time.YearMonth;
import java.util.List;

public class LogViewUI {
    /**
     * Displays a set of Transactions from a specific YearMonth date
     * @param date YearMonth date of the Transactions to be displayed
     * @param txns ArrayList of Transactions to be displayed
     */
    public static void displayCurrentTransactions(YearMonth date, List<Transaction> txns) {
        Console.write("Transaction Log (", Console.TextColor.PURPLE);
        Console.write(date.toString(), Console.TextColor.PURPLE);
        Console.writeLn("):", Console.TextColor.PURPLE);
        for (Transaction txn: txns) {
            Console.writeLn(txn.toShortString(), Console.TextColor.YELLOW);
        }
    }

    /**
     * Gets a command from the user
     * @return Chosen command
     */
    public static String getLogViewInput() {
        while(true) {
            String choiceS = Console.getStringInput("Enter a command (type help for available commands):", false, Console.TextColor.BLUE).toLowerCase();
            Console.writeLn("");
            switch (choiceS) {
                case "next", "n" -> {return "next";}
                case "previous", "p" -> {return "previous";}
                case "sort", "s" -> {return "sort";}
                case "balance", "b" -> {return "balance";}
                case "goal", "goals", "g" -> {return "goals";}
                case "quit", "q" -> {return "quit";}
                case "help", "h" -> displayAvailableCommands();
                default -> displayInvalidCommand();
            }
        }
    }

    public static void displayAvailableCommands() {
        Console.writeLn("Available Commands:", Console.TextColor.BLUE);
        Console.writeLn("""
                        next (n)\t\t-\t\ttraverses to the next month of transactions
                        previous (p)\t-\t\ttraverses to the previous month of transactions
                        sort (s)\t\t-\t\tsorts the transactions in different ways
                        balance (p)\t\t-\t\tcalculates the balance of the log being viewed
                        goals (g)\t\t-\t\tdisplays all goals based on month being viewed
                        quit (q)\t\t-\t\tgoes back to the main menu
                        help (h)\t\t-\t\tdisplays this command list
                        """, Console.TextColor.PURPLE);
    }

    public static void displayInvalidCommand() {
        Console.writeLn("Invalid command! Please enter a valid command.", Console.TextColor.RED);
    }

    public static void displayBalance(float balance) {
        Console.write("\nBalance: ", Console.TextColor.PURPLE);
        Console.writeLn(String.format("$%,.2f", balance), Console.TextColor.GREEN);
        Console.getStringInput("(Press enter to return)", true, Console.TextColor.CYAN);
    }

    public static void displayGoalHeader() {
        Console.writeLn("");
    }

    public static void displayGoal(Goal goal, float spendingTotal) {
        Console.writeLn(goal.toString(), Console.TextColor.YELLOW);
        Console.writeLn("Status: " + String.format("$%,.2f",spendingTotal) + "/" + String.format("$%,.2f",goal.getGoalAmount()) + '\n', Console.TextColor.YELLOW);
    }
}
