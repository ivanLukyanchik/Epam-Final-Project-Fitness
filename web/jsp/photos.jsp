<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/gymPhotos.css">
    <title>Our photos</title>
</head>
<body>
<jsp:include page="/jsp/menu.jsp">
    <jsp:param name="pageTopic" value="gym_photos"/>
    <jsp:param name="currentPage" value="gymPhotos"/>
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
</body>
</html>
