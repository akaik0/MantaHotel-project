<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Edit payment</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/edit-payment.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-utils.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer-admin.css">


    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


    <script type="text/javascript" src="${pageContext.request.contextPath}/js/loader.js"></script>

</head>
<body>

<div id="header"><c:import url="reusable/header.jsp"/></div>

      <div>
        <c:choose>
          <c:when test="${message.error}">
            <p>
              <c:out value="${message.message}" />
            </p>
          </c:when>
      
          <c:otherwise>

<div class="content">       
   <h1 class="px-2 text-3xl font-serif font-medium text-primary ">Current payment state</h1>
      
            <ul class="border border-1 border-primary mx-2 ">
              <li class="p-2 ">Payment:
                <c:out value="${payment.getPaymentID()}" />
              </li>
              <li class="p-2">Selected method:
                <c:out value="${payment.getPaymentMethod()}" />
              </li>
              <li class="p-2">Total Amount:
                <c:out value="${payment.getTotalAmount()}" />
              </li>
              <li class="p-2">Payed:
                <c:out value="${payment.isComplete()}" />
              </li>
              <li class="p-2">Creation date:
                <c:out value="${payment.getDate()}" />
              </li>
              <c:if test="not empty ${payment.getEmail()}">
                <li class="p-2">Registered by:
                  <c:out value="${payment.getEmail()}" />
                </li>
              </c:if>
            </ul>
      
            <form class="px-5 mt-20" method="POST" action="<c:url value=" ${pageContext.request.contextPath}/payment/edit" />">
            <p class="text-2xl text-primary font-serif mb-4 text-center">Edit payment</p>
            <input type="hidden" name="paymentid" value="${payment.getPaymentID()}" />
            <label for="paymentM">Method: </label>
            <select class="border border-primary" name="paymentM" type="text">
                      <option value="Visa">Visa</option>
                      <option value="MasterCard">MasterCard</option>
                      <option value="Maestro">Maestro</option>
                      <option value="American Express">American Express</option>
                      <option value="Paypal">Paypal</option>
                      <option value="Cash">Cash</option>
                  </select><br/><br/>
            <c:if test="${empty sessionScope.personId}">
              <label for="amount">Total amount: </label>
              <input class="border border-primary" name="amount" type="text"/><br/><br/>
              <label for="paymentM">Payed: </label>
              <select class="border border-primary" name="complete" type="text">
                          <option value="true">Yes</option>
                          <option value="false">No</option>
                      </select><br/><br/>
            </c:if>
            <button class="mb-3 text-white font-thin bg-primary w-20 h-6 hover:bg-red" type="submit">Edit</button><br/>
            <button class="text-white font-thin  bg-primary w-36 h-6 hover:bg-red" type="reset">Reset the form</button><br/><br/>
            </form>
          </c:otherwise>
        </c:choose>
      </div>

</div>
    
<div id="footer-admin"></div>

</body>
</html>

