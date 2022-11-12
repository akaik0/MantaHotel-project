<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Edit payment</title>
</head>
<body>

<c:choose>
    <c:when test="${message.error}">
        <p><c:out value="${message.message}"/></p>
    </c:when>

    <c:otherwise>
        <h1>Edit payment</h1>
        <hr/>
        <ul>
            <li>Payment: <c:out value="${payment.getPaymentID()}"/></li>
            <li>Selected method:<c:out value="${payment.getPaymentMethod()}"/></li>
            <li>Total Amount: <c:out value="${payment.getTotalAmount()}"/></li>
            <li>Payed: <c:out value="${payment.isComplete()}"/></li>
            <li>Creation date: <c:out value="${payment.getDate()}"/></li>
            <li>Registered by: <c:out value="${payment.getEmail()}"/></li>
        </ul><br><br/>

        <form method="POST" action="<c:url value="/payment/edit"/>">
            <p>Edits</p>
            <input type="hidden" name="paymentid" value="${payment.getPaymentID()}" />
            <label for="paymentM">Method: </label>
            <select name="paymentM" type="text">
                <option value="Visa">Visa</option>
                <option value="MasterCard">MasterCard</option>
                <option value="Maestro">Maestro</option>
                <option value="American Express">American Express</option>
                <option value="Paypal">Paypal</option>
                <option value="Cash">Cash</option>
            </select><br/><br/>
            <label for="amount">Total amount: </label>
            <input name="amount" type="text"/><br/><br/>
            <label for="paymentM">Payed: </label>
            <select name="complete" type="text">
                <option value="true">Yes</option>
                <option value="false">No</option>
            </select><br/><br/>
            <button type="submit">Edit</button><br/>
            <button type="reset">Reset the form</button>
        </form>
    </c:otherwise>
</c:choose>

</body>
</html>

