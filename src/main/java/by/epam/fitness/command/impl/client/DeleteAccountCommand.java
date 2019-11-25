package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Client;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.impl.ClientServiceImpl;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.SUCCESS;

public class DeleteAccountCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(DeleteAccountCommand.class);
    private ClientService clientService = new ClientServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        Long clientId = (Long) request.getSession().getAttribute(SessionAttributes.ID);
        try {
            Optional<Client> clientOptional = clientService.findById(clientId);
            if (clientOptional.isPresent()) {
                Client client = clientOptional.get();
                client.setActive(false);
                    clientService.save(client);
                    log.info("client with id = "+ clientId + " successfully deleted his profile");
                    request.setAttribute(SUCCESS, true);
                page = Page.LOGOUT_COMMAND;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.CLIENT_PROFILE_PAGE;
        }
        return page;
    }
}
