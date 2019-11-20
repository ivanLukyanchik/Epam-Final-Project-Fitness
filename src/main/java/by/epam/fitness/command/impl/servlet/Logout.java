package by.epam.fitness.command.impl.servlet;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.util.CookieConst;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logoutUser")
public class Logout extends HttpServlet implements ActionCommand {
    private static Logger log = LogManager.getLogger(Logout.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = executeLogout(request, response);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public String executeLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(SessionAttributes.ID);
        String userRole = (String) session.getAttribute(SessionAttributes.ROLE);
        session.removeAttribute(SessionAttributes.USER);
        session.removeAttribute(SessionAttributes.ROLE);
        session.removeAttribute(SessionAttributes.ID);
        clearCookie(CookieConst.TOKEN, request, response);
        if (session.getAttribute(SessionAttributes.CLIENT) != null) {
            session.removeAttribute(SessionAttributes.CLIENT);
            clearCookie(CookieConst.CLIENT_LOGIN, request, response);
        } else {
            session.removeAttribute(SessionAttributes.COACH);
        }
        session.invalidate();
        log.info("client with id = " + userId + " and role = " + userRole + " log out");
        return Page.LOGIN_PAGE;
    }

    private void clearCookie(String cookieName, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                cookie.setPath("/");
                cookie.setMaxAge(0);
                cookie.setValue(null);
                response.addCookie(cookie);
                break;
            }
        }
    }

    @Override
    public String execute(HttpServletRequest request) {
        return JspConst.LOGOUT_PAGE;
    }
}
