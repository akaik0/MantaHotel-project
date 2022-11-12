<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add customer to booking</title>
</head>
<body>
<h1>Add customer to booking</h1>
<hr/>

<c:choose>
    <c:when test="${message.error}">
        <p><c:out value="${message.message}"/></p>
    </c:when>

    <c:when test="${empty customerList}">
        <form method="POST" action="<c:url value="/booking/add-customer"/>">
            <label for="personId">Customer Id: </label> <!-- maybe should ask for name and surname -->
            <input name="personId" type="text"/><br/><br/>
            <label for="bookingId">Booking Id: </label>
            <input name="bookingId" type="text"/><br/><br/>
            <button type="submit">Add</button>
        </form>
    </c:when>

    <c:otherwise>
        <p>Customer "${addedCustomer.name} ${addedCustomer.surname}" has been added correctly</p>
        <hr/>

        <p>List of guests registered to booking "${bookingId}"</p><br><br/>
        <table>
            <thead>
            <tr>
                <th>Name</th><th>Surname</th><th>Id</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="customer" items="${customerList}">
                <tr>
                    <td><c:out value="${customer.name}"/></td>
                    <td><c:out value="${customer.surname}"/></td>
                    <td><c:out value="${customer.customerId}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table><br><br/>

        <a href="${pageContext.request.contextPath}/user-area/add-customer-booking.jsp">Add new guest</a><br><br/>
        <a href="${pageContext.request.contextPath}/user-area/users_homepage.jsp">Back to Home page</a><br><br/>
    </c:otherwise>
</c:choose>

</body>
</html>

