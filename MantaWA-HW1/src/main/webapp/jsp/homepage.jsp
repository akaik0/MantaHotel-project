<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>homepage</title>
</head>
<body>

<h1>Homepage</h1>
<hr/>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">Home</a><br/>

<!--
<form action="${pageContext.request.contextPath}/jsp/insert-booking-form.jsp">
    <button type="submit">Book a room</button><br><br/>
</form>
<form action="${pageContext.request.contextPath}/rooms">
    <button type="submit">Show All Rooms</button><br><br/>
</form>
-->
<a href="${pageContext.request.contextPath}/rooms">Rooms</a><br/>
<a href="${pageContext.request.contextPath}/jsp/insert-booking-form.jsp">Book a room</a><br/>

<c:choose>
    <c:when test="${!empty sessionScope.email && empty sessionScope.role}">
        <p>you are logged in with the email "${sessionScope.email}"</p>
        <a href="${pageContext.request.contextPath}/booking/customer-list">My Bookings</a><br/>
        <a href="${pageContext.request.contextPath}/customer/logout/">Logout</a><br/>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${!empty sessionScope.email && !empty sessionScope.role}">
                <p>you are logged in with the email "${sessionScope.email}"</p>
                <a href="${pageContext.request.contextPath}/user/logout/">Logout</a><br/>
            </c:when>
            <c:otherwise> <p>login to access to all the functionalities</p>
                <a href="${pageContext.request.contextPath}/jsp/login.jsp">login page</a><br/>
                <a href="${pageContext.request.contextPath}/jsp/customer-registration-form.jsp">registration page</a><br/>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>

<div id="homepage-content"></div>


</body>
</html>