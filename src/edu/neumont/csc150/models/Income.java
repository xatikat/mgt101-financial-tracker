package edu.neumont.csc150.models;

public class Income extends Transaction {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type:\t\t\t\tIncome\n");
        sb.append(super.toString());
        return sb.toString();
    }
}
