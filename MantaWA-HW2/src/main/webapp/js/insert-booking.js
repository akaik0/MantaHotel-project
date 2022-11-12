// self executing function here
document.addEventListener('DOMContentLoaded', function(event) {
    // your page initialization code here
    // the DOM will be available here

    document.getElementById('rooms').addEventListener('click', getAvailableRoomList);
});

function getAvailableRoomList() {
    var url = new URL(contextPath+'/rest/room/list-available/');
    url += document.getElementById("beds").value + "/" +
        document.getElementById("checkin").value + "/" +
        document.getElementById("checkout").value;
    genericGETRequest(url, fillAvailableRoomSelector);
}

function fillAvailableRoomSelector(req){
    if (req.readyState === XMLHttpRequest.DONE) {
        if (req.status == 200) {
            var jsonData = JSON.parse(req.responseText);
            var data = jsonData['resource-list'];
            if (data.length == 0) {
                document.getElementById("available-rooms").innerHTML =
                    "<p class='alert alert-danger text-center font-serif' style='width:25%; margin-left: 50%'> No room available has been found. </p>";
            } else {
                content = "<table>";
                content += "<tr><th> </th><th>price</th><th>type</th><th>description</th></tr>";
                for (let i = 0; i < data.length; i++) {
                    content += "<tr style=\"padding: 5px\"><th onclick='insertBooking(&quot;"+data[i]["room"]["roomNumber"]+ "&quot;)'" +
                        " scope='row'><button id='room_number' class='choose-room btn btn-outline-light'>" +
                        sanitize(data[i]["room"]["roomNumber"]) + "</button></th>";
                    content += "<td class='price'>" + sanitize(data[i]["room"]["price"]) + "</td>";
                    content += "<td style=\"padding-right: 5px\">" + sanitize(data[i]["room"]["roomType"]) + "</td>";
                    content += "<td style=\"padding: 5px;\">" + sanitize(data[i]["room"]["description"]) + "</td></tr>";
                }
                content += "</table>";
                document.getElementById("available-rooms").innerHTML = content;

            }
        } else {
            console.log(req.responseText);
            // alert("problem processing the request");
            // window.location.href = ("http://localhost:8080/MantaWA-HW2-1.0/booking/customer-list");
            var err = document.createElement("p");
            err.textContent = "Problem processing the request.";
            err.setAttribute("class", "alert alert-danger text-center font-serif");
            document.getElementById("error").appendChild(err);
        }
    }
}

function insertBooking(room){

    var url = new URL('http://localhost:8080/MantaWA-HW2-1.0/rest/booking/insert/');

    url.searchParams.set('checkin', document.getElementById('checkin').value);
    url.searchParams.set('checkout', document.getElementById('checkout').value);
    url.searchParams.set('paymentM', document.getElementById('paymentM').value);
    url.searchParams.set('requests', document.getElementById('requests').value);
    url.searchParams.set('room', room);

    var elem = document.getElementById('personId');
    if(elem != null && elem != 'undefined' ) {
        url.searchParams.set('personId', document.getElementById('personId').value);
    }

    var httpRequest = new XMLHttpRequest();

    if (!httpRequest) {
        alert('Cannot create an XMLHTTP instance');
        return false;
    }
    httpRequest.onreadystatechange = function (){ insertionResult(httpRequest)};

    httpRequest.open('POST', url);
    httpRequest.send();

}


function insertionResult(req){
    if (req.readyState === XMLHttpRequest.DONE) {
        if (req.status == 201) {
            // alert("Booking inserted correctly");
            // window.location.href = ("http://localhost:8080/MantaWA-HW2-1.0/booking/customer-list");
            window.location.href = ("http://localhost:8080/MantaWA-HW2-1.0/booking/customer-list?inserted=true");
        }
        else {
            console.log(req.responseText);
            // alert("problem processing the request");
            // document.getElementById("error").innerHTML = "problem processing the request";
            var err = document.createElement("p");
            err.textContent = "Booking could not be performed. Check input arguments or set checkin and checkout correctly";
            err.setAttribute("class", "alert alert-danger text-center font-serif");
            document.getElementById("error").appendChild(err);
            //window.location.href = ("http://localhost:8080/MantaWA-HW2-1.0/booking/customer-list?message=" + m);
        }
    }
}