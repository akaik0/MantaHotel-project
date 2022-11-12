<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Register a new user</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css ">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-list.css ">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>

<div id="header"><c:import url="../reusable/header.jsp"/></div>

<div>
    <div id="container" class="container">
        <div class="text-center">
            <h1 class="header-margin">User Registration</h1>

            <form method="POST" action="<c:url value="/user/register/"/>" class="ml-5 mt-2 mb-3">
                <div class="form-group">
                    <label for="first_name">First Name</label>
                    <input type="text" class="form-control col-md-6 center-inputs" name="first_name">
                </div>
                <div class="form-group">
                    <label for="last_name">Last Name</label>
                    <input type="text" class="form-control col-md-6 center-inputs" name="last_name">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control col-md-6 center-inputs" name="password">
                </div>
                <div class="form-group">
                    <label for="rpassword">Repeat Password</label>
                    <input type="password" class="form-control col-md-6 center-inputs" name="rpassword">
                </div>
                <div class="form-group">
                    <label for="role">Role</label>
                    <select name="role" type="text" class="form-control col-md-6 center-inputs">
                        <option value="Front Office">Front Office</option>
                        <option value="Hotel Manager">Hotel Manager</option>
                    </select>
                </div>
                <button type="submit" class="form-control col-md-6 center-inputs">Submit</button>

            </form>
        </div>
    </div>
</div>

<div id="footer-admin" class="footer" > </div>
</body>
</html>