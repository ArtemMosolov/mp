import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {
    public static List<User>  getTestData() {
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
        return userList;
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