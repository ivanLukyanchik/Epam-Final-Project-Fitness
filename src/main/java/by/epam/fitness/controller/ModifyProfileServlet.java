package by.epam.fitness.controller;

import by.epam.fitness.command.CommandResult;
import by.epam.fitness.entity.Client;
import by.epam.fitness.entity.UserRole;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.ClientServiceImpl;
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
import java.util.Optional;

import static by.epam.fitness.util.JspConst.*;

/**
 * The type Modify profile servlet.
 */
@WebServlet("/modifyProfileServlet")
@MultipartConfig(fileSizeThreshold = 1024 *  1024,
        maxFileSize = 1024 *  1024 *  5,
        maxRequestSize = 1024 *  1024 *  5 *  5)
public class ModifyProfileServlet extends HttpServlet {
    private static Logger log = LogManager.getLogger(ModifyProfileServlet.class);
    private ClientService clientService = new ClientServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandResult commandResult = execute(request);
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
        doPost(req, resp);
    }

    /**
     * Execute command result.
     *
     * @param request the request
     * @return the command result
     */
    public CommandResult execute(HttpServletRequest request) {
        String page = null;
        String userRole = String.valueOf(request.getSession().getAttribute(SessionAttributes.ROLE));
        if (!(userRole.equals(UserRole.ADMIN) || userRole.equals(UserRole.CLIENT))) {
            return new CommandResult(Page.NO_ACCESS_PAGE);
        } else {
            String name = request.getParameter(PARAM_NAME);
            if (name == null || !DataValidator.isNameValid(name)) {
                log.info("invalid name format was received:" + name);
                request.setAttribute(INVALID_NAME, true);
                return new CommandResult(Page.CLIENT_PROFILE_COMMAND);
            }
            String surname = request.getParameter(PARAM_SURNAME);
            if (surname == null || !DataValidator.isSurnameValid(surname)) {
                log.info("invalid surname format was received:" + surname);
                request.setAttribute(INVALID_SURNAME, true);
                return new CommandResult(Page.CLIENT_PROFILE_COMMAND);
            }
            String login = request.getParameter(PARAM_LOGIN);
            String oldLogin = request.getParameter(OLD_LOGIN);
            if (login == null || !DataValidator.isLoginValid(login) || oldLogin == null || !DataValidator.isLoginValid(oldLogin)) {
                log.info("invalid login format was received:" + login + " or " + oldLogin);
                request.setAttribute(INVALID_LOGIN, true);
                return new CommandResult(Page.CLIENT_PROFILE_COMMAND);
            }
            String email = request.getParameter(PARAM_EMAIL);
            if (email == null || !DataValidator.isEmailValid(email)) {
                log.info("invalid email format was received:" + email);
                request.setAttribute(INVALID_EMAIL, true);
                return new CommandResult(Page.CLIENT_PROFILE_COMMAND);
            }
            InputStream inputStream = null;
            try {
                Part filePart = request.getPart(PARAM_PHOTO);
                if (!filePart.getSubmittedFileName().equals("")) {
                    if (filePart.getContentType().contains("image")) {
                        inputStream = filePart.getInputStream();
                    } else {
                        request.setAttribute(NOT_IMAGE, true);
                        return new CommandResult(Page.CLIENT_PROFILE_COMMAND);
                    }
                }
                String role = String.valueOf(request.getSession().getAttribute(SessionAttributes.ROLE));
                Long clientId;
                if (role.equals(UserRole.CLIENT)) {
                    clientId = (Long) request.getSession().getAttribute(SessionAttributes.ID);
                } else {
                    String clientIdString = request.getParameter(CLIENT_ID);
                    if (clientIdString == null || !DataValidator.isIdentifiableIdValid(clientIdString)) {
                        log.info("invalid client id format was received:" + clientIdString);
                        request.setAttribute(JspConst.INVALID_EXERCISE_ID_FORMAT, true);
                        return new CommandResult(Page.ADMIN_CLIENTS_COMMAND);
                    }
                    clientId = Long.valueOf(clientIdString);
                }
                Optional<Client> clientOptional = clientService.findById(clientId);
                if (clientOptional.isPresent()) {
                    Client client = clientOptional.get();
                    client.setName(name);
                    client.setSurname(surname);
                    client.setLogin(login);
                    client.setEmail(email);
                    if (inputStream != null) {
                        client.setIs(inputStream);
                    }
                    if (login.equals(oldLogin)) {
                        clientService.save(client);
                        log.info("client with id = " + clientId + " successfully changed his profile data");
                        request.getSession().setAttribute(SUCCESS, true);
                        request.setAttribute(ADMIN_CLIENT_ID, clientId);
                        page = Page.CLIENT_PROFILE_COMMAND;
                    } else if (clientService.isLoginUnique(login)) {
                        clientService.save(client);
                        log.info("client with id = " + clientId + " successfully changed his profile data");
                        request.getSession().setAttribute(SUCCESS, true);
                        request.setAttribute(ADMIN_CLIENT_ID, clientId);
                        page = Page.CLIENT_PROFILE_COMMAND;
                    } else {
                        request.setAttribute(WRONG_DATA, true);
                        return new CommandResult(Page.CLIENT_PROFILE_COMMAND);
                    }
                }
            } catch (ServiceException | IOException | ServletException e) {
                log.error("Problem with service occurred!", e);
                page = Page.CLIENT_PROFILE_COMMAND;
            }
            return new CommandResult(page, true);
        }
    }
}
