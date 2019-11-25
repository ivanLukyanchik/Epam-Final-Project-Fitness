package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Client;
import by.epam.fitness.entity.Comment;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.CommentService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ClientServiceImpl;
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
    private ClientService clientService = new ClientServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        try {
            List<Comment> comments = commentService.findAll();
            Map<Comment, Client> commentUserMap = makeCommentMapForAdmin(comments);
            request.setAttribute(JspConst.COMMENTS, commentUserMap);
            page = Page.ADMIN_COMMENTS;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ADMIN_COMMENTS;
        }
        return page;
    }

    private Map<Comment, Client> makeCommentMapForAdmin(List<Comment> comments) throws ServiceException {
        Map<Comment, Client> commentUserMap = new HashMap<>();
        for (Comment comment : comments) {
            Optional<Client> optionalUser = clientService.findById(comment.getClientId());
            optionalUser.ifPresent(user -> commentUserMap.put(comment, user));
        }
        return commentUserMap;
    }
}
