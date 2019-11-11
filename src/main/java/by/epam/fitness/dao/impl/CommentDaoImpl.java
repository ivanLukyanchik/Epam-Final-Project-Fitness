package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.CommentDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Comment;
import by.epam.fitness.pool.ConnectionPool;

import java.sql.*;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private static final String SQL_CREATE_TABLE = "INSERT INTO comment (coach_id, client_id, comment_content) VALUES (?,?,?)";
    private static final String SQL_UPDATE_TABLE = "UPDATE comment SET coach_id=?, client_id=?, comment_content=? WHERE id_comment=?";


    @Override
    public Long save(Comment comment) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Long coachId = comment.getCoachId();
        Long clientId = comment.getClientId();
        String commentContent = comment.getCommentContent();
        Long generatedId = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            if (comment.getId() != null) {
                preparedStatement = connection.prepareStatement(SQL_UPDATE_TABLE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(4, comment.getId());
            } else {
                preparedStatement = connection.prepareStatement(SQL_CREATE_TABLE, Statement.RETURN_GENERATED_KEYS);
            }
            preparedStatement.setLong(1, coachId);
            preparedStatement.setLong(2, clientId);
            preparedStatement.setString(3, commentContent);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return generatedId;
    }

    @Override
    public List<Comment> findAll() throws DaoException {
        return null;
    }

    @Override
    public List<Comment> findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Comment comment) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Comment update(Comment comment) {
        return null;
    }
}
