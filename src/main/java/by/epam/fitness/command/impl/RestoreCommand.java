package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.mail.SendingEmail;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RestoreCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RestoreCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String email = request.getParameter(JspConst.PARAM_EMAIL);
        if (email==null || !dataValidator.isEmailValid(email)){
            log.info("invalid email format was received:" + email);
            request.setAttribute("invalidEmail", "Wrong email");
            return "/restore";
        }
        String login = request.getParameter(JspConst.PARAM_LOGIN);
        if (login==null || !dataValidator.isLoginValid(login)) {
            log.info("invalid login format was received:" + login);
            request.setAttribute("invalidLogin", "Wrong login");
            return "/restore";
        }
        try {
            if (userService.restoreUser1(login, email)) {
                SendingEmail.restorePassword(login, email);
                page = "/finalRestore";
            } else {
                request.setAttribute("wrongData", "There is no such email");
                page = "/restore";
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = "/restore";
        }
        return page;
    }
}
