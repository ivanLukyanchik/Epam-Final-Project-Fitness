<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="edit" var="edit"/>
<fmt:message bundle="${locale}" key="sets" var="sets"/>
<fmt:message bundle="${locale}" key="set_title" var="title"/>
<fmt:message bundle="${locale}" key="repeats" var="repeats"/>
<fmt:message bundle="${locale}" key="update" var="update"/>
<fmt:message bundle="${locale}" key="exercise_name" var="exercise_name"/>
<fmt:message bundle="${locale}" key="exercise_description" var="exercise_description"/>
<fmt:message bundle="${locale}" key="add_exercise" var="add_exercise"/>
<fmt:message bundle="${locale}" key="add" var="add"/>
<fmt:message bundle="${locale}" key="cant_choose_exercise" var="cant_choose_exercise"/>
<fmt:message bundle="${locale}" key="buy" var="buy"/>
<fmt:message bundle="${locale}" key="save" var="save"/>
<fmt:message bundle="${locale}" key="reject_exercise" var="reject_exercise"/>
<fmt:message bundle="${locale}" key="all_exercises" var="all_exercises"/>
<fmt:message bundle="${locale}" key="exercise_already_exists" var="exercise_already_exists"/>
<fmt:message bundle="${locale}" key="added_exercise" var="added_exercise"/>
<fmt:message bundle="${locale}" key="updated_exercise" var="updated_exercise"/>
<fmt:message bundle="${locale}" key="rejected_exercise" var="rejected_exercise"/>
<fmt:message bundle="${locale}" key="no_exercises" var="no_exercises"/>
<fmt:message bundle="${locale}" key="no_coach" var="no_coach"/>
<fmt:message bundle="${locale}" key="show_coaches" var="show_coaches"/>
<fmt:message bundle="${locale}" key="incorrect_repeat_set" var="incorrect_repeat_set"/>
<fmt:message bundle="${locale}" key="click_exercises" var="click_exercises"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <script src="${pageContext.request.contextPath}/script/validation/exerciseValidation.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>My Exercises</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="exercises"/>
    <jsp:param name="currentPage" value="show_client_exercises"/>
</jsp:include>

