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

public class LoginCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(LoginCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private static UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_LOGIN);
        if (login==null || !dataValidator.isLoginValid(login)) {
            log.info("invalid login format was received:" + login);
            request.setAttribute("invalidLogin", "Wrong login or password");
            return  "/login";
        }
        String password = request.getParameter(PARAM_PASSWORD);
        if (password==null || !dataValidator.isPasswordValid(password)){
            log.info("invalid password format was received:" + password);
            request.setAttribute("invalidPassword", "Wrong login or password");
            return "/login";
        }
        try {
            if (userService.checkUserByLoginPassword(login, password)) {
                request.getSession().setAttribute(PARAM_USER, login);
                request.setAttribute(PARAM_USER, login);
                page = "/welcome";
            } else {
                request.setAttribute("wrongData", "Wrong login or password");
                page = "/login";
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = "/login";
        }
        return page;
    }
}
