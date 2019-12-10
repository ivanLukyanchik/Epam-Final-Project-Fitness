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
 * The type Find clients by filter command.
 */
public class FindClientsByFilterCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(FindClientsByFilterCommand.class);
    private ClientService clientService = new ClientServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        Integer membershipNumber;
        Float personalDiscount;
        String page;
        String name = request.getParameter(JspConst.PARAM_NAME);
        String surname = request.getParameter(JspConst.PARAM_SURNAME);
        String login = request.getParameter(JspConst.PARAM_LOGIN);
        String membershipNumberString = request.getParameter(JspConst.MEMBERSHIP_PURCHASED_NUMBER);
        String personalDiscountString = request.getParameter(JspConst.PERSONAL_DISCOUNT);

        if (name.isBlank()) {
            name = null;
        }
        if (surname.isBlank()) {
            surname = null;
        }
        if (login.isBlank()) {
            login = null;
        }
        if (membershipNumberString.isBlank()) {
            membershipNumber = null;
        } else {
            membershipNumber = Integer.parseInt(membershipNumberString);
        }
        if (personalDiscountString.isBlank()) {
            personalDiscount = null;
        } else {
            personalDiscount = Float.parseFloat(personalDiscountString);
        }
        Client clientForData = new Client();
        clientForData.setName(name);
        clientForData.setSurname(surname);
        clientForData.setLogin(login);
        clientForData.setMembershipNumber(membershipNumber);
        clientForData.setPersonalDiscount(personalDiscount);
        try {
            List<Client> clients = clientService.findByFilter(clientForData);
            request.setAttribute(JspConst.ALL_CLIENTS, clients);
            page = Page.ADMIN_CLIENTS;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ADMIN_CLIENTS_COMMAND;
        }
        return new CommandResult(page);
    }
}
