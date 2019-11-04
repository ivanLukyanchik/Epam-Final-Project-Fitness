package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.util.page.Page;

import javax.servlet.http.HttpServletRequest;

public class GymPhotosCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return Page.GYM_PHOTOS_PAGE;
    }
}