<c:choose>
    <c:when test="${membership_valid == false}">
        <h3>${cant_choose_exercise}</h3>
        <form action="${pageContext.servletContext.contextPath}/controller?command=show_order_page" method="post">
            <input type="submit" class="btn btn-success" value="${buy}">
        </form>
    </c:when>

    <c:when test="${noCoach == true}">
        <h3>${no_coach}</h3>
        <form action="${pageContext.servletContext.contextPath}/controller?command=find_coaches" method="post">
            <input type="submit" class="btn btn-info" value="${show_coaches}">
        </form>
    </c:when>

    <c:otherwise>
        <jsp:useBean id="program" type="by.epam.fitness.entity.Program" scope="session"/>

        <c:choose>
            <c:when test="${exerciseAdded eq true}">
                <p class="text-success">${added_exercise}</p>
            </c:when>
            <c:when test="${exerciseUpdated eq true}">
                <p class="text-success">${updated_exercise}</p>
            </c:when>
            <c:when test="${exerciseRejected eq true}">
                <p class="text-success">${rejected_exercise}</p>
            </c:when>
            <c:when test="${exerciseAlreadyExists eq true}">
                <p class="text-danger">${exercise_already_exists}</p>
            </c:when>
            <c:when test="${incorrect_input_data_error eq true}">
                <p class="text-danger">${incorrect_repeat_set}</p>
            </c:when>
        </c:choose>

        <button class="btn btn-primary mx-5 mb-2" data-toggle="collapse" data-target="#hide">${click_exercises}</button>
        <div class="collapse" id="hide">
            <c:if test="${fn:length(clientExercises) eq 0}">
                <h3><c:out value="${no_exercises}"/></h3>
            </c:if>
            <c:forEach var = "i" begin = "1" end = "${program.trainsPerWeek}">
                <div class="container">
                    <div class="row">
                        <c:forEach items="${clientExercises}" var="exerciseProgram">
                            <c:if test="${exerciseProgram.numberTrainDay==i}">
                                <div class="col">
                                    <div class="card" style="width: 18rem;">
                                        <img class="card-img-top" src="data:image/jpg;base64,${exerciseProgram.exercise.image}" alt="No image yet">
                                        <div class="card-body">
                                            <h5 class="card-title">${exerciseProgram.exercise.name} <c:out value="(${exerciseProgram.setNumber} * ${exerciseProgram.repeatNumber})"/></h5>
                                            <p class="card-text">${exerciseProgram.exercise.description}</p>
                                            <form action="${pageContext.request.contextPath}/controller?command=update_exercise" method="post">
                                                <input type="hidden" id="exerciseProgramId" name="exerciseProgramId" value="${exerciseProgram.id}">
                                                <h4>${edit}</h4>
                                                <div>
                                                    <label for="set_update">${sets}</label>
                                                </div>
                                                <div>
                                                    <input onchange="checkSetNumber()" type="text" class="form-control mb-4" id="set_update" value="${fn:escapeXml(exerciseProgram.setNumber)}" required title="${title}" name="set_number">
                                                </div>
                                                <div>
                                                    <label for="repeats_update">${repeats}</label>
                                                </div>
                                                <div>
                                                    <input onchange="checkRepeatNumber()" type="text" class="form-control mb-4" id="repeats_update" value="${fn:escapeXml(exerciseProgram.repeatNumber)}" required title="${title}" name="repeats">
                                                </div>
                                                <input type="submit" class="btn btn-primary" value="${update}" id="update">
                                            </form>
                                            <form action="${pageContext.request.contextPath}/controller?command=reject_exercise" method="post">
                                                <input type="hidden" name="exerciseId" value="${exerciseProgram.exercise.id}">
                                                <input type="submit" class="btn btn-danger" value="${reject_exercise}">
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="text-center my-2">
            <h1>${all_exercises}</h1>
        </div>
        <div class="container">
            <div class="row">
                <c:forEach items="${allExercises}" var="exercise">
                    <div class="col">
                        <div class="card" style="width: 18rem;">
                            <img class="card-img-top"  src="data:image/jpg;base64,${exercise.image}" alt="No image yet">
                            <div class="card-body">
                                <h5 class="card-title">${exercise.name}</h5>
                                <p class="card-text">${exercise_description} : ${exercise.description}</p>
                                <form action="${pageContext.request.contextPath}/controller?command=add_exercise" method="post">
                                    <input type="hidden" name="exerciseId" value="${exercise.id}">
                                    <input type="hidden" name="trainDay" value="${program.trainsPerWeek}">
                                    <input type="hidden" name="programId" value="${program.id}">

                                    <div>
                                        <label for="set_number">${sets}</label>
                                    </div>
                                    <div>
                                        <input onchange="checkSetNumberAdd()" class="form-control mb-4" type="text" id="set_number" name="set_number" required title="${title}">
                                    </div>

                                    <div>
                                        <label for="repeats">${repeats}</label>
                                    </div>
                                    <div>
                                        <input onchange="checkRepeatNumberAdd()" class="form-control mb-4" type="text" id="repeats" name="repeats" required title="${title}">
                                    </div>

                                    <input type="submit" class="btn btn-success" value="${add_exercise}">
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="text-center mt-2">
                <ul class="pagination text-center mx-auto justify-content-center">
                    <c:if test="${page != 1}">
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/controller?command=show_client_exercises&page=${page-1}">Previous</a>
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
                                    <a class="page-link" href="${pageContext.request.contextPath}/controller?command=show_client_exercises&page=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${page < numberOfPages}">
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/controller?command=show_client_exercises&page=${page+1}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
