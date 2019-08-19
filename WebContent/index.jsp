<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/style.css">

</head>
<body>

	<div class="login-page">
		<div class="form">
			<form action="LoginServlet" method="post" class="login-form">

				Login: <input type="text" id="login" name="login"> <br>
				Password: <input type="password" id="password" name="password">
				<br> <button type="submit" value="Submit">Submit</button>

			</form>

		</div>
	</div>
</body>
</html>