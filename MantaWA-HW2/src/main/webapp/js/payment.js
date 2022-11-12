function searchPaymentBooking(){
    var url = new URL('http://localhost:8080/MantaWA-HW2-1.0/rest/payment/booking/'+
        document.getElementById("bookingId").value);
    genericGETRequest(url, fillSearchBooking);
}

function listPayments(){
    var url = new URL('http://localhost:8080/MantaWA-HW2-1.0/rest/payment/customer/'+
        document.getElementById("personId").value);
    genericGETRequest(url, fillSearchCustomer);
}



function fillSearchBooking(response){

    if (response.readyState === XMLHttpRequest.DONE) {

        if (response.status === 200) {
            var jsonData = JSON.parse(response.responseText);
            var data = jsonData["payment"];
            if (data.length === 0) {
                document.getElementById("searched-payment").innerHTML = "No payment found.";
            } else {
                content = "<table>";
                content += "<tr><th>Payment</th><th>method</th><th>amount</th><th>date</th><th>payed</th>" +
                    "<th>registered by</th></tr>";
                content += "<tr><td>" + sanitize(data["paymentId"]) + "</td>";
                content += "<td>" + sanitize(data["paymentMethod"]) + "</td>";
                content += "<td>" + sanitize(data["totalAmount"]) + "</td>";
                content += "<td>" + sanitize(data["date"]) + "</td>";
                content += "<td>" + sanitize(data["complete"]) + "</td>";
                content += "<td>" + sanitize(data["email"]) + "</td></tr>"
                content += "</table>";
                document.getElementById("searched-payment").innerHTML = content;

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
                document.getElementById("list-payments").innerHTML = "No payment found.";
            } else {
                content = "<table>";
                content += "<tr><th>Payment</th><th>method</th><th>amount</th><th>date</th><th>payed</th>" +
                    "<th>registered by</th></tr>";
                for (let i=0; i<data.length; i++) {
                    content += "<tr><td>" + sanitize(data[i]["payment"]["paymentId"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["payment"]["paymentMethod"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["payment"]["totalAmount"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["payment"]["date"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["payment"]["complete"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["payment"]["email"]) + "</td></tr>"
                }
                content += "</table>";
                document.getElementById("list-payments").innerHTML = content;

            }
        }
    }
}