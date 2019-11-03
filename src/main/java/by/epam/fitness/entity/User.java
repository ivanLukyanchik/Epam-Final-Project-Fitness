package by.epam.fitness.entity;

public class User extends Entity {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String userHash;
    private Integer membershipNumber;
    private Float personalDiscount;

    public User() {}

    public User(String name, String surname, String login, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(Integer membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public Float getPersonalDiscount() {
        return personalDiscount;
    }

    public void setPersonalDiscount(Float personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserHash() {
        return userHash;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }
}
