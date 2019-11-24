package by.epam.fitness.entity;

import java.io.InputStream;
import java.util.Objects;

public class Exercise extends Entity {
    private Long id;
    private String name;
    private String description;
    private String image;
    private InputStream is;

    public Exercise(){}

    public Exercise(Long id, String name, String description, String image, InputStream is){
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.is = is;
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

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
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
