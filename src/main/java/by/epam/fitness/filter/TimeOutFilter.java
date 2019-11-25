package by.epam.fitness.filter;

import by.epam.fitness.util.JspConst;
import by.epam.fitness.util.page.Page;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TimeOutFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        if (!session.isNew()) {
            chain.doFilter(request, response);
        } else {
            request.setAttribute(JspConst.TIME_OUT, true);
            request.getRequestDispatcher(Page.LOGIN_PAGE).forward(request, response);
        }
    }

    @Override
    public void destroy() {}
}
