<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="lang.text.english" var="en"/>
<fmt:message bundle="${locale}" key="lang.text.russian" var="ru"/>
<fmt:message bundle="${locale}" key="lang.text.belorussian" var="be"/>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<nav>
<%--    <form action="controller">--%>
<%--        <input type="hidden" name="command" value="locale"/>--%>
<%--        <input type="hidden" name="currentPage" value="${param.currentPage}"/>--%>
<%--        <select name="lang">--%>
<%--            <option value="${ru}">Ru</option>--%>
<%--            <option value="${en}">En</option>--%>
<%--            <option value="${be}">Bel</option>--%>
<%--        </select>--%>
<%--        <p><input type="submit" value="OK"></p>--%>
<%--    </form>--%>

    <%--<li class="nav-item dropdown">--%>
    <a class="nav-link dropdown-toggle" id="dropdown09" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-globe" style="font-size: 30px" aria-hidden="true"></i></a>
    <div class="dropdown-menu" aria-labelledby="dropdown09">
        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/controller?command=locale&currentPage=${param.currentPage}&lang=en_US"><img src="img/flag/uk.png" width="40" height="20" alt="Eng"> Eng</a>
        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/controller?command=locale&currentPage=${param.currentPage}&lang=ru_RU"><img src="img/flag/rus.png" width="40" height="20" alt="Rus"> Rus</a>
        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/controller?command=locale&currentPage=${param.currentPage}&lang=be_BY"><img src="img/flag/bel.png" width="40" height="20" alt="Bel"> Bel</a>
    </div>
    <%--</li>--%>
</nav>
