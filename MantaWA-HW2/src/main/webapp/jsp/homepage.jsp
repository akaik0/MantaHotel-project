<%@ page contentType="text/html;charset=utf-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Manta Hotel</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css ">

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
</head>


<body>
  <div class="min-h-screen text-justify">
    <c:choose>
      <c:when test="${!empty sessionScope.email && empty sessionScope.role}">

        <div class="sticky top-0 flex w-full items-center justify-between bg-white/90 text-primary">
          <div class="logo">
            <a href="${pageContext.request.contextPath}/jsp/homepage.jsp">
              <img class="p-2" src="${pageContext.request.contextPath}/html/images/manta-logo.png" width="160px"
                alt="The logo of the Hotel Manta" />
            </a>
          </div>

          <div class="">
            <ul class="flex justify-end space-x-9 p-7">
              <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white"><a
                  href="${pageContext.request.contextPath}/jsp/homepage.jsp">ABOUT US</a></li>
              <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white"><a
                  href="${pageContext.request.contextPath}/room/">ROOMS</a></li>
              <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white"><a
                  href="${pageContext.request.contextPath}/jsp/insert-booking-form.jsp">BOOK</a></li>
              <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white"><a
                  href="${pageContext.request.contextPath}/booking/customer-list">MY BOOKINGS</a></li>
              <li>
                <p>You are logged as: "${sessionScope.email}" </p>
              <li class="flex w-20 justify-center border-transparent hover:bg-red hover:text-white"><a
                  href="${pageContext.request.contextPath}/customer/logout/">LOG OUT</a></li>
            </ul>
          </div>
        </div>
      </c:when>

      <c:otherwise>
        <c:choose>
          <c:when test="${!empty sessionScope.email && !empty sessionScope.role}">

            <div class="sticky top-0 flex w-full items-center justify-between bg-white/90 text-primary">
              <div class="logo">
                <a href="${pageContext.request.contextPath}/jsp/homepage.jsp">
                  <img class="p-2" src="${pageContext.request.contextPath}/html/images/manta-logo.png" width="160px"
                    alt="The logo of the Hotel Manta" />
                </a>
              </div>

              <div class="">
                <ul class="flex justify-end space-x-9 p-7">
                  <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white"><a
                      href="${pageContext.request.contextPath}/jsp/insert-booking-form.jsp">NEW BOOKING</a>
                  </li>

                  <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white"><a
                      href="${pageContext.request.contextPath}/jsp/user-area/add-customer-booking.jsp">INSERT GUEST</a>
                  </li>

                  <li class="flex w-20 justify-center border-transparent hover:bg-red hover:text-white">
                    <a href="${pageContext.request.contextPath}/jsp/user-area/payment.jsp">PAYMENTS</a>
                  </li>

                  <c:if test="${sessionScope.role.equals('Hotel Manager')}">
                    <li class="flex w-20 justify-center border-transparent hover:bg-red hover:text-white"><a
                        href="${pageContext.request.contextPath}/jsp/manager-area/user-list.jsp">USERS</a>
                    </li>
                    <li class="flex w-20 justify-center border-transparent hover:bg-red hover:text-white"><a
                        href="${pageContext.request.contextPath}/jsp/manager-area/view-logs.jsp">LOGS</a>
                    </li>
                  </c:if>

                  <c:choose>
                    <c:when test="${!empty sessionScope.email && !empty sessionScope.role}">
                      <li class="flex w-20 justify-center border-transparent hover:bg-red hover:text-white"><a
                          href="${pageContext.request.contextPath}/user/logout/">LOGOUT</a></li>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                  </c:choose>
                  </li>
                </ul>
              </div>
            </div>  
          </c:when>

          <c:otherwise>
            <div class="sticky top-0 flex w-full items-center justify-between bg-white/90 text-primary">
              <div class="logo">
                <a href="${pageContext.request.contextPath}/jsp/homepage.jsp">
                  <img class="p-2" src="${pageContext.request.contextPath}/html/images/manta-logo.png" width="160px"
                    alt="The logo of the Hotel Manta" />
                </a>
              </div>

              <div class="">
                <ul class="flex justify-end space-x-9 p-7">
                  <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white"><a
                      href="#aboutus">ABOUT US</a></li>
                  <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white"><a
                      href="${pageContext.request.contextPath}/rooms">ROOMS</a></li>
                  <li class="flex w-30 justify-center border-transparent hover:bg-red hover:text-white"><a
                      href="${pageContext.request.contextPath}/jsp/insert-booking-form.jsp">BOOK</a></li>
                  <li class="flex w-20 justify-center border-transparent hover:bg-red hover:text-white"><a
                      href="${pageContext.request.contextPath}/jsp/login.jsp">LOGIN</a></li>
                  <li class="flex w-24 justify-center border-transparent hover:bg-red hover:text-white"><a
                      href="${pageContext.request.contextPath}/jsp/customer-registration-form.jsp">REGISTER</a></li>
                </ul>
              </div>
            </div>
          </c:otherwise>

        </c:choose>
      </c:otherwise>

    </c:choose>


    <div class="content bg-gradient-to-b from-white to-sand" style="padding-right: 5px;">
      <div class="flex justify-around">
        <div class="ml-4 mr-7 w-1/3 self-center">
          <h1 class="font-serif text-6xl font-semibold">Let the waves caress you</h1>
          <br />
          <p>
            Best place where to enjoy the peace and relax. Our Hotel will make your holidays the most unfogettable
            and pleasant experience. With us you will be able to speriment the union between nature, elegance and
            progress. <br />
            we have introduced a suite of exclusive benefits to ensure that every aspect of your stay with us, from
            planning to in-room indulgence, is supremely comfortable, safe, and seamless.
          </p>
        </div>

        <div class="mt-4 mb-5"><img class="rounded-2xl"
            src="https://nousdesign.co.uk/wp-content/uploads/2017/07/1.-Setai-Hotel-Sea-of-Galilee_Reception_-by-Nous-Design.jpg"
            alt="Reception of the Manta Hotel" width="600px" /></div>
      </div>

      <div class="mt-20 flex justify-around" id="aboutus">
        <div class="mb-4"><img class="rounded-2xl"
            src="https://nousdesign.co.uk/wp-content/uploads/2017/07/2.Setai-Hotel-Sea-of-Galilee_Dining_-by-Nous-Design.jpg"
            width="600px" alt="image of the hotel restaurant" /></div>
        <div class="ml-4 w-1/3 self-center">
          <h2 class="center font-serif text-3xl font-bold">About Us</h2>
          <p>Manta Hotel offers unparalleled elegance and service which have allowed guests around the world to
            create exquisite memories. We want to show our commitment to provide a superlative guest experience we
            have introduced a suite of exclusive benefits to ensure that every aspect of your stay with us, from
            planning to in-room indulgence is supremely comfortable, safe, and seamless.</p>
        </div>
      </div>

      <div class="mt-20 flex justify-around">
        <div class="ml-4 w-1/3 self-center">
          <h2 class="font-serif text-3xl font-bold">Our Rooms</h2>
          <p>We offer a big variety of rooms. The aesthetics of the 125 rooms and suites are the epitome of
            industrial-chic. Nine categories of guest rooms are spread over five floors, with the top floor reserved
            for luxurious rooms and suites with their own terraces and views</p>
        </div>
        <div class="mb-4">
          <a href="${pageContext.request.contextPath}/rooms"><img class="rounded-2xl"
              src="https://nousdesign.co.uk/wp-content/uploads/2017/07/5.Setai-Hotel-Sea-of-Galilee_Bedroom_-by-Nous-Design.jpg"
              width="600px" /></a>
        </div>
      </div>

      <div class="mt-20 pb-9 flex justify-around">
        <div class="mb-4"><img class="rounded-2xl"
            src="https://nousdesign.co.uk/wp-content/uploads/2017/07/9.Setai-Hotel-Sea-of-Galilee_Drive_-by-Nous-Design.jpg"
            width="600px" alt="image of the hotel restaurant" /></div>
        <div class="ml-4 w-1/3 self-center">
          <h2 class="font-serif text-3xl font-bold">Important information</h2>
          <div class="mt-4 grid grid-cols-2 gap-x-9 gap-y-10">
            <div>
              <h4 class="text-xl">Address:</h4>
              <p class="text-sm">
                (732) 842-6083 <br />
                29 Richard Pl <br />
                Middletown, <br />
                New Jersey(NJ), 07748
              </p>
            </div>
            <div>
              <h4 class="text-xl">Check-in/ Check-out:</h4>
              <p class="inline font-semibold">Check-in:</p>
              <p class="inline">14:00-23:00</p>
              <br />
              <p class="inline font-semibold">Check-out:</p>
              <p class="inline">10:00-12:00</p>
            </div>
            <div>
              <h4 class="text-xl">Available sevices</h4>
              <p>
                Air conditioned hotel <br />
                Laundry service <br />
                Concierge service <br />
                Business center
              </p>
            </div>
            <div>
              <h4 class="text-xl">Pets</h4>
              Small dogs are allowed
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div id="footer"></div>

</body>

</html>