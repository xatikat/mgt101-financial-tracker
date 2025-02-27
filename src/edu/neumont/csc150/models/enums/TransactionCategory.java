package edu.neumont.csc150.models.enums;

public enum TransactionCategory {
    // main categories
    NEEDS, WANTS,
    SAVINGS, DEBTS,
    BILLS
    // subcategories (could add more)
    //GROCERIES, INSURANCE,
    //TRANSPORTATION
    ;

    @Override
    public String toString() {
        return switch (this) {
            case NEEDS -> "Needs";
            case WANTS -> "Wants";
            case SAVINGS -> "Savings";
            case DEBTS -> "Debts";
            //case INSURANCE -> "Insurance";
            //case GROCERIES -> "Groceries";
            //case TRANSPORTATION -> "Transportation";
            default -> "Bills";
        };
    }
}
