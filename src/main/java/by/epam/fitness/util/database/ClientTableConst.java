package by.epam.fitness.util.database;

public enum  ClientTableConst {
    ID("id_client"),

    COACH_ID("coach_id"),

    NAME("name"),

    SURNAME("surname"),

    LOGIN("login"),

    PASSWORD("password"),

    EMAIL("email"),

    HASH("hash"),

    ACTIVE("active"),

    MEMBERSHIP_PURCHASED_NUMBER("membership_purchased_number"),

    PERSONAL_DISCOUNT("personal_discount"),

    PROGRAM_ID("program_id");

    private String fieldName;

    private ClientTableConst(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName(){
        return fieldName;
    }
}
