import java.util.*;
import java.util.stream.Collectors;

public class Java7 {

    public static void main(String[] args) {

        //task_1();
        //task_2();
        //task_3();
        task_4();

    }

    private static void task_1() {

        List<User> userList = TestData.getTestData();

//        створити мапу де ключем буде імя і заченням буде сам ліст юзерів з таким імям

        Map<String, List<User>> userMap = new HashMap<>();

        for(User user : userList) {
            String userName = user.getNick();
            if(userMap.containsKey(userName)) {
                userMap.get(userName).add(user);
            } else {
                userMap.put(userName, new LinkedList<>(Arrays.asList(user)));
            }
        }

        //userMap.forEach((k, v) -> System.out.println(k + " " + v));
        for(String key : userMap.keySet()) {
            System.out.println(key + " " + userMap.get(key));
        }

//        RESULT
//        Petia [User{id=2, nick='Petia', age=8, sex=MALE, credit=null}, User{id=9, nick='Petia', age=8, sex=ANIMAL, credit=null}]
//        Olia [User{id=4, nick='Olia', age=17, sex=FEMALE, credit=null}]
//        Dura [User{id=7, nick='Dura', age=20, sex=FEMALE, credit=null}]
//        Vika [User{id=5, nick='Vika', age=18, sex=MALE, credit=null}]
//        Katia [User{id=3, nick='Katia', age=10, sex=ANIMAL, credit=null}]
//        Vasia [User{id=1, nick='Vasia', age=5, sex=FEMALE, credit=null}, User{id=8, nick='Vasia', age=5, sex=MALE, credit=null}]
//        Dima [User{id=6, nick='Dima', age=19, sex=ANIMAL, credit=null}]
    }

    private static void task_2() {

        List<User> userList = TestData.getTestData();

//        	отримати ліст юзерів яким більше 18 років

        List<User> adultUserList = new LinkedList<>();

        for (User user : userList) {
            if(user.getAge() > 18) {
                adultUserList.add(user);
            }
        }

        for (User user : adultUserList) {
            System.out.println(user);
        }

//        RESULT
//        User{id=6, nick='Dima', age=19, sex=ANIMAL, credit=null}
//        User{id=7, nick='Dura', age=20, sex=FEMALE, credit=null}
    }

    private static void task_3() {

        List<User> userList = TestData.getTestData();

//        погрупувати юзерів по віку: мапа де ключем є вік а занченням ліст імен + тільки неповнолітні юзери

        List<User> adultUserList = new LinkedList<>();

        for (User user : userList) {
            if(user.getAge() < 18) {
                adultUserList.add(user);
            }
        }

        Map<Integer, List<User>> userMap = new HashMap<>();

        for(User user : adultUserList) {
            Integer userAge = user.getAge();
            if(userMap.containsKey(userAge)) {
                userMap.get(userAge).add(user);
            } else {
                userMap.put(userAge, new LinkedList<>(Arrays.asList(user)));
            }
        }

        for(Integer key : userMap.keySet()) {
            System.out.println(key + " " + userMap.get(key));
        }



//        RESULT :
//        17 [User{id=4, nick='Olia', age=17, sex=FEMALE, credit=null}]
//        5 [User{id=1, nick='Vasia', age=5, sex=FEMALE, credit=null}, User{id=8, nick='Vasia', age=5, sex=MALE, credit=null}]
//        8 [User{id=2, nick='Petia', age=8, sex=MALE, credit=null}, User{id=9, nick='Petia', age=8, sex=ANIMAL, credit=null}]
//        10 [User{id=3, nick='Katia', age=10, sex=ANIMAL, credit=null}]

    }

    private static void task_4() {

        List<User> userList = TestData.getTestData();

//        погрупувати юзерів по статі і віку: мапа де ключем є стать а значенням ліст мапів де кллючем є вік а значенням ліст імен

//        userList.stream()
//                .collect(Collectors.groupingBy(User::getSex,
//                        Collectors.groupingBy(User::getAge,
//                                Collectors.mapping(User::getNick, Collectors.toList()))))
//                .forEach((k, v) -> System.out.println(k + " " + v));

        Set<SEX> sex = new HashSet<>();
        for(User user : userList) {
            sex.add(user.getSex());
        }
        List<SEX> gender = new ArrayList<>(sex);

        Map<Integer, List<User>> userMap = new HashMap<>();

        for(User user : userList) {
            Integer userAge = user.getAge();
            if(userMap.containsKey(userAge)) {
                userMap.get(userAge).add(user);
            } else {
                userMap.put(userAge, new LinkedList<>(Arrays.asList(user)));
            }
        }

        Map<SEX, List<Map<Integer, List<String>>>> resultMap  = new HashMap<>();

        for(Integer key : userMap.keySet()) {
            List<User> users = userMap.get(key);
            for (User usr : users) {
                SEX userSex = usr.getSex();
                if (resultMap.containsKey(userSex)) {
                    List<Map<Integer, List<String>>> mapList = resultMap.get(userSex);
                    if(mapList != null) {
                        for (Map<Integer, List<String>> userAges : mapList) {
                            for (Integer age : userMap.keySet()) {
                                List<String> usList = userAges.get(age);
                                if(usList != null) {
                                    usList.add(usr.getNick());
                                } else {
                                    usList = new ArrayList<>();
                                    usList.add(usr.getNick());
                                    userAges.put(age, usList);
                                }
                            }
                        }
                    } else {
                        resultMap.put(userSex, new ArrayList<Map<Integer, List<String>>>());
                    }
                } else {
                    List<String> names = new ArrayList<>();
                    Map<Integer, List<String>> ages = new HashMap<>();
                    ages.put(usr.getAge(), names);
                    List<Map<Integer, List<String>>> list = new ArrayList<>();
                    list.add(ages);
                    resultMap.put(userSex, list);
                }
            }
        }

        for(SEX userSex : resultMap.keySet()) {
            List<Map<Integer, List<String>>> listOfAges = resultMap.get(userSex);
            for(Map<Integer, List<String>> listOfMaps : listOfAges) {
                for(Integer ages : listOfMaps.keySet()) {
                    List<String> names = listOfMaps.get(ages);
                    for(String name : names) {
                        System.out.println(userSex + " " + name);
                    }
                }
             }
        }


//        RESULT
//        ANIMAL {19=[Dima], 8=[Petia], 10=[Katia]}
//        MALE {18=[Vika], 5=[Vasia], 8=[Petia]}
//        FEMALE {17=[Olia], 20=[Dura], 5=[Vasia]}

    }

}
