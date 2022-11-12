<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Select Room</title>
</head>
<body>
<h1>Select Room</h1>
<hr/>
<form method="POST" action="<c:url value="/booking/insert/complete"/>">
    <c:if test='${not empty roomList}'>
        <table>
            <thead>
            <tr>
                <th>Room</th><th>Beds</th><th>Price</th><th>Type</th><th>Description</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="room" items="${roomList}">
                <tr> <!-- on click -->
                    <td><c:out value="${room.roomNumber}"/></td>
                    <td><c:out value="${room.beds}"/></td>
                    <td><c:out value="${room.price}"/></td>
                    <td><c:out value="${room.roomType}"/></td>
                    <td><c:out value="${room.description}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <label for="room">Select Room:</label>
        <input name="room" type="text"/><br/><br/>
        <button type="submit">Submit</button><br/>
    </c:if>
</form>
</body>
</html>


