package com.sangeet.project.model;

import java.util.List;

public class ExactExpense extends Expense{
    public ExactExpense(Double expenseAmount,
                        User paidBy,
                        List<Split> splitAmongUsers,
                        ExpenseMetadata expenseMetadata) {
        super(expenseAmount, paidBy, splitAmongUsers, expenseMetadata);
    }

    @Override
    public boolean validateExpense() {
        for(Split split: getSplitAmongUsers()){
            if(!(split instanceof ExactSplit)){
                return false;
            }
        }

        Double totalAmount = getExpenseAmount();
        Double splitAmongUserSumAmount = 0.;
        for(Split split: getSplitAmongUsers()){
            splitAmongUserSumAmount += ((ExactSplit)split).getAmount();
        }

        return splitAmongUserSumAmount.equals(totalAmount);
    }
}
