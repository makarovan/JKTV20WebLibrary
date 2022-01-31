<%-- 
    Document   : index
    Created on : Jan 20, 2022, 2:22:16 PM
    Author     : pupil
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<form style="width: 30%; margin: auto; margin-top: 20px;" action="updateUser" method="POST">
    <fieldset>
        <legend>Редактирование пользователя</legend>
        <div class="form-group">
            <label for="exampleSelect2" class="form-label mt-4">Список пользователей</label>
            <select multiple="" class="form-select" id="users" name="users">
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}">${user.firstname} ${user.lastname}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="firstname" class="form-label mt-4">Имя</label>
            <input type="text" class="form-control" id="firstname" name="firstname" area-describedby="firstname" placeholder="Введите имя читателя">
        </div>
        <div class="form-group">
            <label for="lastname" class="form-label mt-4">Фамилия</label>
            <input type="text" class="form-control" id="lastname" name="lastname" area-describedby="lastname" placeholder="Введите фамилию читателя">
        </div>
        <div class="form-group">
            <label for="phone" class="form-label mt-4">Номер телефона</label>
            <input type="text" class="form-control" id="phone" name="phone" area-describedby="phone" placeholder="Введите телефон читателя">
        </div>
        <div class="form-group">
            <label for="login" class="form-label mt-4">Имя пользователя</label>
            <input type="text" class="form-control" id="login" name="login" area-describedby="login" placeholder="Введите имя пользователя читателя">
            <input type="hidden" id="id" name="id">
        </div>
        <div class="form-group">
            <label for="password" class="form-label mt-4">Пароль</label>
            <input type="text" class="form-control" id="password" name="password" area-describedby="password" placeholder="Введите пароль читателя">
        </div>

        <button type="submit" class="btn btn-primary" style="margin-top: 20px;">Редактировать пользователя</button>
    </fieldset>
</form>