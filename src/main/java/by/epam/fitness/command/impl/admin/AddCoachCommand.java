package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.CoachServiceImpl;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.fitness.util.JspConst.*;

/**
 * The type Add coach command.
 */
public class AddCoachCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddCoachCommand.class);
    private CoachService coachService = new CoachServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String name = request.getParameter(PARAM_NAME);
        if (name==null || !DataValidator.isNameValid(name)) {
            log.info("invalid name format was received:" + name);
            request.setAttribute(INVALID_NAME, true);
            return new CommandResult(Page.ADMIN_COACHES_COMMAND);
        }
        String surname = request.getParameter(PARAM_SURNAME);
        if (surname==null || !DataValidator.isSurnameValid(surname)) {
            log.info("invalid name format was received:" + surname);
            request.setAttribute(INVALID_SURNAME, true);
            return new CommandResult(Page.ADMIN_COACHES_COMMAND);
        }
        String patronymic = request.getParameter(PARAM_PATRONYMIC);
        if (patronymic==null || !DataValidator.isPatronymicValid(patronymic)) {
            log.info("invalid patronymic format was received:" + patronymic);
            request.setAttribute(INVALID_PATRONYMIC, true);
            return new CommandResult(Page.ADMIN_COACHES_COMMAND);
        }
        String login = request.getParameter(PARAM_LOGIN);
        if (login==null || !DataValidator.isLoginValid(login)) {
            log.info("invalid login format was received:" + login);
            request.setAttribute(INVALID_LOGIN, true);
            return new CommandResult(Page.ADMIN_COACHES_COMMAND);
        }
        String password = request.getParameter(PARAM_PASSWORD);
        if (password==null || !DataValidator.isPasswordValid(password)) {
            log.info("invalid password format was received:" + password);
            request.setAttribute(INVALID_PASSWORD, true);
            return new CommandResult(Page.ADMIN_COACHES_COMMAND);
        }
        Coach coach = makeCoach(request);
        try {
            long coachId = coachService.save(coach);
            log.info("Coach with id = " + coachId + " successfully saved");
            request.getSession().setAttribute(SUCCESS, true);
            page = Page.ADMIN_COACHES_COMMAND;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ADMIN_COACHES_COMMAND;
        }
        return new CommandResult(page, true);
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
