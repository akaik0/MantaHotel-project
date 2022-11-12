<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>List of Bookings</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="../css/booking.css">

    <script src="${pageContext.request.contextPath}/js/utils.js"></script>
    <script src="${pageContext.request.contextPath}/js/mybookings.js"></script>
</head>

<body>

<div id="header"><c:import url="reusable/header.jsp"/></div>



<c:choose>

    <c:when test="${message.error}">
        <p class="alert alert-danger text-center font-serif"><c:out value="${message.message}"/></p>
    </c:when>

    <c:otherwise>

        <div class="text-justify content bg-gradient-to-b from-white to-sand">

            <div id="error"></div>

            <c:if test="${success}">
                <p class="alert alert-success text-center font-serif">Booking inserted succesfully.</p>
            </c:if>

            <!-- display the found booking, if any -->
            <c:if test='${not empty bookingList}'>

                <div class="slideshow-container">
                    <c:forEach var="booking" items="${bookingList}">
                        <div class="mySlides">
                            <div class="booking-box">
                                <!--<div class="id"><c:out value="${booking.bookingid}"/></div>-->
                                <p>Checkin: <c:out value="${booking.checkin}"/></p>
                                <p>Checkout: <c:out value="${booking.checkout}"/></p>
                                <p>Payment: <span class="payment"><c:out value="${booking.paymentid}"/></span></p>
                                <c:if test="${not empty booking.requests}">
                                    <p>Requests: <c:out value="${booking.requests}"/></p>
                                </c:if>
                                <p>Made in date: <c:out value="${booking.date}"/></p>
                            </div>
                        </div>
                    </c:forEach>
                    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                    <a class="next" onclick="plusSlides(1)">&#10095;</a>
                </div>

                <!-- Dots/bullets/indicators -->
                <div class="dot-container">
                    <c:forEach var="categoryName" items="${bookingList}" varStatus="loop">
                        <span class="dot" onclick="currentSlide(${loop.index})"></span>
                        <!--<span class="dot"></span>-->
                    </c:forEach>
                </div>

            </c:if>
        </div>

    </c:otherwise>
</c:choose>


<div id="footer"><c:import url="reusable/footer.jsp"/></div>

</body>
</html>

