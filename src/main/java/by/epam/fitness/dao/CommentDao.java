package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Comment;

public interface CommentDao extends BaseDao<Long, Comment> {
    Long save(Comment comment) throws DaoException;
}
