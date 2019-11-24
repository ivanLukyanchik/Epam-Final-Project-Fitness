<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="no_comments" var="no_comments"/>
<fmt:message bundle="${locale}" key="client_no_image" var="client_no_image"/>
<fmt:message bundle="${locale}" key="comment_deleted" var="comment_deleted"/>
<fmt:message bundle="${locale}" key="delete_comment" var="delete_comment"/>
<fmt:message bundle="${locale}" key="no_comments_admin" var="no_comments_admin"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <title>Comments</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="adminComments"/>
    <jsp:param name="currentPage" value="adminComments"/>
</jsp:include>

<c:if test="${requestScope.success eq true}">
    ${comment_deleted}
</c:if>

<c:if test="${fn:length(comments) eq 0}">
    <h3><c:out value="${no_comments_admin}"/></h3>
</c:if>

<c:forEach items="${comments}" var="comment">
    <hr/>
    <img src="data:image/jpg;base64,${comment.value.image}" alt="${client_no_image}" width="60" height="60" style="border-radius: 25px"/>
    <h3><c:out value="${comment.value.name} ${comment.value.surname} (login : ${comment.value.login})"/></h3>
    <h5><c:out value="${comment.key.commentContent}"/></h5>
    <form method="post" action="${pageContext.servletContext.contextPath}/controller?command=delete_comment">
        <input type="hidden" name="commentId" value="${comment.key.id}">
        <input type="submit" value="${delete_comment}">
    </form>
    <hr/>
</c:forEach>

<footer>
    ${footer}
</footer>
</body>
</html>
