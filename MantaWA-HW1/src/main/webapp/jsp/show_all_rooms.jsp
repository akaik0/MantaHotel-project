<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Show All Rooms</title>
</head>

<body>
<h1>All Rooms</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/show-message.jsp"/>

<c:if test="${sessionScope.role.equals('Hotel Manager')}">
    <a href="${pageContext.request.contextPath}/jsp/manager-area/insert-room.jsp">Add new room</a><br><br/>
</c:if>

<c:if test='${not empty rooms}'>
    <table>
        <thead>
        <tr>
            <th>Room Number</th>
            <th>Beds</th>
            <th>Room Type</th>
            <th>Price</th>
            <th>Description</th>
        </tr>
        </thead>

        <tbody>

        <c:forEach items="${rooms}" var="r">
            <tr>
                    <td><c:out value="${r.roomNumber}"/></td>
                    <td><c:out value="${r.beds}"/></td>
                    <td><c:out value="${r.roomType}"/></td>
                    <td><c:out value="${r.price}"/></td>
                    <td><c:out value="${r.description}"/></td>
            </tr>
         </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>


