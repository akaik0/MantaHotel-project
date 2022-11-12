// self executing function here
document.addEventListener('DOMContentLoaded', function(event) {

    currentSlide(document.getElementsByClassName("mySlides").length);

    var payment = document.getElementsByClassName("payment");
    for (let i=0;i<payment.length;i++){
        payment[i].addEventListener("click", () =>
        {document.location.href = "http://localhost:8080/MantaWA-HW2-1.0/payment/searchID?paymentid="+
            payment[i].textContent;});
    }

});

/*
let slideIndex = 1;
showSlides(slideIndex);
*/

function plusSlides(n) {
    showSlides(slideIndex += n);
}


function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("dot");
    if (n >= slides.length) {slideIndex = 1}
    if (n < 1) {slideIndex = slides.length}
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex-1].style.display = "block";
    dots[slideIndex-1].className += " active";
}
