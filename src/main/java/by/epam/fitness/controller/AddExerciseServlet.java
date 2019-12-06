package by.epam.fitness.controller;

import by.epam.fitness.command.CommandResult;
import by.epam.fitness.entity.Exercise;
import by.epam.fitness.entity.UserRole;
import by.epam.fitness.service.ExerciseService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ExerciseServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.SessionAttributes;
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

import static by.epam.fitness.util.JspConst.NOT_IMAGE;
import static by.epam.fitness.util.JspConst.SUCCESS;

@WebServlet("/addExerciseServlet")
@MultipartConfig(fileSizeThreshold = 1024 *  1024,
        maxFileSize = 1024 *  1024 *  5,
        maxRequestSize = 1024 *  1024 *  5 *  5)
public class AddExerciseServlet extends HttpServlet {
    private static Logger log = LogManager.getLogger(AddExerciseServlet.class);
    private ExerciseService exerciseService = new ExerciseServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandResult commandResult = execute(request); // FIXME: 06.12.2019
        String page;
        if (commandResult.getPage() != null) {
            page = commandResult.getPage();
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            if (commandResult.isRedirect()) {
                response.sendRedirect(request.getContextPath() + page);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp); // FIXME: 05.12.2019
    }

    private CommandResult execute(HttpServletRequest request) {
        String page;
        String userRole = String.valueOf(request.getSession().getAttribute(SessionAttributes.ROLE));
        if (!(userRole.equals(UserRole.ADMIN))) {
            return new CommandResult(Page.NO_ACCESS_PAGE);
        } else {
            String name = request.getParameter(JspConst.PARAM_NAME);
            if (name == null || !DataValidator.isExerciseNameValid(name)) {
                log.info("format name is not correct: " + name);
                request.setAttribute(JspConst.INVALID_NAME, true);
                return new CommandResult(Page.ADMIN_EXERCISES_COMMAND);
            }
            String description = request.getParameter(JspConst.PARAM_DESCRIPTION);
            if (description == null || !DataValidator.isExerciseDescriptionValid(description)) {
                log.info("format description is not correct: " + description);
                request.setAttribute(JspConst.INVALID_DESCRIPTION, true);
                return new CommandResult(Page.ADMIN_EXERCISES_COMMAND);
            }
            InputStream inputStream = null;
            try {
                Part filePart = request.getPart(JspConst.PARAM_IMAGE);
                if (!filePart.getSubmittedFileName().equals("")) {
                    if (filePart.getContentType().contains("image")) {
                        inputStream = filePart.getInputStream();
                    } else {
                        request.setAttribute(NOT_IMAGE, true);
                        return new CommandResult(Page.ADMIN_EXERCISES_COMMAND);
                    }
                }
                Exercise exercise = new Exercise(null, name, description, null, inputStream);
                long exerciseId = exerciseService.save(exercise);
                log.info("Exercise with id = " + exerciseId + " was successfully saved");
                request.getSession().setAttribute(SUCCESS, true);
                page = Page.ADMIN_EXERCISES_COMMAND;
            } catch (ServiceException | IOException | ServletException e) {
                log.error("Problem with service occurred!", e);
                page = Page.ADMIN_EXERCISES_COMMAND;
            }
            return new CommandResult(page, true);
        }
    }
}
