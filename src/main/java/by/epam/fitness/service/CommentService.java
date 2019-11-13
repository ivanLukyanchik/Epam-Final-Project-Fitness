package by.epam.fitness.service;

import by.epam.fitness.entity.Comment;

import java.util.List;

public interface CommentService {
    Long save(Comment comment) throws ServiceException;
    List<Comment> findByCoachId(long coachId) throws ServiceException;
}
