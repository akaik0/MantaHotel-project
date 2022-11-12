<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Unauthorized</title>
</head>
<body>
You are not authorized to see the requested page.<br/>
<c:choose>
  <c:when test="${not empty sessionScope.role}">
    <a href="${pageContext.request.contextPath}/jsp/user-area/users_homepage.jsp">go back to the homepage</a>
  </c:when>
  <c:otherwise>
    <a href="${pageContext.request.contextPath}/jsp/homepage.jsp">go back to the homepage</a>
  </c:otherwise>
</c:choose>
</body>
</html>