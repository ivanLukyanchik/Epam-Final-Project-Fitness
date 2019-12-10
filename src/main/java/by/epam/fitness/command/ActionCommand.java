package by.epam.fitness.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface Action command.
 */
public interface ActionCommand {
    /**
     * Execute command result.
     *
     * @param request  the request
     * @param response the response
     * @return the command result
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response);
}
