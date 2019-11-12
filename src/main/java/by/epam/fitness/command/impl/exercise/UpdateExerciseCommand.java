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

import java.util.Optional;

import static by.epam.fitness.util.JspConst.INVALID_EXERCISE_ID_FORMAT;
import static by.epam.fitness.util.JspConst.NOT_EXIST_EXERCISE_ID;

public class UpdateExerciseCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddExerciseCommand.class);
    private ExerciseProgramService exerciseProgramService = new ExerciseProgramServiceImpl();
    private DataValidator dataValidator = new DataValidator();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String repeatsString = request.getParameter(JspConst.REPEATS);
        if (!dataValidator.isSetNumberValid(repeatsString)) { // FIXME: 13.11.2019 везде проверка на null вместе с validation
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
        String exerciseDtoIdString = request.getParameter(JspConst.EXERCISE_DTO_ID);
        if (!dataValidator.isIdentifiableIdValid(exerciseDtoIdString)) {
            log.info("incorrect exercise id was received:" + exerciseDtoIdString);
            request.setAttribute(INVALID_EXERCISE_ID_FORMAT, true);
            return Page.EXERCISES;
        }
        Long exerciseDtoId = Long.valueOf(exerciseDtoIdString);
        try {
            if (!dataValidator.isExerciseExist(exerciseDtoId)) {
                log.info("exercise with id = " + exerciseDtoId + " doesn't exist");
                request.setAttribute(NOT_EXIST_EXERCISE_ID, true);
                return Page.EXERCISES;
            }
            Optional<ExerciseProgram> exerciseProgramOptional = exerciseProgramService.findById(exerciseDtoId);
            if (exerciseProgramOptional.isPresent()) {
                ExerciseProgram exerciseProgram = exerciseProgramOptional.get();
                exerciseProgram.setSetNumber(setNumber);
                exerciseProgram.setRepeatNumber(repeats);
                exerciseProgram.save(exerciseProgram);
            }
            log.info("exercise with id = " + exerciseDtoId + " has been changed");
            page = Page.EXERCISES;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.EXERCISES;
        }
        return page;
    }
}
