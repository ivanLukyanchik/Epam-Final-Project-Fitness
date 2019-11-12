<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>My Exercises</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="exercises"/>
    <jsp:param name="currentPage" value="exercises"/>
</jsp:include>

<c:choose>
    <c:when test="${is_membership_valid == false}">
        <h3>${cant_choose}</h3>
        <form action="${pageContext.servletContext.contextPath}/controller?command=show_order_page" method="post">
            <input type="submit" class="button" value="${buy}">
        </form>
    </c:when>
</c:choose>
</body>
</html>
