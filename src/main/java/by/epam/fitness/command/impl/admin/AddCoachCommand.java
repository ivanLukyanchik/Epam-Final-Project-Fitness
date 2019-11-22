package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.CoachServiceImpl;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.fitness.util.JspConst.*;

public class AddCoachCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddCoachCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private CoachService coachService = new CoachServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String name = request.getParameter(PARAM_NAME);
        if (name==null || !dataValidator.isNameValid(name)) {
            log.info("invalid name format was received:" + name);
            request.setAttribute(INVALID_NAME, true);
            return Page.ADMIN_COACHES;
        }
        String surname = request.getParameter(PARAM_SURNAME);
        if (surname==null || !dataValidator.isSurnameValid(surname)) {
            log.info("invalid name format was received:" + surname);
            request.setAttribute(INVALID_SURNAME, true);
            return Page.ADMIN_COACHES;
        }
        String patronymic = request.getParameter(PARAM_PATRONYMIC);
        if (patronymic==null || !dataValidator.isPatronymicValid(patronymic)) {
            log.info("invalid patronymic format was received:" + patronymic);
            request.setAttribute(INVALID_PATRONYMIC, true);
            return Page.ADMIN_COACHES;
        }
        String login = request.getParameter(PARAM_LOGIN);
        if (login==null || !dataValidator.isLoginValid(login)) {
            log.info("invalid login format was received:" + login);
            request.setAttribute(INVALID_LOGIN, true);
            return Page.ADMIN_COACHES;
        }
        String password = request.getParameter(PARAM_PASSWORD);
        if (password==null || !dataValidator.isPasswordValid(password)) {
            log.info("invalid password format was received:" + password);
            request.setAttribute(INVALID_PASSWORD, true);
            return Page.ADMIN_COACHES;
        }
        Coach coach = makeCoach(request);
        try {
            long coachId = coachService.save(coach);
            log.info("Coach with id = " + coachId + " successfully saved");
            request.setAttribute(SUCCESS, true);
            page = Page.ADMIN_COACHES_COMMAND;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ADMIN_COACHES;
        }
        return page;
    }

    private Coach makeCoach(HttpServletRequest request) {
        String name = request.getParameter(PARAM_NAME);
        String surname = request.getParameter(PARAM_SURNAME);
        String patronymic = request.getParameter(PARAM_PATRONYMIC);
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        return new Coach(null, name, surname, patronymic, login, password);
    }
}
