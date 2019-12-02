package by.epam.fitness.command.factory;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandEnum;
import by.epam.fitness.command.impl.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static Logger log = LogManager.getLogger(ActionFactory.class);

    public static ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand currentCommand = new EmptyCommand();
        String action = request.getParameter("command");
        if(action == null || action.isEmpty()) {
            return currentCommand;
        }
        try {
            currentCommand = CommandEnum.getCurrentCommand(action);
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", true);
            log.info("Wrong action!");
        }
        return currentCommand;
    }
}
