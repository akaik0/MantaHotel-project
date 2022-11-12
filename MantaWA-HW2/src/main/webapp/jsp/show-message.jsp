<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Show Message</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/showmessage.css ">
</head>
<body>

<div class="message">

<c:choose>
    <c:when test="${message.error}">
        <ul>
            <li>message: <c:out value="${message.message}"/></li>
        </ul>
            <br><a href="${pageContext.request.contextPath}/jsp/homepage.jsp" class="link">Get back to the homepage</a><br/>
    </c:when>

    <c:otherwise>
        <p><c:out value="${message.message}"/></p>
            <br><a href="${pageContext.request.contextPath}/jsp/homepage.jsp" class="link">Get back to the homepage</a><br/>
    </c:otherwise>
</c:choose>
</div>

</body>
</html>