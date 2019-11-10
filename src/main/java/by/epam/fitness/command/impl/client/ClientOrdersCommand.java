package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.OrderInformation;
import by.epam.fitness.service.OrderInformationService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.OrderInformationServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ClientOrdersCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ClientOrdersCommand.class);
    private OrderInformationService orderInformationService = new OrderInformationServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(SessionAttributes.ID);
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
}
