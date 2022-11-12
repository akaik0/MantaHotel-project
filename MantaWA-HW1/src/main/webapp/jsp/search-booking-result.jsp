<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Searched Booking</title>
</head>

<body>
<h1>Searched Booking</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/show-message.jsp"/>

<c:if test='${not empty booking}'>
    <ul>
        <li>Booking: <c:out value="${booking.bookingid}"/></li>
        <li>customer <c:out value="${booking.personid}"/></li>
        <li>checkin: <c:out value="${booking.checkin}"/></li>
        <li>checkout: <c:out value="${booking.checkout}"/></li>
        <li>price: <c:out value="${booking.paymentid}"/></li>
        <li>date: <c:out value="${booking.date}"/></li>
        <c:if test="${not empty booking.requests}">
            <li>requests: <c:out value="${booking.requests}"/></li>
        </c:if>
    </ul><br><br/>
</c:if>

<c:if test='${not empty bookingList}'>
    <c:if test="${not empty customer}">
        <p>List of bookings for "${customer.name} ${customer.surname}"</p><br><br/>
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

    <!-- meaning that we want the list of bookings by date -->
    <c:if test="${not empty date}">
        <p>List of bookings with checkin or checkout date in "${date}"</p><br><br/>
        <table>
            <thead>
            <tr>
                <th>BookingId</th><th>Customer</th><th>Checkin</th><th>Checkout</th><th>Payment</th><th>Requests</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="booking" items="${bookingList}">
                <tr>
                    <td><c:out value="${booking.bookingid}"/></td>
                    <td><c:out value="${booking.personid}"/></td>
                    <td><c:out value="${booking.checkin}"/></td>
                    <td><c:out value="${booking.checkout}"/></td>
                    <td><c:out value="${booking.paymentid}"/></td>
                    <c:if test="${not empty booking.requests}">
                        <td><c:out value="${booking.requests}"/></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

     <!-- meaning that we want the list of bookings that are not payed -->
        <c:if test="${not empty notpayed}">
            <p>List of bookings that have not payed</p><br><br/>
            <table>
                <thead>
                <tr>
                    <th>BookingId</th><th>Customer</th><th>Checkin</th><th>Checkout</th><th>Payment</th><th>Requests</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="booking" items="${bookingNotPayed}">
                    <tr>
                        <td><c:out value="${booking.bookingid}"/></td>
                        <td><c:out value="${booking.personid}"/></td>
                        <td><c:out value="${booking.checkin}"/></td>
                        <td><c:out value="${booking.checkout}"/></td>
                        <td><c:out value="${booking.paymentid}"/></td>
                        <c:if test="${not empty booking.requests}">
                            <td><c:out value="${booking.requests}"/></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

</c:if>

<a href="${pageContext.request.contextPath}/jsp/user-area/users_homepage.jsp">Back to Home page</a><br/>

</body>
</html>


