<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	<title>Product Registration</title>
	
	<link rel="stylesheet" href="resources/css/registration.css">
	
	<script src="resources/js/jquery.min.js" type="text/javascript"></script>
	<script src="resources/js/jquery.maskMoney.min.js" type="text/javascript"></script>

</head>
<body>

	<a href="allowedaccess.jsp">Back</a>
	<a href="index.jsp">Sign out</a>


	<center>
		<h1>Product Registration</h1>
		<h3 style="color: red">${msg}</h3>
	</center>

	<form action="saveProducts" method="post" id="formProd"
		onsubmit="return validation() ? true : false">
		<ul class="form-style-1">
			<li class="required">
				<table>
					<tr>
						<td>Id:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${product.id}" class="field-long"></td>
					</tr>

					<tr>
						<td>Name:</td>
						<td><input type="text" id="name" name="name"
							value="${product.name}" maxlength="100"></td>
					</tr>

					<tr>
						<td>Quant:</td>
						<td><input type="number" id="quant" name="quant"
							value="${product.quant}"></td>
					</tr>
					<tr>
						<td>Price:</td>
						<td><input type="text" id="price" name="price"
							value="${product.priceTemp}" data-thousands="." data-decimal="," ></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="submit" value="Save" style="width: 84px"> 
							<input type="submit" value="Cancel" style="width: 83px" 
								onclick="document.getElementById('formProd').action = 'saveProducts?action=reset'" >    
						</td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Products</caption>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Quant</th>
				<th>Price</th>
				<th>Delete</th>
				<th>Edit</th>
			</tr>

			<c:forEach items="${products}" var="product">

				<tr>

					<td><c:out value="${product.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${product.name}"></c:out></td>
					<td><c:out value="${product.quant}"></c:out></td>
					
					<td><c:out value="${product.price}"></c:out></td>

					<td><a href="saveProducts?action=delete&product=${product.id}"><img
							src="resources/img/excluirIcon.png" alt="Delete" width="20px"
							height="20px"></a></td>
					<td><a href="saveProducts?action=edit&product=${product.id}"><img
							src="resources/img/editIcon.png" alt="Delete" width="20px"
							height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		function validation() {
			if (document.getElementById("name").value == '') {
				alert('Invalid name');
				return false;
			} else if (document.getElementById("quant").value == '') {
				alert('Invalid quant');
				return false;
			} else if (document.getElementById("price").value == '') {
				alert('Invalid price');
				return false;
			}
			return true;
		}
	</script>

</body>

	<script type="text/javascript">
		$(function () {
			$('#price').maskMoney();
			
		})
	
	</script>

</html>


