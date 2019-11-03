package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.util.page.Page;

import javax.servlet.http.HttpServletRequest;

public class NoAccessCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return Page.NO_ACCESS_PAGE;
    }
}
