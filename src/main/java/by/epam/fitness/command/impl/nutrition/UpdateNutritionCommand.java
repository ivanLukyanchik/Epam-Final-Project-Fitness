package by.epam.fitness.command.impl.nutrition;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
import by.epam.fitness.entity.Nutrition;
import by.epam.fitness.service.NutritionService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.NutritionServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import by.epam.fitness.util.validation.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static by.epam.fitness.util.JspConst.*;

/**
 * The type Update nutrition command.
 */
public class UpdateNutritionCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(UpdateNutritionCommand.class);
    private NutritionService nutritionService = new NutritionServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        String newNutritionDescription = request.getParameter(JspConst.NUTRITION_DESCRIPTION);
        if (newNutritionDescription==null || !DataValidator.isNutritionDescriptionValid(newNutritionDescription)){
            log.info("incorrect nutrition description");
            request.setAttribute(JspConst.INCORRECT_INPUT_NUTRITION_DATA_ERROR, true);
            return new CommandResult(Page.CLIENT_NUTRITION_COMMAND);
        }
        String nutritionIdString = request.getParameter(NUTRITION_ID);
        try {
            if (nutritionIdString == null || !isNutritionExist(nutritionIdString)) {
                log.info("nutrition with id:" + nutritionIdString + " doesn't exist");
                request.setAttribute(JspConst.NOT_EXIST_NUTRITION_ID, true);
                return new CommandResult(Page.CLIENT_NUTRITION_COMMAND);
            }
            Long nutritionId = Long.parseLong(nutritionIdString);
            String nutritionTime = request.getParameter(NUTRITION_TIME);
            setNewNutrition(nutritionId, nutritionTime, newNutritionDescription);
            request.getSession().setAttribute(NUTRITION_UPDATED, true);
            log.info("nutrition with id = " + nutritionIdString + " has been updated");
            page = Page.CLIENT_NUTRITION_COMMAND;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.CLIENT_NUTRITION_COMMAND;
        }
        return new CommandResult(page, true);
    }

    private void setNewNutrition(Long nutritionId, String nutritionTime, String newNutritionDescription) throws ServiceException {
        Optional<Nutrition> nutritionOptional = nutritionService.findById(nutritionId);
        switch (nutritionTime){
            case MORNING : {
                nutritionOptional.ifPresent(nutrition -> nutrition.setMorningNutrition(newNutritionDescription));
                break;
            }
            case LUNCH : {
                nutritionOptional.ifPresent(nutrition -> nutrition.setLunchNutrition(newNutritionDescription));
                break;
            }
            case DINNER : {
                nutritionOptional.ifPresent(nutrition -> nutrition.setDinnerNutrition(newNutritionDescription));
                break;
            }
        }
        if (nutritionOptional.isPresent()) {
            nutritionService.save(nutritionOptional.get());
        }
    }

    private boolean isNutritionExist(String nutritionIdString) throws ServiceException {
        long nutritionId = Long.parseLong(nutritionIdString);
        Optional<Nutrition> nutrition = nutritionService.findById(nutritionId);
        return nutrition.isPresent();
    }
}