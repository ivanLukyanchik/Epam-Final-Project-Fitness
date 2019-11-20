package by.epam.fitness.command.impl.servlet;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.User;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import by.epam.fitness.service.impl.UserServiceImpl;
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
import java.util.Optional;

import static by.epam.fitness.util.JspConst.*;

@WebServlet("/modifyProfile")
@MultipartConfig
public class ModifyProfile extends HttpServlet implements ActionCommand {
    private static Logger log = LogManager.getLogger(ModifyProfile.class);
    private static DataValidator dataValidator = new DataValidator();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }

    public String execute(HttpServletRequest request) {
        String page = null;
        String name = request.getParameter(PARAM_NAME);
        if (name==null || !dataValidator.isNameValid(name)) {
            log.info("invalid name format was received:" + name);
            request.setAttribute(INVALID_NAME, true);
            return Page.CLIENT_PROFILE_PAGE;
        }
        String surname = request.getParameter(PARAM_SURNAME);
        if (surname==null || !dataValidator.isSurnameValid(surname)) {
            log.info("invalid surname format was received:" + surname);
            request.setAttribute(INVALID_SURNAME, true);
            return Page.CLIENT_PROFILE_PAGE;
        }
        String login = request.getParameter(PARAM_LOGIN);
        String oldLogin = request.getParameter(OLD_LOGIN);
        if (login==null || !dataValidator.isLoginValid(login) || oldLogin==null || !dataValidator.isLoginValid(oldLogin)) {
            log.info("invalid login format was received:" + login  + " or " + oldLogin);
            request.setAttribute(INVALID_LOGIN, true);
            return Page.CLIENT_PROFILE_PAGE;
        }
        String email = request.getParameter(PARAM_EMAIL);
        if (email==null || !dataValidator.isEmailValid(email)){
            log.info("invalid email format was received:" + email);
            request.setAttribute(INVALID_EMAIL, true);
            return Page.CLIENT_PROFILE_PAGE;
        }
        InputStream inputStream = null;
            try {
                Part filePart = request.getPart(PARAM_PHOTO);
                if (!filePart.getSubmittedFileName().equals("")) {
                    inputStream = filePart.getInputStream();
                }
                Long clientId = (Long) request.getSession().getAttribute(SessionAttributes.ID);
                Optional<User> clientOptional = userService.findById(clientId);
                if (clientOptional.isPresent()) {
                    User user = clientOptional.get();
                    user.setName(name);
                    user.setSurname(surname);
                    user.setLogin(login);
                    user.setEmail(email);
                    if (inputStream != null) {
                        user.setIs(inputStream);
//                        try {
//                            ImageIO.read(inputStream).toString();
//                            user.setIs(inputStream);
//                            // It's an image (only BMP, GIF, JPG and PNG are recognized).
//                        } catch (Exception e) {
//                            // It's not an image.
//                            request.setAttribute(NOT_IMAGE, true);
//                            log.info("incorrect image format was received from user with id = " + user.getId());
//                        }
                    }
                    if (login.equals(oldLogin) && (!userService.isLoginUnique(login))) { // FIXME: 19.11.2019 rubbish here
                        userService.save(user);
                        log.info("client with id = " + clientId + " successfully changed his profile data");
                        request.setAttribute(SUCCESS, true);
                        page = Page.CLIENT_PROFILE_COMMAND;
                    } else if (userService.isLoginUnique(login)) {
                        userService.save(user);
                        log.info("client with id = " + clientId + " successfully changed his profile data");
                        request.setAttribute(SUCCESS, true);
                        page = Page.CLIENT_PROFILE_COMMAND;
                    } else {
                        request.setAttribute(WRONG_DATA, true);
                        page = Page.CLIENT_PROFILE_COMMAND;
                    }
                }
            } catch (ServiceException | IOException | ServletException e) {
                log.error("Problem with service occurred!", e);
                page = Page.CLIENT_PROFILE_PAGE;
            }
        return page;
    }
}
