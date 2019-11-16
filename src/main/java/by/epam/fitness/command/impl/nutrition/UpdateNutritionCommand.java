package by.epam.fitness.command.impl.nutrition;

import by.epam.fitness.command.ActionCommand;
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
import java.util.Optional;

import static by.epam.fitness.util.JspConst.*;

public class UpdateNutritionCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(UpdateNutritionCommand.class);
    private DataValidator dataValidator = new DataValidator();
    private NutritionService nutritionService = new NutritionServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String newNutritionDescription = request.getParameter(JspConst.NUTRITION_DESCRIPTION);
        if (newNutritionDescription==null || !dataValidator.isNutritionDescriptionValid(newNutritionDescription)){
            log.info("incorrect nutrition description");
            request.setAttribute(JspConst.INCORRECT_INPUT_NUTRITION_DATA_ERROR, true);
            return Page.NUTRITION;
        }
        String nutritionIdString = request.getParameter(NUTRITION_ID);
        try {
            if (nutritionIdString == null || !isNutritionExist(nutritionIdString)) {
                log.info("nutrition with id:" + nutritionIdString + " doesn't exist");
                request.setAttribute(JspConst.NOT_EXIST_NUTRITION_ID, true);
            }
            Long nutritionId = Long.parseLong(request.getParameter(NUTRITION_ID));
            String nutritionTime = request.getParameter(NUTRITION_TIME);
            setNewNutrition(nutritionId, nutritionTime, newNutritionDescription);
            log.info("nutrition with id = " + nutritionIdString + " has been changed");
            page = "/controller?command=show_client_nutrition";
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.NUTRITION;
        }
        return page;
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