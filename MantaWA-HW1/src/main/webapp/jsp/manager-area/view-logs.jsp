<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>View Logs</title>
</head>

<body>
<h1>View Logs</h1>
<hr/>

<c:choose>
    <c:when test="${message.error}">
        <p><c:out value="${message.message}"/></p>
    </c:when>
    <c:when test='${empty logList}'>
        <form method="GET" action="<c:url value="/logs/day"/>">
            <button type="submit">View today's logs</button><br><br/>
        </form>
        <p>or insert specifications</p><br><br/>
        <form method="GET" action="<c:url value="/logs/user"/>">
            <label for="user">User email:</label>
            <input name="user" type="text"/><br/><br/>
            <label for="log_day">Day:</label>
            <input name="log_day" type="text"/><br/><br/>
            <button type="submit">View</button><br><br/>
        </form>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty logList}">
            <table>
                <thead>
                <tr>
                    <th>User</th><th>Login</th><th>Logout</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="log" items="${logList}">
                    <tr>
                        <td><c:out value="${log.email}"/></td>
                        <td><c:out value="${log.login}"/></td>
                        <td><c:out value="${log.logout}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:otherwise>
</c:choose>

</body>
</html>


