package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Goal;
import edu.neumont.csc150.models.enums.TransactionCategory;
import edu.neumont.csc150.views.AppUI;
import edu.neumont.csc150.views.GoalUI;
import edu.neumont.csc150.views.TransactionUI;


import java.util.List;

public class GoalController {
    public static void viewGoalMenu(List<Goal> goalLog) {
        boolean doesContinue = true;
        AppUI.displayHR();
        while (doesContinue) {
            GoalUI.displayGoals(goalLog);
            switch (GoalUI.getGoalMenuInput()) {
                case "add" -> goalLog.add(createGoal());
                case "remove" -> {
                    if (!goalLog.isEmpty()) {
                        goalLog.remove(GoalUI.getGoalIndexInput(goalLog.size()));
                    } else {
                        GoalUI.displayNoGoals();
                    }
                }
                case "quit" -> doesContinue = false;
            }
        }
    }

    /**
     * Create a goal with all 3 parameters (category can be null)
     * @return Created goal
     */
    private static Goal createGoal() {
        String goalName = GoalUI.getGoalNameInput();
        float goalAmount = GoalUI.getGoalAmountInput();
        // reusing the category selector :p
        TransactionCategory goalCategory = getGoalCat();

        return new Goal(goalName, goalAmount, goalCategory);
    }

    /**
     * Allows user to select goal category (or use none)
     * @return TransactionCategory that the goal will abide by
     */
    private static TransactionCategory getGoalCat() {
        do {
            String categoryS = GoalUI.getGoalCatInput();
            try {
                return TransactionCategory.valueOf(categoryS.toUpperCase());
            } catch (IllegalArgumentException e) {
                // check if user wanted to list categories
                if (categoryS.equals("list")) {
                    TransactionUI.displayValidCategories();
                } else if (categoryS.isBlank()) {
                    return null;
                } else {
                    // invalid category
                    TransactionUI.displayInvalidCategory();
                }
            }
        } while (true);
    }
}
