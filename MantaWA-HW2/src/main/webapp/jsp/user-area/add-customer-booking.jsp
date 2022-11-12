<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add customer to booking</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/utils.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/add-cust-booking.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-payments.css ">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-admin.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
</head>
<body>
<c:import url="/jsp/reusable/header.jsp"/>

<div class="container py-5 px-5 ">

<h1 class="text-xl p-2 border-bottom text-center">ADD CUSTOMER TO BOOKING</h1>

<c:choose>
    <c:when test="${message.error}">
        <p><c:out value="${message.message}"/></p>
    </c:when>

    <c:when test="${empty customerList}">
        <form class="form-horizontal" method="POST" action="<c:url value="/booking/add-customer"/>">
            <div class="form-group">
                <label class="control-label col-sm-2" for="personId">CustomerID: </label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="personId" placeholder="Enter Customer Id" name="personId">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="bookingId">BookingID:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="bookingId" placeholder="Enter Booking Id" name="bookingId">
                </div>
                <div class="col-sm-10">
                    <button class="add mb-2 w-52 h-8 bg-primary text-white" type="submit">ADD</button>
                </div>
            </div>

    </c:when>

    <c:otherwise>
        <h2 class="text-xl mt-4 py-3 text-center">List of guests registered to booking "${bookingId}"</h2>
            <div class="table-responsive-sm my-3">
                <table class="listCust table table-hover">
                    <caption class="text-secondary">Customer has been added correctly<hr></caption>
                    <thead class="bg-primary text-white">
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Surname</th>
                        <th scope="col">Id</th>
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
                </table>
            </div>
            <div class="col-md-12 text-center">
                <a href="${pageContext.request.contextPath}/jsp/user-area/add-customer-booking.jsp"><button class="add mb-2 w-52 h-8 bg-primary text-white" type="submit">Add new guest</button></a>
            </div>
        </c:otherwise>

</c:choose>
<div id="footer-admin" class="footer" > </div>
</body>
</html>

