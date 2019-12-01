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
<fmt:message bundle="${locale}" key="choose_file" var="choose_file"/>
<fmt:message bundle="${locale}" key="account_deleting" var="account_deleting"/>
<fmt:message bundle="${locale}" key="sure_delete" var="sure_delete"/>
<fmt:message bundle="${locale}" key="understand_delete" var="understand_delete"/>
<fmt:message bundle="${locale}" key="footer.copyright" var="footer"/>

<html>
<head>
    <link rel="shortcut icon" href="img/favicon/1.ico"/>
    <script src="${pageContext.request.contextPath}/script/validation/registerValidation.js"></script>
    <script src="${pageContext.request.contextPath}/script/util.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>«Olympia» Fitness Centre</title>
</head>
<body class="d-flex flex-column">
<jsp:include page="../menu.jsp">
    <jsp:param name="pageTopic" value="profile"/>
    <jsp:param name="currentPage" value="client_profile"/>
</jsp:include>

<c:choose>
    <c:when test="${not empty requestScope.wrongData}">
        <p class="text-danger">${wrongLogin}</p>
    </c:when>
    <c:when test="${not empty requestScope.notImage}">
        <p class="text-danger">${not_image}</p>
    </c:when>
    <c:when test="${not empty requestScope.invalidPassword}">
        <p class="text-danger">${registration_pattern_error}</p>
    </c:when>
    <c:when test="${not empty requestScope.invalidSurname}">
        <p class="text-danger">${registration_pattern_error}</p>
    </c:when>
    <c:when test="${not empty requestScope.invalidName}">
        <p class="text-danger">${registration_pattern_error}</p>
    </c:when>
    <c:when test="${not empty requestScope.invalidLogin}">
        <p class="text-danger">${username_pattern_error}</p>
    </c:when>
    <c:when test="${not empty requestScope.invalidEmail}">
        <p class="text-danger">${email_pattern_error}</p>
    </c:when>
    <c:when test="${not empty requestScope.success}">
        <p class="text-success">${success}</p>
    </c:when>
    <c:when test="${not empty requestScope.paymentSuccess}">
        <p class="text-success">${paymentSuccess}</p>
    </c:when>
</c:choose>

