package by.epam.fitness.command.impl.admin;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.impl.CoachServiceImpl;
import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The type Admin coaches command.
 */
public class AdminCoachesCommand implements ActionCommand {
    private static Logger log = LogManager.getLogger(AdminCoachesCommand.class);
    private CoachService coachService = new CoachServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        try {
            List<Coach> coaches = coachService.findAll();
            request.setAttribute(JspConst.COACHES, coaches);
            page = Page.ADMIN_COACHES;
        } catch (ServiceException e) {
            log.error("Problem with service occurred!", e);
            page = Page.ADMIN_COACHES;
        }
        return new CommandResult(page);
    }
}
