<%@ page import="by.epam.fitness.util.JspConst" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<fmt:message bundle="${locale}" key="return_to_profile" var="return_to_profile"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <script src="${pageContext.request.contextPath}/script/validation/registerValidation.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Restore</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="currentPage" value="passwordRestore"/>
</jsp:include>

<form method="POST" action="controller" class="text-center border border-light p-5">

    <p class="h4 mb-4">${password_message}</p>

    <input type="hidden" name="command" value="password_restore">
    <input type="hidden" name="email" value="<%= request.getParameter(JspConst.PARAM_KEY_1) %>">
    <input type="hidden" name="login" value="<%= request.getParameter(JspConst.PARAM_KEY_2) %>">
    <input type="hidden" name="hash" value="<%= request.getParameter(JspConst.PARAM_KEY_3) %>">
    <div>
        <label for="password">${password}</label>
        <input id="password" type="password" class="form-control mb-4" name="password" required placeholder="${password_placeholder}" title="${registration_pattern_error}"/>
    </div>
    <br/>
    <div>
        <label for="confirmPassword">${confirmPassword}</label>
        <input id="confirmPassword" type="password" class="form-control mb-4" name="confirmPassword" required placeholder="${password_placeholder}" title="${registration_pattern_error}"/>
    </div>
    <c:choose>
        <c:when test="${not empty requestScope.invalidPassword}">
            <p class="text-danger">${registration_pattern_error}</p>
        </c:when>
        <c:when test="${not empty requestScope.passwordsNotMatch}">
            <p class="text-danger">${passwordsNotMatch}</p>
        </c:when>
        <c:when test="${not empty requestScope.incorrectData}">
            <p class="text-danger">${incorrectDataMessage}</p>
        </c:when>
    </c:choose>
    <input onclick="checkRegisterPassword()" class="btn btn-info my-4 btn-block" type="submit" value="OK"/>
    <c:if test="${not empty sessionScope.client}">
        <a href="${pageContext.request.contextPath}/controller?command=client_profile">${return_to_profile}</a>
    </c:if>
</form>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
