package by.epam.fitness.controller;

import by.epam.fitness.entity.Exercise;
import by.epam.fitness.service.ExerciseService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ExerciseServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

import static by.epam.fitness.util.JspConst.SUCCESS;

@WebServlet("/addExerciseServlet")
@MultipartConfig
public class AddExercise extends HttpServlet {
    private static Logger log = LogManager.getLogger(AddExercise.class);
    private static DataValidator dataValidator = new DataValidator();
    private ExerciseService exerciseService = new ExerciseServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

    private String execute(HttpServletRequest request) {
        String page;
        String name = request.getParameter(JspConst.PARAM_NAME);
        if (name == null || !dataValidator.isExerciseNameValid(name)) {
            log.info("format name is not correct: " + name);
            request.setAttribute(JspConst.INVALID_NAME, true);
            return Page.ADMIN_EXERCISES_COMMAND;
        }
        String description = request.getParameter(JspConst.PARAM_DESCRIPTION);
        if (description == null || !dataValidator.isExerciseDescriptionValid(description)) {
            log.info("format description is not correct: " + description);
            request.setAttribute(JspConst.PARAM_DESCRIPTION, true);
            return Page.ADMIN_EXERCISES_COMMAND;
        }
        InputStream inputStream = null;
        try {
            Part filePart = request.getPart(JspConst.PARAM_IMAGE);
            if (!filePart.getSubmittedFileName().equals("")) {
                inputStream = filePart.getInputStream();
            }

//            if (inputStream != null) {
//                try {
//                    if (ImageIO.read(inputStream) == null) {
//                        // It's not an image.
//                        request.setAttribute(NOT_IMAGE, true);
//                        log.info("incorrect image format was received from admin");
//                        return Page.ADMIN_EXERCISES_COMMAND;
//                    }
//                } catch (IOException e) {
//                    log.error("IOException occurred ", e);
//                    request.setAttribute(NOT_IMAGE, true);
//                    return Page.ADMIN_EXERCISES_COMMAND;
//                }
//            }

            Exercise exercise = new Exercise(null, name, description, null, inputStream);
            long exerciseId = exerciseService.save(exercise);
            log.info("Exercise with id = " + exerciseId + " was successfully saved");
            request.setAttribute(SUCCESS, true);
            page = Page.ADMIN_EXERCISES_COMMAND;
        } catch (ServiceException | IOException | ServletException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ADMIN_EXERCISES_COMMAND;
        }
        return page;
    }

}
