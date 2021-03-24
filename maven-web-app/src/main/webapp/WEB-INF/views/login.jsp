<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Yahoo!!</title>
</head>
<body>
<form action="/login" method="post">
	<p><font color="red"> ${errorMessage} </font></p>
	Name <input type="text" name="name"/>
	Password <input type="password" name="password"/>
	<input type="submit" value="login"/>
</form>
</body>
</html>