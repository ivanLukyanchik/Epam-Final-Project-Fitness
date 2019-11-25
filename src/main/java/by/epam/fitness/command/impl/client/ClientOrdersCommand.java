package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.OrderInformation;
import by.epam.fitness.entity.UserRole;
import by.epam.fitness.service.OrderInformationService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.OrderInformationServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.epam.fitness.util.JspConst.COACH_CLIENT_ID;

public class ClientOrdersCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ClientOrdersCommand.class);
    private OrderInformationService orderInformationService = new OrderInformationServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        HttpSession session = request.getSession();
        String role = String.valueOf(session.getAttribute(SessionAttributes.ROLE));
        Long clientId = null;
        if (role.equals(UserRole.COACH)) {
            clientId = getClientIdForAppropriateCoach(session, request);
        } else {
            clientId = (Long) session.getAttribute(SessionAttributes.ID);
        }
        try {
            List<OrderInformation> ordersList = orderInformationService.findOrdersByClientId(clientId);
            session.setAttribute(JspConst.ORDERS, ordersList);
            page = Page.CLIENT_ORDERS;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.WELCOME_PAGE;
        }
        return page;
    }

    private Long getClientIdForAppropriateCoach(HttpSession session, HttpServletRequest request) {
        String clientIdString = request.getParameter(COACH_CLIENT_ID);
        Long clientId;
        if (clientIdString == null) {
            clientId = (Long) session.getAttribute(COACH_CLIENT_ID);
        } else {
            clientId= Long.valueOf(request.getParameter(COACH_CLIENT_ID));
            session.setAttribute(COACH_CLIENT_ID,clientId);
        }
        return clientId;
    }
}
