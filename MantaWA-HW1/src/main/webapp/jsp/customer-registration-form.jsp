<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
       <meta charset="utf-8">
       <title>register</title>
    </head>
    <body>

        <form method="POST" action="<c:url value="/customer/register/"/>">
            <label for="email">email:</label>
            <input name="email" type="text"/><br/><br/>
            <label for="name">first name:</label>
            <input name="name" type="text"/><br/><br/>
            <label for="surname">last name:</label>
            <input name="surname" type="text"/><br/><br/>
            <label for="password">password:</label>
            <input name="password" type="password"/><br/><br/>
            <label for="rpassword">repeat password:</label>
            <input name="rpassword" type="password"/><br/><br/>
            <label for="birthDate">birthDate:</label>
            <input name="birthDate" type="date"/><br/><br/>
            <label for="phoneNumber">phoneNumber:</label>
            <input name="phoneNumber" type="tel" pattern="[0 -9]{10}$" required/><br/><br/>
            <button type="submit">Submit</button><br/>
        </form>
    </body>
</html>