<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Manta Hotel</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css ">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css ">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
</head>
<body>

<div id="header"><c:import url="reusable/header.jsp"/></div>

<div class="min-h-screen text-justify">

    <div class="login">
        <h1 style="font-size: 1.875rem;">Register </h1><br>

        <form method="POST" action="<c:url value="/customer/register/"/>">
            <label for="email">Email</label></br>
            <input name="email" type="text" class="form-control"/><br/><br/>
            <label for="name">First Name</label></br>
            <input name="name" type="text" class="form-control"/><br/><br/>
            <label for="surname">Last Name</label></br>
            <input name="surname" type="text" class="form-control"/><br/><br/>
            <label for="password">Password</label></br>
            <input name="password" type="password" class="form-control"/><br/><br/>
            <label for="rpassword">Repeat Password</label></br>
            <input name="rpassword" type="password" class="form-control"/><br/><br/>
            <label for="birthDate">Birth Date</label></br>
            <input name="birthDate" type="date" class="form-control"/><br/><br/>
            <label for="phoneNumber">Phone Number</label></br>
            <input name="phoneNumber" type="tel" pattern="[0 -9]{10}$" required class="form-control"/><br/><br/>
            <button type="submit">Submit</button>
            <br/>
        </form>
        <br>
        <p>Already have an account? Login <a href="${pageContext.request.contextPath}/jsp/login.jsp"
                                                                class="link">here</a></p>
        <a href="${pageContext.request.contextPath}/jsp/homepage.jsp" class="link">Back to Home Page</a><br/>
    </div>

    <div id="footer"></div>

</body>
</html>