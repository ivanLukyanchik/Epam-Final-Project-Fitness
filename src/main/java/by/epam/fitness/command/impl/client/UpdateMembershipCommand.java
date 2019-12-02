package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.OrderInformation;
import by.epam.fitness.entity.Client;
import by.epam.fitness.service.OrderInformationService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.impl.OrderInformationServiceImpl;
import by.epam.fitness.service.impl.ClientServiceImpl;
import by.epam.fitness.util.DateProducer;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.UtilException;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.price.MembershipPriceReader;
import by.epam.fitness.util.sale.SaleSystem;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.*;

public class UpdateMembershipCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(UpdateMembershipCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private ClientService clientService = new ClientServiceImpl();
    private OrderInformationService orderInformationService = new OrderInformationServiceImpl();
    private static final String PERIOD_PATTERN="\\D+";
    private final static SaleSystem SALE_SYSTEM = SaleSystem.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String cardNumber = request.getParameter(CARD_NUMBER);
        if (cardNumber==null || !dataValidator.isCardNumberValid(cardNumber)) {
            log.info("incorrect card number:" + cardNumber + " was input");
            request.setAttribute(WRONG_CARD, true);
            return Page.ORDER_PAGE;
        }
        String costString = request.getParameter(COST);
        if (costString==null || !dataValidator.isCostValid(costString)){
            log.info("incorrect cost:" + costString + " was input");
            request.setAttribute(WRONG_PERIOD, true);
            return Page.ORDER_PAGE;
        }
        BigDecimal cost = new BigDecimal(costString);
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(SessionAttributes.ID);
        java.sql.Date newEndMembershipDate;
        try {
            String period = request.getParameter(PERIOD);
            if (period==null || !isPeriodExist(period)){
                log.info("incorrect period:" + period + " was input");
                request.setAttribute(WRONG_PERIOD, true);
                return Page.ORDER_PAGE;
            }
            newEndMembershipDate = defineNewEndMembershipEndDate(request,clientId);
            OrderInformation newOrderInformation = new OrderInformation(null, cost, new Timestamp(new Date().getTime()),
                                                                        newEndMembershipDate, clientId,cardNumber);
            orderInformationService.save(newOrderInformation);
            increaseClientVisitNumber(clientId);
            request.setAttribute(PAYMENT_SUCCESS, true);
            log.info("Gym membership of client with id = " + clientId + " has been updated");
            page = Page.CLIENT_PROFILE_COMMAND;
        } catch (ServiceException | UtilException e) {
            log.error("Exception occurred while defining NewEndMembershipEndDate", e);
            return Page.ORDER_PAGE;
        }
        return page;
    }

    private java.sql.Date defineNewEndMembershipEndDate(HttpServletRequest request, long clientID) throws ServiceException {
        String periodExtension = request.getParameter(PERIOD);
        periodExtension = periodExtension.replaceAll(PERIOD_PATTERN,"");
        Integer periodExtensionInteger = Integer.valueOf(periodExtension);
        Optional<OrderInformation> orderInformation = orderInformationService.findByClientId(clientID);
        Date membershipEndDate = new Date();
        if (orderInformation.isPresent()) {
            membershipEndDate = orderInformation.get().getMembershipEndDate();
        }
        Date newMembershipEndDate = DateProducer.getCorrectDate(membershipEndDate,periodExtensionInteger);
        return new java.sql.Date(newMembershipEndDate.getTime());
    }

    private void increaseClientVisitNumber(long clientId) throws ServiceException {
        Optional<Client> clientOptional = clientService.findById(clientId);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            int currentVisitNumber = client.getMembershipNumber() + 1;
            client.setMembershipNumber(currentVisitNumber);
            Float newPersonalDiscount = SALE_SYSTEM.getSaleByVisitNumber(currentVisitNumber);
            client.setPersonalDiscount(newPersonalDiscount);
            clientService.save(client);
        }
    }

    private boolean isPeriodExist(String  period) throws UtilException {
        period = period.replaceAll(PERIOD_PATTERN,"");
        Integer periodInteger = Integer.valueOf(period);
        MembershipPriceReader membershipPrices = MembershipPriceReader.getInstance();
        return membershipPrices.getPrices().containsKey(periodInteger);
    }
}
