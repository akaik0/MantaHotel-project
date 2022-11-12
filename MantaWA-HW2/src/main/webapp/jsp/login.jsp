<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
       <meta charset="utf-8">
       <title>Login</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


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

        <c:if test="${message.error}">
        <p class="alert alert-danger text-center font-serif"><c:out value="${message.message}"/></p>
        </c:if>

    <c:choose>
        <c:when test="${empty sessionScope.email}">

        <div class="login">

            <h1>Log in</h1><br>

            <form method="POST" action="<c:url value="/customer/login/"/>">
                <label for="email">Email / Username</label><br/>
                <input name="email" type="text"/><br/><br/>
                <label for="password">Password </label><br/>
                <input name="password" type="password"/><br/><br/>
                <button type="submit">Submit</button><br/>
            </form>
            <br>
            <div id="linkToHomepage">
                <p>If you don't have an account you can register <a href="${pageContext.request.contextPath}/jsp/customer-registration-form.jsp" class="link">here:</a></p>
                <a href="${pageContext.request.contextPath}/jsp/homepage.jsp" class="link" >Back to Home page</a>
                <br/>
            </div>


        </div>

        </c:when>
        <c:otherwise>
        <div class="alreadyloggedin">

            <h1>You are logged in with the email: "${sessionScope.email}"</h1><br>

            <a href="${pageContext.request.contextPath}/customer/logout/">Log out</a><br/>
            <a href="${pageContext.request.contextPath}/jsp/homepage.jsp">Get back to the homepage</a><br/>
        </div>
        </c:otherwise>
    </c:choose>

        <div id="footer"></div>

    </body>
</html>