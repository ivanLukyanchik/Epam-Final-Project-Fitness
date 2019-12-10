package by.epam.fitness.service.impl;

import by.epam.fitness.dao.CommentDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.dao.impl.CommentDaoImpl;
import by.epam.fitness.entity.Comment;
import by.epam.fitness.service.CommentService;
import by.epam.fitness.service.ServiceException;

import java.util.List;

/**
 * The type Comment service.
 */
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = new CommentDaoImpl();

    @Override
    public Long save(Comment comment) throws ServiceException {
        try {
            return commentDao.save(comment);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Comment> findByCoachId(long coachId) throws ServiceException {
        try {
            return commentDao.findByCoachId(coachId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Comment> findAll() throws ServiceException {
        try {
            return commentDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int delete(long id) throws ServiceException {
        try {
            return commentDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
