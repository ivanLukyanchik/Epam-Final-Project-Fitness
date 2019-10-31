<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    pageContext.setAttribute("currentPage", "welcome");
%>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}" scope="session"/>
    <fmt:setBundle basename="locale.pagecontent" var="locale"/>

    <title><fmt:message bundle="${locale}" key="label.title" /></title>
</head>
<header><jsp:include page="/jsp/header.jsp"/></header>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login");
        }
    %>
    <fmt:message bundle="${locale}" key="label.welcome" /> ${user}!
    <a href="${pageContext.request.contextPath}/jsp/videos.jsp">Videos here...</a>
    <form method="post" action="controller">
        <input type="hidden" name="command" value="logout">
        <input type="submit" value="Logout">
    </form>
    <br/>
    <footer>
        <fmt:message bundle="${locale}" key="footer.copyright" />
    </footer>
</body>
</html>