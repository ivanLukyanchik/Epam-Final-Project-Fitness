<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="activation_text" var="activation_text"/>
<fmt:message bundle="${locale}" key="thanks_reg" var="thanks_reg"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <title>Verification</title>
</head>
<body>
<h1>${thanks_reg}</h1>
<h2>${activation_text}</h2>

<footer>
    ${footer}
</footer>
</body>
</html>
