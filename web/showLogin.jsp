<%-- 
    Document   : showLogin
    Created on : Jan 27, 2022, 10:08:14 AM
    Author     : pupil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form style="width: 30%; margin: auto; margin-top: 20px;" action="login" method="POST">
    <fieldset>
        <legend>Авторизация</legend>
        <div class="form-group">
            <label for="login" class="form-label mt-4">Логин</label>
            <input type="text" class="form-control" id="login" name="login" area-describedby="login" placeholder="Введите логин">
        </div>
        <div class="form-group">
            <label for="password" class="form-label mt-4">Пароль</label>
            <input type="password" class="form-control" id="password" name="password" area-describedby="password" placeholder="Введите пароль">
        </div>

        <button type="submit" class="btn btn-primary" style="margin-top: 20px;">Войти</button>
    </fieldset>
</form>