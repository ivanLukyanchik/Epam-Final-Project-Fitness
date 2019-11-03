package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.impl.RegisterCommand;
import by.epam.fitness.entity.User;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.fitness.util.JspConst.*;
import static by.epam.fitness.util.JspConst.PARAM_EMAIL;

public class ModifyProfileCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RegisterCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String name = request.getParameter(PARAM_NAME);
        if (name==null || !dataValidator.isNameValid(name)) {
            log.info("invalid name format was received:" + name);
            request.setAttribute("invalidName", "Wrong name");
            return Page.CLIENT_PROFILE_PAGE;
        }
        String surname = request.getParameter(PARAM_SURNAME);
        if (surname==null || !dataValidator.isSurnameValid(surname)) {
            log.info("invalid name format was received:" + surname);
            request.setAttribute("invalidSurname", "Wrong surname");
            return Page.CLIENT_PROFILE_PAGE;
        }
        String login = request.getParameter(PARAM_LOGIN);
        if (login==null || !dataValidator.isLoginValid(login)) {
            log.info("invalid login format was received:" + login);
            request.setAttribute("invalidLogin", "Wrong login");
            return Page.CLIENT_PROFILE_PAGE;
        }
        String email = request.getParameter(PARAM_EMAIL);
        if (email==null || !dataValidator.isEmailValid(email)){
            log.info("invalid email format was received:" + email);
            request.setAttribute("invalidEmail", "Wrong email");
            return Page.CLIENT_PROFILE_PAGE;
        }
        User user = (User) request.getSession().getAttribute(SessionAttributes.CLIENT);
        String oldLogin = user.getLogin();
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setEmail(email);
        try {
            if (userService.updateUser(user, oldLogin)) {
                log.info("User info was successfully changed");
                request.setAttribute("success", "success");
                page = Page.CLIENT_PROFILE_PAGE;
            } else {
                request.setAttribute("wrongData", "User with this login already exists");
                page = Page.CLIENT_PROFILE_PAGE;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.CLIENT_PROFILE_PAGE;
        }
        return page;
    }
}
