<jsp:useBean id="client" type="by.epam.fitness.entity.Client" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale.pagecontent" var="locale"/>

<fmt:message bundle="${locale}" key="user_name" var="name"/>
<fmt:message bundle="${locale}" key="surname" var="surname"/>
<fmt:message bundle="${locale}" key="login" var="login"/>
<fmt:message bundle="${locale}" key="coach" var="coach"/>
<fmt:message bundle="${locale}" key="membership_number" var="membership_number"/>
<fmt:message bundle="${locale}" key="private_discount" var="private_discount"/>
<fmt:message bundle="${locale}" key="membership_valid_until" var="membership_valid_until"/>
<fmt:message bundle="${locale}" key="coach.no_coach" var="no_coach"/>
<fmt:message bundle="${locale}" key="order.no_membership" var="no_membership"/>
<fmt:message bundle="${locale}" key="message.wrongLogin" var="wrongLogin"/>
<fmt:message bundle="${locale}" key="register.pattern.error" var="registration_pattern_error"/>
<fmt:message bundle="${locale}" key="login.pattern.error" var="username_pattern_error"/>
<fmt:message bundle="${locale}" key="email.pattern.error" var="email_pattern_error"/>
<fmt:message bundle="${locale}" key="success_modify" var="success"/>
<fmt:message bundle="${locale}" key="modify" var="modify"/>
<fmt:message bundle="${locale}" key="paymentSuccess" var="paymentSuccess"/>
<fmt:message bundle="${locale}" key="not_valid_membership" var="not_valid_membership"/>
<fmt:message bundle="${locale}" key="not_image" var="not_image"/>
<fmt:message bundle="${locale}" key="have_no_image" var="have_no_image"/>
<fmt:message bundle="${locale}" key="upload_photo" var="upload_photo"/>
<fmt:message bundle="${locale}" key="change_password" var="change_password"/>
<fmt:message bundle="${locale}" key="delete_account" var="delete_account"/>
<fmt:message bundle="${locale}" key="sales_description" var="sales_description"/>
<fmt:message bundle="${locale}" key="s_d_1" var="s_d_1"/>
<fmt:message bundle="${locale}" key="s_d_2" var="s_d_2"/>
<fmt:message bundle="${locale}" key="s_d_3" var="s_d_3"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <script src="${pageContext.request.contextPath}/script/validation/registerValidation.js"></script>
    <script src="${pageContext.request.contextPath}/script/util.js"></script>
    <title>«Olympia» Fitness Centre</title>
</head>
<body>
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="profile"/>
    <jsp:param name="currentPage" value="client_profile"/>
</jsp:include>

<form method="post" action="modifyProfileServlet" enctype="multipart/form-data">
    <input type="hidden" name="client_id" value="${client.id}">
    <div class="col-1">
        <label for="name">${name}</label>
    </div>
    <div class="col-2">
        <input onchange="checkName()" type="text" id="name" name="name" value="${client.name}" required title="${registration_pattern_error}">
    </div>
    <div class="col-1">
        <label for="surname">${surname}</label>
    </div>
    <div class="col-2">
        <input onchange="checkSurname()" type="text" id="surname" name="surname" value="${client.surname}" required title="${registration_pattern_error}">
    </div>
    <div class="col-1">
        <label for="login">${login}</label>
    </div>
    <div class="col-2">
        <input type="hidden" name="oldLogin" value="${client.login}">
        <input onchange="checkLogin()" type="text" id="login" name="login" value="${client.login}" required title="${username_pattern_error}">
    </div>
    <div class="col-1">
        <label for="email">Email:</label>
    </div>
    <div class="col-2">
        <input onchange="checkEmail()" type="text" id="email" name="email" value="${client.email}" required title="${email_pattern_error}">
    </div>
    <p><img src="data:image/jpg;base64,${client.image}" alt="${have_no_image}" width="100" height="100" style="border-radius: 25px"/></p>
    <label for="photo">${upload_photo}</label>
    <input type="file" id="photo" name="photo" accept="image/x-png,image/jpeg" />
    <br/>
    <c:choose>
        <c:when test="${not empty requestScope.wrongData}">
            ${wrongLogin}
        </c:when>
        <c:when test="${not empty requestScope.notImage}">
            ${not_image}
        </c:when>
        <c:when test="${not empty requestScope.invalidPassword}">
            ${registration_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.invalidSurname}">
            ${registration_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.invalidName}">
            ${registration_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.invalidLogin}">
            ${username_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.invalidEmail}">
            ${email_pattern_error}
        </c:when>
        <c:when test="${not empty requestScope.success}">
            ${success}
        </c:when>
        <c:when test="${not empty requestScope.paymentSuccess}">
            ${paymentSuccess}
        </c:when>
    </c:choose>
    <br/>
    <input onclick="checkForChangingAnyData()" type="submit" value="${modify}">
