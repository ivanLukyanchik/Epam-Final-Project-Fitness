package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;

import javax.servlet.http.HttpServletRequest;

import static by.epam.fitness.util.JspConst.CHANGED_LOCALE;
import static by.epam.fitness.util.JspConst.MESSAGE;

public class LocaleCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String lang = request.getParameter(JspConst.LANGUAGE);
        request.getSession().setAttribute(JspConst.LOCAL, lang);
        request.getSession().setAttribute(MESSAGE, CHANGED_LOCALE);
        request.setAttribute(MESSAGE, CHANGED_LOCALE);
        String page = request.getParameter(JspConst.CURRENT_PAGE);
        if (page == null || page.isEmpty()) {
            return Page.LOGIN_PAGE;
        } else if (page.equals(JspConst.LOGIN_PAGE)) {
            return Page.LOGIN_PAGE;
        } else if (page.equals(JspConst.WELCOME_PAGE)) {
            return Page.WELCOME_PAGE;
        } else if (page.equals(JspConst.REGISTER_PAGE)) {
            return Page.REGISTER_PAGE;
        } else if (page.equals(JspConst.PASSWORD_RESTORE_PAGE)) {
            return Page.PASSWORD_RESTORE_PAGE;
        } else if (page.equals(JspConst.RESTORE_PAGE)) {
            return Page.RESTORE_PAGE;
        } else if (page.equals(JspConst.CLIENT_PROFILE_PAGE)) {
            return Page.CLIENT_PROFILE_PAGE;
        } else if (page.equals(JspConst.GYM_PHOTOS_PAGE)) {
            return Page.GYM_PHOTOS_PAGE;
        } else if (page.equals(JspConst.ORDER_PAGE)) {
            return Page.ORDER_PAGE;
        } else if (page.equals(JspConst.CLIENT_ORDERS)) {
            return Page.CLIENT_ORDERS;
        } else if (page.equals(JspConst.ALL_COACHES)) {
            return Page.ALL_COACHES;
        } else if (page.equals(JspConst.EXERCISES)) {
            return Page.EXERCISES;
        } else if (page.equals(JspConst.NUTRITION)) {
            return Page.NUTRITION;
        } else if (page.equals(JspConst.COACH_CLIENTS)) {
            return Page.COACH_CLIENTS;
        } else if (page.equals(JspConst.COACH_COMMENTS)) {
            return Page.COACH_COMMENTS;
        } else if (page.equals(JspConst.VERIFICATION_PAGE)) {
            return Page.VERIFY_PAGE;
        } else if (page.equals(JspConst.NO_ACCESS)) {
            return Page.NO_ACCESS_PAGE;
        }
        return Page.LOGIN_PAGE;
    }
}
