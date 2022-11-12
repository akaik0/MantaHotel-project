<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
       <meta charset="utf-8">
       <title>login</title>
    </head>
    <body>

    <h1>Login page</h1>
    <hr/>

    <c:choose>
        <c:when test="${empty sessionScope.email}">

            <form method="POST" action="<c:url value="/customer/login/"/>">
                <label for="email">email:</label>
                <input name="email" type="text"/><br/><br/>
                <label for="password">password:</label>
                <input name="password" type="password"/><br/><br/>
                <button type="submit">Submit</button><br/>
            </form>
            <p>If you don't have an account you should register here</p>

            <a href="${pageContext.request.contextPath}/jsp/customer-registration-form.jsp">registration page</a><br/>
            <a href="${pageContext.request.contextPath}/jsp/homepage.jsp">Back to Home page</a><br/>

        </c:when>
        <c:otherwise> <p>You are logged in with the email "${sessionScope.email}"</p>
            <a href="${pageContext.request.contextPath}/customer/logout/">Logout</a><br/>
            <a href="${pageContext.request.contextPath}/jsp/homepage.jsp">Get back to the homepage</a><br/>
        </c:otherwise>
    </c:choose>

    </body>
</html>