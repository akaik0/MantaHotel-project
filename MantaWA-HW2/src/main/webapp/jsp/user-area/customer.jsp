<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Customers info</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-admin.css">

    <script src="${pageContext.request.contextPath}/js/utils.js"></script>
    <script src="${pageContext.request.contextPath}/js/customer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
</head>

<body>

<div id="header"><c:import url="../reusable/header.jsp"/></div>

<!-- displaying error messages -->
<c:if test="${message.error}">
    <p class="alert alert-danger text-center font-serif"><c:out value="${message.message}"/></p>
</c:if>
<div id="error"></div>

<div class="container">
    <div class="row">
        <h3 class="font-serif" style="font-size: 1.875em!important; color: #7A2826">Search customer</h3>
    </div>
    <div class="row">
        <div class="d-flex justify-content-between">
            <label for="nameandsur" style="color: #965452">Name and Surname: </label>
            <input id="nameandsur" type="text" class="form-control"/><br/><br/>
        </div>
    </div>
    <div class="row">
        <button class="btn btn-info" id="search">Search</button><br/>
    </div>

    <div class="row">
        <div id="result"></div>
    </div>

    <hr/>

    <div class="row">
        <h3 class="font-serif" style="font-size: 1.875em!important; color: #7A2826">Customer's list</h3>
    </div>
    <div id="list" class="container"></div>
</div>

<script>
    $("#list").ready(listCustomer());
</script>

<div id="footer-admin"> </div>

</body>
</html>


