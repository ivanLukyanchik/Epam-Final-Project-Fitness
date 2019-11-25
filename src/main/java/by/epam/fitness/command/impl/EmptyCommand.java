package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.fitness.util.page.Page.LOGIN_PAGE;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return LOGIN_PAGE;
    }
}
