<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Search Booking Form</title>
</head>

<body>
<h1>Bookings</h1>
<hr/>

<a href="${pageContext.request.contextPath}/jsp/insert-booking-form.jsp">Register new booking</a><br><br/>
<a href="${pageContext.request.contextPath}/jsp/user-area/add-customer-booking.jsp">Add guest to booking</a><br><br/>

<h2>Search Booking by Id</h2>

<form method="GET" action="<c:url value="/booking/search"/>">
  <label for="bookingId">Booking:</label>
  <input name="bookingId" type="text"/><br/><br/>
  <button type="submit">Search</button><br/>
</form>
<hr/>

<h2>Search Bookings by Customer</h2>

<form method="GET" action="<c:url value="/booking/list"/>">
  <label for="customer">Customer Id:</label>
  <input name="customer" type="text"/><br/><br/>
  <button type="submit">Search</button><br/>
</form>
<hr/>

<h2>Search Bookings by Date</h2>

<form method="GET" action="<c:url value="/booking/list/date"/>">
  <label for="date">Checkin or checkout date:</label>
  <input name="date" type="text"/><br/><br/>
  <button type="submit">Search</button><br/>
</form>
<hr/>
<h2>Get not payed bookings</h2>

<form method="GET" action="<c:url value="/booking/list/notpayed"/>">
  <button type="submit">Search</button><br/>
</form>
<hr/>
<br><br/>

<a href="${pageContext.request.contextPath}/jsp/user-area/users_homepage.jsp">go back to homepage</a><br><br/>

</body>
</html>


