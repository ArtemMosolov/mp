import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8 {

    public static void main(String[] args) {

        //task_1();
        //task_2();
        //task_3();
        //task_4();
        task_5();

    }


    private static void task_1() {

        List<User> userList = TestData.getTestData();

//        створити мапу де ключем буде імя і заченням буде сам ліст юзерів з таким імям

        userList.stream()
                .collect(Collectors.groupingBy(User::getNick, Collectors.mapping(User::getNick, Collectors.toList())))
                .forEach((k, v) -> System.out.println(k + " " + v));

//        RESULT
//        Dura [Dura]
//        Olia [Olia]
//        Petia [Petia, Petia]
//        Vika [Vika]
//        Katia [Katia]
//        Dima [Dima]
//        Vasia [Vasia, Vasia]
    }

    private static void task_2() {

        List<User> userList = TestData.getTestData();

//        	отримати ліст юзерів яким більше 18 років

        userList.stream()
                .filter(userAge -> userAge.getAge() > 18)
                .collect(Collectors.toList())
                .forEach(System.out::println);

//        RESULT
//        User{id=1, nick='Vasia', age=22, sex=FEMALE}
//        User{id=2, nick='Vasia', age=23, sex=MALE}
//        User{id=3, nick='Vasia', age=24, sex=ANIMAL}
//        User{id=4, nick='Petia', age=44, sex=FEMALE}
//        User{id=5, nick='Petia', age=33, sex=MALE}
//        User{id=6, nick='Petia', age=22, sex=ANIMAL}
    }

    private static void task_3() {

        List<User> userList = TestData.getTestData();

//        погрупувати юзерів по віку: мапа де ключем є вік а занченням ліст імен + тільки неповнолітні юзери

//        usersMap.entrySet().stream()
//                .map(Map.Entry::getValue)
//                .flatMap(List::stream)
//                .filter(user -> user.getAge() < 18)
//                .collect(Collectors.groupingBy(User::getAge,
//                        Collectors.mapping(User::getAge, Collectors.toList())));

        // what return type ?
        // http://www.mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/

        userList.stream()
                .filter(user -> user.getAge() < 18)
                .collect(Collectors.groupingBy(User::getAge, Collectors.mapping(User::getNick, Collectors.toList())))
                .forEach((k, v) -> System.out.println(k + " " + v));

//        RESULT :
//        17 [Olia]
//        5 [Vasia, Typa]
//        8 [Petia, Java]
//        10 [Katia]

    }

    private static void task_4() {

        List<User> userList = TestData.getTestData();

//        погрупувати юзерів по статі і віку: мапа де ключем є стать а значенням ліст мапів де кллючем є вік а значенням ліст імен

        userList.stream()
                .collect(Collectors.groupingBy(User::getSex,
                        Collectors.groupingBy(User::getAge,
                                Collectors.mapping(User::getNick, Collectors.toList()))))
        .forEach((k, v) -> System.out.println(k + " " + v));


//        usersMap.entrySet().stream()
//                .map(a -> a.getValue())
//                .flatMap(b -> b.stream())
//                .map(c -> c.entrySet())
//                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

//        RESULT
//        ANIMAL {19=[Dima], 8=[Petia], 10=[Katia]}
//        MALE {18=[Vika], 5=[Vasia], 8=[Petia]}
//        FEMALE {17=[Olia], 20=[Dura], 5=[Vasia]}

    }

    private static void task_5() {

        List<User> userList = TestData.getTestData();

        //        сформувати мапу де ключем буде юзер а валюшкою список його непогашених кредитів + тільки для повнолітніх юзерів

//        userList.stream()
//                .filter(user -> user.getAge() > 18)
//                .collect(Collectors.groupingBy(User::getNick, Collectors.mapping(User::getCredit, Collectors.toList())))
//                .forEach((k, v) -> System.out.println(k + " " + v));

        userList.stream()
                .filter(user -> user.getAge() > 18)
                .map(user -> {
                    User userWithDebt = user;
                    userWithDebt.setCredit(user.getCredit().stream()
                            .filter(balance -> balance.getBalance() > 0)
                            .collect(Collectors.toList()));
                    return userWithDebt;
                })
                .collect(Collectors.groupingBy(Function.identity(),  Collectors.mapping(User::getCredit, Collectors.toList())))
                .forEach((k, v) -> System.out.println(k + " " + v));

//        RESULT
//        User{id=7, nick='Dura', age=20, sex=FEMALE, credit=[Credit{balance=1500}, Credit{balance=500}, Credit{balance=700}]} [[Credit{balance=1500}, Credit{balance=500}, Credit{balance=700}]]
//        User{id=6, nick='Dima', age=19, sex=ANIMAL, credit=[]} [[]]

    }

}

