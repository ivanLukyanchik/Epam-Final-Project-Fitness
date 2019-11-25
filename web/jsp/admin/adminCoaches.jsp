<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="coach_added" var="coach_added"/>
<fmt:message bundle="${locale}" key="coach_name" var="coach_name"/>
<fmt:message bundle="${locale}" key="surname" var="surname"/>
<fmt:message bundle="${locale}" key="patronymic" var="patronymic"/>
<fmt:message bundle="${locale}" key="login" var="login"/>
<fmt:message bundle="${locale}" key="coach_password" var="coach_password"/>
<fmt:message bundle="${locale}" key="add_coach" var="add_coach"/>
<fmt:message bundle="${locale}" key="register.pattern.error" var="registration_pattern_error"/>
<fmt:message bundle="${locale}" key="login.pattern.error" var="username_pattern_error"/>
<fmt:message bundle="${locale}" key="no_coaches" var="no_coaches"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <script src="${pageContext.request.contextPath}/script/validation/registerValidation.js"></script>
    <script src="${pageContext.request.contextPath}/script/util.js"></script>
    <title>Coaches</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="adminCoaches"/>
    <jsp:param name="currentPage" value="admin_coaches"/>
</jsp:include>

<c:if test="${requestScope.success eq true}">
    ${coach_added}
</c:if>

<form method="post" action="${pageContext.servletContext.contextPath}/controller?command=add_coach">
    <br/>
    <label for="name">${coach_name}</label>
    <input type="text" onchange="checkName()" id="name" name="name" required title="${registration_pattern_error}"/>

    <br/>
    <label for="surname">${surname}</label>
    <input type="text" onchange="checkSurname()" id="surname" name="surname" required title="${registration_pattern_error}"/>

    <br/>
    <label for="patronymic">${patronymic}</label>
    <input type="text" onchange="checkPatronymic()" id="patronymic" name="patronymic" required title="${registration_pattern_error}"/>

    <br/>
    <label for="login">${login}</label>
    <input onchange="checkLogin()" type="text" id="login" name="login" required title="${username_pattern_error}"/>

    <br/>
    <label for="password">${coach_password}</label>
    <input onchange="checkLogin()" type="password" id="password" name="password" required title="${registration_pattern_error}"/>
    <button type="button" onclick="showHide()" id="eye">
        <img src="https://cdn0.iconfinder.com/data/icons/feather/96/eye-16.png" alt="eye" />
    </button>

    <br/>
    <input type="submit" value="${add_coach}">
</form>

<c:if test="${fn:length(coaches) eq 0}">
    <h3><c:out value="${no_coaches}"/></h3>
</c:if>

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
