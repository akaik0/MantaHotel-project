<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
       <meta charset="utf-8">
       <title>Insert new guest</title>
    </head>
    <body>

        <form method="POST" action="<c:url value="/customer/guest/"/>">
            <label for="email">email:</label>
            <input name="email" type="text"/><br/><br/>
            <label for="name">first name:</label>
            <input name="name" type="text"/><br/><br/>
            <label for="surname">last name:</label>
            <input name="surname" type="text"/><br/><br/>
            <label for="birthDate">birth date:</label>
            <input name="birthDate" type="text"/><br/><br/>
            <label for="documentNumber">document number:</label>
            <input name="documentNumber" type="text"/><br/><br/>
            <button type="submit">Submit</button><br/>

        </form>

        <c:choose>
            <c:when test="${message.error}">
                <p><c:out value="${message.message}"/></p>
            </c:when>
            <c:otherwise></c:otherwise>-
        </c:choose>
    </body>
</html>