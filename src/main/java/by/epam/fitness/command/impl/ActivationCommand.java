package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.fitness.util.JspConst.PARAM_KEY_1;
import static by.epam.fitness.util.JspConst.PARAM_KEY_2;

public class ActivationCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ActionCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String email = request.getParameter(PARAM_KEY_1);
        String userHash = request.getParameter(PARAM_KEY_2);
        try {
            if (userService.registerUser2(email, userHash)) {
                page = "/login";
            } else {
                page = "/register";
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = "/register";
        }
        return page;
    }
}
