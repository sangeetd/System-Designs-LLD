package com.sangeet.project.model;

import java.util.List;
import java.util.UUID;

public abstract class Expense {

    private String id;
    private Double expenseAmount;
    private User paidBy;
    private List<Split> splitAmongUsers;
    private ExpenseMetadata expenseMetadata;

    public Expense(Double expenseAmount,
                   User paidBy,
                   List<Split> splitAmongUsers,
                   ExpenseMetadata expenseMetadata) {
        this.id = UUID.randomUUID().toString();
        this.expenseAmount = expenseAmount;
        this.paidBy = paidBy;
        this.splitAmongUsers = splitAmongUsers;
        this.expenseMetadata = expenseMetadata;
    }

    public String getId() {
        return id;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplitAmongUsers() {
        return splitAmongUsers;
    }

    public ExpenseMetadata getExpenseMetadata() {
        return expenseMetadata;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public void setSplitAmongUsers(List<Split> splitAmongUsers) {
        this.splitAmongUsers = splitAmongUsers;
    }

    public void setExpenseMetadata(ExpenseMetadata expenseMetadata) {
        this.expenseMetadata = expenseMetadata;
    }

    public abstract boolean validateExpense();

}
