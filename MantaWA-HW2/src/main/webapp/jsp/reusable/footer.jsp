<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Header</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
</head>
<body>

<div class="bg-primary-500 text-white/70">
  <div class="flex justify-between px-2 ">
    <div><img src="http://localhost:8080/MantaWA-HW2-1.0/html/images/manta-logo-footer.png" alt="Logo of the MantaHotel" width="160px"></div>
    <div class="flex justify-between space-x-24">
      <div class="">
        <span class="font-bold text-lg">Where to find us</span>
        <ul>
          <li>Address: <br>
            (732) 842-6083 <br>
            29 Richard Pl <br>
            Middletown, <br> New Jersey(NJ), 07748</li>
          <li><a href="" target="_blank" class="hover:underline ">Privacy Policy</a></li>
        </ul>
      </div>
      <div class="">
        <span class="font-bold text-lg">About</span>
        <ul>
          <li><a href="http://localhost:8080/MantaWA-HW2-1.0/rooms" class="hover:underline ">Rooms</a></li>
        </ul>
      </div>
      <div class="">
        <span class="font-bold text-lg">Connect</span>
        <ul>
          <li>Contact us:</li>
          <li><a href="tel:123-456-7890" class="hover:underline ">tel:+123-456-7890</a></li>
          <li><a href="mailto:hotelmanta@gmail.com" class="hover:underline ">mail: hotelmanta@gmail.com</a></li>
          <li><a href="" class="hover:underline "> Instagram</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div class="flex justify-center text-xs text-white/50 mt-6 pb-2"><span>Copyright &copy;</span></div>
</div>
