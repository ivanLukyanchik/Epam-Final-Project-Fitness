package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Exercise;
import by.epam.fitness.service.ExerciseService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ExerciseServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminExercisesCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AdminExercisesCommand.class);
    private ExerciseService exerciseService = new ExerciseServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        try {
            List<Exercise> exercises = exerciseService.findAll();
            request.setAttribute(JspConst.All_EXERCISES, exercises);
            page = Page.ADMIN_EXERCISES;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ADMIN_EXERCISES;
        }
        return page;
    }
}
