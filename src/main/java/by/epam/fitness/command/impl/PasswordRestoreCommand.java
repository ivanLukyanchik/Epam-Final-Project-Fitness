package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.User;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

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
            request.setAttribute(INVALID_PASSWORD, true);
            return Page.PASSWORD_RESTORE_PAGE;
        }
        String confirmedPassword = request.getParameter(PARAM_CONFIRMED_PASSWORD);
        if (confirmedPassword==null || !dataValidator.isPasswordValid(confirmedPassword)){
            log.info("invalid password format was received:" + confirmedPassword);
            request.setAttribute(INVALID_PASSWORD, true);
            return Page.PASSWORD_RESTORE_PAGE;
        }
        if (!password.equals(confirmedPassword)) {
            log.info("Passwords do not match: " + confirmedPassword + " and " + password);
            request.setAttribute(PASSWORDS_NOT_MATCH, true);
            return Page.PASSWORD_RESTORE_PAGE;
        }
        String email = request.getParameter(PARAM_EMAIL);
        if (email==null || !dataValidator.isEmailValid(email)){
            log.info("invalid email format was received, link was modified:" + email);
            request.setAttribute(INCORRECT_DATA, true);
            return Page.PASSWORD_RESTORE_PAGE;
        }
        String login = request.getParameter(PARAM_LOGIN);
        if (login==null || !dataValidator.isLoginValid(login)) {
            log.info("invalid login format was received, link was modified:" + login);
            request.setAttribute(INCORRECT_DATA, true);
            return Page.PASSWORD_RESTORE_PAGE;
        }
        String hash = request.getParameter(PARAM_HASH);
        if (hash==null || !dataValidator.isHashValid(hash)) {
            log.info("invalid hash format was received, link was modified:" + hash);
            request.setAttribute(INCORRECT_DATA, true);
            return Page.PASSWORD_RESTORE_PAGE;
        }
        try {
            Optional<User> clientOptional = userService.findByLoginHash(login, email, hash);
            if (clientOptional.isPresent()) {
                User user = clientOptional.get();
                user.setActive(true);
                user.setPassword(password);
                userService.save(user);
                log.info("password of user " + login + " was changed");
                request.setAttribute(PASSWORD_CHANGED, true);
                page = Page.LOGIN_PAGE;
            } else {
                page = Page.PASSWORD_RESTORE_PAGE;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.PASSWORD_RESTORE_PAGE;
        }
        return page;
    }
}
