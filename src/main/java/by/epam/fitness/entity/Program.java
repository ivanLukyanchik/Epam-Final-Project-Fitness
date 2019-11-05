package by.epam.fitness.entity;

import java.util.Objects;

public class Program extends Entity {
    private Long id;
    private Long nutritionId;
    private int trainsPerWeek;

    public Program(Long id, Long nutritionId, int trainsPerWeek) {
        this.id = id;
        this.nutritionId = nutritionId;
        this.trainsPerWeek = trainsPerWeek;
    }

    public Program(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(Long nutritionId) {
        this.nutritionId = nutritionId;
    }

    public int getTrainsPerWeek() { return trainsPerWeek; }

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
