package by.epam.fitness.entity;

import java.util.Objects;

/**
 * The type Program.
 */
public class Program extends Entity {
    private Long id;
    private Long nutritionId;
    private int trainsPerWeek;

    /**
     * Instantiates a new Program.
     *
     * @param id            the id
     * @param nutritionId   the nutrition id
     * @param trainsPerWeek the trains per week
     */
    public Program(Long id, Long nutritionId, int trainsPerWeek) {
        this.id = id;
        this.nutritionId = nutritionId;
        this.trainsPerWeek = trainsPerWeek;
    }

    /**
     * Instantiates a new Program.
     */
    public Program(){}

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
     * Gets nutrition id.
     *
     * @return the nutrition id
     */
    public Long getNutritionId() {
        return nutritionId;
    }

    /**
     * Sets nutrition id.
     *
     * @param nutritionId the nutrition id
     */
    public void setNutritionId(Long nutritionId) {
        this.nutritionId = nutritionId;
    }

    /**
     * Gets trains per week.
     *
     * @return the trains per week
     */
    public int getTrainsPerWeek() { return trainsPerWeek; }

    /**
     * Sets trains per week.
     *
     * @param trainsPerWeek the trains per week
     */
    public void setTrainsPerWeek(int trainsPerWeek) {
        this.trainsPerWeek = trainsPerWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Program program = (Program) o;
        return Objects.equals(getId(), program.getId()) &&
                Objects.equals(getNutritionId(), program.getNutritionId()) &&
                Objects.equals(getTrainsPerWeek(), program.getTrainsPerWeek());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getNutritionId(),getTrainsPerWeek());
    }
}
