import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Java7 {

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

        Map<String, List<User>> userMap = new HashMap<>();

        for(User user : userList) {
            String userName = user.getNick();
            if(userMap.containsKey(userName)) {
                userMap.get(userName).add(user);
            } else {
                List<User> list = new ArrayList<>();
                list.add(user);
                userMap.put(userName, list);
            }
        }

        System.out.println(userMap);

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

        List<User> adultUserList = new ArrayList<>();

        for (User user : userList) {
            if(user.getAge() > 18) {
                adultUserList.add(user);
                System.out.println(user);
            }
        }

//        RESULT
//        User{id=6, nick='Dima', age=19, sex=ANIMAL, credit=null}
//        User{id=7, nick='Dura', age=20, sex=FEMALE, credit=null}
    }

    private static void task_3() {

        List<User> userList = TestData.getTestData();

//        погрупувати юзерів по віку: мапа де ключем є вік а занченням ліст імен + тільки неповнолітні юзери

        Map<Integer, List<User>> userMap = new HashMap<>();

        for(User user : userList) {
            Integer userAge = user.getAge();
            if(user.getAge() < 18) {
                if(userMap.containsKey(userAge)) {
                    userMap.get(userAge).add(user);
                } else {
                    List<User> list = new ArrayList<>();
                    list.add(user);
                    userMap.put(userAge, list);
                }
            }
        }

        System.out.println(userMap);

//        RESULT :
//        17 [User{id=4, nick='Olia', age=17, sex=FEMALE, credit=null}]
//        5 [User{id=1, nick='Vasia', age=5, sex=FEMALE, credit=null}, User{id=8, nick='Vasia', age=5, sex=MALE, credit=null}]
//        8 [User{id=2, nick='Petia', age=8, sex=MALE, credit=null}, User{id=9, nick='Petia', age=8, sex=ANIMAL, credit=null}]
//        10 [User{id=3, nick='Katia', age=10, sex=ANIMAL, credit=null}]

    }

    private static void task_4() {

        List<User> userList = TestData.getTestData();

//        погрупувати юзерів по статі і віку: мапа де ключем є стать а значенням ліст мапів де кллючем є вік а значенням ліст імен

        Map<SEX, Map<Integer, List<String>>> resultMap  = new HashMap<>();

        for(User user : userList) {
            Map<Integer, List<String>> genderMap = resultMap.get(user.getSex());
            if(genderMap == null) {
                genderMap = new HashMap<>();
                List<String> names = new ArrayList<>();
                names.add(user.getNick());
                genderMap.put(user.getAge(), names);
                resultMap.put(user.getSex(), genderMap);
            } else {
                List<String> names = genderMap.get(user.getAge());
                if(names == null) {
                    names = new ArrayList<>();
                    names.add(user.getNick());
                    genderMap.put(user.getAge(), names);
                } else {
                    genderMap.get(user.getAge()).add(user.getNick());
                }
            }
        }

        System.out.println(resultMap);

//        RESULT
//        ANIMAL {19=[Dima], 8=[Petia], 10=[Katia]}
//        MALE {18=[Vika], 5=[Vasia], 8=[Petia]}
//        FEMALE {17=[Olia], 20=[Dura], 5=[Vasia]}

    }

    private static void task_5() {

        List<User> userList = TestData.getTestData();

//        сформувати мапу де ключем буде юзер а валюшкою список його непогашених кредитів + тільки для повнолітніх юзерів


        Map<User, List<Credit>> resultMap = new HashMap<>();

        for(User user : userList) {
            if(user.getAge() > 18) {
                List<Credit> credits = resultMap.get(user);
                if(credits == null) {
                    credits = user.getCredit();
                    List<Credit> debt = new ArrayList<>();
                    for(Credit credit : credits) {
                        if(credit.getBalance() > 0) {
                            debt.add(credit);
                        }
                    }
                    resultMap.put(user, debt);
                }
            }
        }

        System.out.println(resultMap);

//        RESULT
//        User{id=7, nick='Dura', age=20, sex=FEMALE, credit=[Credit{balance=500}, Credit{balance=700}]} [[Credit{balance=500}, Credit{balance=700}]]
//        User{id=6, nick='Dima', age=19, sex=ANIMAL, credit=[]} [[]]

    }

}
