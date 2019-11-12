<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 12.11.2019
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
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
</body>
</html>
