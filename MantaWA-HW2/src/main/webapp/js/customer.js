document.addEventListener('DOMContentLoaded', function(event) {
    // your page initialization code here
    // the DOM will be available here
    //currentSlide(document.getElementsByClassName("mySlides").length);

    document.getElementById('search').addEventListener('click', listCustomerByName);
});


function listCustomerByName() {
    var url = new URL(contextPath+'/rest/customer/');
    if (document.getElementById("nameandsur").value != undefined) {
        url += document.getElementById("nameandsur").value;
    }
    else {
        url += "list/";
    }
    genericGETRequest(url, fillCustomerByNameSelector);
}


function fillCustomerByNameSelector(req){
    if (req.readyState === XMLHttpRequest.DONE) {
        if (req.status == 200) {
            var jsonData = JSON.parse(req.responseText);
            var data = jsonData['resource-list'];
            if (data.length == 0) {
                document.getElementById("result").innerHTML =
                    "<p class='alert alert-danger text-center font-serif' style='width:25%; margin-left: 50%'> No customer has been found. </p>";
            } else {
                content = "<table class=\"table table-striped ml-5 mt-2 mb-2\">";
                content += "<tr><th>Name</th><th>Surname</th><th>Id</th><th>Email</th><th>Phone</th><th>Birth Date</th></tr>";
                for (let i = 0; i < data.length; i++) {
                    content += "<tr><td>" + sanitize(data[i]["customer"]["name"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["customer"]["surname"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["customer"]["personId"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["customer"]["email"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["customer"]["phoneNumber"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["customer"]["birthDate"]) + "</td></tr>";
                }
                content += "</table>";
                document.getElementById("result").innerHTML = content;

            }
        } else {
            console.log(req.responseText);
            var err = document.createElement("p");
            err.textContent = "Problem processing the request.";
            err.setAttribute("class", "alert alert-danger text-center font-serif");
            document.getElementById("error").appendChild(err);
        }
    }
}

function listCustomer() {
    var url = new URL(contextPath+'/rest/customer/list/');
    genericGETRequest(url, fillCustomerSelector);
}


function fillCustomerSelector(req){
    if (req.readyState === XMLHttpRequest.DONE) {
        if (req.status == 200) {
            var jsonData = JSON.parse(req.responseText);
            var data = jsonData['resource-list'];
            if (data.length == 0) {
                document.getElementById("list").innerHTML =
                    "<p class='alert alert-danger text-center font-serif' style='width:25%; margin-left: 50%'> No customer has been found. </p>";
            } else {
                content = "<table class=\"table table-striped ml-5 mt-2 mb-2\">";
                content += "<tr><th>Name</th><th>Surname</th><th>Id</th><th>Email</th><th>Phone</th><th>Birth Date</th></tr>";
                for (let i = 0; i < data.length; i++) {
                    content += "<tr><td>" + sanitize(data[i]["customer"]["name"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["customer"]["surname"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["customer"]["personId"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["customer"]["email"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["customer"]["phoneNumber"]) + "</td>";
                    content += "<td>" + sanitize(data[i]["customer"]["birthDate"]) + "</td></tr>";
                }
                content += "</table>";
                document.getElementById("list").innerHTML = content;

            }
        } else {
            console.log(req.responseText);
            var err = document.createElement("p");
            err.textContent = "Problem processing the request.";
            err.setAttribute("class", "alert alert-danger text-center font-serif");
            document.getElementById("error").appendChild(err);
        }
    }
}
