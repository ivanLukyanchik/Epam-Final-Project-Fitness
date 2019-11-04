package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttributes.USER);
        session.removeAttribute(SessionAttributes.ROLE);
        if (session.getAttribute(SessionAttributes.CLIENT) != null) {
            session.removeAttribute(SessionAttributes.CLIENT);
        } else {
            session.removeAttribute(SessionAttributes.COACH);
        }
        session.invalidate();
        return Page.LOGIN_PAGE;
    }
}
