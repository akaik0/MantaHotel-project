<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Insert booking</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="../css/booking.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-admin.css">

    <script src="${pageContext.request.contextPath}/js/utils.js"></script>
    <script src="${pageContext.request.contextPath}/js/insert-booking.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>

</head>
<body>

<div id="header"><c:import url="reusable/header.jsp"/></div>

<c:choose>
    <c:when test="${empty sessionScope.email}">
        <div style="margin: 9%">
            <p class="font-serif d-flex justify-content-center" style="font-weight: bold; font-size: xx-large;
            color: #7A2826; margin: 10px;">Login to book a room</p>
            <div class="d-flex justify-content-center">
                <a href="${pageContext.request.contextPath}/jsp/login.jsp" class="btn btn-info" style="margin-right:5px">Login page</a>
                <a href="${pageContext.request.contextPath}/jsp/customer-registration-form.jsp" class="btn btn-info"
                   style="margin-left:5px">Registration page</a><br/>
            </div>
        </div>
        <div id="footer"><c:import url="reusable/footer.jsp"/></div>
    </c:when>

    <c:otherwise>

        <!-- displaying error messages -->
        <c:if test="${message.error}">
            <p class="alert alert-danger text-center font-serif"><c:out value="${message.message}"/></p>
        </c:if>
        <div id="error"></div>

        <c:if test="${not empty sessionScope.role}">
            <c:import url="reusable/insert-booking.jsp"/>
            <div id="footer-admin"> </div>
        </c:if>

        <c:if test="${empty sessionScope.role}">
            <div class="text-justify content bg-gradient-to-b from-white to-sand">
                <c:import url="reusable/insert-booking.jsp"/>
                <div id="footer"><c:import url="reusable/footer.jsp"/></div>
            </div>
        </c:if>

    </c:otherwise>

</c:choose>

</body>
</html>

