<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="restore_check" var="restore_check"/>

<html>
<head>
    <title>Restore</title>
</head>
<body>
<h2>${restore_check}</h2>
</body>
</html>
