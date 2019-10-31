package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.fitness.util.JspConst.*;

public class PasswordRestoreCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(PasswordRestoreCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String password = request.getParameter(PARAM_PASSWORD);
        if (password==null || !dataValidator.isPasswordValid(password)){
            log.info("invalid password format was received:" + password);
            request.setAttribute("invalidPassword", "Wrong login or password");
            return "/passwordRestore";
        }
        String email = request.getParameter(PARAM_EMAIL);
        String login = request.getParameter(PARAM_LOGIN);
        try {
            if (userService.restoreUser2(email, password, login)) {
                request.setAttribute("passwordChanged", "Your password has been changed");
                page = "/login";
            } else {
                page = "/passwordRestore";
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = "/passwordRestore";
        }
        return page;
    }
}
