package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Client;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.entity.Comment;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.CommentService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ClientServiceImpl;
import by.epam.fitness.service.impl.CoachServiceImpl;
import by.epam.fitness.service.impl.CommentServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdminCommentsCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AdminCommentsCommand.class);
    private CommentService commentService = new CommentServiceImpl();
    private CoachService coachService = new CoachServiceImpl();
    private ClientService clientService = new ClientServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        try {
            List<Comment> comments = commentService.findAll();
            Map<Comment, Map<Client, Coach>> commentUserCoachMap = makeCommentMapForAdmin(comments);
            request.setAttribute(JspConst.COMMENTS, commentUserCoachMap);
            page = Page.ADMIN_COMMENTS;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ADMIN_COMMENTS;
        }
        return page;
    }

    private  Map<Comment, Map<Client, Coach>> makeCommentMapForAdmin(List<Comment> comments) throws ServiceException {
        Map<Comment, Map<Client, Coach>> commentUserCoachMap = new HashMap<>();
        for (Comment comment : comments) {
            Optional<Client> optionalClient = clientService.findById(comment.getClientId());
            Optional<Coach> optionalCoach = coachService.findById(comment.getCoachId());
            if (optionalCoach.isPresent() && optionalClient.isPresent()) {
                Map<Client, Coach> clientCoachMap= new HashMap<>();
                clientCoachMap.put(optionalClient.get(), optionalCoach.get());
                commentUserCoachMap.put(comment, clientCoachMap);
            }
        }
        return commentUserCoachMap;
    }
}
