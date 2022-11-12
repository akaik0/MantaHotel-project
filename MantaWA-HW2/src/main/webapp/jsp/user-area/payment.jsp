<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payments</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-payments.css ">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tables.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">

    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/utils.js"></script>
    <script src="${pageContext.request.contextPath}/js/payment.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>

</head>

<body>
    <div class=" min-h-screen text-justify bg-white ">

        <div id="header">
            <c:import url="/jsp/reusable/header.jsp" />
        </div>


        <div class="content">
            <c:choose>
                <c:when test="${message.error}">
                    <p>
                        <c:out value="${message.message}" />
                    </p>
                </c:when>

                <c:otherwise>
                    <div class=" justify-items-center grid m-28 ">
                        <div
                            class=" text-center mb-10 min-w-fit w-96 grid-rows-3 drop-shadow-xl shadow border-primary border  text-lg">
                            <h2 class="text-xl py-3">Edit Payment</h2>

                            <form class="grid grid-rows-3 text-center" method="GET" action=" <c:url value="
                                http://localhost:8080/MantaWA-HW2-1.0/payment/searchID" /> ">
                            <div><label class="row-start-1 " for="paymentid">Insert payment ID: </label></div>
                            <div><input class="border border-sand hover:border-red" name="paymentid" type="text" />
                            </div>
                            <div><button class="mb-2  w-52 h-8  bg-primary hover:bg-red text-white"
                                    type="submit">Submit</button></div>
                            </form>
                        </div>

                        <div
                            class=" text-center mb-6 min-w-fit w-96 grid-rows-3 drop-shadow-xl shadow border-primary border text-lg">
                            <h2 class="text-xl py-3">Search Payment by Booking</h2>

                            <form class="grid grid-rows-3 text-center" action="javascript:"
                                onsubmit="searchPaymentBooking(this)">
                                <div><label class="row-start-1 " for="personId">Insert booking ID:</label></div>
                                <div><input class="border-sand border hover:border-red" id="bookingId" type="text" />
                                </div>
                                <div><button class=" mb-2 w-52 h-8 bg-primary hover:bg-red text-white"
                                        type="submit">Search</button></div>
                                <span id="list-payments" class="w-4/5 overflow-y-auto"></span>
                            </form>
                        </div>

                        <div
                            class=" text-center mb-6 min-w-fit w-96 grid-rows-3 drop-shadow-xl shadow border-primary border text-lg">
                            <h2 class="text-xl py-3">Search Payment by Customer</h2>

                            <form class="grid grid-rows-3 text-center" action="javascript:"
                                onsubmit="listPayments(this)">
                                <div><label class="row-start-1 " for="personId">Insert customer ID:</label></div>
                                <div><input class="border-sand border hover:border-red" id="personId" type="text" />
                                </div>
                                <div><button class=" mb-2 w-52 h-8 bg-primary hover:bg-red text-white"
                                        type="submit">Search</button></div>
                                <span id="list-payments" class="w-4/5 overflow-y-auto"></span>
                            </form>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div id="footer-admin"> </div>
    </div>

    </div>
    
    <div id="footer-admin" class="footer"> </div>
</body>

</html>