<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Insert booking</title>
</head>
<body>

<c:choose>
    <c:when test="${empty sessionScope.email}">
        <p>Login to book a room</p>
        <a href="${pageContext.request.contextPath}/jsp/login.jsp">login page</a><br/>
        <a href="${pageContext.request.contextPath}/jsp/customer-registration-form.jsp">registration page</a><br/>
    </c:when>

    <c:otherwise>
        <h1>Insert new booking</h1>
        <hr/>
        <form method="POST" action="<c:url value="/booking/insert/info"/>">
            <c:if test="${empty sessionScope.personId}">
                <label for="personId">Customer Id: </label>
                <input name="personId" type="text"/><br/><br/>
            </c:if>
            <label for="beds">Number of people: </label>
            <input name="beds" type="text"/><br/><br/>
            <label for="checkin">Checkin (date and hour): </label>
            <input name="checkin" type="datetime-local"/><br/><br/>
            <label for="checkout">Checkout (date and hour): </label>
            <input name="checkout" type="datetime-local"/><br/><br/>
            <textarea name="requests" cols="20" rows="4">Requests...</textarea><br><br/>
            <label for="paymentM">Payment Method: </label>
            <select name="paymentM" type="text">
                <option value="Visa">Visa</option>
                <option value="MasterCard">MasterCard</option>
                <option value="Maestro">Maestro</option>
                <option value="American Express">American Express</option>
                <option value="PayPal">PayPal</option>
                <option value="Cash">Cash</option>
            </select><br/><br/>
            <button type="submit">Submit</button><br/>
            <button type="reset">Reset the form</button>
        </form>
        <c:choose>
            <c:when test="${message.error}">
                <p><c:out value="${message.message}"/></p>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>

</body>
</html>

