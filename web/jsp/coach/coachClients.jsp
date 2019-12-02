<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="no_clients" var="no_clients"/>
<fmt:message bundle="${locale}" key="nutrition_button" var="nutrition_button"/>
<fmt:message bundle="${locale}" key="exercises_button" var="exercises_button"/>
<fmt:message bundle="${locale}" key="client_no_image" var="client_no_image"/>
<fmt:message bundle="${locale}" key="orders_button" var="orders_button"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>My clients</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="coachClients"/>
    <jsp:param name="currentPage" value="coach_clients"/>
</jsp:include>

<c:choose>
    <c:when test="${fn:length(all_clients) eq 0}">
        <h3>${no_clients}</h3>
    </c:when>

    <c:otherwise>
        <c:forEach items="${all_clients}" var="client">
            <div class="media border p-3">
                <img src="data:image/jpg;base64,${client.image}" alt="${client_no_image}" class="mr-3 mt-3 rounded-circle" style="width:60px;">
                <div class="media-body">
                    <h4><c:out value="${client.name} ${client.surname} (login : ${client.login})"/></h4>
                    <div class="container">
                        <div class="row">
                            <div class="col-3">
                                <form action="${pageContext.request.contextPath}/controller?command=show_client_exercises" method="post">
                                    <input type="hidden" name="coach_client_id" value="${client.id}">
                                    <input type="submit" class="btn btn-primary" value="${exercises_button}"/>
                                </form>
                            </div>
                            <div class="col-3">
                                <form action="${pageContext.request.contextPath}/controller?command=show_client_nutrition" method="post">
                                    <input type="hidden" name="coach_client_id" value="${client.id}"/>
                                    <input type="submit" class="btn btn-success" value="${nutrition_button}"/>
                                </form>
                            </div>
                            <div class="col-3">
                                <form action="${pageContext.request.contextPath}/controller?command=client_orders" method="post">
                                    <input type="hidden" name="coach_client_id" value="${client.id}"/>
                                    <input type="submit" class="btn btn-info" value="${orders_button}"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
