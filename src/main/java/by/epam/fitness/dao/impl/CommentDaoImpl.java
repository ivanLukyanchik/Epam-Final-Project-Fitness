package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.CommentBuilder;
import by.epam.fitness.dao.CommentDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Comment;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentDaoImpl implements CommentDao {
    private static final String SQL_CREATE_TABLE = "INSERT INTO comment (coach_id, client_id, comment_content) VALUES (?,?,?)";
    private static final String SQL_UPDATE_TABLE = "UPDATE comment SET coach_id=?, client_id=?, comment_content=? WHERE id_comment=?";
    private static final String SQL_FIND_BY_COACH_ID = "SELECT * FROM comment WHERE coach_id=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM comment";
    private static final String SQL_DELETE = "DELETE FROM comment WHERE id_comment=?";
    private CommentBuilder builder = new CommentBuilder();

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
    public Optional<Comment> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Comment> findByCoachId(long coachId) throws DaoException {
        List<Comment> comments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Comment comment = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_COACH_ID);
            preparedStatement.setLong(1, coachId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comment = builder.build(resultSet);
                comments.add(comment);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return comments;
    }

    @Override
    public List<Comment> findAll() throws DaoException {
        List<Comment> comments = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        Comment comment = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()) {
                comment = builder.build(resultSet);
                comments.add(comment);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return comments;
    }

    @Override
    public int delete(long id) throws DaoException {
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return result;
    }
}