<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="login.username.placeholder" var="username_placeholder"/>
<fmt:message bundle="${locale}" key="login.pattern.error" var="username_pattern_error"/>
<fmt:message bundle="${locale}" key="register.pattern.error" var="registration_pattern_error"/>
<fmt:message bundle="${locale}" key="login.password.placeholder" var="password_placeholder"/>
<fmt:message bundle="${locale}" key="register.email.placeholder" var="email_placeholder"/>
<fmt:message bundle="${locale}" key="email.pattern.error" var="email_pattern_error"/>
<fmt:message bundle="${locale}" key="restore.incorrect" var="incorrectDataMessage"/>
<fmt:message bundle="${locale}" key="register.yes.account" var="yes_account"/>
<fmt:message bundle="${locale}" key="lang.text.english" var="en"/>
<fmt:message bundle="${locale}" key="lang.text.russian" var="ru"/>
<fmt:message bundle="${locale}" key="lang.text.belorussian" var="be"/>
<fmt:message bundle="${locale}" key="label.login" var="login"/>
<fmt:message bundle="${locale}" key="label.password" var="password"/>
<fmt:message bundle="${locale}" key="label.email" var="email"/>
<fmt:message bundle="${locale}" key="button.submit" var="submit"/>
<fmt:message bundle="${locale}" key="message.wrongLogin" var="wrongLogin"/>
<fmt:message bundle="${locale}" key="label.name" var="name"/>
<fmt:message bundle="${locale}" key="label.surname" var="surname"/>
<fmt:message bundle="${locale}" key="login.surname.placeholder" var="surname_placeholder"/>
<fmt:message bundle="${locale}" key="login.name.placeholder" var="name_placeholder"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <script src="${pageContext.request.contextPath}/script/validation/registerValidation.js"></script>
    <script src="${pageContext.request.contextPath}/script/util.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Registration</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="currentPage" value="show_register_page"/>
</jsp:include>

<form method="POST" action="controller" class="text-center border border-light p-5">

    <p class="h4 mb-4">Sign up</p>

    <input type="hidden" name="command" value="register"/>

    <div class="form-row mb-4">
        <div class="col">
    <%--        <label for="name">${name}</label>--%>
            <input onchange="checkName()" id="name" class="form-control" type="text" name="name" required placeholder="${name_placeholder}" title="${registration_pattern_error}"/>
        </div>
        <div class="col">
    <%--        <label for="surname">${surname}</label>--%>
            <input onchange="checkSurname()" id="surname" class="form-control" type="text" name="surname" required placeholder="${surname_placeholder}" title="${registration_pattern_error}"/>
        </div>
    </div>

    <div>
        <label for="login">${login}</label>
        <input onchange="checkLogin()" id="login" type="text" name="login" class="form-control mb-4" required placeholder="${username_placeholder}" title="${username_pattern_error}"/>
    </div>

    <label for="password">${password}</label>
    <div class="input-group">
        <input onchange="checkPassword()" id="password" type="password" name="password" class="form-control mb-4" required placeholder="${password_placeholder}" title="${registration_pattern_error}"/>
        <span class="input-group-btn">
            <button type="button" class="btn btn-default" onclick="showHide()" id="eye">
                <img src="https://cdn0.iconfinder.com/data/icons/feather/96/eye-16.png" alt="eye" />
            </button>
        </span>
    </div>

    <div>
        <label for="email">${email}</label>
        <input onchange="checkEmail()" id="email" type="text" name="email" class="form-control mb-4" required placeholder="${email_placeholder}" title="${email_pattern_error}"/>
    </div>
    <c:choose>
        <c:when test="${not empty requestScope.wrongData}">
            ${wrongLogin}
        </c:when>
        <c:when test="${not empty requestScope.invalidPassword}">
            ${registration_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.invalidSurname}">
            ${registration_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.invalidName}">
            ${registration_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.invalidLogin}">
            ${username_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.invalidEmail}">
            ${email_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.incorrectData}">
            ${incorrectDataMessage}
        </c:when>
    </c:choose>
    <input onclick="checkForRegistrationAnyData()" class="btn btn-info my-4 btn-block" type="submit" value="${submit}"/>
    <a href="${pageContext.request.contextPath}/login">${yes_account}</a>
</form>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
