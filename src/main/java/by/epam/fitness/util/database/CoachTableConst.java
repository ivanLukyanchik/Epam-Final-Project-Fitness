package by.epam.fitness.util.database;

/**
 * The enum Coach table const.
 */
public enum CoachTableConst {
    /**
     * Id coach table const.
     */
    ID("id_coach"),

    /**
     * Name coach table const.
     */
    NAME("name"),

    /**
     * Surname coach table const.
     */
    SURNAME("surname"),

    /**
     * Patronymic coach table const.
     */
    PATRONYMIC("patronymic"),

    /**
     * Login coach table const.
     */
    LOGIN("login"),

    /**
     * Password coach table const.
     */
    PASSWORD("password");

    private String fieldName;

    private CoachTableConst(String fieldName) {
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
