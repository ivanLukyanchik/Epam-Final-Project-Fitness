<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<fmt:message bundle="${locale}" key="cant_choose" var="cant_choose"/>
<fmt:message bundle="${locale}" key="buy" var="buy"/>
<fmt:message bundle="${locale}" key="save" var="save"/>
<fmt:message bundle="${locale}" key="reject_exercise" var="reject_exercise"/>
<fmt:message bundle="${locale}" key="all_exercises" var="all_exercises"/>
<fmt:message bundle="${locale}" key="exercise_already_exists" var="exercise_already_exists"/>
<fmt:message bundle="${locale}" key="added_exercise" var="added_exercise"/>
<fmt:message bundle="${locale}" key="updated_exercise" var="updated_exercise"/>
<fmt:message bundle="${locale}" key="rejected_exercise" var="rejected_exercise"/>

<html>
<head>
    <title>My Exercises</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="exercises"/>
    <jsp:param name="currentPage" value="exercises"/>
</jsp:include>

<c:choose>
    <c:when test="${membership_valid == false}">
        <h3>${cant_choose}</h3>
        <form action="${pageContext.servletContext.contextPath}/controller?command=show_order_page" method="post">
            <input type="submit" class="button" value="${buy}">
        </form>
    </c:when>

    <c:otherwise>
        <jsp:useBean id="program" type="by.epam.fitness.entity.Program" scope="session"/>

        <c:choose>
            <c:when test="${exerciseAdded eq true}">
                ${added_exercise}
            </c:when>
            <c:when test="${exerciseUpdated eq true}">
                ${updated_exercise}
            </c:when>
            <c:when test="${exerciseRejected eq true}">
                ${rejected_exercise}
            </c:when>
            <c:when test="${exerciseAlreadyExists eq true}">
                ${exercise_already_exists}
            </c:when>
        </c:choose>

        <c:forEach var = "i" begin = "1" end = "${program.trainsPerWeek}">
            <ol>
                <c:forEach items="${clientExercises}" var="exerciseProgram">
                    <c:if test="${exerciseProgram.numberTrainDay==i}">
                        <li><h2>${exerciseProgram.exercise.name}</h2></li>
                        <h3>${exerciseProgram.exercise.description}</h3>
                        <c:out value="(${exerciseProgram.setNumber} * ${exerciseProgram.repeatNumber})"/>
                        <h2>${edit}</h2>
                        <form action="${pageContext.request.contextPath}/controller?command=update_exercise" method="post">
                            <input type="hidden" id="exerciseProgramId" name="exerciseProgramId" value="${exerciseProgram.id}">
                            <div class="col-1">
                                <label for="set_update">${sets}</label>
                            </div>
                            <div class="col-2">
                                <input type="text" id="set_update" value="${exerciseProgram.setNumber}" title="${title}" name="set_number">
                            </div>
                            <div class="col-1">
                                <label for="repeatsUpdate">${repeats}</label>
                            </div>
                            <div class="col-2">
                                <input type="text" id="repeatsUpdate" value="${exerciseProgram.repeatNumber}" title="${title}"  name="repeats">
                            </div>
                            <input type="submit" value="${update}" id="update">
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/controller?command=reject_exercise" method="post">
                            <input type="hidden" name="exerciseId" value="${exerciseProgram.exercise.id}">
                            <input type="submit" value="${reject_exercise}">
                        </form>
                    </c:if>
                </c:forEach>
            </ol>
        </c:forEach>

        <hr/>

        <h1>${all_exercises}</h1>
        <c:forEach items="${allExercises}" var="exercise">
            <ul>
                <form action="${pageContext.request.contextPath}/controller?command=add_exercise" method="post">
                    <input type="hidden" name="exerciseId" value="${exercise.id}">
                    <input type="hidden" name="trainDay" value="${program.trainsPerWeek}">
                    <input type="hidden" name="programId" value="${program.id}">
                    <li><h2>${exercise.name}</h2></li>
                    ${exercise_description} : ${exercise.description}
                    <br/>

                    <div class="col-1">
                        <label for="set_number">${sets}</label>
                    </div>

                    <div class="col-2">
                        <input type="text" id="set_number" name="set_number" required title="${title}">
                    </div>

                    <div class="col-1">
                        <label for="repeats">${repeats}</label>
                    </div>

                    <div class="col-2">
                    <input type="text" id="repeats" name="repeats" required title="${title}">
                    </div>

                    <input type="submit" value="${add_exercise}">
                </form>
            </ul>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
