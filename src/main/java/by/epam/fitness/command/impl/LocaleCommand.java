package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static by.epam.fitness.util.JspConst.*;

public class LocaleCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String lang = request.getParameter(LANGUAGE);
        request.getSession().setAttribute(LOCAL, lang);
        request.getSession().setAttribute(MESSAGE, CHANGED_LOCALE);
        request.setAttribute(MESSAGE, CHANGED_LOCALE);
        String page = request.getParameter(CURRENT_PAGE);
        if (page == null || page.isEmpty()) {
            return "/login";
        } else if (page.equals(LOGIN_PAGE)) {
            return "/login";
        } else if (page.equals(WELCOME_PAGE)) {
            return "/welcome";
        } else if (page.equals(REGISTER_PAGE)) {
            return "/register";
        } else if (page.equals(PASSWORD_RESTORE_PAGE))  {
            return "/passwordRestore";
        } else if (page.equals(RESTORE_PAGE))  {
            return "/restore";
        }
        return "/login";
    }
}
