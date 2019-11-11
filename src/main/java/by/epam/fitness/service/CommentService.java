package by.epam.fitness.service;

import by.epam.fitness.entity.Comment;

public interface CommentService {
    Long save(Comment comment) throws ServiceException;
}
