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
<fmt:message bundle="${locale}" key="label.password" var="password"/>
<fmt:message bundle="${locale}" key="lang.text.english" var="en"/>
<fmt:message bundle="${locale}" key="lang.text.russian" var="ru"/>
<fmt:message bundle="${locale}" key="lang.text.belorussian" var="be"/>
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
    <input type="hidden" name="email" value="<%= request.getParameter("key1") %>">
    <input type="hidden" name="login" value="<%= request.getParameter("key2") %>">
    <div class="group">
        <label for="password">${password}</label>
        <input id="password" type="password" name="password" required placeholder="${password_placeholder}" title="${registration_pattern_error}"/>
    </div>
    <br/>
    <c:choose>
        <c:when test="${not empty requestScope.invalidPassword}">
            ${registration_pattern_error}
        </c:when>
    </c:choose>
    <br/>
    <input onclick="checkRegisterPassword()" type="submit" value="OK"/>
    <br/>
</form>
</body>
</html>
