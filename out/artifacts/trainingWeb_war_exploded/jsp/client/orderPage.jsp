<jsp:useBean id="client" scope="session" type="by.epam.fitness.entity.Client"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="fitnessTag" prefix="fitness" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="card_pattern_error" var="card_pattern_error"/>
<fmt:message bundle="${locale}" key="card_placeholder" var="card_placeholder"/>
<fmt:message bundle="${locale}" key="how_long" var="how_long"/>
<fmt:message bundle="${locale}" key="amount" var="amount"/>
<fmt:message bundle="${locale}" key="discount" var="discount"/>
<fmt:message bundle="${locale}" key="final_amount" var="final_amount"/>
<fmt:message bundle="${locale}" key="order.credit_card" var="credit_card"/>
<fmt:message bundle="${locale}" key="pay" var="pay"/>
<fmt:message bundle="${locale}" key="wrong_cost_period" var="wrong_cost_period"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <script src="${pageContext.request.contextPath}/script/orderCostSetter.js"></script>
    <script src="${pageContext.request.contextPath}/script/validation/orderValidation.js"></script>
    <title>«Olympia» Fitness Centre</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="gym_membership"/>
    <jsp:param name="currentPage" value="show_order_page"/>
</jsp:include>

    <div class="col-1">
        <label for="period_cost">${how_long}</label>
    </div>
    <div class="col-2">
        <select oninput="checkOffer()" id="period_cost" onchange="setCost()" name="period">
            <fitness:prices/>
        </select>
    </div>

    <div class="col-1">
        <label for="cost">${amount}</label>
    </div>
    <div class="col-2">
        <input type="text" id="cost" name="cost" value="0$" readonly>
    </div>

    <div class="col-1">
        <label for="personal_discount">${discount}</label>
    </div>
    <div class="col-2">
        <input type="text" id="personal_discount" name="personal_discount" value="${client.personalDiscount}" readonly>
    </div>

    <div class="col-1">
        <label for="final_cost">${final_amount}</label>
    </div>
    <div class="col-2">
        <input type="text" id="final_cost" name="final_cost" value="0.0$" readonly>
    </div>
    <br/>

    <form name="form" action="${pageContext.servletContext.contextPath}/controller?command=update_gym_membership" method="post">
        <input type="hidden" id="period" name="period">
        <label for="cardNumber">${credit_card}</label>
        <input onchange="checkCardNumber()" type="text" id="cardNumber" name="cardNumber" placeholder="${card_placeholder}" title="${card_pattern_error}">
        <label for="finalCostModalWindow">${final_amount}</label>
        <input id="finalCostModalWindow" name="cost" type="text" readonly>
        <c:choose>
            <c:when test="${not empty requestScope.wrongCard}">
                ${card_pattern_error}
            </c:when>
            <c:when test="${not empty requestScope.wrongPeriod}">
                ${wrong_cost_period}
            </c:when>
        </c:choose>
        <input onclick="checkOffer()" class="button" type="submit" value="${pay}">
    </form>

<footer>
    ${footer}
</footer>
</body>
</html>
