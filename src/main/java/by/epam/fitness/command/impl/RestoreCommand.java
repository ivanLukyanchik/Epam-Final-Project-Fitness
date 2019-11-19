package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.mail.SendingEmail;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Random;

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
            request.setAttribute(JspConst.INVALID_EMAIL, true);
            return Page.RESTORE_PAGE;
        }
        String login = request.getParameter(JspConst.PARAM_LOGIN);
        if (login==null || !dataValidator.isLoginValid(login)) {
            log.info("invalid login format was received:" + login);
            request.setAttribute(JspConst.INVALID_LOGIN, true);
            return Page.RESTORE_PAGE;
        }
        Random random = new SecureRandom();
        String userHash = DigestUtils.sha512Hex("" + random.nextInt(999999));
        try {
            if (userService.restoreUser(login, email, userHash)) {
                SendingEmail.restorePassword(login, email, userHash);
                log.info("client with login = " + login + " restored his password");
                page = Page.FINAL_RESTORE_PAGE;
            } else {
                log.info("there is no client with such login " + login + " or email " + email);
                request.setAttribute(JspConst.WRONG_DATA, true);
                page = Page.RESTORE_PAGE;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.RESTORE_PAGE;
        }
        return page;
    }
}
