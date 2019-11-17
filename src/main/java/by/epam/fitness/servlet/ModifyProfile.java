package by.epam.fitness.servlet;

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
import java.nio.charset.StandardCharsets;
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
            request.setAttribute("invalidName", "Wrong name");
            return Page.CLIENT_PROFILE_PAGE;
        }
        String surname = request.getParameter(PARAM_SURNAME);
        if (surname==null || !dataValidator.isSurnameValid(surname)) {
            log.info("invalid name format was received:" + surname);
            request.setAttribute("invalidSurname", "Wrong surname");
            return Page.CLIENT_PROFILE_PAGE;
        }
        String login = request.getParameter(PARAM_LOGIN);
        if (login==null || !dataValidator.isLoginValid(login)) {
            log.info("invalid login format was received:" + login);
            request.setAttribute("invalidLogin", "Wrong login");
            return Page.CLIENT_PROFILE_PAGE;
        }
        String email = request.getParameter(PARAM_EMAIL);
        if (email==null || !dataValidator.isEmailValid(email)){
            log.info("invalid email format was received:" + email);
            request.setAttribute("invalidEmail", "Wrong email");
            return Page.CLIENT_PROFILE_PAGE;
        }
        InputStream inputStream = null;
            try {
                Part filePart = request.getPart("photo");
                String image = null;
                if (filePart != null) {
                    inputStream = filePart.getInputStream();
                }
                Long clientID = (Long) request.getSession().getAttribute(SessionAttributes.ID);
                Optional<User> clientOptional = userService.findById(clientID);
                if (clientOptional.isPresent()) {
                    User user = clientOptional.get();
                    user.setName(name);
                    user.setSurname(surname);
                    user.setLogin(login);
                    user.setEmail(email);
                    User user1 = (User) request.getSession().getAttribute(SessionAttributes.CLIENT);
                    user1.setName(name);
                    user1.setSurname(surname);
                    user1.setLogin(login);
                    user1.setEmail(email);
                    if (inputStream != null) {
                        user.setIs(inputStream);
                    }
                    if (userService.save1(user)) {
                        log.info("client with id = "+ clientID + " successfully changed his profile data");
                        request.setAttribute(SUCCESS, true);
                        page = "/controller?command=client_profile";
                    } else {
                        request.setAttribute(WRONG_DATA, true);
                        page = Page.CLIENT_PROFILE_PAGE;
                    }
                }
            } catch (ServiceException | IOException | ServletException e) {
            log.error("Problem with service occurred!", e);
            page = Page.CLIENT_PROFILE_PAGE;
            }
        return page;
    }

    private  String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is, StandardCharsets.UTF_8).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
