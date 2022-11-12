<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Insert booking</title>
</head>
<body>

<div class="row">
    <div class="container">

        <c:if test="${empty sessionScope.personId}">
            <div class="d-flex justify-content-between">
                <label for="personId" style="color: #965452; width:100px;">Customer Id: </label>
                <input id="personId" type="text" class="form-control"/><br/><br/>
            </div>
        </c:if>

        <div class="d-flex justify-content-between">
            <label for="beds" style="color: #965452; width:100px;">Number of people: </label>
            <select id="beds" type="text" class="form-control">
                <option value="1">1 Adult</option>
                <option value="2">2 Adults</option>
                <option value="3">3 Adults</option>
            </select><br/><br/>
        </div>

        <div class="d-flex justify-content-between">
            <label for="checkin" style="color: #965452; width:100px;">Checkin: </label>
            <input id="checkin" type="datetime-local" class="form-control"/><br/><br/>
        </div>

        <div class="d-flex justify-content-between">
            <label for="checkout" style="color: #965452; width:100px;">Checkout: </label>
            <input id="checkout" type="datetime-local" class="form-control"/><br/><br/>
        </div>

        <div class="d-flex justify-content-between">
            <label for="paymentM" style="color: #965452; width:100px;">Payment Method: </label>
            <select id="paymentM" type="text" class="form-control">
                <option value="Visa">Visa</option>
                <option value="MasterCard">MasterCard</option>
                <option value="Maestro">Maestro</option>
                <option value="American Express">American Express</option>
                <option value="PayPal">PayPal</option>
                <option value="Cash">Cash</option>
            </select><br/><br/>
        </div>

        <button class="btn btn-info" id="rooms">See available rooms</button><br/>
    </div>

    <div class="container" style="padding-left: 10%;">
        <h2 class="font-serif text-5xl font-semibold" style="color: #861614; padding-left: 20%;">Rooms</h2>
        <div id="available-rooms"></div><br/>

        <label for="requests" style="color: #965452; padding-left: 20%">Requests: </label><br/>
        <textarea cols="30" rows="3" id="requests" name="text" class="form-control" style="color: #965452; margin-left: 20%"></textarea><br><br/>
    </div>
</div>

</body>
</html>
