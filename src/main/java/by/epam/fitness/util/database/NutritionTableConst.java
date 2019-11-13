package by.epam.fitness.util.database;

public enum  NutritionTableConst {
    ID("id_nutrition"),

    ACTIVE("active"),

    NAME("name"),

    MORNING_NUTRITION("morning_nutrition"),

    LUNCH_NUTRITION("lunch_nutrition"),

    DINNER_NUTRITION("lunch_nutrition");

    private String fieldName;

    private NutritionTableConst(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName(){
        return fieldName;
    }
}
