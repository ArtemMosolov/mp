import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java7 {

    public static void main(String[] args) {

        //task_1();
        //task_2();
        //task_3();

        task_5();

    }


    private static void task_1() {

        List<User> userList = new ArrayList<>(Arrays.asList(
                new User(1L, "Vasia", 22, SEX.FEMALE),
                new User(2L, "Vasia", 23, SEX.MALE),
                new User(3L, "Vasia", 24, SEX.ANIMAL),
                new User(4L, "Petia", 44, SEX.FEMALE),
                new User(5L, "Petia", 33, SEX.MALE),
                new User(6L, "Petia", 22, SEX.ANIMAL),
                new User(7L, "Katia", 1, SEX.FEMALE),
                new User(8L, "Katia", 2, SEX.MALE),
                new User(9L, "Katia", 3, SEX.ANIMAL)
        ));

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

        List<User> userList = new ArrayList<>(Arrays.asList(
                new User(1L, "Vasia", 22, SEX.FEMALE),
                new User(2L, "Vasia", 23, SEX.MALE),
                new User(3L, "Vasia", 24, SEX.ANIMAL),
                new User(4L, "Petia", 44, SEX.FEMALE),
                new User(5L, "Petia", 33, SEX.MALE),
                new User(6L, "Petia", 22, SEX.ANIMAL),
                new User(7L, "Katia", 1, SEX.FEMALE),
                new User(8L, "Katia", 2, SEX.MALE),
                new User(9L, "Katia", 3, SEX.ANIMAL)
        ));

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

        List<User> userList = new ArrayList<>(Arrays.asList(
                new User(1L, "Vasia", 5, SEX.FEMALE),
                new User(2L, "Petia", 8, SEX.MALE),
                new User(3L, "Katia", 10, SEX.ANIMAL),
                new User(4L, "Olia", 17, SEX.FEMALE),
                new User(5L, "Vika", 18, SEX.MALE),
                new User(6L, "Dima", 19, SEX.ANIMAL),
                new User(7L, "Dura", 20, SEX.FEMALE),
                new User(8L, "Typa", 5, SEX.MALE),
                new User(9L, "Java", 8, SEX.ANIMAL)
        ));

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
        Map<SEX, List<Map<Integer, List<String>>>> usersMap = new LinkedHashMap<SEX, List<Map<Integer, List<String>>>>();

        Map<Integer, List<String>> mapA = new LinkedHashMap<>();
        List<String> nameA = Arrays.asList("vasia", "petia", "dima");
        List<String> nameB = Arrays.asList("ivan", "anton", "victor");
        mapA.put(5, nameA);
        mapA.put(10, nameA);

        Map<Integer, List<String>> mapB = new LinkedHashMap<>();
        List<String> nameC = Arrays.asList("volodymyr", "sasha", "oleh");
        List<String> nameD = Arrays.asList("artem", "yurii", "petro");
        mapB.put(20, nameC);
        mapB.put(30, nameD);

        Map<Integer, List<String>> mapC = new LinkedHashMap<>();
        List<String> nameE = Arrays.asList("vika", "katia", "olia");
        List<String> nameF = Arrays.asList("ivanka", "krystia", "natalia");
        mapC.put(5, nameE);
        mapC.put(10, nameF);

        Map<Integer, List<String>> mapD = new LinkedHashMap<>();
        List<String> nameH = Arrays.asList("veronika", "maruna", "gala");
        List<String> nameG = Arrays.asList("oksana", "kate", "sasha");
        mapD.put(20, nameH);
        mapD.put(30, nameG);


        usersMap.put(SEX.MALE, new LinkedList<Map<Integer, List<String>>>(Arrays.asList(mapA, mapB)));
        usersMap.put(SEX.FEMALE, new LinkedList<Map<Integer, List<String>>>(Arrays.asList(mapC, mapD)));


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

        usersMap.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap(List::stream)
                .filter(user -> user.getAge() > 18)
                .collect(Collectors.groupingBy(User::getNick, Collectors.mapping(User::getCredit, Collectors.toList())))
                .forEach((k, v) -> System.out.println(k + " KREDIT " + v));



    }

}

class User {

    private Long id;
    private String nick;
    private int age;
    private SEX sex;
    private Credit credit;

    public User() {}

    public User(Long id, String name, int age, SEX sex) {
        this.id = id;
        this.nick = name;
        this.age = age;
        this.sex = sex;
    }

    public User(Long id, String name, int age, SEX sex, Credit credit) {
        this.id = id;
        this.nick = name;
        this.age = age;
        this.sex = sex;
        this.credit = credit;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SEX getSex() {
        return sex;
    }

    public void setSex(SEX sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (nick != null ? !nick.equals(user.nick) : user.nick != null) return false;
        return sex == user.sex;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        result = 31 * result + (int) age;
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", credit=" + credit +
                '}';
    }
}

enum SEX {
    MALE, FEMALE, ANIMAL, UNKNOWN
}

class Credit {

    private Long id;
    private BigDecimal debt;

    public Credit(Long id, BigDecimal debt) {
        this.id = id;
        this.debt = debt;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", debt=" + debt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credit credit = (Credit) o;

        if (id != null ? !id.equals(credit.id) : credit.id != null) return false;
        return debt != null ? debt.equals(credit.debt) : credit.debt == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (debt != null ? debt.hashCode() : 0);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }
}
