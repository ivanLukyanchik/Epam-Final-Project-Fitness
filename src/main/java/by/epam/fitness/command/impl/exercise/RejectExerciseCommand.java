package by.epam.fitness.command.impl.exercise;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.service.ExerciseProgramService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ExerciseProgramServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.fitness.util.JspConst.EXERCISE_ID;

public class RejectExerciseCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RejectExerciseCommand.class);
    private ExerciseProgramService exerciseProgramService = new ExerciseProgramServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String exerciseIdString = request.getParameter(EXERCISE_ID);
        long exerciseId = Long.parseLong(exerciseIdString);
        try {
            exerciseProgramService.deleteExercise(exerciseId);
            log.info("exercise with id = " + exerciseId + " has been rejected");
            request.setAttribute(JspConst.EXERCISE_REJECTED, true);
            page = "/controller?command=show_client_exercises";
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.EXERCISES;
        }
        return page;
    }
}
