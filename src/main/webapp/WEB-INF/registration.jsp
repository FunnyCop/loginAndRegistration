<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
</head>
<body>
    <h1>Register!</h1>
    
    <form:form action = "/registration" method = "post" modelAttribute = "user">

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

		<div class = "form-group">

			<form:label path = "confirm">Confirm Password:</form:label>
			<form:input path = "confirm" class = "form-control" />
			<form:errors path = "confirm" class = "text-danger" />

		</div>

		<input type = "submit" value = "Register" class = "btn btn-primary" />

		<a href = "/login">Login</a>

    </form:form>
    
</body>
</html>