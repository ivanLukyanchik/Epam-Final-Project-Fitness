<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="client_no_image" var="client_no_image"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <title>Clients</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="adminClients"/>
    <jsp:param name="currentPage" value="adminClients"/>
</jsp:include>

<c:forEach items="${all_clients}" var="client">
    <br/>
    <img src="data:image/jpg;base64,${client.image}" alt="${client_no_image}" width="60" height="60" style="border-radius: 25px"/>
    <c:out value="${client.name} ${client.surname} (login : ${client.login})"/>
    <form action="${pageContext.request.contextPath}/controller?command=change_client_active" method="post">
        <input type="hidden" id="admin_client_id" name="admin_client_id" value="${client.id}"/>
        <c:if test="${client.active eq true}">
            <input type="submit" value="Заблокировать пользователя"/>
        </c:if>
        <c:if test="${client.active eq false}">
            <input type="submit" value="Разблокировать пользователя"/>
        </c:if>
    </form>

    <form action="${pageContext.request.contextPath}/controller?command=client_profile" method="post">
        <input type="hidden" name="admin_client_id" value="${client.id}" />
        <input type="submit" value="Показать профиль">
    </form>
    <br/>
    <hr/>
</c:forEach>

<footer>
    ${footer}
</footer>
</body>
</html>
