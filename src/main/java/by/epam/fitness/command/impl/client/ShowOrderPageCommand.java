package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.util.page.Page;

import javax.servlet.http.HttpServletRequest;

public class ShowOrderPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return Page.ORDER_PAGE;
    }
}