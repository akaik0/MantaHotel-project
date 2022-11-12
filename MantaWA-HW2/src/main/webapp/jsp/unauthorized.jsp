<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Unauthorized</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/unauthorized.css ">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css ">


  <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
</head>
<body>
<div class="min-h-screen text-justify">
    <div class="sticky top-0 flex w-full items-center justify-between bg-white/90 text-primary">
              <div class="logo">
                <a href="${pageContext.request.contextPath}/jsp/homepage.jsp">
                  <img class="p-2" src="${pageContext.request.contextPath}/html/images/manta-logo.png" width="160px" alt="The logo of the Hotel Manta"/>
                </a>
    </div>
    <div class="">
                <ul class="flex justify-end space-x-9 p-7">
                  <li><a href="${pageContext.request.contextPath}/jsp/homepage.jsp#aboutus">ABOUT US</a></li>
                  <li><a href="${pageContext.request.contextPath}/rooms">ROOMS</a></li>
                  <li><a href="${pageContext.request.contextPath}/jsp/insert-booking-form.jsp">BOOKING</a>
                    <c:choose>
                      <c:when test="${!empty sessionScope.email && empty sessionScope.role}">
                        <p>you are logged in with the email "${sessionScope.email}"</p>
                        <a href="${pageContext.request.contextPath}/booking/customer-list">My Bookings</a><br/>
                        <a href="${pageContext.request.contextPath}/customer/logout/">Logout</a><br/>
                      </c:when>
                      <c:otherwise>
                        <c:choose>
                          <c:when test="${!empty sessionScope.email && !empty sessionScope.role}">
                            <p>you are logged in with the email "${sessionScope.email}"</p>
                            <a href="${pageContext.request.contextPath}/user/logout/">Logout</a><br/>
                          </c:when>
                        </c:choose>
                      </c:otherwise>
                    </c:choose>
                  </li>
                  <li class="flex w-20 justify-center border-transparent hover:bg-red hover:text-white"><a href="${pageContext.request.contextPath}/jsp/login.jsp">LOGIN</a></li>
                  <li class="flex w-24 justify-center border-transparent hover:bg-red hover:text-white"><a href="${pageContext.request.contextPath}/jsp/customer-registration-form.jsp">REGISTER</a></li>
                </ul>
              </div>
            </div>
            <div id="message"> You are not authorized to see the requested page.<br/>
                <c:choose>
                  <c:when test="${not empty sessionScope.role}">
                    <a href="${pageContext.request.contextPath}/jsp/user-area/users_homepage.jsp" class="link">Go back to the homepage</a>
                  </c:when>
                  <c:otherwise>
                    <a href="${pageContext.request.contextPath}/jsp/homepage.jsp" class="link">Go back to the homepage</a>
                  </c:otherwise>
                </c:choose>
             </div>
</div>

<div id="footer"></div>
</body>
</html>