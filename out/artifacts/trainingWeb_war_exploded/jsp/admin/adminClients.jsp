<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="client_no_image" var="client_no_image"/>
<fmt:message bundle="${locale}" key="status_updated" var="status_updated"/>
<fmt:message bundle="${locale}" key="block_user" var="block_user"/>
<fmt:message bundle="${locale}" key="unblock_user" var="unblock_user"/>
<fmt:message bundle="${locale}" key="show_profile" var="show_profile"/>
<fmt:message bundle="${locale}" key="no_registered_clients" var="no_registered_clients"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <title>Clients</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="adminClients"/>
    <jsp:param name="currentPage" value="admin_clients"/>
</jsp:include>

<c:if test="${requestScope.success eq true}">
    ${status_updated}
</c:if>

<c:if test="${fn:length(all_clients) eq 0}">
    <h3><c:out value="${no_registered_clients}"/></h3>
</c:if>

<c:forEach items="${all_clients}" var="client">
    <br/>
    <img src="data:image/jpg;base64,${client.image}" alt="${client_no_image}" width="60" height="60" style="border-radius: 25px"/>
    <c:out value="${client.name} ${client.surname} (login : ${client.login})"/>
    <form action="${pageContext.request.contextPath}/controller?command=change_client_active" method="post">
        <input type="hidden" id="admin_client_id" name="admin_client_id" value="${client.id}"/>
        <c:if test="${client.active eq true}">
            <input type="submit" value="${block_user}"/>
        </c:if>
        <c:if test="${client.active eq false}">
            <input type="submit" value="${unblock_user}"/>
        </c:if>
    </form>

    <form action="${pageContext.request.contextPath}/controller?command=client_profile" method="post">
        <input type="hidden" name="admin_client_id" value="${client.id}" />
        <input type="submit" value="${show_profile}">
    </form>
    <br/>
    <hr/>
</c:forEach>

<footer>
    ${footer}
</footer>
</body>
</html>
