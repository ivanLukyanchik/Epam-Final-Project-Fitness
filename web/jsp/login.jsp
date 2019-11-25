<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<fmt:message bundle="${locale}" key="remember_me" var="remember_me"/>
<fmt:message bundle="${locale}" key="success_delete" var="success_delete"/>
<fmt:message bundle="${locale}" key="user_activated" var="user_activated"/>
<fmt:message bundle="${locale}" key="time_out" var="time_out"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <script src="${pageContext.request.contextPath}/script/validation/loginValidation.js"></script>
    <script src="${pageContext.request.contextPath}/script/util.js"></script>
    <title>LogIn</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="currentPage" value="show_login_page"/>
</jsp:include>

<form method="POST" action="${pageContext.request.contextPath}/controller?command=login_user">
    <div class="group">
    <label for="login">${login}</label>
    <input id="login" oninput="checkLogin()" type="text" name="login" required placeholder="${username_placeholder}" title="${username_pattern_error}"/>
    </div>
    <br/><br/>
    <div class="group">
    <label for="password">${password}</label>
    <input id="password" oninput="checkPassword()" type="password" name="password" required placeholder="${password_placeholder}" title="${registration_pattern_error}"/>
    <button type="button" onclick="showHide()" id="eye">
        <img src="https://cdn0.iconfinder.com/data/icons/feather/96/eye-16.png" alt="eye" />
    </button>
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
        <c:when test="${not empty requestScope.success}">
            ${success_delete}
        </c:when>
        <c:when test="${not empty requestScope.userActivated}">
            ${user_activated}
        </c:when>
        <c:when test="${not empty requestScope.timeOut}">
            ${time_out}
        </c:when>
    </c:choose>
    <br/>
    <a href="${pageContext.request.contextPath}/register">${no_account}</a>
    <br/><br/>
    <input onclick="checkForLoginAnyData()" type="submit" value="${submit}"/>
    <br/><br/>
    <input type="checkbox" id="rememberMe" name="rememberMe" value="true"/>
    <label for="rememberMe">${remember_me}</label>
</form>

<footer>
    ${footer}
</footer>
</body>
</html>
