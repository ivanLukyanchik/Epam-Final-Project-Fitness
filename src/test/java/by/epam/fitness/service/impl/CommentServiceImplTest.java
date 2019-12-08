package by.epam.fitness.service.impl;

import by.epam.fitness.entity.Comment;
import by.epam.fitness.service.CommentService;
import by.epam.fitness.service.ServiceException;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CommentServiceImplTest {
    private final CommentService commentService = new CommentServiceImpl();

    @Test
    public void testFindByCoachId() throws ServiceException {
        List<Comment> comments = commentService.findByCoachId(7);
        int actual = comments.size();
        assertEquals(actual, 0);
    }

    @Test
    public void testFindAll() throws ServiceException {
        int size = commentService.findAll().size();
        boolean actual = size > 0;
        assertTrue(actual);
    }
}