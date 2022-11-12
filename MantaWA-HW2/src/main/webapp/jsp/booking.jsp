<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Search Booking Form</title>

  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css ">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bookingHomepage.css ">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
  <script src="${pageContext.request.contextPath}/js/utils.js"></script>
  <script src="${pageContext.request.contextPath}/js/booking.js"></script>
</head>

<body>



  <div class="bookingHomepage">
    <!-- Header -->
    <div id="header"><c:import url="reusable/header.jsp"/></div>

    <!-- Second header -->

    <c:if test="${empty sessionScope.email || empty sessionScope.role}">
      <div class="static top-0 flex w-full items-center justify-between text-primary" style="background-color: rgba(28,13,15,0.68)">
        <p style="margin: 5px 10%; color:white;">You are not logged in!</p>
      </div>
    </c:if>

    <c:choose>
      <c:when test="${!empty sessionScope.email && !empty sessionScope.role}">

        <div class="column">

          <form action="javascript:" onsubmit="searchBooking(this)">
            <div class="search">
              <h2>Search Booking by Id:</h2>
              <div class="buttonRight">
                <input id="bookingId" placeholder="Booking ID" type="text"/><br/><br/>
                <button type="submit" style="width:auto; float:left;">Search</button><br/>
              </div>
            </div>
            <div class="tableContainer">
              <div id="searched-booking"></div>
            </div>
          </form>

          <hr/>

          <form action="javascript:" onsubmit="searchBookingCustomer(this)">
            <div class="search">
              <h2>Search Bookings by Customer:</h2>
              <div class="buttonRight">
                <input id="customerId" placeholder="Customer ID"type="text"/><br/><br/>
                <button type="submit" style="width:auto; float:left;">Search</button><br/>
              </div>
            </div>
            <span id="customer-booking"></span>
          </form>

          <hr/>

          <form action="javascript:" onsubmit="searchBookingDate(this)">
            <div class="search">
              <h2>Search Bookings by Date:</h2>
              <div class="buttonRight">
                <input id="date" placeholder="Date" type="date"/><br/><br/>
                <button type="submit" style="width:auto; float:left;">Search</button><br/>
              </div>
            </div>
              <span id="date-booking"></span>
          </form>

          <br><br/>

          <!--
          <a href="${pageContext.request.contextPath}/jsp/homepage.jsp" class="link">Back to the homepage</a><br><br/>
          -->

        </div>
      </c:when>
      <c:otherwise>
        <div class="column">
          <h1 style="align-content: center">You're not logged in!</h1>
          <a href="${pageContext.request.contextPath}/jsp/login.jsp" class="link">Login</a><br/>
        </div>
      </c:otherwise>
    </c:choose>
  </div>


  <div id="footer-admin" class="footer"> </div>

</body>
</html>


