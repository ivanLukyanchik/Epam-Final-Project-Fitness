package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.entity.User;
import by.epam.fitness.entity.UserRole;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.CoachServiceImpl;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.fitness.util.JspConst.PARAM_LOGIN;
import static by.epam.fitness.util.JspConst.PARAM_PASSWORD;

public class LoginCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(LoginCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private static UserService userService = new UserServiceImpl();
    private static CoachService coachService = new CoachServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        User user = null;
        Coach coach = null;
        String login = request.getParameter(PARAM_LOGIN);
        if (login==null || !dataValidator.isLoginValid(login)) {
            log.info("invalid login format was received:" + login);
            request.setAttribute("invalidLogin", "Wrong login or password");
            return Page.LOGIN_PAGE;
        }
        String password = request.getParameter(PARAM_PASSWORD);
        if (password==null || !dataValidator.isPasswordValid(password)) {
            log.info("invalid password format was received:" + password);
            request.setAttribute("invalidPassword", "Wrong login or password");
            return Page.LOGIN_PAGE;
        }
        try {
            if (userService.checkUserByLoginPassword(login, password).isPresent()) {
                user = userService.checkUserByLoginPassword(login, password).get();
                request.getSession().setAttribute(SessionAttributes.CLIENT, user);
                request.getSession().setAttribute(SessionAttributes.USER, login);
                request.getSession().setAttribute(SessionAttributes.ROLE, UserRole.CLIENT);
                request.getSession().setAttribute(SessionAttributes.ID, user.getId());
                log.info("client with id = " + user.getId() + " log in");
                page = Page.WELCOME_PAGE;
            } else if (coachService.checkCoachByLoginPassword(login, password).isPresent()) {
                coach = coachService.checkCoachByLoginPassword(login, password).get();
                request.getSession().setAttribute(SessionAttributes.COACH, coach);
                request.getSession().setAttribute(SessionAttributes.USER, login);
                request.getSession().setAttribute(SessionAttributes.ROLE, UserRole.COACH);
                request.getSession().setAttribute(SessionAttributes.ID, coach.getId());
                log.info("coach with id = " + coach.getId() + " log in");
                page = Page.WELCOME_PAGE;
            } else {
                request.setAttribute(JspConst.WRONG_DATA, true);
                page = Page.LOGIN_PAGE;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.LOGIN_PAGE;
        }
        return page;
    }
}
