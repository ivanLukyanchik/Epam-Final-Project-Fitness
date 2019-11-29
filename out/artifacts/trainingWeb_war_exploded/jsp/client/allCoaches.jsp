<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="current_coach" var="current_coach"/>
<fmt:message bundle="${locale}" key="comment" var="your_comment"/>
<fmt:message bundle="${locale}" key="save" var="save"/>
<fmt:message bundle="${locale}" key="max_symbols" var="max_symbols"/>
<fmt:message bundle="${locale}" key="choose_coach" var="choose_coach"/>
<fmt:message bundle="${locale}" key="choose_this_coach" var="choose_this_coach"/>
<fmt:message bundle="${locale}" key="cant_choose" var="cant_choose"/>
<fmt:message bundle="${locale}" key="buy" var="buy"/>
<fmt:message bundle="${locale}" key="invalid_coach_id" var="invalid_coach_id"/>
<fmt:message bundle="${locale}" key="not_exist_id" var="not_exist_id"/>
<fmt:message bundle="${locale}" key="invalid_comment" var="invalid_comment"/>
<fmt:message bundle="${locale}" key="reject" var="reject_coach"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <script src="${pageContext.request.contextPath}/script/validation/commentValidation.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>All coaches</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="allCoaches"/>
    <jsp:param name="currentPage" value="find_coaches"/>
</jsp:include>

<c:choose>
    <c:when test="${membership_valid == true}">
        <c:choose>
            <c:when test="${coach_client_id != null}">
                <ul class="list-group">
                    <c:forEach items="${coaches}" var="coach">
                            <c:choose>
                                <c:when test="${coach_client_id == coach.id}">
                                    <li class="list-group-item" style="background-color: lightblue"><c:out value="${coach.name} ${coach.surname} ${coach.patronymic} (${current_coach})"/>
                                        <form action="${pageContext.request.contextPath}/controller?command=reject_coach" method="post">
                                            <input type="hidden" id="coachId" name="coachId" value="${coach.id}"/>
                                            <input type="submit" class="btn btn-danger" value="${reject_coach}"/>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/controller?command=add_comment" method="post">
                                            <input type="hidden" name="coachId" value="${coach.id}"/>
                                            <i class="fas fa-pencil-alt prefix"></i>
                                            <div class="md-form amber-textarea active-amber-textarea my-3">
                                                <textarea onchange="checkComment()" class="md-textarea form-control" id="commentContent" name="commentContent" placeholder="${your_comment}" required title="${invalid_comment}"></textarea>
                                            </div>
                                            <input class="btn btn-primary" type="submit" value="${save}"/>
                                            <h3>${max_symbols} ${max_number_symbols_attribute}</h3>
                                        </form>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="list-group-item list-group-item-action"><c:out value="${coach.name} ${coach.surname} ${coach.patronymic}"/></li>
                                </c:otherwise>
                            </c:choose>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <h2>${choose_coach}</h2>
                <c:forEach items="${coaches}" var="coach">
                    <li class="list-group-item list-group-item-action"><c:out value="${coach.name} ${coach.surname} ${coach.patronymic}"/></li>
                    <form action="${pageContext.servletContext.contextPath}/controller?command=choose_coach" method="post">
                        <input type="hidden" id="coachId" name="coachId" value="${coach.id}"/>
                        <input type="submit" value="${choose_this_coach}"/>
                    </form>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </c:when>

    <c:otherwise>
        <h3>${cant_choose}</h3>
        <form action="${pageContext.servletContext.contextPath}/controller?command=show_order_page" method="post">
            <input type="submit" class="button" value="${buy}">
        </form>
    </c:otherwise>
</c:choose>
<br/>
<c:choose>
    <c:when test="${invalidCoachId eq true}">
        ${invalid_coach_id}
    </c:when>
    <c:when test="${notExistId eq true}">
        ${not_exist_id}
    </c:when>
    <c:when test="${invalidComment eq true}">
        ${invalid_comment}
    </c:when>
</c:choose>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
