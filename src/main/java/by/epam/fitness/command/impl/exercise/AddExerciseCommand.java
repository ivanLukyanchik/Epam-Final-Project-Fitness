package by.epam.fitness.command.impl.exercise;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.ExerciseProgram;
import by.epam.fitness.service.ExerciseProgramService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ExerciseProgramServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddExerciseCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddExerciseCommand.class);
    private ExerciseProgramService exerciseProgramService = new ExerciseProgramServiceImpl();
    private DataValidator dataValidator = new DataValidator();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String repeatsString = request.getParameter(JspConst.REPEATS);
        if (!dataValidator.isSetNumberValid(repeatsString)) {
            log.info("format number of repeats is not correct");
            request.setAttribute(JspConst.INCORRECT_INPUT_DATA_ERROR, true);
            return Page.EXERCISES;
        }
        String setNumberString = request.getParameter(JspConst.SET_NUMBER);
        if (!dataValidator.isSetNumberValid(setNumberString)) {
            log.info("format number of set number is not correct");
            request.setAttribute(JspConst.INCORRECT_INPUT_DATA_ERROR, true);
            return Page.EXERCISES;
        }
        Integer repeats = Integer.valueOf(repeatsString);
        Integer setNumber = Integer.valueOf(setNumberString);
        try {
            ExerciseProgram exerciseProgram = makeExercise(request, repeats, setNumber);
//            exerciseProgramService.save(exerciseProgram);
            log.info("exercise with id = " + exerciseProgram.getId() + " has been added");
            page = Page.EXERCISES;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.EXERCISES;
        }
        return page;
    }

    private ExerciseProgram makeExercise(HttpServletRequest request, Integer repeats, Integer setNumber) throws ServiceException {
//        ExerciseService service = new ExerciseService();
        long exerciseId = Long.parseLong(request.getParameter(JspConst.EXERCISE_ID));
//        Optional<Exercise> exercise = service.findById(exerciseId);
        int trainDay = Integer.parseInt(request.getParameter(JspConst.TRAIN_DAY));
        long programId = Long.parseLong(request.getParameter(JspConst.PROGRAM_ID));
//        return new ExerciseProgram(null, exercise.get(), repeats, setNumber, programId, trainDay);
        return null;
    }
}
