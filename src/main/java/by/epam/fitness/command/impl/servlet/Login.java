package by.epam.fitness.command.impl.servlet;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Admin;
import by.epam.fitness.entity.Client;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.entity.UserRole;
import by.epam.fitness.service.AdminService;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.impl.AdminServiceImpl;
import by.epam.fitness.service.impl.CoachServiceImpl;
import by.epam.fitness.service.impl.ClientServiceImpl;
import by.epam.fitness.util.CookieConst;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.fitness.util.JspConst.PARAM_LOGIN;
import static by.epam.fitness.util.JspConst.PARAM_PASSWORD;

@WebServlet("/loginUser")
public class Login extends HttpServlet implements ActionCommand {
    private static Logger log = LogManager.getLogger(Login.class);
    private static DataValidator dataValidator = new DataValidator();
    private static ClientService clientService = new ClientServiceImpl();
    private static CoachService coachService = new CoachServiceImpl();
    private static AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = executeLogin(request, response);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

    public String executeLogin(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        Client client = null;
        Coach coach = null;
        Admin admin = null;
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
        boolean rememberMe = Boolean.parseBoolean(request.getParameter(JspConst.REMEMBER_ME));
        try {
            if (clientService.checkUserByLoginPassword(login, password).isPresent()) {
                client = clientService.checkUserByLoginPassword(login, password).get();
                request.getSession().setAttribute(SessionAttributes.CLIENT, client);
                request.getSession().setAttribute(SessionAttributes.USER, login);
                request.getSession().setAttribute(SessionAttributes.ROLE, UserRole.CLIENT);
                request.getSession().setAttribute(SessionAttributes.ID, client.getId());
                if (rememberMe) {
                    Cookie cookieLogin = new Cookie(CookieConst.CLIENT_LOGIN, login);
                    cookieLogin.setMaxAge(CookieConst.EXPIRY);
                    cookieLogin.setPath("/");
                    response.addCookie(cookieLogin);
                    Cookie cookieToken = new Cookie(CookieConst.TOKEN, client.getUserHash());
                    cookieToken.setMaxAge(CookieConst.EXPIRY);
                    cookieToken.setPath("/");
                    response.addCookie(cookieToken);
                }
                log.info("client with id = " + client.getId() + " log in. RememberMe = " + rememberMe);
                page = Page.WELCOME_PAGE;
            } else if (coachService.checkCoachByLoginPassword(login, password).isPresent()) {
                coach = coachService.checkCoachByLoginPassword(login, password).get();
                request.getSession().setAttribute(SessionAttributes.COACH, coach);
                request.getSession().setAttribute(SessionAttributes.USER, login);
                request.getSession().setAttribute(SessionAttributes.ROLE, UserRole.COACH);
                request.getSession().setAttribute(SessionAttributes.ID, coach.getId());
                log.info("coach with id = " + coach.getId() + " log in");
                page = Page.WELCOME_PAGE;
            } else if (adminService.checkAdminByLoginPassword(login, password).isPresent()) {
                admin = adminService.checkAdminByLoginPassword(login, password).get();
                request.getSession().setAttribute(SessionAttributes.ADMIN, admin);
                request.getSession().setAttribute(SessionAttributes.USER, login);
                request.getSession().setAttribute(SessionAttributes.ROLE, UserRole.ADMIN);
                request.getSession().setAttribute(SessionAttributes.ID, admin.getId());
                log.info("admin with id = " + admin.getId() + " log in");
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

    @Override
    public String execute(HttpServletRequest request) {
        return JspConst.LOGIN_PAGE;
    }
}
