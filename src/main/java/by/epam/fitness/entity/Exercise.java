package by.epam.fitness.entity;

import java.util.Objects;

public class Exercise extends Entity {
    private Long id;
    private String name;
    private String description;
    private String image;

    public Exercise(){}

    public Exercise(Long id, String name, String description, String image){
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Exercise exercise = (Exercise) o;
        return  Objects.equals(getId(), exercise.getId()) &&
                Objects.equals(getName(), exercise.getName()) &&
                Objects.equals(getDescription(), exercise.getDescription()) &&
                Objects.equals(getImage(), exercise.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getName(),getDescription(),getImage());
    }
}
