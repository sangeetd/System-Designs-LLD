package com.sangeet.project.model;

import java.util.List;

public class PercentageExpense extends Expense{

    public PercentageExpense(Double expenseAmount, User paidBy, List<Split> splitAmongUsers, ExpenseMetadata expenseMetadata) {
        super(expenseAmount, paidBy, splitAmongUsers, expenseMetadata);
    }

    @Override
    public boolean validateExpense() {
        for(Split split: getSplitAmongUsers()){
            if(! (split instanceof PercentageSplit) ){
                return false;
            }
        }

        Double totalPercentage = 100.;
        Double splitAmongUserPercentage = 0.;
        for(Split split: getSplitAmongUsers()){
            splitAmongUserPercentage += ((PercentageSplit)split).getPercentage();
        }

        return splitAmongUserPercentage.equals(totalPercentage);
    }
}
