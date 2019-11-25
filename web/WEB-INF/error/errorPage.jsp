<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Error!</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/errorPage.css">
</head>
<body>
<div class="container">
    <h1>Oops!</h1>
    <div class="container">
        <h2 th:text="'Error message: ' + ${errorMsg}"></h2>
        <a class="btn btn-lg btn-warning" href="login">Return to login page</a>
        <br/> <br/> <br/>
        <a class="btn btn-lg btn-warning" href="welcome">Return to main page</a>
    </div>
    <div class="container">
    <br/><br/>
    Request from ${pageContext.errorData.requestURI} is failed
    <br/>
    Servlet name or type: ${pageContext.errorData.servletName}
    <br/>
    Status code: ${pageContext.errorData.statusCode}
    <br/>
    Exception: ${pageContext.errorData.throwable}
    </div>
</div>
<footer>
    Copyright 1999-2019, Lukyanchik
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>