package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.entity.OrderInformation;
import by.epam.fitness.entity.User;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.OrderInformationService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.CoachServiceImpl;
import by.epam.fitness.service.impl.OrderInformationServiceImpl;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.MembershipValidChecker;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class ClientProfileCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ClientProfileCommand.class);
    private OrderInformationService orderInformationService = new OrderInformationServiceImpl();
    private UserService userService = new UserServiceImpl();
    private CoachService coachService = new CoachServiceImpl();
    private MembershipValidChecker membershipValidChecker = new MembershipValidChecker();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        Long clientId = (Long) session.getAttribute(SessionAttributes.ID);
        try {
            List<OrderInformation> ordersList = orderInformationService.findOrdersByClientId(clientId);
            session.setAttribute(JspConst.ORDERS, ordersList);
            Optional<User> userOptional = userService.findById(clientId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                request.setAttribute(JspConst.CLIENT, user);
                Optional<OrderInformation> orderInformation = orderInformationService.findByClientId(clientId);
                if (orderInformation.isPresent()) {
                    session.setAttribute(JspConst.MEMBERSHIP_VALID, membershipValidChecker.isCurrentMembershipValid(user.getId()));
                }
                Long coachId = user.getCoachId();
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
}
