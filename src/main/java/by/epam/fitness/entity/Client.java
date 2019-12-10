package by.epam.fitness.entity;

import java.io.InputStream;
import java.util.Objects;

/**
 * The type Client.
 */
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

    /**
     * Instantiates a new Client.
     */
    public Client() {}

    /**
     * Instantiates a new Client.
     *
     * @param id               the id
     * @param coachId          the coach id
     * @param name             the name
     * @param surname          the surname
     * @param login            the login
     * @param password         the password
     * @param email            the email
     * @param userHash         the user hash
     * @param active           the active
     * @param membershipNumber the membership number
     * @param personalDiscount the personal discount
     * @param programId        the program id
     * @param image            the image
     * @param is               the is
     */
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

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets membership number.
     *
     * @return the membership number
     */
    public Integer getMembershipNumber() {
        return membershipNumber;
    }

    /**
     * Sets membership number.
     *
     * @param membershipNumber the membership number
     */
    public void setMembershipNumber(Integer membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    /**
     * Gets personal discount.
     *
     * @return the personal discount
     */
    public Float getPersonalDiscount() {
        return personalDiscount;
    }

    /**
     * Sets personal discount.
     *
     * @param personalDiscount the personal discount
     */
    public void setPersonalDiscount(Float personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets user hash.
     *
     * @return the user hash
     */
    public String getUserHash() {
        return userHash;
    }

    /**
     * Sets user hash.
     *
     * @param userHash the user hash
     */
    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active.
     *
     * @param active the active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets coach id.
     *
     * @return the coach id
     */
    public Long getCoachId() {
        return coachId;
    }

    /**
     * Sets coach id.
     *
     * @param coachId the coach id
     */
    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    /**
     * Gets program id.
     *
     * @return the program id
     */
    public Long getProgramId() {
        return programId;
    }

    /**
     * Sets program id.
     *
     * @param programId the program id
     */
    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    /**
     * Gets is.
     *
     * @return the is
     */
    public InputStream getIs() {
        return is;
    }

    /**
     * Sets is.
     *
     * @param is the is
     */
    public void setIs(InputStream is) {
        this.is = is;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
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
