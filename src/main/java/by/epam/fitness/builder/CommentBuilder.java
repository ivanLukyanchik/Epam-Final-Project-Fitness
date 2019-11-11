package by.epam.fitness.builder;

import by.epam.fitness.entity.Comment;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.util.database.CommentTableConst;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentBuilder implements Builder<Comment> {
    @Override
    public Comment build(ResultSet resultSet) throws ServiceException {
        try {
            Long commentId = resultSet.getLong(CommentTableConst.ID.getFieldName());
            Long clientId = resultSet.getLong(CommentTableConst.CLIENT_ID.getFieldName());
            Long coachId = resultSet.getLong(CommentTableConst.COACH_ID.getFieldName());
            String commentContent = resultSet.getString(CommentTableConst.COMMENT_CONTENT.getFieldName());
            return new Comment(commentId, clientId, coachId, commentContent);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
