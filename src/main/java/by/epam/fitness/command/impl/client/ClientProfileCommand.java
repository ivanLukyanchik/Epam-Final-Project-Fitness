package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Client;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.entity.OrderInformation;
import by.epam.fitness.entity.UserRole;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.OrderInformationService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ClientServiceImpl;
import by.epam.fitness.service.impl.CoachServiceImpl;
import by.epam.fitness.service.impl.OrderInformationServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.MembershipValidChecker;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.ADMIN_CLIENT_ID;

public class ClientProfileCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ClientProfileCommand.class);
    private OrderInformationService orderInformationService = new OrderInformationServiceImpl();
    private ClientService clientService = new ClientServiceImpl();
    private CoachService coachService = new CoachServiceImpl();
    private MembershipValidChecker membershipValidChecker = new MembershipValidChecker();
    private static DataValidator dataValidator = new DataValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        HttpSession session = request.getSession();
        String role = String.valueOf(session.getAttribute(SessionAttributes.ROLE));
        Long clientId;
        if (role.equals(UserRole.CLIENT)) {
            clientId = (Long) session.getAttribute(SessionAttributes.ID);
        } else {
            clientId = getClientIdForAppropriateAdmin(session, request);
            if (clientId == -1L) {
                return Page.ADMIN_CLIENTS;
            }
        }
        try {
            List<OrderInformation> ordersList = orderInformationService.findOrdersByClientId(clientId);
            request.setAttribute(JspConst.ORDERS, ordersList);
            Optional<Client> userOptional = clientService.findById(clientId);
            if (userOptional.isPresent()) {
                Client client = userOptional.get();
                request.setAttribute(JspConst.CLIENT, client);
                Optional<OrderInformation> orderInformation = orderInformationService.findByClientId(clientId);
                if (orderInformation.isPresent()) {
                    request.setAttribute(JspConst.MEMBERSHIP_VALID, membershipValidChecker.isCurrentMembershipValid(client.getId()));
                }
                Long coachId = client.getCoachId();
                Optional<Coach> coach = coachService.findById(coachId);
                coach.ifPresent(aCoach -> {
                    request.setAttribute(JspConst.COACH_NAME, aCoach.getName());
                    request.setAttribute(JspConst.COACH_SURNAME, aCoach.getSurname());
                });
            }
            page = Page.CLIENT_PROFILE_PAGE;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.CLIENT_PROFILE_PAGE;
        }
        return page;
    }

    private Long getClientIdForAppropriateAdmin(HttpSession session, HttpServletRequest request) {
        String clientIdString = request.getParameter(ADMIN_CLIENT_ID);
        Long clientId;
        if (clientIdString == null) {
            clientId = (Long) session.getAttribute(ADMIN_CLIENT_ID);
        } else {
            clientIdString = request.getParameter(ADMIN_CLIENT_ID);
            if (!dataValidator.isIdentifiableIdValid(clientIdString)) {
                log.info("invalid client id format from admin was received:" + clientIdString);
                request.setAttribute(JspConst.INVALID_EXERCISE_ID_FORMAT, true);
                return -1L;
            }
            clientId= Long.valueOf(clientIdString);
            session.setAttribute(ADMIN_CLIENT_ID,clientId);
        }
        return clientId;
    }
}
