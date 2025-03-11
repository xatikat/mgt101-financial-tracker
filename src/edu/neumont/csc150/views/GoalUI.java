package edu.neumont.csc150.views;

import edu.neumont.csc150.models.Goal;

import java.util.List;

public class GoalUI {

    public static void displayGoals(List<Goal> goalLog) {
        Console.writeLn("Existing Goals:", Console.TextColor.PURPLE);
        for (Goal goal : goalLog) {
            Console.writeLn(goal.toString(), Console.TextColor.YELLOW);
        }
        Console.writeLn("");
    }

    //region GOAL MENU

    public static String getGoalMenuInput() {
        while(true) {
            String choiceS = Console.getStringInput("Enter a command (type help for available commands):", false, Console.TextColor.BLUE).toLowerCase();
            Console.writeLn("");
            switch (choiceS) {
                case "add", "a", "create" -> {return "add";}
                case "remove", "r", "delete", "del" -> {return "remove";}
                case "quit", "q" -> {return "quit";}
                case "help", "h" -> displayAvailableCommands();
                default -> displayInvalidCommand();
            }
        }
    }

    private static void displayInvalidCommand() {
        Console.writeLn("Invalid command! Please type help for a list of commands.", Console.TextColor.RED);
    }

    private static void displayAvailableCommands() {
        Console.writeLn("Available Commands:", Console.TextColor.BLUE);
        Console.writeLn("""
                        add (a)\t\t-\t\ttraverses to the next month of transactions
                        remove (r)\t-\t\ttraverses to the previous month of transactions
                        quit (q)\t\t-\t\tgoes back to the main menu
                        help (h)\t\t-\t\tdisplays this command list
                        """, Console.TextColor.PURPLE);
    }

    //endregion

    //region GOAL CREATION INPUTS

    public static String getGoalNameInput() {
        String input = Console.getStringInput("Enter the goal name:", false, Console.TextColor.BLUE);
        Console.writeLn("");
        return input;
    }

    public static float getGoalAmountInput() {
        float input = Console.getFloatInput("Enter the goal amount:", 0, Float.MAX_VALUE, Console.TextColor.BLUE);
        Console.writeLn("");
        return input;
    }

    public static String getGoalCatInput() {
        String choice = Console.getStringInput("Enter transaction category (Type list for valid categories, leave blank for none):", true, Console.TextColor.BLUE).toLowerCase();
        Console.writeLn("");
        return choice;
    }

    //endregion

    public static int getGoalIndexInput(int size) {
        int input = Console.getIntInput("Enter the goal to remove (0 - " + size + "):", 0, size, Console.TextColor.BLUE);
        Console.writeLn("");
        return input;
    }

    public static void displayNoGoals() {
        Console.writeLn("There are no existing goals to be deleted.", Console.TextColor.RED);
    }
}
