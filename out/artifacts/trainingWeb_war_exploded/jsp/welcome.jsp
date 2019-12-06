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
<fmt:message bundle="${locale}" key="new_style_bottom" var="new_style_bottom"/>
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
<fmt:message bundle="${locale}" key="your_fitness" var="your_fitness" />
<fmt:message bundle="${locale}" key="button.submit" var="submit" />
<fmt:message bundle="${locale}" key="your_mail" var="your_mail" />
<fmt:message bundle="${locale}" key="invalid_comment" var="invalid_comment" />
<fmt:message bundle="${locale}" key="message_us" var="message_us" />
<fmt:message bundle="${locale}" key="group" var="group" />
<fmt:message bundle="${locale}" key="exer_main" var="exer_main" />
<fmt:message bundle="${locale}" key="low_stress" var="low_stress" />
<fmt:message bundle="${locale}" key="low_stress_b" var="low_stress_b" />
<fmt:message bundle="${locale}" key="insomnia" var="insomnia" />
<fmt:message bundle="${locale}" key="insomnia_b" var="insomnia_b" />
<fmt:message bundle="${locale}" key="goal" var="goal" />
<fmt:message bundle="${locale}" key="goal_b" var="goal_b" />
<fmt:message bundle="${locale}" key="view_offer" var="view_offer" />
<fmt:message bundle="${locale}" key="footer.copyright" var="footer" />

<html>
<head>
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <script src="${pageContext.request.contextPath}/script/validation/messageValidation.js"></script>
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

<div class="carousel slide" data-ride="carousel" id="slides">
    <ul class="carousel-indicators">
        <li data-target="#slides" data-slide-to="0" class="active"></li>
        <li data-target="#slides" data-slide-to="1"></li>
        <li data-target="#slides" data-slide-to="2"></li>
    </ul>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img class="transparent75" src="img/gym/4.jpg">
            <div class="carousel-caption">
                <h2 class="display-2">${your_fitness}</h2>
                <h3>${characteristic}</h3>
            </div>
        </div>
        <div class="carousel-item">
            <img class="transparent75" src="img/gym/2.jpg">
        </div>
        <div class="carousel-item">
            <img class="transparent75" src="img/gym/1.jpg">
        </div>
    </div>
</div>

<div class="text-center">
    <c:if test="${sessionScope.coachChosen eq true}">
        <h1 class="text-success">${coach_chosen}</h1>
        <c:remove var="coachChosen" scope="session" />
    </c:if>
    <c:if test="${sessionScope.commentSaved eq true}">
        <h1 class="text-success">${comment_saved}</h1>
        <c:remove var="commentSaved" scope="session" />
    </c:if>
    <c:if test="${sessionScope.coachRejected eq true}">
        <h1 class="text-success">${coach_rejected}</h1>
        <c:remove var="coachRejected" scope="session" />
    </c:if>
    <c:if test="${sessionScope.nutritionRejected eq true}">
        <h1 class="text-success">${nutrition_rejected}</h1>
        <c:remove var="nutritionRejected" scope="session" />
    </c:if>
</div>

<div class="container-fluid">
    <div class="row jumbotron">
        <div class="col-xs-12 col-sm-12 col-md-9 col-lg-9 col-xl-10">
            <p class="lead">${slogan}${trainers_study}</p>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3 col-xl-2">
            <a href="${pageContext.servletContext.contextPath}/controller?command=show_order_page"><button class="btn btn-success btn-lg" type="button">${view_offer}</button></a>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row text-center alert">
        <div class="col-12">
            <h1 class="display-4">${group}</h1>
        </div>
        <hr>
        <div class="col-12">
            <p class="lead">${exer_main}</p>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row text-center padding">
        <div class="col-xs-12 col-sm-6 col-md-4">
            <i class="fas fa-smile"></i>
            <h3>${low_stress}</h3>
            <p>${low_stress_b}</p>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-4">
            <i class="fas fa-bed"></i>
            <h3>${insomnia}</h3>
            <p>${insomnia_b}</p>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-4">
            <i class="fas fa-dumbbell"></i>
            <h3>${goal}</h3>
            <p>${goal_b}</p>
        </div>
    </div>
</div>

<figure>
    <div class="fixed-wrap">
        <div id="fixed">
        </div>
    </div>
</figure>

<div class="container-fluid">
    <div class="row text-center alert">
        <div class="col-12">
            <h1 class="display-4">${reasons}</h1>
        </div>
        <hr>
        <div class="col-12">
            <ol class="rounded">
                <li><a href="${pageContext.servletContext.contextPath}/controller?command=show_order_page">${first_reason}</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/controller?command=show_order_page">${second_reason}</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/controller?command=show_order_page">${third_reason}</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/controller?command=show_order_page">${fourth_reason}</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/controller?command=show_order_page">${fifth_reason}</a></li>
            </ol>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row text-center alert">
        <div class="col-12">
            <h1 class="display-4">${where}</h1>
        </div>
        <hr>
        <div class="col-12">
            <div style="overflow:hidden;width: 100%;"><iframe width="100%" height="300" src="https://maps.google.com/maps?width=1055&amp;height=440&amp;hl=en&amp;q=%D0%9C%D0%B8%D0%BD%D1%81%D0%BA%2C%20%D1%83%D0%BB.%20%D0%91%D0%B5%D0%B4%D1%8B%204+(%C2%AB%D0%9E%D0%BB%D0%B8%D0%BC%D0%BF%D0%B8%D1%8F%C2%BB%20%D0%A4%D0%B8%D1%82%D0%BD%D0%B5%D1%81-%D1%86%D0%B5%D0%BD%D1%82%D1%80)&amp;ie=UTF8&amp;t=k&amp;z=12&amp;iwloc=B&amp;output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe><div style="position: absolute;width: 80%;bottom: 10px;left: 0;right: 0;margin-left: auto;margin-right: auto;color: #000;text-align: center;"><small style="line-height: 1.8;font-size: 2px;background: #fff;">Powered by <a href="https://embedgooglemaps.com/en/">embedgooglemaps EN</a> & <a href="https://iamsterdamcard.it">www.iamsterdamcard.it</a></small></div><style>#gmap_canvas img{max-width:none!important;background:none!important}</style></div>
        </div>
    </div>
</div>

<footer class="footer mt-auto py-3">
    <div class="container-fluid">
        <div class="row text-center">
            <div class="col-12 social">
                <a href="https://www.instagram.com/vanya_lukyanchik/"><i class="fab fa-instagram"></i></a>
                <a href="https://vk.com/vanek17121999"><i class="fab fa-vk"></i></a>
            </div>
        </div>
        <div class="text-center">
            <span class="text-muted">${footer}</span>
        </div>
    </div>
</footer>
</body>
</html>