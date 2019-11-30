<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="no_comments" var="no_comments"/>
<fmt:message bundle="${locale}" key="client_no_image" var="client_no_image"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Comments About Me</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="coachComments"/>
    <jsp:param name="currentPage" value="coach_comments"/>
</jsp:include>

<c:choose>
    <c:when test="${fn:length(comments) eq 0}">
        <h3><c:out value="${no_comments}"/></h3>
    </c:when>

    <c:otherwise>
        <c:forEach items="${comments}" var="comment">
            <div class="media border p-3">
                <img src="data:image/jpg;base64,${comment.value.image}" alt="${client_no_image}" class="mr-3 mt-3 rounded-circle" style="width:60px;">
                <div class="media-body">
<%--                    <h4>John Doe <small><i>Posted on February 19, 2016</i></small></h4>--%>
                    <h4><c:out value="${comment.value.name} ${comment.value.surname} (login : ${comment.value.login})"/></h4>
                    <p><c:out value="${comment.key.commentContent}"/></p>
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
