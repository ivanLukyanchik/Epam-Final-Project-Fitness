package by.epam.fitness.controller;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandResult;
import by.epam.fitness.command.factory.ActionFactory;
import by.epam.fitness.pool.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Controller.
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionCommand command = ActionFactory.defineCommand(request);
        CommandResult commandResult = command.execute(request, response);
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
        } else {
            page = "/login";
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().closeAllConnections();
    }
}
