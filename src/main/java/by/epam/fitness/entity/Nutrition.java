package by.epam.fitness.entity;

import java.util.Objects;

public class Nutrition extends Entity {
    private Long id;
    private String name;
    private String morningNutrition;
    private String lunchNutrition;
    private String dinnerNutrition;

    public Nutrition(Long id, String name, String morningNutrition, String lunchNutrition, String dinnerNutrition){
        this.id = id;
        this.name = name;
        this.morningNutrition = morningNutrition;
        this.lunchNutrition = lunchNutrition;
        this.dinnerNutrition = dinnerNutrition;
    }

    public Nutrition(){}

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

    public String getMorningNutrition() {
        return morningNutrition;
    }

    public void setMorningNutrition(String morningNutrition) {
        this.morningNutrition = morningNutrition;
    }

    public String getLunchNutrition() {
        return lunchNutrition;
    }

    public void setLunchNutrition(String lunchNutrition) {
        this.lunchNutrition = lunchNutrition;
    }

    public String getDinnerNutrition() {
        return dinnerNutrition;
    }

    public void setDinnerNutrition(String dinnerNutrition) {
        this.dinnerNutrition = dinnerNutrition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Nutrition nutrition = (Nutrition) o;
        return  Objects.equals(getId(), nutrition.getId()) &&
                Objects.equals(getName(), nutrition.getName()) &&
                Objects.equals(getMorningNutrition(), nutrition.getMorningNutrition()) &&
                Objects.equals(getLunchNutrition(), nutrition.getLunchNutrition()) &&
                Objects.equals(getDinnerNutrition(), nutrition.getDinnerNutrition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getName(),getMorningNutrition(),getLunchNutrition(),getDinnerNutrition());
    }
}
