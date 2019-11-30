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
<fmt:message bundle="${locale}" key="return_to_login" var="return_to_login"/>
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
    <jsp:param name="currentPage" value="restore"/>
</jsp:include>

<form method="POST" action="controller" class="text-center border border-light p-5">

    <p class="h4 mb-4">${message}</p>

    <input type="hidden" name="command" value="restore">

    <label for="email">${email}</label>
    <input id="email" name="email" type="text" class="form-control mb-4" required placeholder="example@gmail.com" title="${email_pattern_error}"/>

    <label for="login">${login}</label>
    <input id="login" name="login" type="text" class="form-control mb-4" required placeholder="VasyaPupkin" title="${username_pattern_error}"/>
    <c:choose>
        <c:when test="${not empty requestScope.wrongData}">
            <p class="text-danger">${restore_error_mail}</p>
        </c:when>
        <c:when test="${not empty requestScope.invalidEmail}">
            <p class="text-danger">${email_pattern_error}</p>
        </c:when>
        <c:when test="${not empty requestScope.invalidLogin}">
            <p class="text-danger">${username_pattern_error}</p>
        </c:when>
    </c:choose>
    <input onclick="checkRegisterLoginEmail()" class="btn btn-info my-4 btn-block" type="submit" value="OK">
    <a href="${pageContext.servletContext.contextPath}/login">${return_to_login}</a>
</form>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
