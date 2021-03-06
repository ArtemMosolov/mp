import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestData {
    public static List<User>  getTestData() {
        List<User> userList = new ArrayList<>(Arrays.asList(
                new User(1L, "Vasia", 5, SEX.FEMALE, Arrays.asList(new Credit(100), new Credit(500), new Credit(0))),
                new User(2L, "Petia", 8, SEX.MALE, Arrays.asList(new Credit(-500), new Credit(200), new Credit(1000))),
                new User(3L, "Katia", 10, SEX.ANIMAL, Arrays.asList(new Credit(0), new Credit(0), new Credit(0))),
                new User(4L, "Olia", 17, SEX.FEMALE, Arrays.asList(new Credit(2000), new Credit(-300), new Credit(0))),
                new User(5L, "Vika", 18, SEX.MALE, Arrays.asList(new Credit(500), new Credit(0), new Credit(0))),
                new User(6L, "Dima", 19, SEX.ANIMAL, Arrays.asList(new Credit(0), new Credit(0), new Credit(0))),
                new User(7L, "Dura", 20, SEX.FEMALE, Arrays.asList(new Credit(-100), new Credit(500), new Credit(700))),
                new User(8L, "Vasia", 5, SEX.MALE, Arrays.asList(new Credit(300), new Credit(0), new Credit(0))),
                new User(9L, "Petia", 8, SEX.ANIMAL, Arrays.asList(new Credit(800), new Credit(500), new Credit(20)))
        ));
        return userList;
    }
}

class User {

    private Long id;
    private String nick;
    private int age;
    private SEX sex;
    private List<Credit> credit;

    public User() {}

    public User(Long id, String name, int age, SEX sex) {
        this.id = id;
        this.nick = name;
        this.age = age;
        this.sex = sex;
    }

    public User(Long id, String name, int age, SEX sex, List<Credit> credit) {
        this.id = id;
        this.nick = name;
        this.age = age;
        this.sex = sex;
        this.credit = credit;
    }

    public List<Credit> getCredit() {
        return credit;
    }

    public void setCredit(List<Credit> credit) {
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//
//        if (age != user.age) return false;
//        if (id != null ? !id.equals(user.id) : user.id != null) return false;
//        if (nick != null ? !nick.equals(user.nick) : user.nick != null) return false;
//        if (sex != user.sex) return false;
//        return credit != null ? credit.equals(user.credit) : user.credit == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (nick != null ? nick.hashCode() : 0);
//        result = 31 * result + age;
//        result = 31 * result + (sex != null ? sex.hashCode() : 0);
//        result = 31 * result + (credit != null ? credit.hashCode() : 0);
//        return result;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(id, user.id) &&
                Objects.equals(nick, user.nick) &&
                sex == user.sex &&
                Objects.equals(credit, user.credit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nick, age, sex, credit);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("nick", nick)
                .add("age", age)
                .add("sex", sex)
                .add("credit", credit)
                .toString();
    }
}

enum SEX {
    MALE, FEMALE, ANIMAL, UNKNOWN
}

class Credit {

    private Integer balance;

    public Credit(Integer balance) {
        this.balance = balance;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return Objects.equals(balance, credit.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("balance", balance)
                .toString();
    }
}