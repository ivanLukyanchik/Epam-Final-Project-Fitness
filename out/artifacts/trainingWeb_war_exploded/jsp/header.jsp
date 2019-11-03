<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="lang.text.english" var="en"/>
<fmt:message bundle="${locale}" key="lang.text.russian" var="ru"/>
<fmt:message bundle="${locale}" key="lang.text.belorussian" var="be"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="controller">
    <input type="hidden" name="command" value="locale"/>
    <input type="hidden" name="currentPage" value="${currentPage}"/>
    lang: <select name="lang">
        <option value="${ru}">Ru</option>
        <option value="${en}">En</option>
        <option value="${be}">Bel</option>
    </select>
    <p><input type="submit" value="OK"></p>
</form>
</body>
</html>
