<jsp:useBean id="nutrition" class="by.epam.fitness.entity.Nutrition" scope="request"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <script src="${pageContext.request.contextPath}/script/validation/nutritionValidation.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>My nutrition</title>
</head>
<body class="d-flex flex-column">
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
            <input type="hidden" name="nutrition_id" value="${nutrition.id}"/>
            <input type="submit" class="btn btn-success" value="${activate_nutrition}"/>
        </form>
    </c:if>
</c:if>

<c:if test="${empty no_nutrition}">
    <c:choose>
        <c:when test="${membership_valid == false}">
            <h3>${cant_choose_nutrition}</h3>
            <form action="${pageContext.servletContext.contextPath}/controller?command=show_order_page" method="post">
                <input type="submit" class="btn btn-success" value="${buy}">
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
                    <p class="text-success">${added_nutrition}</p>
                </c:when>
                <c:when test="${nutritionUpdated eq true}">
                    <p class="text-success">${updated_nutrition}</p>
                </c:when>
                <c:when test="${nutritionRejected eq true}">
                    <p class="text-success">${rejected_nutrition}</p>
                </c:when>
                <c:when test="${incorrect_input_nutrition_data_error eq true}">
                    <p class="text-danger">${invalid_nutrition}</p>
                </c:when>
            </c:choose>

            <form action="${pageContext.servletContext.contextPath}/controller?command=reject_nutrition" method="post">
                <input type="hidden" name="nutrition_id" value="${nutrition.id}"/>
                <div class="text-center">
                    <input type="submit" class="btn btn-danger" value="${reject_nutrition}">
                </div>
            </form>

            <div class="container">
            <div class="row">

                <div class="col-md-4">
                    <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="img/nutrition_period/morning.jpg" alt="Morning image">
                        <div class="card-body">
                            <h5 class="card-title">${morning}</h5>
                            <p class="card-text"><c:out value="${nutrition.morningNutrition}"/></p>
                            <form name="form" action="${pageContext.request.contextPath}/controller?command=update_nutrition" method="post">
                                <input type="hidden" name="nutrition_id" value="${nutrition.id}"/>
                                <input type="hidden" name="nutrition_time" value="morning"/>
                                <div class="md-form amber-textarea active-amber-textarea">
                                    <i class="fas fa-pencil-alt prefix"></i>
                                    <textarea onchange="checkNutrition()" name="nutrition_description" class="md-textarea form-control my-3" required title="${invalid_nutrition}">${fn:escapeXml(nutrition.morningNutrition)}</textarea>
                                </div>
                                <input type="submit" class="btn btn-primary" value="${save}">
                                <h3>${max_symbols} ${max_number_symbols_attribute}</h3>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="img/nutrition_period/lunch.jpg" alt="Lunch image">
                        <div class="card-body">
                            <h5 class="card-title">${lunch}</h5>
                            <p class="card-text"><c:out value="${nutrition.lunchNutrition}"/></p>
                            <form name="form" action="${pageContext.request.contextPath}/controller?command=update_nutrition" method="post">
                                <input type="hidden" name="nutrition_id" value="${nutrition.id}"/>
                                <input type="hidden" name="nutrition_time" value="lunch"/>
                                <div class="md-form amber-textarea active-amber-textarea">
                                    <i class="fas fa-pencil-alt prefix"></i>
                                    <textarea  onchange="checkNutrition()" name="nutrition_description" class="md-textarea form-control my-3" required title="${invalid_nutrition}">${fn:escapeXml(nutrition.lunchNutrition)}</textarea>
                                </div>
                                <input type="submit" class="btn btn-primary" value="${save}">
                                <h3>${max_symbols} ${max_number_symbols_attribute}</h3>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="img/nutrition_period/dinner.jpg" alt="Dinner image">
                        <div class="card-body">
                            <h5 class="card-title">${dinner}</h5>
                            <p class="card-text"><c:out value="${nutrition.dinnerNutrition}"/></p>
                            <form name="form" action="${pageContext.request.contextPath}/controller?command=update_nutrition" method="post">
                                <input type="hidden" id="nutrition_id" name="nutrition_id" value="${nutrition.id}"/>
                                <input type="hidden" id="nutrition_time" name="nutrition_time" value="dinner"/>
                                <div class="md-form amber-textarea active-amber-textarea">
                                    <i class="fas fa-pencil-alt prefix"></i>
                                    <textarea  onchange="checkNutrition()" id="nutrition_description" name="nutrition_description" class="md-textarea form-control my-3" required title="${invalid_nutrition}">${fn:escapeXml(nutrition.dinnerNutrition)}</textarea>
                                </div>
                                <input type="submit" class="btn btn-primary" value="${save}">
                                <h3>${max_symbols} ${max_number_symbols_attribute}</h3>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </c:otherwise>
    </c:choose>
</c:if>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
