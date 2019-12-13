package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
import by.epam.fitness.entity.Client;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ClientServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * The type Show order page command.
 */
public class ShowOrderPageCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ShowOrderPageCommand.class);
    private ClientService clientService = new ClientServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        long clientId = (long) request.getSession().getAttribute(SessionAttributes.ID);
        try {
            Optional<Client> clientOptional = clientService.findById(clientId);
            if (clientOptional.isPresent()) {
                request.setAttribute(JspConst.CLIENT_PERSONAL_SALE, clientOptional.get().getPersonalDiscount());
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
        }
        return new CommandResult(Page.ORDER_PAGE);
    }
}