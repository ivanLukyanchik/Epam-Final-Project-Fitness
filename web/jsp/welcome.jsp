<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
<fmt:message bundle="${locale}" key="admin" var="admin" />
<fmt:message bundle="${locale}" key="stranger" var="stranger" />
<fmt:message bundle="${locale}" key="coach_chosen" var="coach_chosen" />
<fmt:message bundle="${locale}" key="comment_saved" var="comment_saved" />
<fmt:message bundle="${locale}" key="coach_rejected" var="coach_rejected" />
<fmt:message bundle="${locale}" key="nutrition_rejected" var="nutrition_rejected" />
<fmt:message bundle="${locale}" key="footer.copyright" var="footer" />

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>${title}</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="/jsp/menu.jsp">
    <jsp:param name="pageTopic" value="about_us"/>
    <jsp:param name="currentPage" value="home_page"/>
</jsp:include>

<c:choose>
    <c:when test="${not empty sessionScope.client}">
        <h2>${client} ${sessionScope.user}, ${welcome}</h2>
    </c:when>
    <c:when test="${not empty sessionScope.coach}">
        <h2>${coach} ${sessionScope.user}, ${welcome}</h2>
    </c:when>
    <c:when test="${not empty sessionScope.admin}">
        <h2>${admin} ${sessionScope.user}, ${welcome}</h2>
    </c:when>
    <c:otherwise>
        <h2>${stranger}, ${welcome}</h2>
    </c:otherwise>
</c:choose>

<c:if test="${coachChosen eq true}">
    <h3>${coach_chosen}</h3>
</c:if>
<c:if test="${commentSaved eq true}">
    <h3>${comment_saved}</h3>
</c:if>
<c:if test="${coachRejected eq true}">
    <h3>${coach_rejected}</h3>
</c:if>
<c:if test="${nutritionRejected eq true}">
    <h3>${nutrition_rejected}</h3>
</c:if>

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

${where}
<br/>
<div style="overflow:hidden;width: 700px;position: relative;"><iframe width="540" height="300" src="https://maps.google.com/maps?width=700&amp;height=440&amp;hl=en&amp;q=%D0%9C%D0%B8%D0%BD%D1%81%D0%BA%2C%20%D1%83%D0%BB.%20%D0%91%D0%B5%D0%B4%D1%8B%204+(%C2%AB%D0%9E%D0%BB%D0%B8%D0%BC%D0%BF%D0%B8%D1%8F%C2%BB%20%D0%A4%D0%B8%D1%82%D0%BD%D0%B5%D1%81-%D1%86%D0%B5%D0%BD%D1%82%D1%80)&amp;ie=UTF8&amp;t=k&amp;z=12&amp;iwloc=B&amp;output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe><div style="position: absolute;width: 80%;bottom: 10px;left: 0;right: 0;margin-left: auto;margin-right: auto;color: #000;text-align: center;"><small style="line-height: 1.8;font-size: 2px;background: #fff;">Powered by <a href="https://embedgooglemaps.com/en/">embedgooglemaps EN</a> & <a href="https://iamsterdamcard.it">www.iamsterdamcard.it</a></small></div><style>#gmap_canvas img{max-width:none!important;background:none!important}</style></div><br/>
<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>