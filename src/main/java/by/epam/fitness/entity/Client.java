package by.epam.fitness.entity;

import java.io.InputStream;
import java.util.Objects;

public class Client extends Entity {
    private Long id;
    private Long coachId;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String userHash;
    private boolean active;
    private Integer membershipNumber;
    private Float personalDiscount;
    private Long programId;
    private String image;
    private InputStream is;

    public Client() {}

    public Client(Long id, Long coachId, String name, String surname, String login, String password, String email,
                  String userHash, boolean active, Integer membershipNumber, Float personalDiscount, Long programId,
                  String image, InputStream is) {
        this.id = id;
        this.coachId = coachId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.userHash = userHash;
        this.active = active;
        this.membershipNumber = membershipNumber;
        this.personalDiscount = personalDiscount;
        this.programId = programId;
        this.image = image;
        this.is = is;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(coachId, client.coachId) &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(login, client.login) &&
                Objects.equals(password, client.password) &&
                Objects.equals(email, client.email) &&
                Objects.equals(userHash, client.userHash) &&
                Objects.equals(membershipNumber, client.membershipNumber) &&
                Objects.equals(personalDiscount, client.personalDiscount) &&
                Objects.equals(programId, client.programId) &&
                Objects.equals(image, client.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, coachId, name, surname, login, password, email, userHash, membershipNumber,
                personalDiscount, programId, image);
    }
}
