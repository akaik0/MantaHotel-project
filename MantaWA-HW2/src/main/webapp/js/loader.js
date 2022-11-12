var contextPath = 'http://localhost:8080/MantaWA-HW2-1.0';


function sanitize(str) {
    return String(str).replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;');
}

document.addEventListener('DOMContentLoaded', function(event) {
    loadTemplate();
})


function loadTemplate(){

    var footerUrl = new URL(contextPath+'/html/reusable/footer.html');
    sendGenericGetRequest(footerUrl, loadFooter);

    var footeradminUrl = new URL(contextPath+'/html/reusable/footer-admin.html');
    sendGenericGetRequest(footeradminUrl, loadFooterAdmin);

    var headermanagerUrl = new URL(contextPath+'/html/reusable/headerManager.html');
    sendGenericGetRequest(headermanagerUrl, loadHeaderManager);

    var headeruserUrl = new URL(contextPath+'/html/reusable/headerUser.html');
    sendGenericGetRequest(headeruserUrl, loadHeaderUser);


}

function sendGenericGetRequest(url, callback){

    var httpRequest = new XMLHttpRequest();

    if (!httpRequest) {
        alert('Cannot create an XMLHTTP instance');
        return false;
    }
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status == 200) {
                callback(httpRequest.responseText)
            }
            else {
                console.log(req.responseText);
                alert("problem processing the request");
            }
        }

    };

    httpRequest.open('GET', url);
    httpRequest.send();
}


function loadFooter(data){
    document.getElementById("footer").innerHTML= data;
}

function loadFooterAdmin(data){
    document.getElementById("footer-admin").innerHTML= data;
}


function loadHeaderManager(data){
    document.getElementById("headerManager").innerHTML=data;
}

function loadHeaderUser(data){
    document.getElementById("headerUser").innerHTML=data;
}