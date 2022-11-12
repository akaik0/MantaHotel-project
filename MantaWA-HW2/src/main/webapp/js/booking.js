function searchBooking(){
    var url = new URL('http://localhost:8080/MantaWA-HW2-1.0/rest/booking/'+
        document.getElementById("bookingId").value);
    genericGETRequest(url, fillSearchArea);

}

function searchBookingCustomer(){
    var url = new URL('http://localhost:8080/MantaWA-HW2-1.0/rest/booking/customer/'+
        document.getElementById("customerId").value);
    genericGETRequest(url, fillSearchCustomer);
}

function searchBookingDate(){
    var url = new URL('http://localhost:8080/MantaWA-HW2-1.0/rest/booking/date/'+
        document.getElementById("date").value);
    genericGETRequest(url, fillSearchDate);
}

function searchNotPaid(){
    var url = new URL('http://localhost:8080/MantaWA-HW2-1.0/rest/booking/notpaid/');
    genericGETRequest(url, fillSearchNotPaid);
}

function fillSearchArea(response){

    if (response.readyState === XMLHttpRequest.DONE) {

        if (response.status === 200) {
            var jsonData = JSON.parse(response.responseText);
            var data = jsonData["booking"];
            if (data.length === 0) {
                document.getElementById("searched-booking").innerHTML = "No booking found.";
            } else {
                content = "<table>";
                content += "<thead><tr><th>Booking</th><th>customer</th><th>checkin</th><th>checkout</th><th>payment</th>" +
                    "<th>date</th><th>requests</th></tr></thead>";
                content += "<tbody><tr><td>" + sanitize(data["bookingId"]) + "</td>";
                content += "<td>" + sanitize(data["personId"]) + "</td>";
                content += "<td>" + sanitize(data["checkin"]) + "</td>";
                content += "<td>" + sanitize(data["checkout"]) + "</td>";
                content += "<td onclick='redirectPayment(&quot;"+data["paymentId"]+ "&quot;)'>"
                    + sanitize(data["paymentId"]) + "</td>";
                content += "<td>" + sanitize(data["date"]) + "</td>";
                content += "<td>" + sanitize(data["requests"]) + "</td></tr></tbody>"
                content += "</table>";
                document.getElementById("searched-booking").innerHTML = content;

            }
        }
    }
}


function fillSearchCustomer(response){

    if (response.readyState === XMLHttpRequest.DONE) {

        if (response.status === 200) {
            var jsonData = JSON.parse(response.responseText);
            var data = jsonData["resource-list"];
            if (data.length === 0) {
                document.getElementById("customer-booking").innerHTML = "No booking found.";
            } else {
                content = "<table>";
                content += "<thead><tr><th>Booking</th><th>checkin</th><th>checkout</th><th>payment</th>" +
                    "<th>requests</th></tr></thead>";
                for (let i=0; i<data.length; i++) {
                    content += "<tbody><tr><td>" + sanitize(data[i]["booking"]["bookingId"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["booking"]["checkin"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["booking"]["checkout"]) + "</td>";
                    content += "<td onclick='redirectPayment(&quot;"+data[i]["booking"]["paymentId"]+ "&quot;)'>"
                        + sanitize(data[i]["booking"]["paymentId"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["booking"]["requests"]) + "</td></tr></tbody>"
                }
                content += "</table>";
                document.getElementById("customer-booking").innerHTML = content;

            }
        }
    }
}

function fillSearchDate(response){

    if (response.readyState === XMLHttpRequest.DONE) {

        if (response.status === 200) {
            var jsonData = JSON.parse(response.responseText);
            var data = jsonData["resource-list"];
            if (data.length === 0) {
                document.getElementById("date-booking").innerHTML = "No booking found.";
            } else {
                content = "<table>";
                content += "<thead><tr><th>Booking</th><th>customer</th><th>checkin</th><th>checkout</th><th>payment</th>" +
                    "<th>requests</th></tr></thead>";
                for (let i=0; i<data.length; i++) {
                    content += "<tbody><tr><td>" + sanitize(data[i]["booking"]["bookingId"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["booking"]["personId"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["booking"]["checkin"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["booking"]["checkout"]) + "</td>";
                    content += "<td onclick='redirectPayment(&quot;"+data[i]["booking"]["paymentId"]+ "&quot;)'>"
                        + sanitize(data[i]["booking"]["paymentId"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["booking"]["requests"]) + "</td></tr></tbody>"
                }
                content += "</table>";
                document.getElementById("date-booking").innerHTML = content;

            }
        }
    }
}

function redirectPayment(uuid){
    document.location.href = "http://localhost:8080/MantaWA-HW2-1.0/payment/searchID?paymentid="+uuid.toString();
}
