package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
import by.epam.fitness.entity.Client;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ClientServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The type Admin clients command.
 */
public class AdminClientsCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AdminClientsCommand.class);
    private ClientService clientService = new ClientServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        try {
            List<Client> clients = clientService.findAll();
            request.setAttribute(JspConst.ALL_CLIENTS, clients);
            page = Page.ADMIN_CLIENTS;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ADMIN_CLIENTS;
        }
        return new CommandResult(page);
    }
}
