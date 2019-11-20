package by.epam.fitness.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {
    String execute(HttpServletRequest request);
    default String executeLogin(HttpServletRequest request, HttpServletResponse response) {return null;}
    default String executeLogout(HttpServletRequest request, HttpServletResponse response) {return null;}
}
