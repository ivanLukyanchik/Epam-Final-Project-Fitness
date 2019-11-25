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
    <title>Comments About Me</title>
</head>
<body>
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
            <hr/>
            <img src="data:image/jpg;base64,${comment.value.image}" alt="${client_no_image}" width="60" height="60" style="border-radius: 25px"/>
            <h3><c:out value="${comment.value.name} ${comment.value.surname} (login : ${comment.value.login})"/></h3>
            <h5><c:out value="${comment.key.commentContent}"/></h5>
            <hr/>
        </c:forEach>
    </c:otherwise>
</c:choose>

<footer>
    ${footer}
</footer>
</body>
</html>
