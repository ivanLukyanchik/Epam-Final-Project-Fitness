package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.util.page.Page;

import javax.servlet.http.HttpServletRequest;

public class ClientProfileCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        // FIXME: 02.11.2019 Check role, session etc.
        return Page.CLIENT_PROFILE_PAGE;
    }
}
