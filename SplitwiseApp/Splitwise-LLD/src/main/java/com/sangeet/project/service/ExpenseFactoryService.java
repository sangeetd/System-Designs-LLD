package com.sangeet.project.service;

import com.sangeet.project.model.*;

import java.util.List;

public class ExpenseFactoryService {

    public static Expense createExpense(ExpenseType type,
                                        Double amount,
                                        User paidBy,
                                        List<Split> splitAmongUsers,
                                        ExpenseMetadata expenseMetadata) {
        switch (type) {
            case EXACT:
                return new ExactExpense(amount, paidBy, splitAmongUsers, expenseMetadata);
            case EQUAL:
                Integer splitSize = splitAmongUsers.size();
                Double equalSplitAmount = ((double) Math.round(amount * 100 / splitSize)) / 100.;
                for (Split split : splitAmongUsers) {
                    split.setAmount(equalSplitAmount);
                }
                //splitAmongUsers.get(0).setAmount(equalSplitAmount + (amount - equalSplitAmount * splitSize));
                return new EqualExpense(amount, paidBy, splitAmongUsers, expenseMetadata);
            case PERCENTAGE:
                for (Split split : splitAmongUsers) {
                    PercentageSplit percentageSplit = (PercentageSplit) split;
                    split.setAmount((amount * percentageSplit.getPercentage()) / 100.0);
                }
                return new PercentageExpense(amount, paidBy, splitAmongUsers, expenseMetadata);
        }
        return null;
    }

}
