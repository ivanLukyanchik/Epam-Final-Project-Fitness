package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.fitness.util.JspConst.PARAM_KEY_1;
import static by.epam.fitness.util.JspConst.PARAM_KEY_2;
import static by.epam.fitness.util.page.Page.LOGIN_PAGE;
import static by.epam.fitness.util.page.Page.REGISTER_PAGE;

public class ActivationCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ActionCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String email = request.getParameter(PARAM_KEY_1);
        if (email==null || !dataValidator.isEmailValid(email)){
            log.info("invalid email format was received, link was modified:" + email);
            request.setAttribute("incorrectData", "incorrectData");
            return REGISTER_PAGE;
        }
        String userHash = request.getParameter(PARAM_KEY_2);
        if (userHash==null || !dataValidator.isHashValid(userHash)) {
            log.info("invalid hash format was received, link was modified:" + userHash);
            request.setAttribute("incorrectData", "incorrectData");
            return REGISTER_PAGE;
        }
        try {
            if (userService.registerUser2(email, userHash)) {
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
