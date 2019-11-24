package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.service.ExerciseService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ExerciseServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.fitness.util.JspConst.EXERCISE_ID;

public class DeleteExerciseCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(DeleteExerciseCommand.class);
    private ExerciseService exerciseService = new ExerciseServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String exerciseIdString = request.getParameter(EXERCISE_ID);
        long exerciseId = Long.parseLong(exerciseIdString);
        try {
            exerciseService.deleteExercise(exerciseId);
            log.info("exercise with id = " + exerciseId + " was successfully deleted");
            request.setAttribute(JspConst.EXERCISE_DELETED, true);
            page = Page.ADMIN_EXERCISES_COMMAND;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ADMIN_EXERCISES;
        }
        return page;
    }
}
