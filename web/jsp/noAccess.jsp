<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="no_access" var="no_access"/>
<fmt:message bundle="${locale}" key="return_to_login" var="return_to_login"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <title>Restricted</title>
</head>
<body>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="currentPage" value="noAccess"/>
</jsp:include>

<h1>${no_access}</h1>
<a href="${pageContext.servletContext.contextPath}/login" style="position: absolute;margin-top: 250px;margin-left: 680px;background: white;color: black;font-weight: 700;width: 84px;height: 20px;text-shadow: none;border-radius: 5px;">${return_to_login}</a>
<footer>
    ${footer}
</footer>
</body>
</html>
