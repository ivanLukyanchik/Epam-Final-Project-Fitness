package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
import by.epam.fitness.entity.Client;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ClientServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.ADMIN_CLIENT_ID;

/**
 * The type Change client active command.
 */
public class ChangeClientActiveCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ChangeClientActiveCommand.class);
    private ClientService clientService = new ClientServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        String clientIdString = request.getParameter(ADMIN_CLIENT_ID);
        if (clientIdString == null || !DataValidator.isIdentifiableIdValid(clientIdString)) {
            log.info("invalid client id format was received:" + clientIdString);
            request.setAttribute(JspConst.INVALID_EXERCISE_ID_FORMAT, true);
            return new CommandResult(Page.ADMIN_CLIENTS_COMMAND);
        }
        try {
            Optional<Client> clientOptional = clientService.findById(Long.parseLong(clientIdString));
            if (clientOptional.isPresent()) {
                Client client = clientOptional.get();
                if (client.isActive()) {
                    client.setActive(false);
                } else {
                    client.setActive(true);
                }
                clientService.save(client);
                request.getSession().setAttribute(JspConst.SUCCESS, true);
                page = Page.ADMIN_CLIENTS_COMMAND;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ADMIN_CLIENTS_COMMAND;
        }
        return new CommandResult(page, true);
    }
}
