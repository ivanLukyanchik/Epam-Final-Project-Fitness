<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="home" var="home"/>
<fmt:message bundle="${locale}" key="button.logout" var="logout"/>
<fmt:message bundle="${locale}" key="about_us" var="about_us"/>
<fmt:message bundle="${locale}" key="lang.text.english" var="en"/>
<fmt:message bundle="${locale}" key="lang.text.russian" var="ru"/>
<fmt:message bundle="${locale}" key="lang.text.belorussian" var="be"/>
<fmt:message bundle="${locale}" key="profile_topic" var="profile_topic"/>
<fmt:message bundle="${locale}" key="profile" var="profile"/>

<nav>
    <ul class="top-menu">
        <li id="home"><a href="${pageContext.servletContext.contextPath}/controller?command=home_page" >${home}</a></li>
        <li id="signOut"><a href="${pageContext.servletContext.contextPath}/controller?command=logout">${logout}</a></li>
        <li id="language">
            <form action="controller">
                <input type="hidden" name="command" value="locale"/>
                <input type="hidden" name="currentPage" value="${param.currentPage}"/>
                <select name="lang">
                <option value="${ru}">Ru</option>
                <option value="${en}">En</option>
                <option value="${be}">Bel</option>
            </select>
                <p><input type="submit" value="OK"></p>
            </form>
    </ul>
</nav>
<div id="heading">
<c:choose>
    <c:when test="${param.pageTopic eq 'about_us'}">
        <h1>${about_us}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'coaches'}">
        <h1>${coaches}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'coach_clients'}">
        <h1>${coach_clients}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'profile'}">
        <h1>${profile_topic}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'nutrition'}">
        <h1>${nutrition}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'exercises'}">
        <h1>${exercises}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'buy_gym_membership'}">
        <h1>${buy_gym_membership}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'show_comments'}">
        <h1>${comments_about_me}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'gym_photos'}">
        <h1>${gym_photos}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'orders'}">
        <h1>${my_orders}</h1>
    </c:when>
</c:choose>>
</div>
<nav>
    <li>
        <a href="${pageContext.servletContext.contextPath}/controller?command=client_profile">${profile}</a>
    </li>
    <li>
        <a href="${pageContext.servletContext.contextPath}/controller?command=gym_photos">Photos</a>
    </li>
</nav>