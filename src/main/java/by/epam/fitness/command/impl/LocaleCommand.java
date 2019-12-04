package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.fitness.util.JspConst.CHANGED_LOCALE;
import static by.epam.fitness.util.JspConst.MESSAGE;

public class LocaleCommand implements ActionCommand {
    private static final String COMMAND = "/controller?command=";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter(JspConst.LANGUAGE);
        request.getSession().setAttribute(JspConst.LOCAL, lang);
        request.getSession().setAttribute(MESSAGE, CHANGED_LOCALE); // FIXME: 25.11.2019 удалить 2 след строки
        request.setAttribute(MESSAGE, CHANGED_LOCALE);
        String page = request.getParameter(JspConst.CURRENT_PAGE);
        if (page == null || page.isEmpty()) {
            return Page.LOGIN_PAGE;
        } else if (page.equals(JspConst.PASSWORD_RESTORE_PAGE)) {
            return Page.PASSWORD_RESTORE_PAGE;
        } else if (page.equals(JspConst.RESTORE_PAGE)) {
            return Page.RESTORE_PAGE;
        } else if (page.equals(JspConst.VERIFICATION_PAGE)) {
            return Page.VERIFY_PAGE;
        } else if (page.equals(JspConst.FINAL_RESTORE_PAGE)) {
            return Page.FINAL_RESTORE_PAGE;
        }
        return COMMAND + page;
    }
}
