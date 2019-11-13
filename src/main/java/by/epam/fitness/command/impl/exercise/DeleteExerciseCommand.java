package by.epam.fitness.command.impl.exercise;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.ExerciseProgram;
import by.epam.fitness.entity.UserRole;
import by.epam.fitness.service.ExerciseProgramService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ExerciseProgramServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static by.epam.fitness.util.JspConst.EXERCISE_DTO_ID;

public class DeleteExerciseCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(DeleteExerciseCommand.class);
    private ExerciseProgramService exerciseProgramService = new ExerciseProgramServiceImpl();
    private DataValidator dataValidator = new DataValidator();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionAttributes.ROLE).equals(UserRole.COACH)){
            // FIXME: 12.11.2019 continue for coach
        }
        String exerciseDtoIdString = request.getParameter(EXERCISE_DTO_ID); // FIXME: 12.11.2019 везде DTO убрать!!!!
        if (!dataValidator.isIdentifiableIdValid(exerciseDtoIdString)) {
            log.info("incorrect exercise id was received:" + exerciseDtoIdString);
            request.setAttribute(JspConst.INVALID_EXERCISE_ID_FORMAT, true); //все это на стр предусмотреть
            return Page.EXERCISES;
        }
        Long exerciseDtoId = Long.valueOf(exerciseDtoIdString);
        try {
            if (!isExerciseExist(exerciseDtoId)) {
                log.info("exercise with id = " + exerciseDtoId + " doesn't exist");
                request.setAttribute(JspConst.NOT_EXIST_EXERCISE_ID, true);
                return Page.EXERCISES;
            }
            exerciseProgramService.deleteExercise(exerciseDtoId);
            log.info("exercise with id = " + exerciseDtoId + " has been deleted");
            //attribute should be added
            page = Page.EXERCISES;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.EXERCISES;
        }
        return page;
    }

    private boolean isExerciseExist(Long exerciseId) throws ServiceException {
        ExerciseProgramService exerciseProgramService = new ExerciseProgramServiceImpl();
        Optional<ExerciseProgram> exerciseProgram = exerciseProgramService.findById(exerciseId);
        return exerciseProgram.isPresent();
    }
}
