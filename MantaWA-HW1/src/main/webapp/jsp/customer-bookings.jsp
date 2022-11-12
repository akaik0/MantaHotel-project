<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>List of Bookings</title>
</head>

<body>
<h1>List of Bookings</h1>
<hr/>

<!-- display the message -->
<!--<c:import url="/jsp/show-message.jsp"/>-->

<c:choose>

    <c:when test="${message.error}">
        <p><c:out value="${message.message}"/></p>
    </c:when>

    <c:otherwise>
        <!-- display the found booking, if any -->
        <c:if test='${not empty bookingList}'>
            <table>
                <thead>
                <tr>
                    <th>BookingId</th><th>Checkin</th><th>Checkout</th><th>Payment</th><th>Date</th><th>Requests</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="booking" items="${bookingList}">
                    <tr>
                        <td><c:out value="${booking.bookingid}"/></td>
                        <td><c:out value="${booking.checkin}"/></td>
                        <td><c:out value="${booking.checkout}"/></td>
                        <td><c:out value="${booking.paymentid}"/></td>
                        <td><c:out value="${booking.date}"/></td>
                        <c:if test="${not empty booking.requests}">
                            <td><c:out value="${booking.requests}"/></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:otherwise>
</c:choose>

<a href="${pageContext.request.contextPath}/jsp/homepage.jsp">Back to Home page</a><br/>
<!-- back to customer or user homepage -->
</body>
</html>

