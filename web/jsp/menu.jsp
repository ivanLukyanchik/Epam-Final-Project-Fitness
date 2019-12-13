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
<fmt:message bundle="${locale}" key="client" var="client" />
<fmt:message bundle="${locale}" key="coach" var="coach" />
<fmt:message bundle="${locale}" key="admin" var="admin" />
<fmt:message bundle="${locale}" key="stranger" var="stranger" />
<fmt:message bundle="${locale}" key="comments_about_coach_topic" var="comments_about_coach_topic" />
<fmt:message bundle="${locale}" key="return_to_login" var="return_to_login" />
<fmt:message bundle="${locale}" key="name" var="company_name"/>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/main.css">
</head>

<nav class="navbar navbar-expand-lg navbar-dark bg-info">
    <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/controller?command=home_page">${company_name}</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=home_page">${home} <span class="sr-only">(current)</span></a>
            </li>
            <c:choose>
                <c:when test="${sessionScope.role eq 'client'}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=gym_photos">${photos}</a>
                    </li>
                    <li>
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=show_order_page">${gym_membership_nav}</a>
                    </li>
                    <li>
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=client_orders">${my_orders_nav}</a>
                    </li>
                    <li>
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=find_coaches">${coaches_nav}</a>
                    </li>
                    <li>
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=show_client_exercises&page=1">${exercises_nav}</a>
                    </li>
                    <li>
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=show_client_nutrition">${nutrition_nav}</a>
                    </li>
                </c:when>
                <c:when test="${sessionScope.role eq 'coach'}">
                    <li>
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=coach_clients">${coach_clients_nav}</a>
                    </li>
                    <li>
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=coach_comments">${comments_about_me}</a>
                    </li>
                </c:when>
                <c:when test="${sessionScope.role eq 'admin'}">
                    <li>
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=admin_coaches">${admin_coaches_nav}</a>
                    </li>
                    <li>
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=admin_clients">${admin_clients_nav}</a>
                    </li>
                    <li>
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=admin_exercises&page=1">${admin_exercises_nav}</a>
                    </li>
                    <li>
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=admin_comments">${admin_comments_nav}</a>
                    </li>
                    <li>
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=admin_orders">${admin_orders_nav}</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=gym_photos">${photos}</a>
                    <li>
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=find_coaches">${coaches_nav}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="dropdown09" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-globe" style="font-size: 30px" aria-hidden="true"></i></a>
                <div class="dropdown-menu" aria-labelledby="dropdown09">
                    <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/controller?command=locale&currentPage=${param.currentPage}&lang=en_US"><img src="img/flag/uk.png" width="40" height="20" alt="Eng"> Eng</a>
                    <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/controller?command=locale&currentPage=${param.currentPage}&lang=ru_RU"><img src="img/flag/rus.png" width="40" height="20" alt="Rus"> Rus</a>
                    <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/controller?command=locale&currentPage=${param.currentPage}&lang=be_BY"><img src="img/flag/bel.png" width="40" height="20" alt="Bel"> Bel</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-4" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <c:choose>
                        <c:when test="${sessionScope.role eq 'client'}">
                            <c:choose>
                                <c:when test="${not empty sessionScope.image}">
                                    <img src="data:image/jpg;base64,${sessionScope.image}" alt="no image" class="mr-1 rounded-circle" style="width:38px;">
                                </c:when>
                                <c:otherwise>
                                    <i class="fas fa-user" style="font-size: 25px"></i>
                                </c:otherwise>
                            </c:choose>
                            ${client} ${sessionScope.user}
                        </c:when>
                        <c:otherwise>
                            <i class="fas fa-user" style="font-size: 25px"></i>
                            <c:choose>
                                <c:when test="${sessionScope.role eq 'coach'}">
                                    ${coach} ${sessionScope.user}
                                </c:when>
                                <c:when test="${sessionScope.role eq 'admin'}">
                                    ${admin} ${sessionScope.user}
                                </c:when>
                                <c:otherwise>
                                    ${stranger}
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </a>
                <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-4">
                    <c:if test="${sessionScope.role eq 'client'}">
                        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/controller?command=client_profile">${profile}</a>
                        <div class="dropdown-divider"></div>
                    </c:if>
                    <c:if test="${empty sessionScope.role}">
                        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/controller?command=show_login_page">${return_to_login}</a>
                        <div class="dropdown-divider"></div>
                    </c:if>
                    <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/controller?command=logout_user">${logout}</a>
                </div>
            </li>
        </ul>
    </div>
</nav>


<div id="heading" class="text-center">
<c:choose>
    <c:when test="${param.pageTopic eq 'about_us'}">
<%--        <h1>${about_us}</h1>--%>
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

    <c:when test="${param.pageTopic eq 'comments_about_coach'}">
        <h1>${comments_about_coach_topic}</h1>
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