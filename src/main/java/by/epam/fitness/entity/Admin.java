package by.epam.fitness.entity;

import java.util.Objects;

public class Admin extends Entity {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;

    public Admin(Long id, String name, String surname, String login, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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
        Admin admin = (Admin) o;
        return Objects.equals(id, admin.id) &&
                Objects.equals(name, admin.name) &&
                Objects.equals(surname, admin.surname) &&
                Objects.equals(login, admin.login) &&
                Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, login, password);
    }
}
