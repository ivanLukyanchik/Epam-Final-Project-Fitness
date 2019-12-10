package by.epam.fitness.util.database;

/**
 * The enum Exercise table const.
 */
public enum  ExerciseTableConst {
    /**
     * Id exercise table const.
     */
    ID("id_exercise"),

    /**
     * Name exercise table const.
     */
    NAME("name"),

    /**
     * Description exercise table const.
     */
    DESCRIPTION("description"),

    /**
     * Image exercise table const.
     */
    IMAGE("image");

    private String fieldName;

    private ExerciseTableConst(String fieldName) {
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
