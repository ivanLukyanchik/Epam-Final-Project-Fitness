<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="exercise_description" var="exercise_description"/>
<fmt:message bundle="${locale}" key="exercise_added" var="exercise_added"/>
<fmt:message bundle="${locale}" key="exercise_deleted" var="exercise_deleted"/>
<fmt:message bundle="${locale}" key="exercise_name" var="exercise_name"/>
<fmt:message bundle="${locale}" key="exercise_description" var="exercise_description"/>
<fmt:message bundle="${locale}" key="upload_image" var="upload_image"/>
<fmt:message bundle="${locale}" key="add_exercise" var="add_exercise"/>
<fmt:message bundle="${locale}" key="delete_exercise" var="delete_exercise"/>
<fmt:message bundle="${locale}" key="exercise_name_pattern_error" var="exercise_name_pattern_error"/>
<fmt:message bundle="${locale}" key="exercise_description_pattern_error" var="exercise_description_pattern_error"/>
<fmt:message bundle="${locale}" key="no_exercises_admin" var="no_exercises_admin"/>
<fmt:message bundle="${locale}" key="not_image" var="not_image"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <script src="${pageContext.request.contextPath}/script/validation/exerciseValidation.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Exercises</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="adminExercises"/>
    <jsp:param name="currentPage" value="admin_exercises"/>
</jsp:include>

<c:if test="${requestScope.success eq true}">
    ${exercise_added}
</c:if>
<c:if test="${requestScope.exerciseDeleted eq true}">
    ${exercise_deleted}
</c:if>
<c:if test="${not empty requestScope.notImage}">
    ${not_image}
</c:if>

<form method="post" action="addExerciseServlet" enctype="multipart/form-data">
    <br/>
    <label for="name">${exercise_name}</label>
    <input type="text" onchange="checkName()" id="name" name="name" required title="${exercise_name_pattern_error}"/>

    <br/>
    <label for="description">${exercise_description}</label>
    <input type="text" onchange="checkDescription()" id="description" name="description" required title="${exercise_description_pattern_error}"/>

    <br/>
    <label for="image">${upload_image}</label>
    <input type="file" id="image" name="image" accept="image/x-png,image/jpeg" />

    <br/>
    <input type="submit" value="${add_exercise}">
</form>

<c:if test="${fn:length(allExercises) eq 0}">
    <h3><c:out value="${no_exercises_admin}"/></h3>
</c:if>

<c:forEach items="${allExercises}" var="exercise">
    <ul>
        <li><h2>${exercise.name}</h2></li>
        <form method="post" action="${pageContext.servletContext.contextPath}/controller?command=delete_exercise">
            <input type="hidden" name="exerciseId" value="${exercise.id}">
            <input type="submit" value="${delete_exercise}">
        </form>
        <p><img src="data:image/jpg;base64,${exercise.image}" alt="No image yet" width="200" height="200" style="border-radius: 25px"/></p>
            ${exercise_description} : ${exercise.description}
        <br/>
    </ul>
</c:forEach>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
