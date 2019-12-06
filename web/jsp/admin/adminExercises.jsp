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
<fmt:message bundle="${locale}" key="choose_file" var="choose_file"/>
<fmt:message bundle="${locale}" key="exercise_form" var="exercise_form"/>
<fmt:message bundle="${locale}" key="exercise_name_not_valid" var="exercise_name_not_valid"/>
<fmt:message bundle="${locale}" key="exercise_description_not_valid" var="exercise_description_not_valid"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
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

<div class="text-center">
    <c:if test="${requestScope.success eq true}">
        <p class="text-success">${exercise_added}</p>
        <c:remove var="success" scope="session" />
    </c:if>
    <c:if test="${sessionScope.exerciseDeleted eq true}">
        <p class="text-success">${exercise_deleted}</p>
        <c:remove var="exerciseDeleted" scope="session" />
    </c:if>
    <c:if test="${not empty requestScope.notImage}">
        <p class="text-danger">${not_image}</p>
    </c:if>
    <c:if test="${not empty requestScope.invalidName}">
        <p class="text-danger">${exercise_name_not_valid}</p>
    </c:if>
    <c:if test="${not empty requestScope.invalidDescription}">
        <p class="text-danger">${exercise_description_not_valid}</p>
    </c:if>
</div>

<button class="btn btn-primary mx-5 mb-2" data-toggle="collapse" data-target="#hide">${exercise_form}</button>
<div class="collapse" id="hide">
    <form method="post" action="addExerciseServlet" class="text-center border border-light px-5" enctype="multipart/form-data">
        <label for="name">${exercise_name}</label>
        <input type="text" onchange="checkName()" class="form-control mb-4" id="name" name="name" required placeholder="${exercise_name}" title="${exercise_name_pattern_error}"/>

        <label for="description">${exercise_description}</label>
        <input type="text" onchange="checkDescription()" class="form-control mb-4" id="description" name="description" required placeholder="${exercise_description}" title="${exercise_description_pattern_error}"/>

        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="inputGroupFileAddon01">${upload_image}</span>
            </div>
            <div class="custom-file">
                <input type="file" name="image" accept="image/x-png,image/jpeg" class="custom-file-input" id="image"
                       aria-describedby="inputGroupFileAddon01">
                <label class="custom-file-label mb-4" for="image">${choose_file}</label>
            </div>
        </div>
        <input type="submit" class="btn btn-success my-4 btn-block" value="${add_exercise}">
    </form>
</div>

<c:if test="${fn:length(allExercises) eq 0}">
    <h3><c:out value="${no_exercises_admin}"/></h3>
</c:if>

<div class="container">
    <div class="row">
        <c:forEach items="${allExercises}" var="exercise">
            <div class="col">
                <div class="card" style="width: 18rem;">
                    <img class="card-img-top"  src="data:image/jpg;base64,${exercise.image}" alt="No image yet">
                    <div class="card-body">
                        <h5 class="card-title">${exercise.name}</h5>
                        <p class="card-text">${exercise_description} : ${exercise.description}</p>
                        <form method="post" action="${pageContext.servletContext.contextPath}/controller?command=delete_exercise">
                            <input type="hidden" name="exerciseId" value="${exercise.id}">
                            <input type="submit" class="btn btn-danger" value="${delete_exercise}">
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<div class="text-center mt-2">
    <nav aria-label="Navigation for pages">
        <ul class="pagination text-center mx-auto justify-content-center">
            <c:if test="${page != 1}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/controller?command=admin_exercises&page=${page-1}">Previous</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${page eq i}">
                        <li class="page-item active"><a class="page-link"> ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/controller?command=admin_exercises&page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${page < numberOfPages}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/controller?command=admin_exercises&page=${page+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
