package com.sangeet.project;

import com.sangeet.project.controller.ExpenseController;
import com.sangeet.project.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SplitwiseLldApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test(){

		//https://workat.tech/machine-coding/article/how-to-design-splitwise-machine-coding-ayvnfo1tfst6

		ExpenseController expenseController = new ExpenseController();

		User u1 = new User("User1", "user1@splitwiselld.tech", "9988776655");
		User u2 = new User("User2", "user2@splitwiselld.tech", "5566778899");
		User u3 = new User("User3", "user3@splitwiselld.tech", "1122334455");
		User u4 = new User("User4", "user4@splitwiselld.tech", "5544332211");

		expenseController.addUser(u1);
		expenseController.addUser(u2);
		expenseController.addUser(u3);
		expenseController.addUser(u4);

		Expense generatedExpense = null;

//		System.out.println("## Equal expense");
//		generatedExpense = expenseController.createExpense(
//				ExpenseType.EQUAL, 5000.,
//				u1.getId(),
//				Arrays.asList(new EqualSplit(u1),
//						new EqualSplit(u2),
//						new EqualSplit(u3),
//						new EqualSplit(u4)),
//				new ExpenseMetadata("Testing if equal is split of amount is working correctly or not")
//		);
//		System.out.println();
//
//		System.out.println("## Expense history");
//		System.out.println(expenseController.expenseHistory(generatedExpense.getId()));


		System.out.println("## All balance");
		expenseController.showAllBalances().
				stream().forEach(s -> System.out.println(s));
		System.out.println();

		System.out.println("## User 1 balance");
		expenseController.passbookOfUser(u1.getId()).
				stream().forEach(s -> System.out.println(s));
		System.out.println();

		System.out.println("## Equal expense");
		generatedExpense = expenseController.createExpense(
			ExpenseType.EQUAL, 1000.,
				u1.getId(),
				Arrays.asList(new EqualSplit(u1),
						new EqualSplit(u2),
						new EqualSplit(u3),
						new EqualSplit(u4)),
				new ExpenseMetadata("Electricity bill")
		);
		System.out.println();

		System.out.println("## Expense history");
		System.out.println(expenseController.expenseHistory(generatedExpense.getId()));

		System.out.println("## User 1 balance");
		expenseController.passbookOfUser(u1.getId()).
				stream().forEach(s -> System.out.println(s));
		System.out.println();

		System.out.println("## All balance");
		expenseController.showAllBalances().
				stream().forEach(s -> System.out.println(s));
		System.out.println();

		System.out.println("## Exact expense");
		generatedExpense = expenseController.createExpense(
				ExpenseType.EXACT,
				1250.,
				u1.getId(),
				Arrays.asList(new ExactSplit(u2, 370.), new ExactSplit(u3, 880.)),
				new ExpenseMetadata("Shopping bill")
		);
		System.out.println();

		System.out.println("## Expense history");
		System.out.println(expenseController.expenseHistory(generatedExpense.getId()));

		System.out.println("## User 1 balance");
		expenseController.passbookOfUser(u1.getId()).
				stream().forEach(s -> System.out.println(s));
		System.out.println();

		System.out.println("## All balance");
		expenseController.showAllBalances().
				stream().forEach(s -> System.out.println(s));
		System.out.println();

		System.out.println("## Percentage expense");
		generatedExpense = expenseController.createExpense(
				ExpenseType.PERCENTAGE, 1200.,
				u4.getId(),
				Arrays.asList(new PercentageSplit(u1, 40.),
						new PercentageSplit(u2, 20.),
						new PercentageSplit(u3, 20.),
						new PercentageSplit(u4, 20.)),
				new ExpenseMetadata("Party bill (user 1 + his brother, user 2, user 3, user 4)")
		);
		System.out.println();

		System.out.println("## Expense history");
		System.out.println(expenseController.expenseHistory(generatedExpense.getId()));

		System.out.println("## User 4 balance");
		expenseController.passbookOfUser(u4.getId()).
				stream().forEach(s -> System.out.println(s));
		System.out.println();

		System.out.println("## All balance");
		expenseController.showAllBalances().
				stream().forEach(s -> System.out.println(s));
		System.out.println();

		System.out.println("## Equal expense with invalid split strategy");
		Exception exception = assertThrows(RuntimeException.class, () -> {
			expenseController.createExpense(
					ExpenseType.EQUAL, 2000.,
					u1.getId(),
					Arrays.asList(new PercentageSplit(u1, 40.), //INVALID SPLIT STRATEGY
							new EqualSplit(u2),
							new ExactSplit(u3, 3000.), //INVALID SPLIT STRATEGY
							new EqualSplit(u4)),
					new ExpenseMetadata("Invalid strategy check Req all EqualSplit found PercentageSplit & ExactSplit")
			);
		});

		String actualMessage = exception.getMessage();
		System.out.println("Exception: Equal expense with invalid split strategy: "+actualMessage);
		assertTrue(actualMessage.contains("Exception = InvalidExpenseException"));

		System.out.println("## Exact expense with splited amount different from total expense amount");
		exception = assertThrows(RuntimeException.class, () -> {
			expenseController.createExpense(
					ExpenseType.EXACT,
					2000.,
					u1.getId(),
					Arrays.asList(new ExactSplit(u2, 1500.),
							new ExactSplit(u3, 1500.)),
					new ExpenseMetadata("Splited amount different from total expense amount") //2000.0 != 1500.0 + 1500.0
			);
		});

		actualMessage = exception.getMessage();
		System.out.println("Exception: Equal expense with invalid split strategy: "+actualMessage);
		assertTrue(actualMessage.contains("Exception = InvalidExpenseException"));

		System.out.println("## Percentage expense with percentage factor not making up to 100%");
		exception = assertThrows(RuntimeException.class, () -> {
			expenseController.createExpense(
					ExpenseType.PERCENTAGE, 1200.,
					u4.getId(),
					Arrays.asList(new PercentageSplit(u1, 50.),
							new PercentageSplit(u2, 30.),
							new PercentageSplit(u3, 20.),
							new PercentageSplit(u4, 20.)),
					new ExpenseMetadata("Percentage factors not making up to 100%") //50+30+20+20 != 100
			);
		});

		actualMessage = exception.getMessage();
		System.out.println("Exception: Equal expense with invalid split strategy: "+actualMessage);
		assertTrue(actualMessage.contains("Exception = InvalidExpenseException"));

	}

}
