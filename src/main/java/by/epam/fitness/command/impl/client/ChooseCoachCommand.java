package by.epam.fitness.command.impl.client;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.entity.User;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.CoachServiceImpl;
import by.epam.fitness.service.impl.UserServiceImpl;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.COACH_ID;

public class ChooseCoachCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(ChooseCoachCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String coachIdString = request.getParameter(COACH_ID);
        if (coachIdString == null || !dataValidator.isIdentifiableIdValid(coachIdString)){
            log.info("invalid coach id format: coach_id:" + coachIdString);
            request.setAttribute("invalidCoachId", true);
            return Page.ALL_COACHES;
        }
        Long coachId = Long.valueOf(coachIdString);
        try {
            if (!isCoachIdExist(coachId)) {
                log.info("coach with id = " + coachId + " doesn't exist");
                request.setAttribute("notExistId", true);
                return Page.ALL_COACHES;
            }
            HttpSession session = request.getSession();
            Long clientId = (Long) session.getAttribute(SessionAttributes.ID);
            Optional<User> user = userService.findById(clientId);
            if (user.isPresent()) {
                user.get().setCoachId(coachId);
                userService.save(user.get());
            }
            log.info("coach with id  = " + coachId + " was chosen");
            request.setAttribute("coachChosen", true);
            page = Page.WELCOME_PAGE;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ALL_COACHES;
        }
        return page;
    }

    private boolean isCoachIdExist(Long coachId) throws ServiceException {
        CoachService coachService = new CoachServiceImpl();
        Optional<Coach> coach = coachService.findById(coachId);
        return coach.isPresent();
    }
}
