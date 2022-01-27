<%-- 
    Document   : index
    Created on : Jan 20, 2022, 2:22:16 PM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:forEach var="book" items="${books}">
    <div class="card mx-4" style="width: 10rem;">
<!--                <img src="..." class="card-img-top" alt="...">-->
        <div class="card-body">
            <h5 class="card-title">${book.caption}</h5>
            <c:forEach var="author" items="${book.author}">
               <h6 class="card-title">${author.name} ${author.lastname}</h6> 
            </c:forEach>
            <h7 class="card-title">${book.quantity}</h7>
            <p class="card-text">Краткое описание</p>
            <a href="#" class="btn btn-primary">Взять</a>
        </div>
    </div>
</c:forEach>

