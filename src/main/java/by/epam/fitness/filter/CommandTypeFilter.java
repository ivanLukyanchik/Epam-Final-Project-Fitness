package by.epam.fitness.filter;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandEnum;
import by.epam.fitness.command.access.CommandAccess;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CommandTypeFilter implements Filter {
    private static Logger log = LogManager.getLogger(CommandTypeFilter.class);
    private static final String COMMAND = "command";

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        Optional<String> userRole = getUserRoleByRequest(httpServletRequest);
        CommandAccess commandAccess = new CommandAccess();
        List<ActionCommand> commandTypes = commandAccess.getAvailableCommandTypesByUser(userRole);
        String action = servletRequest.getParameter(COMMAND);
        ActionCommand currentCommand;
        try {
            currentCommand= CommandEnum.getCurrentCommand(action);
        } catch (IllegalArgumentException exception){
            log.warn("Action with incorrect command:" + action);
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(Page.ERROR_PAGE);
            requestDispatcher.forward(servletRequest, servletResponse);
            return;
        }
        if (commandTypes.contains(currentCommand)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(Page.NO_ACCESS_PAGE);
            requestDispatcher.forward(servletRequest, servletResponse);
        }
    }

    private Optional<String> getUserRoleByRequest(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Optional<String> roleOptional;
        if (session == null) {
            roleOptional = Optional.empty();
        } else {
            String role = (String)session.getAttribute(SessionAttributes.ROLE);
            roleOptional = Optional.ofNullable(role);
        }
        return roleOptional;
    }

    @Override
    public void destroy() {}
}
