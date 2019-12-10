package by.epam.fitness.command.impl.exercise;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
import by.epam.fitness.service.ExerciseProgramService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ExerciseProgramServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.fitness.util.JspConst.EXERCISE_ID;

/**
 * The type Reject exercise command.
 */
public class RejectExerciseCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RejectExerciseCommand.class);
    private ExerciseProgramService exerciseProgramService = new ExerciseProgramServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String exerciseIdString = request.getParameter(EXERCISE_ID);
        if (exerciseIdString==null || !DataValidator.isIdentifiableIdValid(exerciseIdString)) {
            log.info("invalid exercise id format was received:" + exerciseIdString);
            request.setAttribute(JspConst.INVALID_EXERCISE_ID_FORMAT, true);
            return new CommandResult(Page.CLIENT_EXERCISES_COMMAND);
        }
        long exerciseId = Long.parseLong(exerciseIdString);
        try {
            exerciseProgramService.deleteExercise(exerciseId);
            log.info("exercise with id = " + exerciseId + " has been rejected");
            request.getSession().setAttribute(JspConst.EXERCISE_REJECTED, true);
            page = Page.CLIENT_EXERCISES_COMMAND;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.CLIENT_EXERCISES_COMMAND;
        }
        return new CommandResult(page, true);
    }
}
