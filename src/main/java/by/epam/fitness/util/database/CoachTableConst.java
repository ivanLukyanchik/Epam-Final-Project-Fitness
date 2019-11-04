package by.epam.fitness.util.database;

public enum CoachTableConst {
    ID("id_coach"),

    NAME("name"),

    SURNAME("surname"),

    PATRONYMIC("patronymic"),

    LOGIN("login"),

    PASSWORD("password");

    private String fieldName;

    private CoachTableConst(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName(){
        return fieldName;
    }
}
