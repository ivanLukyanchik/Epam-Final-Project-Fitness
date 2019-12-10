package by.epam.fitness.service;

import by.epam.fitness.entity.Comment;

import java.util.List;

/**
 * The interface Comment service.
 */
public interface CommentService {
    /**
     * Save long.
     *
     * @param comment the comment
     * @return the long
     * @throws ServiceException the service exception
     */
    Long save(Comment comment) throws ServiceException;

    /**
     * Find by coach id list.
     *
     * @param coachId the coach id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> findByCoachId(long coachId) throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> findAll() throws ServiceException;

    /**
     * Delete int.
     *
     * @param id the id
     * @return the int
     * @throws ServiceException the service exception
     */
    int delete(long id) throws ServiceException;
}
