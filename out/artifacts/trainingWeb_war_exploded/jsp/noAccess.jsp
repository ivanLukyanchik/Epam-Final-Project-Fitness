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
    <jsp:param name="currentPage" value="no_access"/>
</jsp:include>

<h1>${no_access}</h1>
<a href="${pageContext.servletContext.contextPath}/login" style="text-shadow: none;text-align: center">${return_to_login}</a>
<br/>
<br/>
<a href="${pageContext.servletContext.contextPath}/welcome" style="text-shadow: none;text-align: center">return to main page</a>
<footer>
    ${footer}
</footer>
</body>
</html>
