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
<fmt:message bundle="${locale}" key="cant_choose" var="cant_choose"/>
<fmt:message bundle="${locale}" key="buy" var="buy"/>
<fmt:message bundle="${locale}" key="invalid_coach_id" var="invalid_coach_id"/>
<fmt:message bundle="${locale}" key="not_exist_id" var="not_exist_id"/>
<fmt:message bundle="${locale}" key="invalid_comment" var="invalid_comment"/>
<fmt:message bundle="${locale}" key="reject" var="reject_coach"/>

<html>
<head>
    <title>All coaches</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="allCoaches"/>
    <jsp:param name="currentPage" value="allCoaches"/>
</jsp:include>
<ul>
    <c:choose>
        <c:when test="${membership_valid == true}">
            <c:choose>
                <c:when test="${coach_client_id != null}">
                    <c:forEach items="${coaches}" var="coach">
                            <c:choose>
                                <c:when test="${coach_client_id == coach.id}">
                                    <li class="coach"><c:out value="${coach.name} ${coach.surname}(${current_coach})"/></li>
                                        <form name="form"
                                              action="${pageContext.request.contextPath}/controller?command=add_comment"
                                              method="post">
                                            <input type="hidden" name="coachId" value="${coach.id}"/>
                                            <h2>${your_comment}</h2>
                                            <textarea id="commentContent"
                                                      name="commentContent"
                                                      class="textArea">
                                            </textarea>
                                            <input class="button" type="submit" value="${save}"/>
                                            <h3>${max_symbols} ${max_number_symbols_attribute}</h3>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/controller?command=reject_coach"
                                              method="post">
                                            <input type="hidden" id="coachId" name="coachId" value="${coach.id}"/>
                                            <input type="submit" value="${reject_coach}"/>
                                        </form>
                                </c:when>
                                <c:otherwise>
                                    <li class="coach"><c:out value="${coach.name} ${coach.surname}"/></li>
                                </c:otherwise>
                            </c:choose>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h2>${choose_coach}</h2>
                    <c:forEach items="${coaches}" var="coach">
                        <li class="coach"><c:out value="${coach.name} ${coach.surname}"/></li>
                        <form action="${pageContext.servletContext.contextPath}/controller?command=choose_coach" method="post">
                            <input type="hidden" id="coachId" name="coachId" value="${coach.id}"/>
                            <input type="submit" value="Choose coach"/>
                        </form>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>

        <c:otherwise>
            <h3>${cant_choose}</h3>
            <form action="${pageContext.servletContext.contextPath}/controller?command=show_order_page"
                  method="post">
                <input type="submit" class="button" value="${buy}">
            </form>
        </c:otherwise>
    </c:choose>
</ul>
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
</body>
</html>
