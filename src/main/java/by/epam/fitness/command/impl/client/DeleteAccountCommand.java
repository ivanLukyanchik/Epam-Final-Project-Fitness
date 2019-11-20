package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.User;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.SUCCESS;

public class DeleteAccountCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(DeleteAccountCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        Long clientId = (Long) request.getSession().getAttribute(SessionAttributes.ID);
        try {
            Optional<User> clientOptional = userService.findById(clientId);
            if (clientOptional.isPresent()) {
                User user = clientOptional.get();
                user.setActive(false);
                    userService.save(user);
                    log.info("client with id = "+ clientId + " successfully deleted his profile");
                    request.setAttribute(SUCCESS, true);
                page = Page.LOGOUT_COMMAND;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.CLIENT_PROFILE_PAGE;
        }
        return page;
    }
}
