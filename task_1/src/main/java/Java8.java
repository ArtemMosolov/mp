import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8 {

    public static void main(String[] args) {

        //task_1();
        //task_2();
        //task_3();
        //task_4();
        //task_5();

    }


    private static void task_1() {

        List<User> userList = TestData.getTestData();

//        створити мапу де ключем буде імя і заченням буде сам ліст юзерів з таким імям

        userList.stream()
                .collect(Collectors.groupingBy(User::getNick, Collectors.mapping(User::getNick, Collectors.toList())))
                .forEach((k, v) -> System.out.println(k + " " + v));

//        RESULT
//        Petia [Petia, Petia, Petia]
//        Katia [Katia, Katia, Katia]
//        Vasia [Vasia, Vasia, Vasia]
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



    }

    private static void task_5() {

        Map<String, List<User>> usersMap = new LinkedHashMap<String, List<User>>();

        usersMap.put("Vasia", Arrays.asList(
                new User(1L, "Vasia", 22, SEX.FEMALE, new Credit(1L, new BigDecimal(234234))),
                new User(2L, "Vasia", 23, SEX.MALE, new Credit(2L, new BigDecimal(4563))),
                new User(3L, "Vasia", 24, SEX.ANIMAL, new Credit(3L, new BigDecimal(324234)))
        ));

        usersMap.put("Petia", Arrays.asList(
                new User(4L, "Petia", 44, SEX.FEMALE, new Credit(4L, new BigDecimal(345645))),
                new User(5L, "Petia", 33, SEX.MALE, new Credit(5L, new BigDecimal(456346))),
                new User(6L, "Petia", 22, SEX.ANIMAL, new Credit(6L, new BigDecimal(456346)))
        ));

        usersMap.put("Katia", Arrays.asList(
                new User(7L, "Katia", 1, SEX.FEMALE, new Credit(7L, new BigDecimal(43563))),
                new User(8L, "Katia", 2, SEX.MALE, new Credit(8L, new BigDecimal(34563))),
                new User(9L, "Katia", 3, SEX.ANIMAL, new Credit(9L, new BigDecimal(6436345)))
        ));

//        сформувати мапу де ключем буде юзер а валюшкою список його непогашених кредитів + тільки для повнолітніх юзерів

        usersMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap(List::stream)
                .filter(user -> user.getAge() > 18)
                .collect(Collectors.groupingBy(User::getNick, Collectors.mapping(User::getCredit, Collectors.toList())))
                .forEach((k, v) -> System.out.println(k + " " + v));

//        RESULT
//        Petia [Credit{id=4, debt=345645}, Credit{id=5, debt=456346}, Credit{id=6, debt=456346}]
//        Vasia [Credit{id=1, debt=234234}, Credit{id=2, debt=4563}, Credit{id=3, debt=324234}]

    }

}

