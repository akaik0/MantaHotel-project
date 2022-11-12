<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Insert room</title>
</head>
<body>
<h1>Insert new room</h1>
<hr/>

<c:choose>
    <c:when test="${message.error}">
        <p><c:out value="${message.message}"/></p>
    </c:when>

    <c:when test="${not empty newRoom}">
        <p>Room "${newRoom.roomNumber}" inserted correctly</p><br><br/>
        <a href="${pageContext.request.contextPath}/user-area/users_homepage.jsp">go back to homepage</a><br/>
    </c:when>

    <c:otherwise>
        <form method="POST" action="<c:url value="/room/insert"/>">
            <label for="roomNumber">Room number: </label>
            <input name="roomNumber" type="text"/><br/><br/>
            <label for="beds">number of beds: </label>
            <input name="beds" type="text"/><br/><br/>
            <label for="price">price per night: </label>
            <input name="price" type="text"/><br/><br/>
            <label for="type">type of room: </label>
            <select name="type" type="text">
                <option value="Single">Single</option>
                <option value="Double">Double</option>
                <option value="Triple">Triple</option>
                <option value="Quad">Quad</option>
                <option value="Twin">Twin</option>
                <option value="Suite">Suite</option>
            </select><br/><br/>
            <label for="description">description: </label>
            <input name="description" type="text"/><br/><br/>
            <button type="submit">Submit</button><br/>
            <button type="reset">Reset the form</button>
        </form>
    </c:otherwise>
</c:choose>

</body>
</html>

