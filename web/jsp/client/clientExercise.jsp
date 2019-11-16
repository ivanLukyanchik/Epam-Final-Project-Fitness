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
        <jsp:useBean id="program" type="by.epam.fitness.entity.Program" scope="request"/>
        <input type="hidden" id="programIdBlock" value="${program.id}"/>
        <c:forEach var = "i" begin = "1" end = "${program.trainsPerWeek}">
            <ul>
                <c:forEach items="${exercises}" var="exercise">
                    <c:if test="${exercise.numberTrainDay==i}">
                        <c:out value="${exercise.name}(${exercise.setNumber},${exercise.repeatNumber})"/>
                        <input type="checkbox" id="modal${exercise.id}"/>
                        <h2>${edit}</h2>
                        <form action="${pageContext.request.contextPath}/controller?command=update_exercise" method="post">
                            <div class="col-1">
                                <label for="set_number">${sets}</label>
                            </div>
                            <div class="col-2">
                                <input type="text" id="set_number" title="${title}" name="set_number">
                            </div>
                            <div class="col-1">
                                <label for="repeatsUpdate">${repeats}</label>
                            </div>
                            <div class="col-2">
                                <input type="text" id="repeatsUpdate" title="${title}"  name="repeats">
                            </div>
                            <input type="hidden" id="exerciseDtoId" name="exerciseDtoId" value="${exercise.id}">
                            <input type="submit" onclick="checkUpdateExerciseData()" value="${update}" id="update">
                        </form>
                        <br/>
                        <form action="${pageContext.request.contextPath}/controller?command=delete_exercise" method="post">
                            <input id="exerciseDtoId" name="exerciseDtoId" value="${exercise.id}">
                            <input type="submit" value="Удалить">
                        </form>
                    </c:if>
                </c:forEach>
            </ul>
        </c:forEach>

        <input type="search"  oninput="makeRequest()" placeholder="${exercise_name}" name="searchType" id="searchType">
        <hr style="margin-left: -20px;width: 110%;">
        <form name="form" class="beatForm" action="${pageContext.request.contextPath}/controller?command=add_exercise" method="post">
            <input type="hidden" id="trainDay" name="trainDay" value="${program.trainsPerWeek}">
            <h2 id="exerciseName"></h2>
            <h2>${exercise_description}</h2><%--            добавить input сюда--%>
            <h2>${add_exercise}</h2>
            <div class="col-1">
                <label for="setNumber{exercise.id}">${sets}</label>
            </div>
            <div class="col-2">
                <input type="text" id="setNumber{exercise.id}" title="${title}"/>
            </div>
            <div class="col-1">
                <label for="repeats{exercise.id}">${repeats}</label>
            </div>
            <div class="col-2">
                <input type="text" id="repeats{exercise.id}" title="${title}"/>
            </div>
            <input type="submit" onclick="setExerciseProgram('{exercise.id}',${program.id})" title="1-2 number" value="${add}">
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>
