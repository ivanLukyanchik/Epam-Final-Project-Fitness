package by.epam.fitness.util.database;

public enum AdminTableConst {
    ID("id_admin"),

    NAME("name"),

    SURNAME("surname"),

    LOGIN("login"),

    PASSWORD("password");

    private String fieldName;

    private AdminTableConst(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName(){
        return fieldName;
    }
}
