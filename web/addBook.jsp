<%-- 
    Document   : index
    Created on : Jan 20, 2022, 2:22:16 PM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<form style="width: 30%; margin: auto; margin-top: 20px;" action="createBook" method="POST">
    <fieldset>
        <legend>Добавление книги</legend>
        <div class="form-group">
            <label for="caption" class="form-label mt-4">Название книги</label>
            <input type="text" class="form-control" id="caption" name="caption" area-describedby="caption" placeholder="Введите название книги">
        </div>
        <div class="form-group">
            <label for="exampleSelect2" class="form-label mt-4">Список авторов</label>
            <select multiple="" class="form-select" id="bookAuthors" name="bookAuthors">
                <c:forEach var="author" items="${authors}">
                    <option value="${author.id}">${author.name} ${author.lastname}. ${author.year}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="publishedYear" class="form-label mt-4">Год издания</label>
            <input type="text" class="form-control" id="publishedYear" name="publishedYear" area-describedby="publishedYear" placeholder="Введите год издания книги">
        </div>
        <div class="form-group">
            <label for="quantity" class="form-label mt-4">Количество экземпляров</label>
            <input type="number" class="form-control" id="quantity" name="quantity" area-describedby="quantity" placeholder="Введите количество экземпляров книги">
        </div>
        <button type="submit" class="btn btn-primary" style="margin-top: 20px;">Добавить книгу</button>
    </fieldset>
</form>