<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <link rel="stylesheet" type="text/css" href="css/gymPhotos.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Our photos</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="/jsp/menu.jsp">
    <jsp:param name="pageTopic" value="gym_photos"/>
    <jsp:param name="currentPage" value="gym_photos"/>
</jsp:include>
<div class="all">
    <input checked type="radio" name="respond" id="desktop">
    <article id="slider">
        <input checked type="radio" name="slider" id="switch1">
        <input type="radio" name="slider" id="switch2">
        <input type="radio" name="slider" id="switch3">
        <input type="radio" name="slider" id="switch4">
        <input type="radio" name="slider" id="switch5">
        <div id="slides">
            <div id="overflow">
                <div class="image">
                    <article><img src="img/gym/1.jpg"></article>
                    <article><img src="img/gym/2.jpg"></article>
                    <article><img src="img/gym/3.jpg"></article>
                    <article><img src="img/gym/4.jpg"></article>
                    <article><img src="img/gym/5.jpg"></article>
                </div>
            </div>
        </div>
        <div id="controls">
            <label for="switch1"></label>
            <label for="switch2"></label>
            <label for="switch3"></label>
            <label for="switch4"></label>
            <label for="switch5"></label>
        </div>
        <div id="active">
            <label for="switch1"></label>
            <label for="switch2"></label>
            <label for="switch3"></label>
            <label for="switch4"></label>
            <label for="switch5"></label>
        </div>
    </article>
    <br/>
</div>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
