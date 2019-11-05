package by.epam.fitness.entity;

import java.util.Objects;

public class User extends Entity {
    private Long id;
    private Long coachId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String userHash;
    private Integer membershipNumber;
    private Float personalDiscount;
    private Long programId;

    public User() {}

    public User(Long id, Long coachId, String name, String surname, String login, String password, String email,
                String userHash, Integer membershipNumber, Float personalDiscount, Long programId) {
        this.id = id;
        this.coachId = coachId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.userHash = userHash;
        this.membershipNumber = membershipNumber;
        this.personalDiscount = personalDiscount;
        this.programId = programId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(coachId, user.coachId) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(userHash, user.userHash) &&
                Objects.equals(membershipNumber, user.membershipNumber) &&
                Objects.equals(personalDiscount, user.personalDiscount) &&
                Objects.equals(programId, user.programId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coachId, name, surname, login, password, email, userHash, membershipNumber,
                personalDiscount, programId);
    }
}
