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
	<center><h3>Didactic Project</h3></center><br/>
	<center><h1>JSP+Servlet+JDBC</h1></center>

	
		<div class="form">
			<form action="LoginServlet" method="post" class="login-form">

				Login: <input type="text" id="login" name="login"> <br>
				Password: <input type="password" id="password" name="password">
				<br> <button type="submit" value="Submit">Submit</button>

			</form>

		</div>
		
		<center><h3><a style="text-decoration: none" href="https://github.com/alvaropotel" >AP GitHub Java Web</a></h3></center><br/>			
		
	</div>
</body>
</html>