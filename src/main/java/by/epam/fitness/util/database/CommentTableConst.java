package by.epam.fitness.util.database;

public enum CommentTableConst {
    ID("id_comment"),

    COACH_ID("coach_id"),

    CLIENT_ID("client_id"),

    COMMENT_CONTENT("comment_content"),

    PASSWORD("password");

    private String fieldName;

    private CommentTableConst(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName(){
        return fieldName;
    }
}
