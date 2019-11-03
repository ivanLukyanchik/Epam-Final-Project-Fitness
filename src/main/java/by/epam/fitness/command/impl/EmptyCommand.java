package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static by.epam.fitness.util.page.Page.LOGIN_PAGE;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return LOGIN_PAGE;
    }
}
