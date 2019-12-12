package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
import by.epam.fitness.util.CookieConst;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.*;

/**
 * The type Logout command.
 */
public class LogoutCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(LogoutCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
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
        }
        session.invalidate();
        log.info("user with id = " + userId + " and role = " + userRole + " log out");
        return new CommandResult(Page.LOGIN_PAGE);
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
}
