package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return "/login";
    }
}
