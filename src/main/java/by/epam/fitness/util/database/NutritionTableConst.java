package by.epam.fitness.util.database;

/**
 * The enum Nutrition table const.
 */
public enum  NutritionTableConst {
    /**
     * Id nutrition table const.
     */
    ID("id_nutrition"),

    /**
     * Active nutrition table const.
     */
    ACTIVE("active"),

    /**
     * Name nutrition table const.
     */
    NAME("name"),

    /**
     * Morning nutrition nutrition table const.
     */
    MORNING_NUTRITION("morning_nutrition"),

    /**
     * Lunch nutrition nutrition table const.
     */
    LUNCH_NUTRITION("lunch_nutrition"),

    /**
     * Dinner nutrition nutrition table const.
     */
    DINNER_NUTRITION("dinner_nutrition");

    private String fieldName;

    private NutritionTableConst(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Get field name string.
     *
     * @return the string
     */
    public String getFieldName(){
        return fieldName;
    }
}
