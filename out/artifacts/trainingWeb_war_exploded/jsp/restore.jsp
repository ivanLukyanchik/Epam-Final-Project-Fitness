<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="login.password.error" var="password_pattern_error"/>
<fmt:message bundle="${locale}" key="login.pattern.error" var="username_pattern_error"/>
<fmt:message bundle="${locale}" key="email.pattern.error" var="email_pattern_error"/>
<fmt:message bundle="${locale}" key="restore.error.email" var="restore_error_mail"/>
<fmt:message bundle="${locale}" key="restore.first.message" var="message"/>
<fmt:message bundle="${locale}" key="label.login" var="login"/>
<fmt:message bundle="${locale}" key="label.email" var="email"/>
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
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="currentPage" value="restore"/>
</jsp:include>

<h1>${message}</h1>
<form method="POST" action="controller">
    <input type="hidden" name="command" value="restore">
    <label for="email">${email}</label>
    <input id="email" name="email" type="text" required placeholder="example@gmail.com" title="${email_pattern_error}"/>
    <br/><br/>
    <label for="login">${login}</label>
    <input id="login" name="login" type="text" required placeholder="VasyaPupkin" title="${username_pattern_error}"/>
    <br/>
    <c:choose>
        <c:when test="${not empty requestScope.wrongData}">
            ${restore_error_mail}
        </c:when>
        <c:when test="${not empty requestScope.invalidEmail}">
            ${email_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.invalidLogin}">
            ${username_pattern_error}
        </c:when>
    </c:choose>
    <br/>
    <input onclick="checkRegisterLoginEmail()" type="submit" value="OK">
    <br/>
</form>

<footer>
    ${footer}
</footer>
</body>
</html>
