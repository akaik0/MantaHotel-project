<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Insert booking Result</title>
</head>
<body>
<h1>Insert Booking</h1>
<hr/>

<c:choose>
    <c:when test="${message.error}">
        <p><c:out value="${message.message}"/></p>
    </c:when>

    <c:otherwise>
        <p>The booking has been inserted correctly</p>
        <hr/>
        <ul>
            <li>Booking: <c:out value="${booking.bookingid}"/></li>
            <li>checkin: <c:out value="${booking.checkin}"/></li>
            <li>checkout: <c:out value="${booking.checkout}"/></li>
            <li>room: <c:out value="${room.roomNumber}"/></li>
            <li>price: <c:out value="${payment.totalAmount}"/></li>
        </ul><br><br/>

        <c:choose>
            <c:when test="${not empty sessionScope.personId}">
                <a href="${pageContext.request.contextPath}/jsp/homepage.jsp">Back to Home page</a><br><br/>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/jsp/user-area/users_homepage.jsp">Back to Home page</a><br><br/>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>

</body>
</html>

