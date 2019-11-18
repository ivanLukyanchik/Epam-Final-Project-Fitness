<%@ page import="by.epam.fitness.util.JspConst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    pageContext.setAttribute("currentPage", "passwordRestore");
%>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="register.pattern.error" var="registration_pattern_error"/>
<fmt:message bundle="${locale}" key="login.password.placeholder" var="password_placeholder"/>
<fmt:message bundle="${locale}" key="restore.message.password" var="password_message"/>
<fmt:message bundle="${locale}" key="passwords.match.error" var="passwordsNotMatch"/>
<fmt:message bundle="${locale}" key="password.confirm" var="confirmPassword"/>
<fmt:message bundle="${locale}" key="restore.incorrect" var="incorrectDataMessage"/>
<fmt:message bundle="${locale}" key="label.password" var="password"/>
<fmt:message bundle="${locale}" key="lang.text.english" var="en"/>
<fmt:message bundle="${locale}" key="lang.text.russian" var="ru"/>
<fmt:message bundle="${locale}" key="lang.text.belorussian" var="be"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <script src="${pageContext.request.contextPath}/script/validation/registerValidation.js"></script>
    <title>Restore</title>
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
<h1>${password_message}</h1>
<form method="POST" action="controller">
    <input type="hidden" name="command" value="password_restore">
    <input type="hidden" name="email" value="<%= request.getParameter(JspConst.PARAM_KEY_1) %>">
    <input type="hidden" name="login" value="<%= request.getParameter(JspConst.PARAM_KEY_2) %>">
    <input type="hidden" name="hash" value="<%= request.getParameter(JspConst.PARAM_KEY_3) %>">
    <div class="group">
        <label for="password">${password}</label>
        <input id="password" type="password" name="password" required placeholder="${password_placeholder}" title="${registration_pattern_error}"/>
    </div>
    <br/>
    <div class="group">
        <label for="confirmPassword">${confirmPassword}</label>
        <input id="confirmPassword" type="password" name="confirmPassword" required placeholder="${password_placeholder}" title="${registration_pattern_error}"/>
    </div>
    <br/>
    <c:choose>
        <c:when test="${not empty requestScope.invalidPassword}">
            ${registration_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.passwordsNotMatch}">
            ${passwordsNotMatch}
        </c:when>
        <c:when test="${not empty requestScope.incorrectData}">
            ${incorrectDataMessage}
        </c:when>
    </c:choose>
    <br/>
    <input onclick="checkRegisterPassword()" type="submit" value="OK"/>
    <br/>
</form>

<footer>
    ${footer}
</footer>
</body>
</html>
