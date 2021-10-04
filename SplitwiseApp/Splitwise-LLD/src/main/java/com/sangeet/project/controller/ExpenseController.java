package com.sangeet.project.controller;

import com.sangeet.project.model.*;
import com.sangeet.project.service.ExpenseFactoryService;

import java.util.*;

public class ExpenseController {

    private List<Expense> expenseList;
    private Map<String, User> userMap;
    private Map<String, Map<String, Double>> expenseSheet;

    public ExpenseController() {
        expenseList = new ArrayList<>();
        userMap = new HashMap<>();
        expenseSheet = new HashMap<>();
    }

    public void addUser(User user) {

        this.userMap.put(user.getId(), user);
        this.expenseSheet.putIfAbsent(user.getId(), new HashMap<>());
    }

    public Expense createExpense(ExpenseType type,
                                 Double expenseAmount,
                                 String paidBy,
                                 List<Split> splitAmongUsers,
                                 ExpenseMetadata expenseMetadata) {

        Expense expense = ExpenseFactoryService.createExpense(
                type,
                expenseAmount,
                this.userMap.get(paidBy),
                splitAmongUsers,
                expenseMetadata);

        if (!expense.validateExpense()) {
            //not a valid expense as per the inputs provide
            throw new RuntimeException("Exception = InvalidExpenseException: Expense is not valid, kindly check your inputs");
        }

        this.expenseList.add(expense);
        for (Split split : expense.getSplitAmongUsers()) {

            String lendingTo = split.getUser().getId();
            Map<String, Double> paidByUserWantsMoneyFrom = this.expenseSheet.get(paidBy);
            paidByUserWantsMoneyFrom.put(lendingTo,
                    paidByUserWantsMoneyFrom.getOrDefault(lendingTo, 0.) + split.getAmount());

            Map<String, Double> usersReturnMoneyToPaidByUser = this.expenseSheet.get(lendingTo);
            usersReturnMoneyToPaidByUser.put(paidBy,
                    usersReturnMoneyToPaidByUser.getOrDefault(paidBy, 0.) - split.getAmount());

        }
        return expense;
    }

    public List<String> passbookOfUser(String id) {
        boolean isEmpty = true;
        List<String> passbook = new ArrayList<>();
        for (Map.Entry<String, Double> expenseSheetOfUser :
                this.expenseSheet.getOrDefault(id, new HashMap<>()).entrySet()) {

            if (expenseSheetOfUser.getValue() != 0) {
                isEmpty = false;
                passbook.add(this.userMap.get(id).getName() + " | " +
                        balances(id, expenseSheetOfUser.getKey(), expenseSheetOfUser.getValue()));
            }
        }

        if (isEmpty) {
            passbook.add(this.userMap.get(id).getName() + " | No balance");
        }
        return passbook;
    }

    public List<String> showAllBalances() {
        boolean isEmpty = true;
        List<String> passbook = new ArrayList<>();
        for (Map.Entry<String, Map<String, Double>> allBalances : this.expenseSheet.entrySet()) {
            isEmpty = true;
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    isEmpty = false;
                    passbook.add(this.userMap.get(allBalances.getKey()).getName() + " | " +
                            balances(allBalances.getKey(), userBalance.getKey(), userBalance.getValue()));
                }
            }

            if (isEmpty) {
                passbook.add(this.userMap.get(allBalances.getKey()).getName() + " | No balance");
            }
        }
        return passbook;
    }

    private String balances(String user1, String user2, double amount) {
        String user1Name = this.userMap.get(user1).getName();
        String user2Name = this.userMap.get(user2).getName();
        StringBuilder sb = new StringBuilder();
        if (amount < 0) {
            sb.append(user1Name)
                    .append(" owes ")
                    .append(user2Name)
                    .append(" : ")
                    .append(Math.abs(amount));
        } else if (amount > 0) {
            sb.append(user2Name)
                    .append(" owes ")
                    .append(user1Name)
                    .append(" : ")
                    .append(Math.abs(amount));
        }
        return sb.toString();
    }

    public String expenseHistory(String expenseId) {

        Optional<Expense> optionalExpense = this.expenseList.stream()
                .filter(expense -> expense.getId().equals(expenseId))
                .findFirst();

        if (!optionalExpense.isPresent()) {
            throw new RuntimeException("Exception = InvalidExceptionId: Expense id not found the history: " + expenseId);
        }

        Expense expense = optionalExpense.get();
        StringBuilder sb = new StringBuilder();
        sb.append(expense.getId() + "\n")
                .append(expense.getExpenseMetadata().getName() + "\n")
                .append(expense.getExpenseAmount() + " : ")
                .append(this.userMap.get(expense.getPaidBy().getId()).getName() + "\n");
        for (String passbookData : passbookOfUser(expense.getPaidBy().getId())) {
            sb.append(passbookData).append("\n");
        }
        return sb.toString();
    }
}
