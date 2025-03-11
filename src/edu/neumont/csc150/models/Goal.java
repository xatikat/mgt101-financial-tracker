package edu.neumont.csc150.models;

import edu.neumont.csc150.models.enums.TransactionCategory;

public class Goal {
    private String goalName;
    private float goalAmount;
    private TransactionCategory goalCategory;

    public Goal(String goalName, float goalAmount, TransactionCategory goalCategory) {
        setGoalName(goalName);
        setGoalAmount(goalAmount);
        setGoalCategory(goalCategory);
    }

    //region GETTERS / SETTERS
    public String getGoalName() {
        return this.goalName;
    }
    private void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public float getGoalAmount() {
        return this.goalAmount;
    }
    private void setGoalAmount(float goalAmount) {
        this.goalAmount = goalAmount;
    }

    public TransactionCategory getGoalCategory() {
        return this.goalCategory;
    }
    private void setGoalCategory(TransactionCategory goalCategory) {
        this.goalCategory = goalCategory;
    }
    //endregion

    /**
     * Returns formatted amount as $
     * @return Formatted String
     */
    public String getGoalAmountString() {
        return String.format("$%,.2f", getGoalAmount());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getGoalName()).append("\n");
        sb.append("Amount: ").append(getGoalAmountString()).append("\n");
        if (getGoalCategory() == null) {
            sb.append("Category: None\n");
        } else {
            sb.append("Category: ").append(getGoalCategory());
        }
        return sb.toString();
    }
}
