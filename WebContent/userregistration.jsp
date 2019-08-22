<%@page import="beans.UserBean"%>
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

	<a href="allowedaccess.jsp"><img alt="home"
		src="resources/img/back.png" width="45px" height="40px"></a>
	<a href="index.jsp"><img alt="home" src="resources/img/home.png"
		width="67px" height="42px"></a>

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
							value="${user.id}" class="field-long" placeholder="Nothing"
							style="width: 173px"></td>

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
					<tr>
						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge"
							value="${user.ibge}"></td>

						<td>States:</td>
						<td><input type="text" id="states" name="states"
							value="${user.states}" placeholder="-----"></td>

					</tr>

					<tr>
						<td>Pic:</td>
						<td><input type="file" name="pic" value="pic"></td>
						<td>Active</td>
						<td><input type="checkbox" id="active" name="active"
							<%if (request.getAttribute("user") != null) {
				UserBean user = (UserBean) request.getAttribute("user");
				if (user.isActive()) {
					out.print(" ");
					out.print("checked=\"checked\"");
					out.print(" ");
				}
			}%>>

						</td>
					</tr>
					<tr>
						<td>PDF:</td>
						<td><input type="file" name="pdf" value="pdf"></td>
						<td>Gender</td>
						<td><input type="radio" name="gender"
							<%if (request.getAttribute("user") != null) {
				UserBean user = (UserBean) request.getAttribute("user");
				if (user.getGender().equalsIgnoreCase("male")) {
					out.print(" ");
					out.print("checked=\"checked\"");
					out.print(" ");
				}
			}%>
							value="male">Male</input> <input type="radio" name="gender"
							<%if (request.getAttribute("user") != null) {
				UserBean user = (UserBean) request.getAttribute("user");
				if (user.getGender().equalsIgnoreCase("female")) {
					out.print(" ");
					out.print("checked=\"checked\"");
					out.print(" ");
				}
			}%>
							value="female">Female</input></td>
					</tr>

					<tr>
						<td>Perfil</td>
						<td><select id="perfil" name="perfil" style="width: 172px;">
								<option value="Not inform"
									<%if (request.getAttribute("user") != null) {
				UserBean user = (UserBean) request.getAttribute("user");
				if (user.getPerfil().equalsIgnoreCase("Not inform")) {
					out.print(" ");
					out.print("selected=\"selected\"");
					out.print(" ");
				}
			}%>>:: Select ::</option>

								<option value="admin"
									<%if (request.getAttribute("user") != null) {
				UserBean user = (UserBean) request.getAttribute("user");
				if (user.getPerfil().equalsIgnoreCase("admin")) {
					out.print(" ");
					out.print("selected=\"selected\"");
					out.print(" ");
				}
			}%>>Admin</option>

								<option value="jdev"
									<%if (request.getAttribute("user") != null) {
				UserBean user = (UserBean) request.getAttribute("user");
				if (user.getPerfil().equalsIgnoreCase("jdev")) {
					out.print(" ");
					out.print("selected=\"selected\"");
					out.print(" ");
				}
			}%>>Junior Developer</option>

								<option value="mdev"
									<%if (request.getAttribute("user") != null) {
				UserBean user = (UserBean) request.getAttribute("user");
				if (user.getPerfil().equalsIgnoreCase("mdev")) {
					out.print(" ");
					out.print("selected=\"selected\"");
					out.print(" ");
				}
			}%>>Mid Developer</option>

								<option value="sdev"
									<%if (request.getAttribute("user") != null) {
				UserBean user = (UserBean) request.getAttribute("user");
				if (user.getPerfil().equalsIgnoreCase("sdev")) {
					out.print(" ");
					out.print("selected=\"selected\"");
					out.print(" ");
				}
			}%>>Senior Developer</option>

								<option value="manager"
									<%if (request.getAttribute("user") != null) {
				UserBean user = (UserBean) request.getAttribute("user");
				if (user.getPerfil().equalsIgnoreCase("manager")) {
					out.print(" ");
					out.print("selected=\"selected\"");
					out.print(" ");
				}
			}%>>Manager</option>

						</select></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Save" style="width: 173px">
						</td>
						<td></td>
						<td><input type="submit" value="cancel"
							onclick="document.getElementById('formUser').action = 'saveUser?action=reset'"
							style="width: 173px"></td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<form action="servletSearch" method="post">
		<ul class="form-style-1">
			<li class="required">
				<table>
					<tr>
						<td>Description</td>
						<td><input type="text" id="consultDescri"
							name="consultDescri" style="width: 343px"></td>
						<td><input type="submit" id="search" name="search"
							value="Search" style="width: 173px"></td>
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

					<td><c:out value="${user.id}"></c:out></td>

					<c:if test="${user.miniPicBase64.isEmpty() == false}">
						<td><a
							href="saveUser?action=download&types=img&user=${user.id}"><img
								src='<c:out value="${user.miniPicBase64}"></c:out>'
								alt="User Pic" title="User Pic" width="32px" height="32px"></a></td>
					</c:if>
					<c:if test="${user.miniPicBase64 == null}">
						<td><img alt="Image User" src="resources/img/defaultuser.png"
							width="35px" height="35px" onclick="alert('Not found')"></td>
					</c:if>

					<c:if test="${user.pdfBase64.isEmpty() == false}">
						<td><a
							href="saveUser?action=download&types=pdf&user=${user.id}"><img
								alt="Pdf User" src="resources/img/pdficon.png" width="35px"
								height="35px"></a></td>
					</c:if>

					<c:if test="${user.pdfBase64 == null}">
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
					<td><a href="saveUser?action=delete&user=${user.id}"
						onclick=" return confirm('Are you sure?')"><img
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