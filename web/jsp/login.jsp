<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    pageContext.setAttribute("currentPage", "login");
%>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="login.username.placeholder" var="username_placeholder"/>
<fmt:message bundle="${locale}" key="login.pattern.error" var="username_pattern_error"/>
<fmt:message bundle="${locale}" key="register.pattern.error" var="registration_pattern_error"/>
<fmt:message bundle="${locale}" key="login.password.placeholder" var="password_placeholder"/>
<fmt:message bundle="${locale}" key="login.password.error" var="password_pattern_error"/>
<fmt:message bundle="${locale}" key="login.no.account" var="no_account"/>
<fmt:message bundle="${locale}" key="label.login" var="login"/>
<fmt:message bundle="${locale}" key="label.password" var="password"/>
<fmt:message bundle="${locale}" key="button.submit" var="submit"/>
<fmt:message bundle="${locale}" key="message.wrongCredentials" var="wrongCredentials"/>
<fmt:message bundle="${locale}" key="forgot_password" var="forgot_password"/>
<fmt:message bundle="${locale}" key="restore.password.changed" var="passwordChanged"/>

<html>
<head>
    <script src="${pageContext.request.contextPath}/script/validation/loginValidation.js"></script>
    <title>LogIn</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty sessionScope.user}">
        <jsp:forward page="/welcome"/>
    </c:when>
</c:choose>
<header><jsp:include page="/jsp/header.jsp"/></header>
<form method="POST" action="controller">
    <input type="hidden" name="command" value="login"/>
    <div class="group">
    <label for="login">${login}</label>
    <input id="login" oninput="checkLogin()" type="text" name="login" required placeholder="${username_placeholder}" title="${username_pattern_error}"/>
    </div>
    <br/><br/>
    <div class="group">
    <label for="password">${password}</label>
    <input id="password" oninput="checkPassword()" type="password" name="password" required placeholder="${password_placeholder}" title="${registration_pattern_error}"/>
    </div>
    <br/>
    <a href="restore">${forgot_password}</a>
    <br/>
    <c:choose>
        <c:when test="${not empty requestScope.wrongData}">
            ${wrongCredentials}
        </c:when>
        <c:when test="${not empty requestScope.passwordChanged}">
            ${passwordChanged}
        </c:when>
        <c:when test="${not empty requestScope.invalidPassword}">
            ${registration_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.invalidLogin}">
            ${username_pattern_error}
        </c:when>
    </c:choose>
    <br/>
    <a href="${pageContext.request.contextPath}/register">${no_account}</a>
    <br/><br/>
    <input onclick="checkForLoginAnyData()" type="submit" value="${submit}"/>
</form>
</body>
</html>
