package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.User;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.*;
import static by.epam.fitness.util.page.Page.LOGIN_PAGE;
import static by.epam.fitness.util.page.Page.REGISTER_PAGE;

public class ActivationCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ActivationCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String email = request.getParameter(PARAM_KEY_1);
        if (email==null || !dataValidator.isEmailValid(email)){
            log.info("invalid email format was received, link was modified:" + email);
            request.setAttribute(JspConst.INCORRECT_DATA, true);
            return REGISTER_PAGE;
        }
        String login = request.getParameter(PARAM_KEY_2);
        if (login==null || !dataValidator.isLoginValid(login)){
            log.info("invalid login format was received, link was modified:" + login);
            request.setAttribute(JspConst.INCORRECT_DATA, true);
            return REGISTER_PAGE;
        }
        String userHash = request.getParameter(PARAM_KEY_3);
        if (userHash==null || !dataValidator.isHashValid(userHash)) {
            log.info("invalid hash format was received, link was modified:" + userHash);
            request.setAttribute(JspConst.INCORRECT_DATA, true);
            return REGISTER_PAGE;
        }
        try {
            Optional<User> clientOptional = userService.findByLoginHash(login, email, userHash);
            if (clientOptional.isPresent()) {
                User user = clientOptional.get();
                user.setActive(true);
                userService.save(user);
                request.setAttribute(USER_ACTIVATED, true);
                log.info("client with login = " + login + " activated his profile via link");
                page = LOGIN_PAGE;
            } else {
                page = REGISTER_PAGE;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = REGISTER_PAGE;
        }
        return page;
    }
}
