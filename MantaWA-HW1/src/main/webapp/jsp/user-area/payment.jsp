<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payments</title>
</head>
<body>
<h1>Payments</h1>
<hr/>

<c:choose>
    <c:when test="${message.error}">
        <p><c:out value="${message.message}"/></p>
    </c:when>

    <c:when test="${not empty payment}">
        <ul>
            <li>Payment: <c:out value="${payment.getPaymentID()}"/></li>
            <li>Selected method:<c:out value="${payment.getPaymentMethod()}"/></li>
            <li>Total Amount: <c:out value="${payment.getTotalAmount()}"/></li>
            <li>Payed: <c:out value="${payment.isComplete()}"/></li>
            <li>Creation date: <c:out value="${payment.getDate()}"/></li>
            <li>Registered by: <c:out value="${payment.getEmail()}"/></li>
        </ul><br><br/>
    </c:when>

    <c:when test="${not empty paymentList}">
        <p>List of payments:</p><br><br/>
        <table>
            <thead>
            <tr>
                <th>PaymentId</th><th>Total amount</th><th>Selected method</th><th>Complete</th><th>Date</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="payment" items="${paymentList}">
                <tr>
                    <td><c:out value="${payment.getPaymentID()}"/></td>
                    <td><c:out value="${payment.getTotalAmount()}"/></td>
                    <td><c:out value="${payment.getPaymentMethod()}"/></td>
                    <td><c:out value="${payment.isComplete()}"/></td>
                    <td><c:out value="${payment.getDate()}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>

    <c:otherwise>
        <h2>Edit payment</h2>
        <form method="GET" action="<c:url value="/payment/searchID"/>">
            <label for="paymentid">PaymentId: </label>
            <input name="paymentid" type="text"/><br/><br/>
            <button type="submit">Submit</button><br/>
        </form>

        <h2>Search payment by booking</h2>
        <form method="GET" action="<c:url value="/payment/searchByBooking"/>">
            <label for="bookingid">BookingId: </label>
            <input name="bookingid" type="text"/><br/><br/>
            <button type="submit">Search</button><br/>
        </form>

        <h2>Search list of payments by customer</h2>
        <form method="GET" action="<c:url value="/payment/list"/>">
            <label for="personid">CustomerId: </label>
            <input name="personid" type="text"/><br/><br/>
            <button type="submit">Search</button><br/>
        </form>
    </c:otherwise>
</c:choose>
<a href="${pageContext.request.contextPath}/user-area/users_homepage.jsp">Back to Home page</a><br><br/>
</body>
</html>
