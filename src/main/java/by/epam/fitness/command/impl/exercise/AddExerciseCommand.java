package by.epam.fitness.command.impl.exercise;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Exercise;
import by.epam.fitness.entity.ExerciseProgram;
import by.epam.fitness.service.ExerciseProgramService;
import by.epam.fitness.service.ExerciseService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ExerciseProgramServiceImpl;
import by.epam.fitness.service.impl.ExerciseServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AddExerciseCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddExerciseCommand.class);
    private ExerciseProgramService exerciseProgramService = new ExerciseProgramServiceImpl();
    private ExerciseService exerciseService = new ExerciseServiceImpl();
    private DataValidator dataValidator = new DataValidator();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String repeatsString = request.getParameter(JspConst.REPEATS);
        if (repeatsString == null || !dataValidator.isSetNumberValid(repeatsString)) {
            log.info("format number of repeats is not correct");
            request.setAttribute(JspConst.INCORRECT_INPUT_DATA_ERROR, true);
            return Page.EXERCISES;
        }
        String setNumberString = request.getParameter(JspConst.SET_NUMBER);
        if (setNumberString == null || !dataValidator.isSetNumberValid(setNumberString)) {
            log.info("format number of set number is not correct");
            request.setAttribute(JspConst.INCORRECT_INPUT_DATA_ERROR, true);
            return Page.EXERCISES;
        }
        Integer repeats = Integer.valueOf(repeatsString);
        Integer setNumber = Integer.valueOf(setNumberString);
        try {
            ExerciseProgram exerciseProgram = makeExercise(request, repeats, setNumber);
            if (!isExerciseExist(exerciseProgram.getExercise().getId())) {
                exerciseProgramService.save(exerciseProgram);
            } else {
                request.setAttribute(JspConst.EXERCISE_ALREADY_EXISTS, true);
                return "/controller?command=show_client_exercises";
            }
            request.setAttribute(JspConst.EXERCISE_ADDED, true);
            log.info("exercise with id = " + exerciseProgram.getExercise().getId() + " has been added");
            page = "/controller?command=show_client_exercises";
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.EXERCISES;
        }
        return page;
    }

    private ExerciseProgram makeExercise(HttpServletRequest request, Integer repeats, Integer setNumber) throws ServiceException {
        long exerciseId = Long.parseLong(request.getParameter(JspConst.EXERCISE_ID));
        int trainDay = Integer.parseInt(request.getParameter(JspConst.TRAIN_DAY));
        long programId = Long.parseLong(request.getParameter(JspConst.PROGRAM_ID));
        Optional<Exercise> exerciseOptional = exerciseService.findById(exerciseId);
        Exercise exercise = exerciseOptional.orElseThrow(ServiceException::new);
        return new ExerciseProgram(null, exercise, repeats, setNumber, programId, trainDay);
    }

    private boolean isExerciseExist(Long exerciseId) throws ServiceException {
        return exerciseProgramService.findByExerciseId(exerciseId);
    }
}
