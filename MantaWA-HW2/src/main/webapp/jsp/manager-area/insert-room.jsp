<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Insert room</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/insert_room.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css ">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-admin.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</head>
<body>

<div id="header"><c:import url="../reusable/header.jsp"/></div>

<div>
    <div id="container" class="container">
        <div class="text-center">

            <h1 class="ml-5 header-margin">Insert new room</h1>
            <hr/>

            <c:choose>
                <c:when test="${message.error}">
                    <p><c:out value="${message.message}"/></p>
                </c:when>

                <c:when test="${not empty newRoom}">
                    <div class="alert alert-success" role="alert">
                        Room "${newRoom.roomNumber}" inserted correctly
                    </div>
                </c:when>

                <c:otherwise>
                    <form method="POST" action="/MantaWA-HW2-1.0/room/insert" class="ml-5 mt-2 mb-3">
                        <div class="form-group">
                            <label for="roomNumber">Room number</label>
                            <input type="text" class="form-control col-md-6 center-inputs" name="roomNumber">
                        </div>
                        <div class="form-group">
                            <label for="beds">Number of beds </label>
                            <input name="beds" type="text" class="form-control col-md-6 center-inputs">
                        </div>
                        <div class="form-group">
                            <label for="price">Price </label>
                            <input name="price" type="text" class="form-control col-md-6 center-inputs">
                        </div>
                        <div class="form-group">
                            <label for="type">type of room </label>
                            <select name="type" class="form-control col-md-6 center-inputs">
                                <option value="Single">Single</option>
                                <option value="Double">Double</option>
                                <option value="Triple">Triple</option>
                                <option value="Quad">Quad</option>
                                <option value="Twin">Twin</option>
                                <option value="Suite">Suite</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="description">description </label>
                            <input name="description" type="text" class="form-control col-md-6 center-inputs">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="form-control col-md-6 center-inputs">Submit</button><br>
                            <button type="reset" class="form-control center-inputs col-md-6 btn btn-dark resetButton">Reset the form</button>
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<div id="footer-admin" class="footer" > </div>
</body>
</html>

