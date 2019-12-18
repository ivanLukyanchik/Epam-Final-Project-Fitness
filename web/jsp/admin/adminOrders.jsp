<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="order.cost" var="cost"/>
<fmt:message bundle="${locale}" key="order.credit_card" var="credit_card"/>
<fmt:message bundle="${locale}" key="order.payment_data" var="payment_data"/>
<fmt:message bundle="${locale}" key="end_date" var="end_date"/>
<fmt:message bundle="${locale}" key="client_no_image" var="client_no_image"/>
<fmt:message bundle="${locale}" key="login" var="login"/>
<fmt:message bundle="${locale}" key="image" var="image"/>
<fmt:message bundle="${locale}" key="no_orders" var="no_orders"/>
<fmt:message bundle="${locale}" key="sort_by" var="sort_by"/>
<fmt:message bundle="${locale}" key="price_sort" var="price_sort"/>
<fmt:message bundle="${locale}" key="time_sort" var="time_sort"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Orders</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="adminOrders"/>
    <jsp:param name="currentPage" value="admin_orders"/>
</jsp:include>

<c:if test="${fn:length(orders) eq 0}">
    <h3><c:out value="${no_orders}"/></h3>
</c:if>

<div class="container row">
    <div class="col-3">
        ${sort_by}
    </div>

    <c:choose>
        <c:when test="${param.sortOrder eq 'priceAsc'}">
            <form method="post" class="col-3" action="${pageContext.servletContext.contextPath}/controller?command=admin_orders">
                <input type="hidden" name="sortOrder" value="priceDesc">
                <button class="btn btn-light active">${price_sort} <i class="fas fa-arrow-up"></i></button>
            </form>
        </c:when>
        <c:when test="${param.sortOrder eq 'priceDesc'}">
            <form method="post" class="col-3" action="${pageContext.servletContext.contextPath}/controller?command=admin_orders">
                <input type="hidden" name="sortOrder" value="priceAsc">
                <button class="btn btn-light active">${price_sort} <i class="fas fa-arrow-down"></i></button>
            </form>
        </c:when>
        <c:otherwise>
            <form method="post" class="col-3" action="${pageContext.servletContext.contextPath}/controller?command=admin_orders">
                <input type="hidden" name="sortOrder" value="priceAsc">
                <button class="btn btn-link">${price_sort} </button>
            </form>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${param.sortOrder eq 'paymentDataAsc'}">
            <form method="post" class="col-3" action="${pageContext.servletContext.contextPath}/controller?command=admin_orders">
                <input type="hidden" name="sortOrder" value="paymentDataDesc">
                <button class="btn btn-light active">${time_sort} <i class="fas fa-arrow-up"></i></button>
            </form>
        </c:when>
        <c:when test="${param.sortOrder eq 'paymentDataDesc'}">
            <form method="post" class="col-3" action="${pageContext.servletContext.contextPath}/controller?command=admin_orders">
                <input type="hidden" name="sortOrder" value="paymentDataAsc">
                <button class="btn btn-light active">${time_sort} <i class="fas fa-arrow-down"></i></button>
            </form>
        </c:when>
        <c:otherwise>
            <form method="post" class="col-3" action="${pageContext.servletContext.contextPath}/controller?command=admin_orders">
                <input type="hidden" name="sortOrder" value="paymentDataAsc">
                <button class="btn btn-link">${time_sort}</button>
            </form>
        </c:otherwise>
    </c:choose>
</div>

<table class="table table-striped table-hover mt-3">
    <tr>
        <th>${login}</th>
        <th>${image}</th>
        <th>${cost}</th>
        <th>${payment_data}</th>
        <th>${end_date}</th>
        <th>${credit_card}</th>
    </tr>
    <c:forEach items="${orders}" var="order">
        <c:set var="continueExecuting" scope="request" value="true"/>
        <c:forEach items="${clients}" var="client">
            <c:if test="${order.clientId==client.id}">
                <c:if test="${continueExecuting}">
                    <tr>
                        <td>${client.login}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=client_profile&admin_client_id=${client.id}">
                                <c:choose>
                                    <c:when test="${not empty client.image}">
                                        <img src="data:image/jpg;base64,${client.image}"  class="rounded-circle" alt="${client_no_image}" width="30" height="30"/>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fas fa-user-circle" style="font-size: 32px"></i>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </td>
                        <td>${order.cost}</td>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.local eq 'en_US'}">
                                    <div>
                                        <fmt:formatDate value="${order.paymentData}" pattern="dd-MM-YYYY HH:mm:ss" />
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div>
                                        <fmt:formatDate value="${order.paymentData}" pattern="dd.MM.YYYY HH:mm:ss" />
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td >
                            <c:choose>
                                <c:when test="${sessionScope.local eq 'en_US'}">
                                    <div>
                                        <fmt:formatDate value="${order.membershipEndDate}" pattern="dd-MM-YYYY HH:mm:ss" />
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div>
                                        <fmt:formatDate value="${order.membershipEndDate}" pattern="dd.MM.YYYY HH:mm:ss" />
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            ${fn:substring(order.cardNumber, 0, 4)}  ${fn:substring(order.cardNumber, 4, 6)}**  ****  ${fn:substring(order.cardNumber, 12, 16)}
                        </td>
                    </tr>
                </c:if>
                <c:set var="continueExecuting" scope="request" value="false"/>
            </c:if>
        </c:forEach>
    </c:forEach>
</table>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
