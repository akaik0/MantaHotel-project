<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Header</title>


  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css">

</head>
<body>


<div class="sticky top-0 flex w-full items-center justify-between bg-white/90 text-primary">
  <div class="logo">
    <c:if test="${empty sessionScope.role}">
      <a href="http://localhost:8080/MantaWA-HW2-1.0/jsp/homepage.jsp">
        <img class="p-2" src="http://localhost:8080/MantaWA-HW2-1.0/html/images/manta-logo.jpg"
             alt="The logo of the Hotel Manta"
             width="160px">
      </a>
    </c:if>

    <c:if test="${not empty sessionScope.role}">
      <a href="http://localhost:8080/MantaWA-HW2-1.0/jsp/booking.jsp">
        <img class="p-2" alt="The logo of the Hotel Manta"
             src="http://localhost:8080/MantaWA-HW2-1.0/html/images/manta-logo.jpg"
             width="160px">
      </a>
    </c:if>
  </div>

  <c:if test="${empty sessionScope.role}">
    <div style="color: rgb(122 40 38 / var(--tw-text-opacity))">
      <ul class="flex justify-end space-x-9 p-7">
        <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white">
          <a href="${pageContext.request.contextPath}/rooms">ROOMS</a></li>
        <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white">
          <a href="${pageContext.request.contextPath}/jsp/insert-booking-form.jsp">BOOK</a></li>
        <c:if test="${empty sessionScope.email}">
          <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white">
            <a href="${pageContext.request.contextPath}/jsp/login.jsp">LOGIN</a></li>
          <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white">
            <a href="${pageContext.request.contextPath}/jsp/customer-registration-form.jsp">REGISTER</a></li>
        </c:if>
        <c:if test="${not empty sessionScope.email}">
          <c:if test="${empty sessionScope.role}">
            <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white">
              <a href="${pageContext.request.contextPath}/booking/customer-list">MY BOOKINGS</a></li>
          </c:if>
          <li><p>You are logged as: "${sessionScope.email}" </p></li>
          <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white"><a
                  href="${pageContext.request.contextPath}/customer/logout/">LOG OUT</a></li>
        </c:if>
      </ul>
    </div>
  </c:if>

  <c:if test="${not empty sessionScope.role}">
    <div style="color: rgb(122 40 38 / var(--tw-text-opacity))">
      <ul class="flex justify-end space-x-9 p-7">

        <c:if test="${sessionScope.role.equals('Front Office')}">
          <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white nav-pad">
            <a href="${pageContext.request.contextPath}/rooms">ROOMS</a></li>
        </c:if>

        <li class="nav-item dropdown flex w-30 justify-center border-transparent hover:bg-red hover:text-white">
          <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            BOOKING
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/insert-booking-form.jsp">Add new Booking</a>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/booking.jsp">Manage Bookings</a>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/user-area/add-customer-booking.jsp">Add guest to Booking</a>
          </div>
        </li>

        <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white nav-pad">
          <a href="${pageContext.request.contextPath}/jsp/user-area/customer.jsp">CUSTOMERS</a></li>

        <c:if test="${sessionScope.role.equals('Hotel Manager')}">
          <li class="nav-item dropdown flex w-30 justify-center border-transparent hover:bg-red hover:text-white">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              ROOMS
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="${pageContext.request.contextPath}/rooms">Room List</a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/manager-area/insert-room.jsp">Add Room</a>
            </div>
          </li>

          <li class="nav-item dropdown flex w-30 justify-center border-transparent hover:bg-red hover:text-white">
            <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              USERS
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/manager-area/user-list.jsp">User List</a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/manager-area/user-registration-form.jsp">Create new User</a>
            </div>
          </li>

          <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white nav-pad">
            <a href="${pageContext.request.contextPath}/jsp/manager-area/view-logs.jsp">LOGS</a></li>
        </c:if>

        <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white nav-pad">
          <a href="${pageContext.request.contextPath}/jsp/user-area/user-insert-customer.jsp">REGISTER GUEST</a></li>
        <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white nav-pad">
          <a href="${pageContext.request.contextPath}/jsp/user-area/payment.jsp">PAYMENTS</a></li>

        <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white nav-pad"><a
                href="${pageContext.request.contextPath}/customer/logout/">LOG OUT</a></li>

      </ul>

    </div>
  </c:if>

</div>

  <c:if test="${!empty sessionScope.email && !empty sessionScope.role}">
    <div class="static top-0 flex w-full items-center justify-between text-primary" style="background-color: rgba(28,13,15,0.68)">
      <hr/>
      <h1 class="admin-header">Admin Area</h1>
      <hr/>
      <p style="margin: 5px 10%; color:white;">You are logged in with the email: ${sessionScope.email}</p>
    </div>
  </c:if>

</body>
</html>
