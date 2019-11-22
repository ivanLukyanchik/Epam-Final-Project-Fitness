<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <title>Coaches</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="adminCoaches"/>
    <jsp:param name="currentPage" value="adminCoaches"/>
</jsp:include>

<form method="post" action="${pageContext.servletContext.contextPath}/controller?command=add_coach">
    <br/>
    <label for="name">Имя</label>
    <input type="text" id="name" name="name"/>

    <br/>
    <label for="surname">Фамилия</label>
    <input type="text" id="surname" name="surname"/>

    <br/>
    <label for="patronymic">Отчество</label>
    <input type="text" id="patronymic" name="patronymic"/>

    <br/>
    <label for="login">Логин</label>
    <input type="text" id="login" name="login"/>

    <br/>
    <label for="password">Пароль</label>
    <input type="password" id="password" name="password"/>

    <br/>
    <input type="submit" value="Добавить тренера">
</form>

<ul>
    <c:forEach items="${coaches}" var="coach">
        <li><c:out value="${coach.name} ${coach.surname} ${coach.patronymic} (login : ${coach.login})"/></li>
    </c:forEach>
</ul>

<footer>
    ${footer}
</footer>
</body>
</html>
