package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.User;
import by.epam.fitness.mail.SendingEmail;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Random;

import static by.epam.fitness.util.JspConst.*;

public class RegisterCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RegisterCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String login = request.getParameter(PARAM_LOGIN);
        if (login==null || !dataValidator.isLoginValid(login)) {
            log.info("invalid login format was received:" + login);
            request.setAttribute("invalidLogin", "Wrong login");
            return "/register";
        }
        String email = request.getParameter(PARAM_EMAIL);
        if (email==null || !dataValidator.isEmailValid(email)){
            log.info("invalid email format was received:" + email);
            request.setAttribute("invalidEmail", "Wrong email");
            return "/register";
        }
        String password = request.getParameter(PARAM_PASSWORD);
        if (password==null || !dataValidator.isPasswordValid(password)){
            log.info("invalid password format was received:" + password);
            request.setAttribute("invalidPassword", "Wrong password");
            return "/register";
        }
        String newPassword = DigestUtils.sha512Hex(password);

        Random random = new SecureRandom();
        String userHash = DigestUtils.sha512Hex("" + random.nextInt(999999));

        //pattern builder should be here
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(newPassword);
        user.setUserHash(userHash);

        try {
            if (userService.registerUser1(user)) {
                SendingEmail.verify(email, userHash);
                page = "/verify";
            } else {
                request.setAttribute("wrongData", "User with this login already exists");
                page = "/register";
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = "/register";
        }
        return page;
    }
}
