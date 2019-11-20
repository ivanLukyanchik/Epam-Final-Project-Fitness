package by.epam.fitness.command.impl.nutrition;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Nutrition;
import by.epam.fitness.entity.UserRole;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.NutritionService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.CoachServiceImpl;
import by.epam.fitness.service.impl.NutritionServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.MembershipValidChecker;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.*;

public class ShowClientNutritionCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ShowClientNutritionCommand.class);
    private MembershipValidChecker membershipValidChecker = new MembershipValidChecker();
    private NutritionService nutritionService = new NutritionServiceImpl();
    private CoachService coachService = new CoachServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String role = String.valueOf(session.getAttribute(SessionAttributes.ROLE));
        request.setAttribute(MAX_NUMBER_SYMBOLS_ATTRIBUTE,MAX_NUMBER_SYMBOLS_VALUE);
        Long userId = null;
        try {
            if (role.equals(UserRole.COACH)) {
                userId = getClientIdForAppropriateCoach(session,request);
            } else {
                userId = (Long) session.getAttribute(SessionAttributes.ID);
                if (!membershipValidChecker.isCurrentMembershipValid(userId)) {
                    request.setAttribute(MEMBERSHIP_VALID, false);
                    return Page.NUTRITION;
                } else {
                    if (coachService.findByClientId(userId).isEmpty()) {
                        request.setAttribute(JspConst.NO_COACH, true);
                        return Page.NUTRITION;
                    }
                    request.setAttribute(MEMBERSHIP_VALID, true);
                }
            }
            Optional<Nutrition> nutritionOptional = nutritionService.findByClientId(userId);
            if (nutritionOptional.isPresent()) {
                if (!nutritionOptional.get().isActive()) {
                    request.setAttribute(NO_NUTRITION, true);
                    request.setAttribute(NUTRITION, nutritionOptional.get());//для активации
                } else {
                    request.setAttribute(NUTRITION, nutritionOptional.get());
                }
            }
            page = Page.NUTRITION;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.NUTRITION;
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
