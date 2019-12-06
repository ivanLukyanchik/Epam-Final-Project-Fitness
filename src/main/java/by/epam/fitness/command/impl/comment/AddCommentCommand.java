package by.epam.fitness.command.impl.comment;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.*;

public class AddCommentCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddCommentCommand.class);
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String commentContent  = request.getParameter(COMMENT_CONTENT).strip();
        request.setAttribute(MAX_NUMBER_SYMBOLS_ATTRIBUTE,MAX_NUMBER_SYMBOLS_VALUE);
        if (commentContent==null || !DataValidator.isCommentContentValid(commentContent)){
            log.info("was received invalid comment format");
            request.setAttribute(INVALID_COMMENT, true);
            return new CommandResult(Page.ALL_COACHES_COMMAND);
        }
        String coachIdString = request.getParameter(COACH_ID);
        try {
            if (coachIdString==null || !isCoachExist(coachIdString)){
                log.info("coach with id = " + coachIdString + " doesn't exist");
                request.setAttribute(NOT_EXIST_ID, true);
                return new CommandResult(Page.ALL_COACHES_COMMAND);
            }
            Long coachId = Long.valueOf(coachIdString);
            HttpSession session = request.getSession();
            Long clientId = (Long) session.getAttribute(SessionAttributes.ID);
            Comment comment = new Comment(null, clientId, coachId, commentContent, new Timestamp(new Date().getTime()));
            commentService.save(comment);
            log.info("comment of client with id = " + clientId + " was successfully saved");
            session.setAttribute(COMMENT_SAVED, true);
            page = Page.WELCOME_PAGE;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ALL_COACHES_COMMAND;
        }
        return new CommandResult(page, true);
    }

    private boolean isCoachExist(String coachIdString) throws ServiceException {
        long coachId = Long.parseLong(coachIdString);
        CoachService coachService = new CoachServiceImpl();
        Optional<Coach> coach = coachService.findById(coachId);
        return coach.isPresent();
    }
}