<form method="post" class="border border-light p-5" action="modifyProfileServlet" enctype="multipart/form-data">
    <input type="hidden" name="client_id" value="${client.id}">

    <div class="form-group row mb-4">
        <div class="col-1">
            <label for="name">${name}</label>
        </div>
        <div class="col-11">
            <input onchange="checkName()" type="text" class="form-control" id="name" name="name" value="${client.name}" required title="${registration_pattern_error}">
        </div>
    </div>

    <div class="form-group row mb-4">
        <div class="col-1">
            <label for="surname">${surname}</label>
        </div>
        <div class="col-11">
            <input onchange="checkSurname()" type="text" class="form-control" id="surname" name="surname" value="${client.surname}" required title="${registration_pattern_error}">
        </div>
    </div>

    <div class="form-group row mb-4">
        <div class="col-1">
            <label for="login">${login}</label>
        </div>
        <div class="col-11">
            <input type="hidden" name="oldLogin" value="${client.login}">
            <input onchange="checkLogin()" type="text" class="form-control" id="login" name="login" value="${client.login}" required title="${username_pattern_error}">
        </div>
    </div>

    <div class="form-group row mb-4">
        <div class="col-1">
            <label for="email">Email:</label>
        </div>
        <div class="col-11">
            <input onchange="checkEmail()" type="text" class="form-control" id="email" name="email" value="${client.email}" required title="${email_pattern_error}">
        </div>
    </div>

    <div class="form-group row mb-4">
        <div class="col-1">
            <img src="data:image/jpg;base64,${client.image}" alt="${have_no_image}" class="rounded-circle" style="width:100px"/>
        </div>
        <div class="col-11">
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="inputGroupFileAddon01">${upload_photo}</span>
                </div>
                <div class="custom-file">
                    <input type="file" name="photo" accept="image/x-png,image/jpeg" class="custom-file-input" id="photo"
                           aria-describedby="inputGroupFileAddon01">
                    <label class="custom-file-label mb-4" for="photo">${choose_file}</label>
                </div>
            </div>
        </div>
    </div>

    <div class="form-group row mb-4">
        <div class="col-1">
            <label for="coach_name">${coach}</label>
        </div>
        <div class="col-11">
            <c:choose>
                <c:when test="${coach_name!=null}">
                    <input type="text" class="form-control" id="coach_name" name="coach_name"  value="${coach_name} ${coach_surname}" readonly>
                </c:when>
                <c:otherwise>
                    <input type="text" class="form-control" id="coach_name" name="coach_name"  value="${no_coach}" readonly>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="form-group row mb-4">
        <div class="col-1">
            <label for="membershipNumber">${membership_number}</label>
        </div>
        <div class="col-11">
            <input type="text" class="form-control" id="membershipNumber" name="visits_number"  value="${client.membershipNumber}" readonly>
        </div>
    </div>

    <div class="form-group row mb-4">
        <div class="col-1">
            <label for="personal_discount">${private_discount}</label>
            <button type="button" onclick="showDescription()" id="question" style="border-radius: 25px">
                <img src="img/util/question.png" alt="question"  width="15" height="15"/>
            </button>
        </div>
        <div class="col-11">
            <input type="text" id="personal_discount" class="form-control" name="personal_discount"  value="${client.personalDiscount}" readonly>
        </div>
        <div id="description" style="display:none">
            ${sales_description}
            <ul>
                <li>${s_d_1}</li>
                <li>${s_d_2}</li>
                <li>${s_d_3}</li>
            </ul>
        </div>
    </div>

    <div class="form-group row mb-4">
        <div class="col-1">
            <label for="end_date_of_trains">${membership_valid_until}</label>
        </div>
        <c:choose>

            <c:when test="${fn:length(orders) eq 0}">
                <div class="col-11">
                    <input type="text" class="form-control" id="end_date_of_trains" name="end_date_of_trains"  value="${no_membership}" readonly>
                </div>
            </c:when>

            <c:when test="${empty membership_valid}">
                <div class="col-11">
                    <input type="text" class="form-control" id="end_date_of_trains" name="end_date_of_trains"  value="${not_valid_membership}" readonly>
                </div>
            </c:when>

            <c:otherwise>
                <c:choose>
                    <c:when test="${sessionScope.local eq 'en_US'}">
                        <div class="col-11">
                            <input type="text" class="form-control" id="end_date_of_trains" name="end_date_of_trains"  value="<fmt:formatDate value="${orders[fn:length(orders)-1].membershipEndDate}" pattern="dd-MM-YYYY" />" readonly>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-11">
                            <input type="text" class="form-control" id="end_date_of_trains" name="end_date_of_trains"  value="<fmt:formatDate value="${orders[fn:length(orders)-1].membershipEndDate}" pattern="dd.MM.YYYY" />" readonly>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </div>

    <input onclick="checkForChangingAnyData()" class="btn btn-success mt-4 btn-block" type="submit" value="${modify}">
</form>

<div class="container text-center p-5">
    <form action="${pageContext.request.contextPath}/passwordRestore" method="post">
        <input type="hidden" name="key1" value="${client.email}">
        <input type="hidden" name="key2" value="${client.login}">
        <input type="hidden" name="key3" value="${client.userHash}">
        <input type="submit" class="btn btn-warning btn-block" value="${change_password}">
    </form>

    <button type="button" class="btn btn-danger btn-block" data-toggle="modal" data-target="#deleteModal">${delete_account}</button>
    <div class="modal fade" id="deleteModal" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">${account_deleting}</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    ${sure_delete}
                </div>
                <div class="modal-footer">
                    <div class="container">
                        <form action="${pageContext.servletContext.contextPath}/controller?command=delete_account" method="post">
                            <input type="submit" class="btn btn-danger btn-block" value="${understand_delete}">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<footer class="footer mt-auto py-3">
    <div class="container text-center">
        <span class="text-muted">${footer}</span>
    </div>
</footer>
</body>
</html>
