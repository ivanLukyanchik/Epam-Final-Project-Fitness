package by.epam.fitness.util.database;

public enum  ProgramTableConst {
    ID("id_program"),

    NUTRITION_ID("nutrition_id"),

    TRAINS_PER_WEEK("trains_per_week");

    private String fieldName;

    private ProgramTableConst(String fieldName) {
        this.fieldName = fieldName;
    }


    public String getFieldName(){
        return fieldName;
    }
}
