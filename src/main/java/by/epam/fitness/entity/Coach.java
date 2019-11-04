package by.epam.fitness.entity;

import java.util.Objects;

public class Coach extends Entity {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String login;
    private String password;

    public Coach() {}

    public Coach(Long id, String name, String surname, String patronymic, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equals(id, coach.id) &&
                Objects.equals(name, coach.name) &&
                Objects.equals(surname, coach.surname) &&
                Objects.equals(patronymic, coach.patronymic) &&
                Objects.equals(login, coach.login) &&
                Objects.equals(password, coach.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, login, password);
    }
}
