package edu.neumont.csc150.models;

public class Expense extends Transaction {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type:\t\t\t\tExpense\n");
        sb.append(super.toString());
        return sb.toString();
    }
}
