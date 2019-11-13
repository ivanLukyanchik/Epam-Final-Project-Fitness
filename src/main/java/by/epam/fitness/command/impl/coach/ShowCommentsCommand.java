package by.epam.fitness.command.impl.coach;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Comment;
import by.epam.fitness.entity.User;
import by.epam.fitness.service.CommentService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.CommentServiceImpl;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.MAX_NUMBER_SYMBOLS_ATTRIBUTE;
import static by.epam.fitness.util.JspConst.MAX_NUMBER_SYMBOLS_VALUE;

public class ShowCommentsCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ShowCommentsCommand.class);
    private CommentService commentService = new CommentServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        request.setAttribute(MAX_NUMBER_SYMBOLS_ATTRIBUTE,MAX_NUMBER_SYMBOLS_VALUE);
        Long coachId = (Long) session.getAttribute(SessionAttributes.ID);
        try {
            List<Comment> comments = commentService.findByCoachId(coachId);
            Map<Comment, User> commentUserMap = makeCommentMapForCoach(comments);
            request.setAttribute(JspConst.COMMENTS, commentUserMap);
            page = Page.COACH_COMMENTS;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.COACH_COMMENTS;
        }
        return page;
    }

    private Map<Comment, User> makeCommentMapForCoach(List<Comment> comments) throws ServiceException {
        Map<Comment, User> commentUserMap = new HashMap<>();
        for (Comment comment : comments) {
            Optional<User> optionalUser = userService.findById(comment.getClientId());
            optionalUser.ifPresent(user -> commentUserMap.put(comment, user));
        }
        return commentUserMap;
    }
}
