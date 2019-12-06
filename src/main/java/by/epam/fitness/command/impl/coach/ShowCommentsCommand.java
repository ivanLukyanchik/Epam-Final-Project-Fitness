package by.epam.fitness.command.impl.coach;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
import by.epam.fitness.entity.Client;
import by.epam.fitness.entity.Comment;
import by.epam.fitness.entity.UserRole;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.CommentService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ClientServiceImpl;
import by.epam.fitness.service.impl.CommentServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.*;

public class ShowCommentsCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ShowCommentsCommand.class);
    private CommentService commentService = new CommentServiceImpl();
    private ClientService clientService = new ClientServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        HttpSession session = request.getSession();
        request.setAttribute(MAX_NUMBER_SYMBOLS_ATTRIBUTE,MAX_NUMBER_SYMBOLS_VALUE);
        String role = String.valueOf(session.getAttribute(SessionAttributes.ROLE));
        Long coachId;
        if (role.equals(UserRole.COACH)) {
            coachId = (Long) session.getAttribute(SessionAttributes.ID);
        } else {
            String coachIdString = request.getParameter(COACH_ID);
            if (coachIdString==null || !DataValidator.isIdentifiableIdValid(coachIdString)) {
                log.info("invalid coach id format from user was received:" + coachIdString);
                request.setAttribute(INVALID_COACH_ID, true);
                return new CommandResult(Page.ALL_COACHES_COMMAND);
            }
            coachId = Long.valueOf(coachIdString);
        }
        try {
            List<Comment> comments = commentService.findByCoachId(coachId);
            Map<Comment, Client> commentUserMap = makeCommentMapForCoach(comments);
            request.setAttribute(JspConst.COMMENTS, commentUserMap);
            page = Page.COACH_COMMENTS;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.COACH_COMMENTS;
        }
        return new CommandResult(page);
    }

    private Map<Comment, Client> makeCommentMapForCoach(List<Comment> comments) throws ServiceException {
        Map<Comment, Client> commentUserMap = new HashMap<>();
        for (Comment comment : comments) {
            Optional<Client> optionalUser = clientService.findActiveById(comment.getClientId());
            optionalUser.ifPresent(user -> commentUserMap.put(comment, user));
        }
        return commentUserMap;
    }
}
