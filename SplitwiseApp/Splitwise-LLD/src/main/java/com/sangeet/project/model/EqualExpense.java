package com.sangeet.project.model;

import java.util.List;

public class EqualExpense extends Expense{

    public EqualExpense(Double expenseAmount, User paidBy, List<Split> splitAmongUsers, ExpenseMetadata expenseMetadata) {
        super(expenseAmount, paidBy, splitAmongUsers, expenseMetadata);
    }

    @Override
    public boolean validateExpense() {
        for(Split split: getSplitAmongUsers()){
            if(! (split instanceof EqualSplit) ){
                return false;
            }
        }
        return true;
    }
}
