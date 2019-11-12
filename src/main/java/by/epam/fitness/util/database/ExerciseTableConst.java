package by.epam.fitness.util.database;

public enum  ExerciseTableConst {
    ID("id_exercise"),

    NAME("name"),

    DESCRIPTION("description"),

    IMAGE("image");

    private String fieldName;

    private ExerciseTableConst(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName(){
        return fieldName;
    }
}
