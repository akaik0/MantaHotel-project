<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${message.error}">
        <ul>
            <li>message: <c:out value="${message.message}"/></li>
        </ul>
    </c:when>

    <c:otherwise>
        <p><c:out value="${message.message}"/></p>
    </c:otherwise>
</c:choose>
