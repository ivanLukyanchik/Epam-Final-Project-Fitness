package by.epam.fitness.command.impl.coach;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.impl.client.RejectCoachCommand;
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
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.epam.fitness.util.JspConst.ALL_CLIENTS;

public class CoachClientsCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RejectCoachCommand.class);
    private ClientService clientService = new ClientServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute(SessionAttributes.ID);
        try {
            List<Client> allClients = clientService.findByCoachId(id);
            session.setAttribute(ALL_CLIENTS, allClients);
            page = Page.COACH_CLIENTS;
        } catch (ServiceException e) {
            log.error("Service exception occurred", e);
            return Page.COACH_CLIENTS;
        }
        return page;
    }
}
