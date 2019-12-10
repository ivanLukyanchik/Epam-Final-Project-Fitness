package by.epam.fitness.util.database;

/**
 * The enum Admin table const.
 */
public enum AdminTableConst {
    /**
     * Id admin table const.
     */
    ID("id_admin"),

    /**
     * Name admin table const.
     */
    NAME("name"),

    /**
     * Surname admin table const.
     */
    SURNAME("surname"),

    /**
     * Login admin table const.
     */
    LOGIN("login"),

    /**
     * Password admin table const.
     */
    PASSWORD("password");

    private String fieldName;

    private AdminTableConst(String fieldName) {
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
