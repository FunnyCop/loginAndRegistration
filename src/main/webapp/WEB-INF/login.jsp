<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
    <h1>Login</h1>

    <form:form action = "/login" method = "post" modelAttribute = "login">

		<div class = "form-group">

			<form:label path = "email">Email:</form:label>
			<form:input path = "email" class = "form-control" />
			<form:errors path = "email" class = "text-danger" />

		</div>

		<div class = "form-group">

			<form:label path = "password">Password:</form:label>
			<form:input path = "password" class = "form-control" />
			<form:errors path = "password" class = "text-danger" />

		</div>

		<input type = "submit" value = "Login" class = "btn btn-primary" />

		<a href = "/registration">Register</a>

    </form:form>
   
</body>
</html>