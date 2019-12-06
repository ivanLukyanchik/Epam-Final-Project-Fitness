package by.epam.fitness.command.impl.exercise;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
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
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static by.epam.fitness.util.JspConst.INVALID_EXERCISE_ID_FORMAT;
import static by.epam.fitness.util.JspConst.NOT_EXIST_EXERCISE_ID;

public class UpdateExerciseCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddExerciseCommand.class);
    private ExerciseProgramService exerciseProgramService = new ExerciseProgramServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String repeatsString = request.getParameter(JspConst.REPEATS);
        if (repeatsString==null || !DataValidator.isRepeatsNumberValid(repeatsString)) {
            log.info("format number of repeats is not correct");
            request.setAttribute(JspConst.INCORRECT_INPUT_DATA_ERROR, true);
            return new CommandResult(Page.CLIENT_EXERCISES_COMMAND);
        }
        String setNumberString = request.getParameter(JspConst.SET_NUMBER);
        if (setNumberString==null ||!DataValidator.isSetNumberValid(setNumberString)) {
            log.info("format number of set number is not correct");
            request.setAttribute(JspConst.INCORRECT_INPUT_DATA_ERROR, true);
            return new CommandResult(Page.CLIENT_EXERCISES_COMMAND);
        }
        int repeats = Integer.parseInt(repeatsString);
        int setNumber = Integer.parseInt(setNumberString);
        String exerciseProgramIdString = request.getParameter(JspConst.EXERCISE_PROGRAM_ID);
        if (exerciseProgramIdString==null || !DataValidator.isIdentifiableIdValid(exerciseProgramIdString)) {
            log.info("incorrect exercise id was received:" + exerciseProgramIdString);
            request.setAttribute(INVALID_EXERCISE_ID_FORMAT, true);
            return new CommandResult(Page.CLIENT_EXERCISES_COMMAND);
        }
        Long exerciseProgramId = Long.valueOf(exerciseProgramIdString);
        try {
            if (!isExerciseExist(exerciseProgramId)) {
                log.info("exercise with id = " + exerciseProgramId + " doesn't exist");
                request.setAttribute(NOT_EXIST_EXERCISE_ID, true);
                return new CommandResult(Page.CLIENT_EXERCISES_COMMAND);
            }
            Optional<ExerciseProgram> exerciseProgramOptional = exerciseProgramService.findById(exerciseProgramId);
            if (exerciseProgramOptional.isPresent()) {
                ExerciseProgram exerciseProgram = exerciseProgramOptional.get();
                exerciseProgram.setSetNumber(setNumber);
                exerciseProgram.setRepeatNumber(repeats);
                exerciseProgramService.save(exerciseProgram);
            }
            request.getSession().setAttribute(JspConst.EXERCISE_UPDATED, true);
            log.info("exercise program with id = " + exerciseProgramId + " has been updated");
            page = Page.CLIENT_EXERCISES_COMMAND;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.CLIENT_EXERCISES_COMMAND;
        }
        return new CommandResult(page, true);
    }

    private boolean isExerciseExist(Long id) throws ServiceException {
        Optional<ExerciseProgram> exerciseProgram = exerciseProgramService.findById(id);
        return exerciseProgram.isPresent();
    }
}
