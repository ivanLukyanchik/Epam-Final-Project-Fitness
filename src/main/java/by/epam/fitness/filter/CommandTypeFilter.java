package by.epam.fitness.filter;

import by.epam.fitness.command.ActionCommand;
import by.epam.fitness.command.CommandEnum;
import by.epam.fitness.command.access.CommandAccess;
import by.epam.fitness.entity.Client;
import by.epam.fitness.entity.UserRole;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.impl.ClientServiceImpl;
import by.epam.fitness.util.SessionAttributes;
import by.epam.fitness.util.page.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CommandTypeFilter implements Filter {
    private static Logger log = LogManager.getLogger(CommandTypeFilter.class);
    private static final String COMMAND = "command";
    private CommandAccess commandAccess = new CommandAccess();
    private ClientService clientService = new ClientServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        Optional<String> userRole = getUserRoleByRequest(httpServletRequest);
        List<ActionCommand> commandTypes = commandAccess.getAvailableCommandTypesByUser(userRole);
        String action = servletRequest.getParameter(COMMAND);
        ActionCommand currentCommand;
        try {
            currentCommand= CommandEnum.getCurrentCommand(action);
        } catch (IllegalArgumentException exception) {
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
        Optional<String> roleOptional = Optional.empty();
        try {
            if (session != null) {
                String role = (String)session.getAttribute(SessionAttributes.ROLE);
                roleOptional = Optional.ofNullable(role);
                if (role == null) {
                    if (getClientByCookie(request).isPresent()) {
                        Client client = getClientByCookie(request).get();
                        setClientToSession(request, client);
                        roleOptional = Optional.of(UserRole.CLIENT);
                    }
                }
            } else { // FIXME: 20.11.2019 ask
                if (getClientByCookie(request).isPresent()) {
                    Client client = getClientByCookie(request).get();
                    setClientToSession(request, client);
                    roleOptional = Optional.of(UserRole.CLIENT);
                }
            }
        } catch (ServiceException e) {
            log.error("Service exception occurred here", e);
        }
        return roleOptional;
    }

    private Optional<Client> getClientByCookie(HttpServletRequest request) throws ServiceException {
        String login = null;
        String hash = null;
        Optional<Client> user = Optional.empty();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("clientLogin")) {
                    login = cookie.getValue();
                }
                if (cookie.getName().equals("token")) {
                    hash = cookie.getValue();
                }
            }
        }
        if (login != null && hash != null) {
            user = clientService.getUserByCookieData(login, hash);
        }
        return user;
    }

    private void setClientToSession(HttpServletRequest request, Client client) {
        request.getSession().setAttribute(SessionAttributes.CLIENT, client);
        request.getSession().setAttribute(SessionAttributes.USER, client.getLogin());
        request.getSession().setAttribute(SessionAttributes.ROLE, UserRole.CLIENT);
        request.getSession().setAttribute(SessionAttributes.ID, client.getId());
    }

    @Override
    public void destroy() {}
}
