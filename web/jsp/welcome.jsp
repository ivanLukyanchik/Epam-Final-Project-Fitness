<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
    pageContext.setAttribute("currentPage", "welcome");
%>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="label.title" var="title"/>
<fmt:message bundle="${locale}" key="button.logout" var="logout"/>
<fmt:message bundle="${locale}" key="label.welcome" var="welcome"/>
<fmt:message bundle="${locale}" key="slogan" var="slogan"/>
<fmt:message bundle="${locale}" key="characteristic" var="characteristic"/>
<fmt:message bundle="${locale}" key="reasons" var="reasons"/>
<fmt:message bundle="${locale}" key="first_reason" var="first_reason"/>
<fmt:message bundle="${locale}" key="second_reason" var="second_reason"/>
<fmt:message bundle="${locale}" key="third_reason" var="third_reason"/>
<fmt:message bundle="${locale}" key="fourth_reason" var="fourth_reason"/>
<fmt:message bundle="${locale}" key="fifth_reason" var="fifth_reason"/>
<fmt:message bundle="${locale}" key="new_style" var="new_style"/>
<fmt:message bundle="${locale}" key="trainers_study" var="trainers_study"/>
<fmt:message bundle="${locale}" key="about_us" var="about_us"/>
<fmt:message bundle="${locale}" key="you_trained_by" var="you_trained_by"/>
<fmt:message bundle="${locale}" key="where" var="where"/>
<fmt:message bundle="${locale}" key="client" var="client" />
<fmt:message bundle="${locale}" key="coach" var="coach" />
<fmt:message bundle="${locale}" key="footer.copyright" var="footer" />

<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="/jsp/menu.jsp">
    <jsp:param name="pageTopic" value="about_us"/>
    <jsp:param name="currentPage" value="welcome"/>
</jsp:include>

<c:choose>
    <c:when test="${not empty sessionScope.client}">
        <h2>${client} ${user}, ${welcome}</h2>
    </c:when>
    <c:when test="${not empty sessionScope.coach}">
        <h2>${coach} ${user}, ${welcome}</h2>
    </c:when>
</c:choose>

<h2>${slogan}</h2>
<h2>${characteristic}</h2>
<p>${reasons}</p>
<ul>
    <li>${first_reason}</li>
    <li>${second_reason}</li>
    <li>${third_reason}</li>
    <li>${fourth_reason}</li>
    <li>${fifth_reason}</li>
</ul>
<h2>${new_style}</h2>
<p>${trainers_study}</p>
<p>${you_trained_by}</p>
<%--перечисление с фото--%>
    <footer>
        ${where}
<%--  вставить карту      --%>
        ${footer}
    </footer>
</body>
</html>