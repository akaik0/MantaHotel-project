<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>homepage</title>
</head>
<body>

<c:choose>
    <c:when test="${!empty sessionScope.email && !empty sessionScope.role}">
        <p>you are logged in with the email "${sessionScope.email}"</p>
        <a href="${pageContext.request.contextPath}/user/logout/">Logout</a><br/>
        <a href="${pageContext.request.contextPath}/jsp/user-area/payment.jsp">Payments</a><br/>
        <a href="${pageContext.request.contextPath}/jsp/booking.jsp">Bookings</a><br/>
        <a href="${pageContext.request.contextPath}/jsp/user-area/user-insert-customer.jsp">Insert guest</a><br/>
        <a href="${pageContext.request.contextPath}/rooms">Rooms</a><br/>
        <c:if test="${sessionScope.role.equals('Hotel Manager')}">
            <a href="${pageContext.request.contextPath}/jsp/manager-area/user-list.jsp">Users</a><br/>
            <a href="${pageContext.request.contextPath}/jsp/manager-area/view-logs.jsp">View Logs</a><br/>
        </c:if>
    </c:when>
    <c:otherwise> <p>login to access to all the functionalities</p>
        <a href="${pageContext.request.contextPath}/jsp/login.jsp">login page</a><br/>
    </c:otherwise>
</c:choose>

<div id="homepage-content"></div>


</body>
</html>