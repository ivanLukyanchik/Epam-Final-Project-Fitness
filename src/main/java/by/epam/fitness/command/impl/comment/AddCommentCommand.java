package by.epam.fitness.command.impl.comment;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.entity.Comment;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.CommentService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.CoachServiceImpl;
import by.epam.fitness.service.impl.CommentServiceImpl;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.*;

public class AddCommentCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddCommentCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String commentContent  = request.getParameter(COMMENT_CONTENT).strip();
        request.setAttribute(MAX_NUMBER_SYMBOLS_ATTRIBUTE,MAX_NUMBER_SYMBOLS_VALUE);
        if (commentContent==null || !dataValidator.isCommentContentValid(commentContent)){
            log.info("was received invalid comment format");
            request.setAttribute("invalidComment", true);
            return Page.ALL_COACHES;
        }
        String coachIdString = request.getParameter(COACH_ID);
        try {
            if (coachIdString==null || !isCoachExist(coachIdString)){
                log.info("coach with id = " + coachIdString + " doesn't exist");
                request.setAttribute("notExistId", true);
                return Page.ALL_COACHES;
            }
            Long coachId = Long.valueOf(coachIdString);
            HttpSession session = request.getSession();
            Long clientId = (Long) session.getAttribute(SessionAttributes.ID);
            Comment comment = new Comment(null, clientId, coachId, commentContent);
            commentService.save(comment);
            log.info("comment of client with id = " + clientId + " was successfully saved");
            request.setAttribute("commentSaved", true);
            page = Page.WELCOME_PAGE;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ALL_COACHES;
        }
        return page;
    }

    private boolean isCoachExist(String coachIdString) throws ServiceException {
        long coachId = Long.parseLong(coachIdString);
        CoachService coachService = new CoachServiceImpl();
        Optional<Coach> coach = coachService.findById(coachId);
        return coach.isPresent();
    }
}
