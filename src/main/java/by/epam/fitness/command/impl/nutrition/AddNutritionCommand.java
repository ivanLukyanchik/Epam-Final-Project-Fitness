package by.epam.fitness.command.impl.nutrition;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.entity.Nutrition;
import by.epam.fitness.service.NutritionService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.NutritionServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static by.epam.fitness.util.JspConst.NUTRITION_ID;

public class AddNutritionCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AddNutritionCommand.class);
    private NutritionService nutritionService = new NutritionServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        long nutritionId = Long.parseLong(request.getParameter(NUTRITION_ID));
        try {
            Optional<Nutrition> nutritionOptional = nutritionService.findById(nutritionId);
            if (nutritionOptional.isPresent()) {
                nutritionOptional.get().setActive(true);
                nutritionService.save(nutritionOptional.get());
                request.setAttribute(JspConst.NUTRITION_ADDED, true);
                log.info("nutrition with id = " + nutritionId + " has been added");
                page = Page.CLIENT_NUTRITION_COMMAND;
            }
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.COACH_CLIENTS;
        }
        return page;
    }
}
