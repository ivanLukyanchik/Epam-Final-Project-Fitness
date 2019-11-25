package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Client;
import by.epam.fitness.entity.OrderInformation;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.OrderInformationService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ClientServiceImpl;
import by.epam.fitness.service.impl.OrderInformationServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdminOrdersCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AdminOrdersCommand.class);
    private OrderInformationService orderInformationService = new OrderInformationServiceImpl();
    private ClientService clientService = new ClientServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        try {
            List<OrderInformation> orders = orderInformationService.findAll();
            Map<OrderInformation, Client> orderClientMap =  makeOrderMapForAdmin(orders);
            request.setAttribute(JspConst.ORDERS, orderClientMap);
            page = Page.ADMIN_ORDERS;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ADMIN_ORDERS;
        }
        return page;
    }

    private Map<OrderInformation, Client> makeOrderMapForAdmin(List<OrderInformation> orders) throws ServiceException {
        Map<OrderInformation, Client> orderClientMap = new HashMap<>();
        for (OrderInformation order : orders) {
            Optional<Client> optionalUser = clientService.findById(order.getClientId());
            optionalUser.ifPresent(user -> orderClientMap.put(order, user));
        }
        return orderClientMap;
    }
}
