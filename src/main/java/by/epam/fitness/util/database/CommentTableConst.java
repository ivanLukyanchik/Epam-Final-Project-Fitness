package by.epam.fitness.util.database;

/**
 * The enum Comment table const.
 */
public enum CommentTableConst {
    /**
     * Id comment table const.
     */
    ID("id_comment"),

    /**
     * Coach id comment table const.
     */
    COACH_ID("coach_id"),

    /**
     * Client id comment table const.
     */
    CLIENT_ID("client_id"),

    /**
     * Comment content comment table const.
     */
    COMMENT_CONTENT("comment_content"),

    /**
     * Payment data comment table const.
     */
    PAYMENT_DATA("payment_data");

    private String fieldName;

    private CommentTableConst(String fieldName) {
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
