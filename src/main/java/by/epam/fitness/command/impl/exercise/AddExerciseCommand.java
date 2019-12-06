package by.epam.fitness.command.impl.exercise;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
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
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AddExerciseCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddExerciseCommand.class);
    private ExerciseProgramService exerciseProgramService = new ExerciseProgramServiceImpl();
    private ExerciseService exerciseService = new ExerciseServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String repeatsString = request.getParameter(JspConst.REPEATS);
        if (repeatsString == null || !DataValidator.isRepeatsNumberValid(repeatsString)) {
            log.info("format number of repeats is not correct");
            request.setAttribute(JspConst.INCORRECT_INPUT_DATA_ERROR, true);
            return new CommandResult(Page.CLIENT_EXERCISES_COMMAND);
        }
        String setNumberString = request.getParameter(JspConst.SET_NUMBER);
        if (setNumberString == null || !DataValidator.isSetNumberValid(setNumberString)) {
            log.info("format number of set number is not correct");
            request.setAttribute(JspConst.INCORRECT_INPUT_DATA_ERROR, true);
            return new CommandResult(Page.CLIENT_EXERCISES_COMMAND);
        }
        Integer repeats = Integer.valueOf(repeatsString);
        Integer setNumber = Integer.valueOf(setNumberString);
        try {
            ExerciseProgram exerciseProgram = makeExercise(request, repeats, setNumber);
            String programIdString = request.getParameter(JspConst.PROGRAM_ID);
            if (programIdString==null || !DataValidator.isIdentifiableIdValid(programIdString)) {
                log.info("invalid program id format was received:" + programIdString);
                request.setAttribute(JspConst.INVALID_EXERCISE_ID_FORMAT, true);
                return new CommandResult(Page.CLIENT_EXERCISES_COMMAND);
            }
            long programId = Long.parseLong(programIdString);
            if (!isExerciseExist(exerciseProgram.getExercise().getId(), programId)) {
                exerciseProgramService.save(exerciseProgram);
            } else {
                request.setAttribute(JspConst.EXERCISE_ALREADY_EXISTS, true);
                return new CommandResult(Page.CLIENT_EXERCISES_COMMAND);
            }
            request.getSession().setAttribute(JspConst.EXERCISE_ADDED, true);
            log.info("exercise with id = " + exerciseProgram.getExercise().getId() + " has been added");
            page = Page.CLIENT_EXERCISES_COMMAND;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.CLIENT_EXERCISES_COMMAND;
        }
        return new CommandResult(page, true);
    }

    private ExerciseProgram makeExercise(HttpServletRequest request, Integer repeats, Integer setNumber) throws ServiceException {
        long exerciseId = Long.parseLong(request.getParameter(JspConst.EXERCISE_ID));
        int trainDay = Integer.parseInt(request.getParameter(JspConst.TRAIN_DAY));
        long programId = Long.parseLong(request.getParameter(JspConst.PROGRAM_ID));
        Optional<Exercise> exerciseOptional = exerciseService.findById(exerciseId);
        Exercise exercise = exerciseOptional.orElseThrow(ServiceException::new);
        return new ExerciseProgram(null, exercise, repeats, setNumber, programId, trainDay);
    }

    private boolean isExerciseExist(Long exerciseId, long programId) throws ServiceException {
        return exerciseProgramService.findByExerciseId(exerciseId, programId);
    }
}
