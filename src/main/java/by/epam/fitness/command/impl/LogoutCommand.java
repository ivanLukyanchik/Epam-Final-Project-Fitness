package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(SessionAttributes.ID);
        String userRole = (String) session.getAttribute(SessionAttributes.ROLE);
        session.removeAttribute(SessionAttributes.USER);
        session.removeAttribute(SessionAttributes.ROLE);
        session.removeAttribute(SessionAttributes.ID);
        if (session.getAttribute(SessionAttributes.CLIENT) != null) {
            session.removeAttribute(SessionAttributes.CLIENT);
        } else {
            session.removeAttribute(SessionAttributes.COACH);
        }
        session.invalidate();
        log.info("client with id = " + userId + " and role = " + userRole + " log out");
        return Page.LOGIN_PAGE;
    }
}
