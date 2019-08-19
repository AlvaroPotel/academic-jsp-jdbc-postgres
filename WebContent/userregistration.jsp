<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="ISO-8859-1">
<title>User Registration</title>
<link rel="stylesheet" href="resources/css/registration.css">

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous">


	
</script>


</head>
<body>

	<a href="allowedaccess.jsp">Back</a>
	<a href="index.jsp">Sign out</a>

	<center>
		<h1>User Registration</h1>
		<h3 style="color: red">${msg}</h3>
	</center>

	<form action="saveUser" method="post" id="formUser"
		onsubmit="return validation()? true : false"
		enctype="multipart/form-data">
		<ul class="form-style-1">
			<li class="required">
				<table>
					<tr>
						<td>Id:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" class="field-long" placeholder="Nothing" style="width: 173px"></td>

						<td>Zip:</td>
						<td><input type="text" id="zip" name="zip"
							onblur="consultZip();" value="${user.zip}" placeholder="24220410"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" placeholder="admin" maxlength="10"></td>

						<td>Street:</td>
						<td><input type="text" id="street" name="street"
							value="${user.street}" placeholder="-----"></td>
					</tr>

					<tr>
						<td>Password:</td>
						<td><input type="password" id="password" name="password"
							value="${user.password}" placeholder="admin" maxlength="10"></td>

						<td>Neighborhood:</td>
						<td><input type="text" id="neighborhood" name="neighborhood"
							value="${user.neighborhood}" placeholder="-----"></td>

					</tr>
					<tr>
						<td>Name:</td>
						<td><input type="text" id="name" name="name"
							value="${user.name}" placeholder="Batata" maxlength="50"></td>

						<td>City:</td>
						<td><input type="text" id="city" name="city"
							value="${user.city}" placeholder="-----"></td>

					</tr>
						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge"
							value="${user.ibge}"></td>
					
						<td>States:</td>
						<td><input type="text" id="states" name="states"
							value="${user.states}" placeholder="-----"></td>

					</tr>
					<tr>

					</tr>

					<tr>
						<td>Pic:</td>
						<td><input type="file" id="pic" name="pic"> <input
							type="text" style="display: none;" id="picTemp" name="picTemp"
							readonly="readonly" value="${user.picBase64}"> <input
							type="text" style="display: none;" id="contPicTemp"
							name="contPicTemp" readonly="readonly"
							value="${user.contentType}"></td>
						<td>PDF:</td>
						<td><input type="file" id="pdf" name="pdf"> <input
							type="text" style="display: none;" id="pdfTmp" name="pdfTemp"
							readonly="readonly" value="${user.pdfBase64}"> <input
							type="text" style="display: none;" id="contPdfTmp"
							name="contPdfTmp" readonly="readonly"
							value="${user.contentTypePdf}"></td>
					</tr>

					<tr>
					</tr>

					<tr>
						<td></td>
						<td>
							<input type="submit" value="Save" style="width: 173px"> 
						</td>
						<td></td>
						<td>
							<input type="submit" value="cancel"	onclick="document.getElementById('formUser').action = 'saveUser?action=reset'" style="width: 173px" >
						</td>
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
				<th>Pic</th>
				<th>PDF</th>
				<th>Name</th>
				<th>Phones</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>

			<c:forEach items="${users}" var="user">

				<tr>

					<td><c:out value="${user.id}" ></c:out></td>
					
					<c:if test="${user.picBase64.isEmpty() == false}">
						<td><a
							href="saveUser?action=download&types=img&user=${user.id}"><img
								src='<c:out value="${user.tempPicUser}"></c:out>' alt="User Pic"
								title="User Pic" width="32px" height="32px"></a></td>
					</c:if>
					<c:if test="${user.picBase64.isEmpty() == true}">
						<td><img alt="Image User" src="resources/img/defaultuser.png"
							width="35px" height="35px" onclick="alert('Not found')"></td>
					</c:if>

					<c:if test="${user.pdfBase64.isEmpty() == false}">
						<td><a
							href="saveUser?action=download&types=pdf&user=${user.id}"><img alt="Pdf User" src="resources/img/pdficon.png"
							width="35px" height="35px"></a></td>
					</c:if>
					<c:if test="${user.pdfBase64.isEmpty() == true}">
						<td><img alt="Pdf User" src="resources/img/notfound.png"
							width="35px" height="35px" onclick="alert('Not found')"></td>
					</c:if>
				
					<td><c:out value="${user.name}"></c:out></td>

					<td><a href="savePhones?action=addPhones&user=${user.id}"><img
							src="resources/img/phone.png" alt="Phones" title="Phones"
							width="35px" height="20px"></a></td>
					<td><a href="saveUser?action=edit&user=${user.id}"><img
							src="resources/img/editIcon.png" alt="Edit" title="Edit"
							width="20px" height="20px"></a></td>
					<td><a href="saveUser?action=delete&user=${user.id}"><img
							src="resources/img/excluirIcon.png" alt="Delete" title="Delete"
							width="20px" height="20px"></a></td>

				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		function validation() {
			if (document.getElementById("login").value == '') {
				alert('Login invalid');
				return false;
			} else if (document.getElementById("password").value == '') {
				alert('Password invalid');
				return false;
			} else if (document.getElementById("name").value == '') {
				alert('Name invalid');
				return false;
			} else if (document.getElementById("phone").value == '') {
				alert('Phone invalid');
				return false;
			}
			return true;
		}

		function consultZip() {
			var zip = $("#zip").val();

			$.getJSON("https://viacep.com.br/ws/" + zip + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							$("#street").val(dados.logradouro);
							$("#neighborhood").val(dados.bairro);
							$("#city").val(dados.localidade);
							$("#states").val(dados.uf);
							$("#ibge").val(dados.ibge);

						} else {
							$("#zip").val('');
							$("#street").val('');
							$("#neighborhood").val('');
							$("#city").val('');
							$("#states").val('');
							$("#ibge").val('');
							alert("ZIP not Found.");
						}
					});

		}
	</script>

</body>
</html>