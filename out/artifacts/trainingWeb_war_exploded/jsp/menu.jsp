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
<fmt:message bundle="${locale}" key="gym_membership" var="gym_membership"/>
<fmt:message bundle="${locale}" key="gym_membership_nav" var="gym_membership_nav"/>
<fmt:message bundle="${locale}" key="gym_photos" var="gym_photos"/>
<fmt:message bundle="${locale}" key="my_orders" var="my_orders"/>
<fmt:message bundle="${locale}" key="my_orders_nav" var="my_orders_nav"/>
<fmt:message bundle="${locale}" key="coaches" var="coaches"/>
<fmt:message bundle="${locale}" key="coaches_nav" var="coaches_nav"/>
<fmt:message bundle="${locale}" key="exercises" var="exercises"/>
<fmt:message bundle="${locale}" key="exercises_nav" var="exercises_nav"/>
<fmt:message bundle="${locale}" key="nutrition" var="nutrition"/>
<fmt:message bundle="${locale}" key="nutrition_nav" var="nutrition_nav"/>
<fmt:message bundle="${locale}" key="coach_clients" var="coach_clients"/>
<fmt:message bundle="${locale}" key="coach_clients_nav" var="coach_clients_nav"/>
<fmt:message bundle="${locale}" key="comments_about_me" var="comments_about_me"/>
<fmt:message bundle="${locale}" key="comments_me_topic" var="comments_me_topic"/>
<fmt:message bundle="${locale}" key="photos" var="photos"/>
<fmt:message bundle="${locale}" key="my_clients" var="my_clients"/>
<fmt:message bundle="${locale}" key="my_clients_orders" var="my_clients_orders"/>
<fmt:message bundle="${locale}" key="my_client's_nutrition" var="my_clients_nutrition"/>
<fmt:message bundle="${locale}" key="my_client's_exercises" var="my_clients_exercises"/>
<fmt:message bundle="${locale}" key="admin_coaches_nav" var="admin_coaches_nav"/>
<fmt:message bundle="${locale}" key="admin_clients_nav" var="admin_clients_nav"/>
<fmt:message bundle="${locale}" key="admin_exercises_nav" var="admin_exercises_nav"/>
<fmt:message bundle="${locale}" key="admin_comments_nav" var="admin_comments_nav"/>
<fmt:message bundle="${locale}" key="admin_orders_nav" var="admin_orders_nav"/>

<nav>
    <ul class="top-menu">
        <li id="home"><a href="${pageContext.servletContext.contextPath}/controller?command=home_page" >${home}</a></li>
        <li id="signOut"><a href="${pageContext.servletContext.contextPath}/controller?command=logout_user">${logout}</a></li>
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

    <c:when test="${param.pageTopic eq 'gym_photos'}">
        <h1>${gym_photos}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'gym_membership'}">
        <h1>${gym_membership}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'profile'}">
        <h1>${profile_topic}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'orders'}">
        <c:if test="${not empty sessionScope.client}">
            <h1>${my_orders}</h1>
        </c:if>
        <c:if test="${not empty sessionScope.coach}">
            <h1>${my_clients_orders}</h1>
        </c:if>
    </c:when>

    <c:when test="${param.pageTopic eq 'allCoaches'}">
        <h1>${coaches}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'exercises'}">
        <c:if test="${not empty sessionScope.client}">
            <h1>${exercises}</h1>
        </c:if>
        <c:if test="${not empty sessionScope.coach}">
            <h1>${my_clients_exercises}</h1>
        </c:if>
    </c:when>

    <c:when test="${param.pageTopic eq 'nutrition'}">
        <c:if test="${not empty sessionScope.client}">
            <h1>${nutrition}</h1>
        </c:if>
        <c:if test="${not empty sessionScope.coach}">
            <h1>${my_clients_nutrition}</h1>
        </c:if>
    </c:when>

    <c:when test="${param.pageTopic eq 'coachClients'}">
        <h1>${coach_clients}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'coachComments'}">
        <h1>${comments_me_topic}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'adminCoaches'}">
        <h1>${admin_coaches_nav}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'adminClients'}">
        <h1>${admin_clients_nav}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'adminExercises'}">
        <h1>${admin_exercises_nav}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'adminComments'}">
        <h1>${admin_comments_nav}</h1>
    </c:when>

    <c:when test="${param.pageTopic eq 'adminOrders'}">
        <h1>${admin_orders_nav}</h1>
    </c:when>

</c:choose>
</div>
<nav>
    <c:if test="${not empty sessionScope.client}">
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=client_profile">${profile}</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=gym_photos">${photos}</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=show_order_page">${gym_membership_nav}</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=client_orders">${my_orders_nav}</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=find_coaches">${coaches_nav}</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=show_client_exercises">${exercises_nav}</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=show_client_nutrition">${nutrition_nav}</a>
        </li>
    </c:if>
    <c:if test="${not empty sessionScope.coach}">
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=coach_clients">${coach_clients_nav}</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=coach_comments">${comments_about_me}</a>
        </li>
    </c:if>
    <c:if test="${not empty sessionScope.admin}">
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=admin_coaches">${admin_coaches_nav}</a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=admin_clients">${admin_clients_nav}</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=admin_exercises">${admin_exercises_nav}</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=admin_comments">${admin_comments_nav}</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=admin_orders">${admin_orders_nav}</a>
        </li>
    </c:if>
</nav>