package by.epam.fitness.command.impl;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Client;
import by.epam.fitness.entity.Nutrition;
import by.epam.fitness.entity.Program;
import by.epam.fitness.mail.SendingEmail;
import by.epam.fitness.service.NutritionService;
import by.epam.fitness.service.ProgramService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.impl.NutritionServiceImpl;
import by.epam.fitness.service.impl.ProgramServiceImpl;
import by.epam.fitness.service.impl.ClientServiceImpl;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.sale.SaleSystem;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Random;

import static by.epam.fitness.util.JspConst.*;

public class RegisterCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(RegisterCommand.class);
    private static DataValidator dataValidator = new DataValidator();
    private ClientService clientService = new ClientServiceImpl();
    private NutritionService nutritionService = new NutritionServiceImpl();
    private ProgramService programService = new ProgramServiceImpl();
    private final static SaleSystem SALE_SYSTEM = SaleSystem.getInstance();
    private final static Integer START_VISIT_NUMBER = 0;
    private final static Integer STANDARD_TRAINS_PER_WEEK = 3;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String name = request.getParameter(PARAM_NAME);
        if (name==null || !dataValidator.isNameValid(name)) {
            log.info("invalid name format was received:" + name);
            request.setAttribute(INVALID_NAME, true);
            return Page.REGISTER_PAGE;
        }
        String surname = request.getParameter(PARAM_SURNAME);
        if (surname==null || !dataValidator.isSurnameValid(surname)) {
            log.info("invalid name format was received:" + surname);
            request.setAttribute(INVALID_SURNAME, true);
            return Page.REGISTER_PAGE;
        }
        String login = request.getParameter(PARAM_LOGIN);
        if (login==null || !dataValidator.isLoginValid(login)) {
            log.info("invalid login format was received:" + login);
            request.setAttribute(INVALID_LOGIN, true);
            return Page.REGISTER_PAGE;
        }
        String email = request.getParameter(PARAM_EMAIL);
        if (email==null || !dataValidator.isEmailValid(email)){
            log.info("invalid email format was received:" + email);
            request.setAttribute(INVALID_EMAIL, true);
            return Page.REGISTER_PAGE;
        }
        String password = request.getParameter(PARAM_PASSWORD);
        if (password==null || !dataValidator.isPasswordValid(password)){
            log.info("invalid password format was received:" + password);
            request.setAttribute(INVALID_PASSWORD, true);
            return Page.REGISTER_PAGE;
        }
        Random random = new SecureRandom();
        String userHash = DigestUtils.sha512Hex("" + random.nextInt(999999));
        Client client = null;
        try {
            client = buildUser(request, userHash);
            if (clientService.registerUser(client)) {
                SendingEmail.verify(login, email, userHash);
                log.info("client with login = " + login + " was registered. Activation Link was sent.");
                page = Page.VERIFY_PAGE;
            } else {
                request.setAttribute(WRONG_DATA, true);
                page = Page.REGISTER_PAGE;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.REGISTER_PAGE;
        }
        return page;
    }

    private Client buildUser(HttpServletRequest request, String userHash) throws ServiceException {
        String name = request.getParameter(PARAM_NAME);
        String surname = request.getParameter(PARAM_SURNAME);
        String login = request.getParameter(PARAM_LOGIN);
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        String newPassword = DigestUtils.sha512Hex(password);
        float personalDiscount = SALE_SYSTEM.getSaleByVisitNumber(START_VISIT_NUMBER);
        Program program = buildProgram();
        return new Client(null, null, name, surname, login, newPassword, email, userHash, false, START_VISIT_NUMBER,
                personalDiscount, program.getId(), null, null);
    }

    private Program buildProgram() throws ServiceException {
        Program program = new Program();
        Nutrition nutrition = buildNutrition();
        program.setNutritionId(nutrition.getId());
        program.setTrainsPerWeek(STANDARD_TRAINS_PER_WEEK);
        Long programId = programService.save(program);
        program.setId(programId);
        return program;
    }

    private Nutrition buildNutrition() throws ServiceException {
        Nutrition nutrition = new Nutrition();
        Long nutritionId  = nutritionService.save(nutrition);
        nutrition.setId(nutritionId);
        return nutrition;
    }
}
