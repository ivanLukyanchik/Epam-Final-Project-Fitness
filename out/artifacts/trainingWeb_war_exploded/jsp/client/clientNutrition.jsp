<jsp:useBean id="nutrition" class="by.epam.fitness.entity.Nutrition" scope="request"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="cant_choose_nutrition" var="cant_choose_nutrition"/>
<fmt:message bundle="${locale}" key="buy" var="buy"/>
<fmt:message bundle="${locale}" key="max_symbols" var="max_symbols"/>
<fmt:message bundle="${locale}" key="save" var="save"/>
<fmt:message bundle="${locale}" key="invalid_nutrition" var="invalid_nutrition"/>
<fmt:message bundle="${locale}" key="morning_nutrition" var="morning"/>
<fmt:message bundle="${locale}" key="lunch_nutrition" var="lunch"/>
<fmt:message bundle="${locale}" key="dinner_nutrition" var="dinner"/>
<fmt:message bundle="${locale}" key="cant_nutrition" var="cant_nutrition"/>
<fmt:message bundle="${locale}" key="reject_nutrition" var="reject_nutrition"/>
<fmt:message bundle="${locale}" key="activate_nutrition" var="activate_nutrition"/>
<fmt:message bundle="${locale}" key="added_nutrition" var="added_nutrition"/>
<fmt:message bundle="${locale}" key="updated_nutrition" var="updated_nutrition"/>
<fmt:message bundle="${locale}" key="rejected_nutrition" var="rejected_nutrition"/>
<fmt:message bundle="${locale}" key="client_no_nutrition" var="client_no_nutrition"/>
<fmt:message bundle="${locale}" key="no_coach" var="no_coach"/>
<fmt:message bundle="${locale}" key="show_coaches" var="show_coaches"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <script src="${pageContext.request.contextPath}/script/validation/nutritionValidation.js"></script>
    <title>My nutrition</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="nutrition"/>
    <jsp:param name="currentPage" value="show_client_nutrition"/>
</jsp:include>

<c:if test="${not empty sessionScope.client}">
    <c:if test="${no_nutrition == true}">
        <h3>${cant_nutrition}</h3>
    </c:if>
</c:if>

<c:if test="${not empty sessionScope.coach}">
    <c:if test="${no_nutrition == true}">
        <h3>${client_no_nutrition}</h3>
        <form action="${pageContext.servletContext.contextPath}/controller?command=add_nutrition" method="post">
            <input type="hidden" id="nutrition_id" name="nutrition_id" value="${nutrition.id}"/>
            <input type="submit" class="button" value="${activate_nutrition}"/>
        </form>
    </c:if>
</c:if>

<c:if test="${empty no_nutrition}">
    <c:choose>
        <c:when test="${membership_valid == false}">
            <h3>${cant_choose_nutrition}</h3>
            <form action="${pageContext.servletContext.contextPath}/controller?command=show_order_page" method="post">
                <input type="submit" class="button" value="${buy}">
            </form>
        </c:when>

        <c:when test="${noCoach == true}">
            <h3>${no_coach}</h3>
            <form action="${pageContext.servletContext.contextPath}/controller?command=find_coaches" method="post">
                <input type="submit" class="button" value="${show_coaches}">
            </form>
        </c:when>

        <c:otherwise>

            <c:choose>
                <c:when test="${nutritionAdded eq true}">
                    ${added_nutrition}
                </c:when>
                <c:when test="${nutritionUpdated eq true}">
                    ${updated_nutrition}
                </c:when>
                <c:when test="${nutritionRejected eq true}">
                    ${rejected_nutrition}
                </c:when>
            </c:choose>

            <form action="${pageContext.servletContext.contextPath}/controller?command=reject_nutrition" method="post">
                <input type="hidden" id="nutrition_id" name="nutrition_id" value="${nutrition.id}"/>
                <input type="submit" class="button" value="${reject_nutrition}">
            </form>

            <label for="morning">${morning}</label>
            <pre id="morning">${nutrition.morningNutrition}</pre>
            <form name="form" action="${pageContext.request.contextPath}/controller?command=update_nutrition" method="post">
                <input type="hidden" id="nutrition_id" name="nutrition_id" value="${nutrition.id}"/>
                <input type="hidden" id="nutrition_time" name="nutrition_time" value="morning"/>
                <textarea onchange="checkNutrition()" id="nutrition_description" name="nutrition_description" class="textArea" required title="${invalid_nutrition}">${nutrition.morningNutrition}</textarea>
                <input type="submit" class="button" value="${save}">
                <h3>${max_symbols} ${max_number_symbols_attribute}</h3>
            </form>
            <hr/>

            <label for="lunch">${lunch}</label>
            <pre id="lunch">${nutrition.lunchNutrition}</pre>
            <form name="form" action="${pageContext.request.contextPath}/controller?command=update_nutrition" method="post">
                <input type="hidden" id="nutrition_id" name="nutrition_id" value="${nutrition.id}"/>
                <input type="hidden" id="nutrition_time" name="nutrition_time" value="lunch"/>
                <textarea onchange="checkNutrition()" id="nutrition_description" name="nutrition_description" class="textArea" required title="${invalid_nutrition}">${nutrition.lunchNutrition}</textarea>
                <input type="submit" class="button" value="${save}">
                <h3>${max_symbols} ${max_number_symbols_attribute}</h3>
            </form>
            <hr/>

            <label for="dinner">${dinner}</label>
            <pre id="dinner">${nutrition.dinnerNutrition}</pre>
            <form name="form" action="${pageContext.request.contextPath}/controller?command=update_nutrition" method="post">
                <input type="hidden" id="nutrition_id" name="nutrition_id" value="${nutrition.id}"/>
                <input type="hidden" id="nutrition_time" name="nutrition_time" value="dinner"/>
                <textarea onchange="checkNutrition()" id="nutrition_description" name="nutrition_description" class="textArea" required title="${invalid_nutrition}">${nutrition.dinnerNutrition}</textarea>
                <input type="submit" class="button" value="${save}">
                <h3>${max_symbols} ${max_number_symbols_attribute}</h3>
            </form>
            <hr/>
        </c:otherwise>
    </c:choose>
</c:if>

<c:if test="${incorrect_input_nutrition_data_error eq true}">
    ${invalid_nutrition}
</c:if>
<footer>
    ${footer}
</footer>
</body>
</html>
