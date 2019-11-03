package by.epam.fitness.util.database;

public enum  ClientTableConst {
    NAME("name"),

    SURNAME("surname"),

    LOGIN("login"),

    PASSWORD("password"),

    EMAIL("email");

    private String fieldName;

    private ClientTableConst(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName(){
        return fieldName;
    }
}
