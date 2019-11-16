package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.User;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class RejectCoachCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RejectCoachCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(SessionAttributes.ID);
        try {
            Optional<User> user = userService.findById(clientId);
            if (user.isPresent()) {
                user.get().setCoachId(null);
                userService.save(user.get());
                log.info("client with id = " + clientId + " rejected his coach");
                request.setAttribute(JspConst.COACH_REJECTED, true);
                page = Page.WELCOME_PAGE;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ALL_COACHES;
        }
        return page;
    }
}
