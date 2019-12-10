package by.epam.fitness.util.database;

/**
 * The enum Exercise program table const.
 */
public enum  ExerciseProgramTableConst {
    /**
     * Id exercise program table const.
     */
    ID("id_exercise_program"),

    /**
     * Program id exercise program table const.
     */
    PROGRAM_ID("program_id"),

    /**
     * Exercise id exercise program table const.
     */
    EXERCISE_ID("exercise_id"),

    /**
     * Repeat number exercise program table const.
     */
    REPEAT_NUMBER("repeat_number"),

    /**
     * Set number exercise program table const.
     */
    SET_NUMBER("set_number"),

    /**
     * Number train day exercise program table const.
     */
    NUMBER_TRAIN_DAY("number_train_day");

    private String fieldName;

    private ExerciseProgramTableConst(String fieldName) {
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
