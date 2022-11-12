<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>View Logs</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css ">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-list.css ">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <style>
        @media only screen and (min-width: 1200px) {
            .footer-logs{
                        position: fixed;
                        bottom: 0;
                        width:100%;
                    }
        }
    </style>

</head>

<body>

<div id="header"><c:import url="../reusable/header.jsp"/></div>

<div>
    <div id="container" class="container">
        <div class="text-center">
            <h1 class="ml-5 mt-2 mb-2 header-margin">View Logs</h1>
            <hr/>

            <c:choose>
            <c:when test="${message.error}">
                <div class="alert alert-danger mb-2 ml-5 mt-2" role="alert">
                    <p><c:out value="${message.message}"/></p>
                </div>
            </c:when>
            <c:when test='${empty logList}'>
            <form method="GET" action="<c:url value="/logs/day"/>" class="ml-5 mb-3 mt-2">
                <button type="submit" class="btn col-md-6">Today's Logs </button>
            </form>
            <hr/>
            <h3 class="ml-5 mt-2 mb-2">Search Logs</h1>
                <form method="GET" action="<c:url value="/logs/user"/>" class="ml-5 mb-3 mt-2">
                    <div class="form-group">
                        <label for="user">User Email</label>
                        <input type="text" class="form-control col-md-6 center-inputs" name="user">
                    </div>
                    <div class="form-group">
                        <label for="log_day">Date</label>
                        <input type="date" class="form-control col-md-6 center-inputs" name="log_day">
                    </div>
                    <button type="submit" class="form-control col-md-6 center-inputs">View</button>
                </form>
                </c:when>
                <c:otherwise>
                <c:if test="${not empty logList}">
                <table class="table table-striped ml-5 mt-2 mb-2">
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

        </div>
    </div>
</div>

<div id="footer-admin" class="footer footer-logs">

</div>
</body>
</html>


