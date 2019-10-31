<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("currentPage", "register");
%>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="locale.pagecontent" var="locale"/>

    <fmt:message bundle="${locale}" key="login.username.placeholder" var="username_placeholder"/>
    <fmt:message bundle="${locale}" key="login.pattern.error" var="username_pattern_error"/>
    <fmt:message bundle="${locale}" key="register.pattern.error" var="registration_pattern_error"/>
    <fmt:message bundle="${locale}" key="login.password.placeholder" var="password_placeholder"/>
    <fmt:message bundle="${locale}" key="register.email.placeholder" var="email_placeholder"/>
    <fmt:message bundle="${locale}" key="email.pattern.error" var="email_pattern_error"/>
    <fmt:message bundle="${locale}" key="lang.text.english" var="en"/>
    <fmt:message bundle="${locale}" key="lang.text.russian" var="ru"/>
    <fmt:message bundle="${locale}" key="lang.text.belorussian" var="be"/>
    <fmt:setLocale value="${sessionScope.local}" scope="session"/>
    <fmt:setBundle basename="locale.pagecontent" var="local"/>
    <fmt:message bundle="${local}" key="label.login" var="login"/>
    <fmt:message bundle="${local}" key="label.password" var="password"/>
    <fmt:message bundle="${local}" key="label.email" var="email"/>
    <fmt:message bundle="${local}" key="button.submit" var="submit"/>
    <fmt:message bundle="${local}" key="message.wrongLogin" var="wrongLogin"/>

    <script src="${pageContext.request.contextPath}/script/validation/registerValidation.js"></script>
    <title>Registration</title>
</head>
<body>
<form action="controller">
    <input type="hidden" name="command" value="locale"/>
    <input type="hidden" name="currentPage" value="${currentPage}"/>
    lang: <select name="lang">
    <option value="${ru}">Ru</option>
    <option value="${en}">En</option>
    <option value="${be}">Bel</option>
</select>
    <p><input type="submit" value="OK"></p>
</form>
<%--<header><jsp:include page="/jsp/header.jsp"/></header>--%>
<form method="POST" action="controller">
    <input type="hidden" name="command" value="register"/>
    <div class="group">
    <label for="login">${login}</label>
    <input id="login" type="text" name="login" required placeholder="${username_placeholder}" title="${username_pattern_error}"/>
    </div>
    <br/><br/>
    <div class="group">
    <label for="password">${password}</label>
    <input id="password" type="password" name="password" required placeholder="${password_placeholder}" title="${registration_pattern_error}"/>
    </div>
    <br/><br/>
    <div class="group">
    <label for="email">${email}</label>
    <input id="email" type="text" name="email" required placeholder="${email_placeholder}" title="${email_pattern_error}"/>
    </div>
    <br/>
    <c:choose>
        <c:when test="${not empty requestScope.wrongData}">
            ${wrongLogin}
        </c:when>
        <c:when test="${not empty requestScope.invalidPassword}">
            ${registration_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.invalidLogin}">
            ${username_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.invalidEmail}">
            ${email_pattern_error}
        </c:when>
    </c:choose>
    <br/>
    <input onclick="checkForRegistrationAnyData()" type="submit" value="${submit}"/>
</form>
</body>
</html>
