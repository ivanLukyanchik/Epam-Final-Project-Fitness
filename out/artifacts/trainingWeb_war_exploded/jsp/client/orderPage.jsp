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
<fmt:message bundle="${locale}" key="offer" var="offer"/>
<fmt:message bundle="${locale}" key="choose_offer" var="choose_offer"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <script src="${pageContext.request.contextPath}/script/orderCostSetter.js"></script>
    <script src="${pageContext.request.contextPath}/script/validation/orderValidation.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>«Olympia» Fitness Centre</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="gym_membership"/>
    <jsp:param name="currentPage" value="show_order_page"/>
</jsp:include>

    <form name="form" action="${pageContext.servletContext.contextPath}/controller?command=update_gym_membership" class="text-center border border-light px-5 py-1" method="post">
        <input type="hidden" id="period" name="period">
        <div>
            <label for="period_cost">${how_long}</label>
        </div>
        <div>
            <select  class="form-control mb-4" oninput="checkOffer()" id="period_cost" onchange="setCost()" name="period" title="${choose_offer}">
                <option>${offer}</option>
                <fitness:prices/>
            </select>
        </div>

        <div>
            <label for="cost">${amount}</label>
        </div>
        <div>
            <input type="text" class="form-control mb-4" id="cost" value="0$" readonly>
        </div>

        <div>
            <label for="personal_discount">${discount}</label>
        </div>
        <div>
            <input type="text" class="form-control mb-4" id="personal_discount" name="personal_discount" value="${client.personalDiscount}" readonly>
        </div>

        <div>
            <label for="final_cost">${final_amount}</label>
        </div>
        <div>
            <input type="text" class="form-control mb-4" id="final_cost" name="final_cost" value="0.0$" readonly>
        </div>
<%--        main info--%>
        <label for="cardNumber">${credit_card}</label>
        <input onchange="checkCardNumber()" class="form-control mb-4" type="text" id="cardNumber" name="cardNumber" placeholder="${card_placeholder}" title="${card_pattern_error}">
        <label for="finalCostModalWindow">${final_amount}</label>
        <input id="finalCostModalWindow" class="form-control mb-4" name="cost" type="text" readonly>
        <c:choose>
            <c:when test="${not empty requestScope.wrongCard}">
                <p class="text-danger">${card_pattern_error}</p>
            </c:when>
            <c:when test="${not empty requestScope.wrongPeriod}">
                <p class="text-danger">${wrong_cost_period}</p>
            </c:when>
        </c:choose>
        <input onclick="checkOffer()" class="btn btn-success my-4 btn-block" type="submit" value="${pay}">
        <div class="row text-center">
            <div class="col-12 payment">
                <i class="fab fa-cc-mastercard"></i>
                <i class="fab fa-cc-visa"></i>
                <i class="fab fa-cc-paypal"></i>
            </div>
        </div>
    </form>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
