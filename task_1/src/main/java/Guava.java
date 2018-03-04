import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;

public class Guava {

	public static void main(String[] args) {

		// task_1();
		// task_2();
		 // task_3();
		// task_4();

	}

	private static void task_1() {
		List<User> userList = TestData.getTestData();

		ImmutableListMultimap<String, User> users = Multimaps.index(userList, new Function<User, String>() {
			@Override
			public String apply(User from) {
				return from.getNick();
			}
		});
		Map<String, List<User>> map = Multimaps.asMap(users);
	}

	private static void task_2() {
		List<User> userList = TestData.getTestData();

		Predicate<User> adultsPredicate = new Predicate<User>() {
			@Override
			public boolean apply(User input) {
				return input.getAge() > 18;
			}
		};
		List<User> adultUserList = Lists.newArrayList(Collections2.filter(userList, adultsPredicate));
	}

	private static void task_3() {

		List<User> userList = TestData.getTestData();

		Predicate<User> notAdultsPredicate = new Predicate<User>() {
			@Override
			public boolean apply(User input) {
				return input.getAge() < 18;
			}
		};
		
		userList = Lists.newArrayList(Collections2.filter(userList, notAdultsPredicate));
		
		ImmutableListMultimap<Integer, User> users = Multimaps.index(userList, new Function<User, Integer>() {
			@Override
			public Integer apply(User from) {
				return from.getAge();
			}
		});
		Map<Integer, List<User>> map = Multimaps.asMap(users);
	}

	private static void task_4() {
		List<User> userList = TestData.getTestData();

		Predicate<User> adultsPredicate = new Predicate<User>() {
			@Override
			public boolean apply(User input) {
				return input.getAge() > 18;
			}
		};
		
		List<User> adultUserList = Lists.newArrayList(Collections2.filter(userList, adultsPredicate));
		
		Function<User, User> filterCredits = new Function<User, User>() {
			@Override
			public User apply(User input) {
				List<Credit> userDebt = input.getCredit();
				Predicate<Credit> positiveDebtPredicate = new Predicate<Credit>() {
					@Override
					public boolean apply(Credit input) {
						return input.getBalance() > 0;
					}
				};
				input.setCredit(Lists.newArrayList(Collections2.filter(userDebt, positiveDebtPredicate)));
				return input;
			}
		};
		
		List<User> usersWithPositiveDebt = Lists.newArrayList(Collections2.transform(adultUserList, filterCredits));
		
	}
}
