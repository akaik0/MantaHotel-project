<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Manta Hotel</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css ">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/roomTables.css ">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>

    <style>
        .nav-pad {
            padding-top: 8px;
        }

        #norooms {
            text-align: center;
        }

        <!--
        #addroom {
            background-color: #7A2826;
            padding: 10px;
            margin: 20px 0 0 ;
            resize: vertical;
            letter-spacing: 1px;
            color: white!important;
            cursor: pointer;
            text-decoration: none;
        }-->

    </style>
</head>


<body>
<div id="header"><c:import url="reusable/header.jsp"/></div>

<div class="min-h-screen text-justify">

    <c:if test='${not empty rooms}'>
        <div class="content bg-gradient-to-b from-white to-sand" style="padding-top:5rem; padding-bottom:5rem;padding-right: 5px;">
            <c:forEach items="${rooms}" var="r" varStatus="loop">

                <c:if test='${loop.count%2==0}'>
                    <div class="flex justify-around" style="margin-top: 5px">
                        <div class="ml-4 w-1/3 self-center" style="padding-bottom: 5px">
                            <h2 class="font-serif text-3xl font-bold">Room number:<c:out value="${r.roomNumber}"/></h2>
                            <p><c:out value="${r.description}"/></p>

                            <table class="roomsTable">
                                <thead>
                                <tr>
                                <th>Beds</th>
                                <th>Room Type</th>
                                <th>Price</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    <td><c:out value="${r.beds}"/></td>
                                    <td><c:out value="${r.roomType}"/></td>
                                    <td><c:out value="${r.price}"/></td>
                                    </tr>
                                </tbody>
                            </table>


                        </div>
                        <div class="mb-4">
                            <a href="${pageContext.request.contextPath}/rooms"><img class="rounded-2xl"
                                                                                    src="${pageContext.request.contextPath}/html/images/rooms/${loop.count}.png"
                                                                                    width="600px" /></a>
                        </div>
                    </div>
                </c:if>


                <c:if test='${loop.count%2==1}'>
                    <div class="flex justify-around" style="margin-top: 5px">

                        <div class="mb-4">
                            <a href="${pageContext.request.contextPath}/rooms"><img class="rounded-2xl"
                            src="${pageContext.request.contextPath}/html/images/rooms/${loop.count}.png"
                            width="600px" /></a>
                        </div>

                        <div class="ml-4 w-1/3 self-center">
                            <h2 class="font-serif text-3xl font-bold">Room number:<c:out value="${r.roomNumber}"/></h2>
                            <p><c:out value="${r.description}"/></p>

                            <table class="roomsTable">
                                <thead>
                                <tr>
                                <th>Beds</th>
                                <th>Room Type</th>
                                <th>Price</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                <td><c:out value="${r.beds}"/></td>
                                <td><c:out value="${r.roomType}"/></td>
                                <td><c:out value="${r.price}"/></td>
                                </tr>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </c:if>
            </c:forEach>

            <!--
            <c:if test="${sessionScope.role.equals('Hotel Manager')}">
                <a href="${pageContext.request.contextPath}/jsp/manager-area/insert-room.jsp" id="addroom">Add new room</a><br><br/>
            </c:if>
            -->
        </div>
    </c:if>

    <c:if test='${empty rooms}'>
        <div id="norooms" class="font-serif text-3xl font-bold"> There are no rooms to show</div>
    </c:if>

</div>

<div id="footer"></div>

</body>

</html>