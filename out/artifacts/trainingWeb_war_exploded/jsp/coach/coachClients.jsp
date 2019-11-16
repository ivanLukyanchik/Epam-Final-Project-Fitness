<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="no_clients" var="no_clients"/>
<fmt:message bundle="${locale}" key="nutrition_button" var="nutrition_button"/>
<fmt:message bundle="${locale}" key="exercises_button" var="exercises_button"/>

<html>
<head>
    <title>My clients</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="coachClients"/>
    <jsp:param name="currentPage" value="coachClients"/>
</jsp:include>

<c:choose>
    <c:when test="${fn:length(all_clients) eq 0}">
        <h3>${no_clients}</h3>
    </c:when>

    <c:otherwise>
        <c:forEach items="${all_clients}" var="client">
            <hr/>
            <c:out value="${client.name} ${client.surname} (login : ${client.login})"/>
            <form action="${pageContext.request.contextPath}/controller?command=show_client_exercises" method="post">
                <input type="hidden" id="coach_client_id" name="coach_client_id" value="${client.id}">
                <input type="submit" value="${exercises_button}"/>
            </form>
            <form action="${pageContext.request.contextPath}/controller?command=show_client_nutrition" method="post">
                <input type="hidden" id="coach_client_id" name="coach_client_id" value="${client.id}"/>
                <input type="submit" value="${nutrition_button}"/>
            </form>
            <br/>
            <hr/>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
