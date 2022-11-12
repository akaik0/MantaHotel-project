<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Register a new user</title>
</head>
<body>

<form method="POST" action="<c:url value="/user/register/"/>">
    <label for="first_name">first name:</label>
    <input name="first_name" type="text"/><br/><br/>
    <label for="last_name">last name:</label>
    <input name="last_name" type="text"/><br/><br/>
    <label for="password">password:</label>
    <input name="password" type="password"/><br/><br/>
    <label for="rpassword">repeat password:</label>
    <input name="rpassword" type="password"/><br/><br/>
    <select name="role" type="text">
        <option value="Front Office">Front Office</option>
        <option value="Hotel Manager">Hotel Manager</option>
    </select><br/><br/>
    <button type="submit">Submit</button><br/>

</form>
</body>
</html>