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
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Comments</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="adminComments"/>
    <jsp:param name="currentPage" value="admin_comments"/>
</jsp:include>

<c:if test="${requestScope.success eq true}">
    <p class="text-success">${comment_deleted}</p>
</c:if>

<c:if test="${fn:length(comments) eq 0}">
    <h3><c:out value="${no_comments_admin}"/></h3>
</c:if>

<c:forEach items="${comments}" var="comment">
    <c:forEach items="${comment.value}" var="clientCoachEntry">
        <div class="media border p-3">
            <img src="data:image/jpg;base64,${clientCoachEntry.key.image}" alt="${client_no_image}" class="mr-3 mt-3 rounded-circle" style="width:60px;">
            <div class="media-body">
    <%--                                                                                                            Map<Comment, Map<Client, Coach>>--%>
                <h4><c:out value="${clientCoachEntry.key.name} ${clientCoachEntry.key.surname} (login : ${clientCoachEntry.key.login})"/>  <small><i>about coach with login : ${clientCoachEntry.value.login}</i></small></h4>
                <p><c:out value="${comment.key.commentContent}"/></p>
                <form method="post" action="${pageContext.servletContext.contextPath}/controller?command=delete_comment">
                    <input type="hidden" name="commentId" value="${comment.key.id}">
                    <input type="submit" class="btn btn-danger" value="${delete_comment}">
                </form>
            </div>
        </div>
    </c:forEach>
</c:forEach>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
