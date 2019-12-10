package by.epam.fitness.util.database;

/**
 * The enum Client table const.
 */
public enum  ClientTableConst {
    /**
     * Id client table const.
     */
    ID("id_client"),

    /**
     * Coach id client table const.
     */
    COACH_ID("coach_id"),

    /**
     * Name client table const.
     */
    NAME("name"),

    /**
     * Surname client table const.
     */
    SURNAME("surname"),

    /**
     * Login client table const.
     */
    LOGIN("login"),

    /**
     * Password client table const.
     */
    PASSWORD("password"),

    /**
     * Email client table const.
     */
    EMAIL("email"),

    /**
     * Hash client table const.
     */
    HASH("hash"),

    /**
     * Active client table const.
     */
    ACTIVE("active"),

    /**
     * Membership purchased number client table const.
     */
    MEMBERSHIP_PURCHASED_NUMBER("membership_purchased_number"),

    /**
     * Personal discount client table const.
     */
    PERSONAL_DISCOUNT("personal_discount"),

    /**
     * Program id client table const.
     */
    PROGRAM_ID("program_id"),

    /**
     * Image client table const.
     */
    IMAGE("image");

    private String fieldName;

    private ClientTableConst(String fieldName) {
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