</form>

<form action="${pageContext.request.contextPath}/passwordRestore" method="post">
    <input type="hidden" name="key1" value="${client.email}">
    <input type="hidden" name="key2" value="${client.login}">
    <input type="hidden" name="key3" value="${client.userHash}">
    <input type="submit" value="${change_password}">
</form>

<br/>
<div class="col-1">
    <label for="coach_name">${coach}</label>
</div>
<div class="col-2">
    <c:choose>
        <c:when test="${coach_name!=null}">
            <input type="text" id="coach_name" name="coach_name"  value="${coach_name} ${coach_surname}" readonly>
        </c:when>
        <c:otherwise>
            <input type="text" id="coach_name" name="coach_name"  value="${no_coach}" readonly>
        </c:otherwise>
    </c:choose>
</div>

<div class="col-1">
    <label for="visits_number">${membership_number}</label>
</div>
<div class="col-2">
    <input type="text" id="visits_number" name="visits_number"  value="${client.membershipNumber}" readonly>
</div>

<div class="col-1" >
    <label for="personal_discount">${private_discount}</label>
</div>
<div class="col-2">
    <input type="text" id="personal_discount" name="personal_discount"  value="${client.personalDiscount}" readonly>
    <button type="button" onclick="showDescription()" id="question" style="border-radius: 25px">
        <img src="img/util/question.png" alt="question"  width="15" height="15"/>
    </button>
</div>

<div id="description" style="display:none">
    ${sales_description}
    <ul>
        <li>${s_d_1}</li>
        <li>${s_d_2}</li>
        <li>${s_d_3}</li>
    </ul>
</div>

<div class="col-1">
    <label for="end_date_of_trains">${membership_valid_until}</label>
</div>
<c:choose>

    <c:when test="${fn:length(orders) eq 0}">
        <div class="2">
            <input type="text" id="end_date_of_trains" name="end_date_of_trains"  value="${no_membership}" readonly>
        </div>
    </c:when>

    <c:when test="${empty membership_valid}">
        <div class="2">
            <input type="text" id="end_date_of_trains" name="end_date_of_trains"  value="${not_valid_membership}" readonly>
        </div>
    </c:when>

    <c:otherwise>
        <c:choose>
            <c:when test="${sessionScope.local eq 'en_US'}">
                <div class="col-1">
                    <input type="text" id="end_date_of_trains" name="end_date_of_trains"  value="<fmt:formatDate value="${orders[fn:length(orders)-1].membershipEndDate}" pattern="dd-MM-YYYY" />" readonly>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-1">
                    <input type="text" id="end_date_of_trains" name="end_date_of_trains"  value="<fmt:formatDate value="${orders[fn:length(orders)-1].membershipEndDate}" pattern="dd.MM.YYYY" />" readonly>
                </div>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>

<br/>
<form action="${pageContext.servletContext.contextPath}/controller?command=delete_account" method="post">
    <input type="submit" value="${delete_account}" style="color: darkred">
</form>

<footer>
    ${footer}
</footer>
</body>
</html>
