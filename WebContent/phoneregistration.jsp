<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Phone Registration</title>
<link rel="stylesheet" href="resources/css/registration.css">

</head>
<body>

	<a href="allowedaccess.jsp">Back</a>
	<a href="index.jsp">Sign out</a>

	<center>
		<h1>Phones Registration</h1>
		<h3 style="color: orange">${msg}</h3>
	</center>

	<form action="savePhones" method="post" id="formUser"
		onsubmit="return validation()? true : false">
		<ul class="form-style-1">
			<li>
				<table>

					<tr>
						<td>Id:</td>
						<td><input type="text" readonly="readonly" id="user"
							name="user" class="field-long" value="${thisUser.id}"></td>

						<td><input type="text" readonly="readonly" id="name"
							name="name" class="field-long" value="${thisUser.name}"></td>
					</tr>

					<tr>

						<td>Number</td>
						<td><input type="text" id="number" name="number"
							style="width: 173px;"></td>
						<td><select id="type" name="type" style="width: 173px;">
								<option>Home</option>
								<option>Office</option>
								<option>Mobile</option>
						</select>
						<td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Save" style="width: 173px">
						</td>
						<td><input type="submit" value="Cancel"
							onclick="document.getElementById('formUser').action = 'savePhones?action=back'"
							style="width: 173px;"></td>
					</tr>

				</table>
			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Users</caption>
			<tr>
				<th>Id</th>
				<th>Number</th>
				<th>Type</th>
				<th>Delete</th>

			</tr>

			<c:forEach items="${phones}" var="phone">

				<tr>

					<td style="width: 150px"><c:out value="${phone.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${phone.numbers}"></c:out></td>
					<td style="width: 150px"><c:out value="${phone.typess}"></c:out></td>

					<td><a
						href="savePhones?user=${phone.users}&action=deletePhone&phoneId=${phone.id}"><img
							src="resources/img/excluirIcon.png" alt="Delete" title="Delete"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		function validation() {
			if (document.getElementById("numbers").value == '') {
				alert('Number invalid');
				return false;

			} else if (document.getElementById("type").value == '') {
				alert('Type invalid');
				return false;
			}
			return true;
		}
	</script>

</body>
</html>