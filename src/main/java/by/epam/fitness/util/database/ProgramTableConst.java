package by.epam.fitness.util.database;

/**
 * The enum Program table const.
 */
public enum  ProgramTableConst {
    /**
     * Id program table const.
     */
    ID("id_program"),

    /**
     * Nutrition id program table const.
     */
    NUTRITION_ID("nutrition_id"),

    /**
     * Trains per week program table const.
     */
    TRAINS_PER_WEEK("trains_per_week");

    private String fieldName;

    private ProgramTableConst(String fieldName) {
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
