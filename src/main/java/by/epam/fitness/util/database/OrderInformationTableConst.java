package by.epam.fitness.util.database;

/**
 * The enum Order information table const.
 */
public enum  OrderInformationTableConst {
    /**
     * Id order information table const.
     */
    ID("id_order_information"),

    /**
     * Cost order information table const.
     */
    COST("cost"),

    /**
     * Payment data order information table const.
     */
    PAYMENT_DATA("payment_data"),

    /**
     * Membership end date order information table const.
     */
    MEMBERSHIP_END_DATE("membership_end_date"),

    /**
     * Card number order information table const.
     */
    CARD_NUMBER("card_number"),

    /**
     * Client id order information table const.
     */
    CLIENT_ID("client_id");

    private String fieldName;

    private OrderInformationTableConst(String fieldName) {
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
