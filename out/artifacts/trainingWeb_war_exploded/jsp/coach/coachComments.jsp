<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="no_comments" var="no_comments"/>

<html>
<head>
    <title>Comments About Me</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="coachComments"/>
    <jsp:param name="currentPage" value="coachComments"/>
</jsp:include>

<c:choose>
    <c:when test="${fn:length(comments) eq 0}">
        <h3><c:out value="${no_comments}"/></h3>
    </c:when>

    <c:otherwise>
        <c:forEach items="${comments}" var="comment">
            <hr/>
            <h3><c:out value="${comment.value.name} ${comment.value.surname}"/></h3>
            <br>
            <h5><c:out value="${comment.key.commentContent}"/></h5>
            <hr/>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
