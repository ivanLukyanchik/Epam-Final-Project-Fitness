package by.epam.fitness.entity;

import java.io.InputStream;
import java.util.Objects;

/**
 * The type Exercise.
 */
public class Exercise extends Entity {
    private Long id;
    private String name;
    private String description;
    private String image;
    private InputStream is;

    /**
     * Instantiates a new Exercise.
     */
    public Exercise(){}

    /**
     * Instantiates a new Exercise.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param image       the image
     * @param is          the is
     */
    public Exercise(Long id, String name, String description, String image, InputStream is){
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.is = is;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
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
