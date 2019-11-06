package by.epam.fitness.util.database;

public enum  OrderInformationTableConst {
    ID("id_order_information"),

    COST("cost"),

    PAYMENT_DATA("payment_data"),

    MEMBERSHIP_END_DATE("membership_end_date"),

    CARD_NUMBER("card_number"),

    CLIENT_ID("client_id");

    private String fieldName;

    private OrderInformationTableConst(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName(){
        return fieldName;
    }
}
