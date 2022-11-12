<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Users list</title>
</head>
<body>
<h1>Users List</h1>

    <a href="${pageContext.request.contextPath}/jsp/manager-area/user-registration-form.jsp">Register a new user</a><br><br/>

    <c:choose>
        <c:when test='${empty userList}'>
            <form method="GET" action="<c:url value="/user/userList/"/>">
                <button type="submit">List of registered users</button><br><br/>
            </form>
            <hr/>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                <tr>
                    <th>Email</th><th>Password</th><th>Name</th><th>Username</th><th>Role</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.password}"/></td>
                        <td><c:out value="${user.first_name}"/></td>
                        <td><c:out value="${user.last_name}"/></td>
                        <td><c:out value="${user.role}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test='${empty updatedUser.email}'>
            <p>Update a user role:</p>
            <form method="POST" action="<c:url value="/user/update/"/>">
                <label for="email">email:</label>
                <input name="email" type="text"/><br/><br/>
                <label for="first_name">first name:</label>
                <input name="first_name" type="text"/><br/><br/>
                <label for="last_name">last name:</label>
                <input name="last_name" type="text"/><br/><br/>
                <label for="role">choose a new role:</label>
                <select name="role" type="text">
                    <option value="Front Office">Front Office</option>
                    <option value="Hotel Manager">Hotel Manager</option>
                </select><br/><br/>
                <button type="submit">Submit</button><br/>
            </form>
        </c:when>
        <c:otherwise>
            <p>User "${requestScope.updatedUser.getEmail()}" updated </p>
            <p>New role "${updatedUser.getRole()}"</p><br><br/>
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}/user-area/users_homepage.jsp">Back to Home page</a><br><br/>

</body>
</html>