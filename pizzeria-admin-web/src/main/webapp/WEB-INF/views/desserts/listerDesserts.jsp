<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Dessert" name="title" />
</jsp:include>

<body class="container">
	<h1>Liste des Desserts</h1>
	<a class="btn btn-primary" href="new">Nouveau Dessert</a>
	<br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<tr>
			<td>Image</td>
			<td>Informations</td>
			<td></td>
		</tr>

		<c:forEach var="dessert" items="${listeDesserts}">
		<tr>
			<td><img src="${dessert.urlImage}"></td>
			<td>
				<div class="row">
					<div class="col-md-6">
						Ref. ${dessert.id}
						<br> <b>${dessert.nom}</b><br>${dessert.prix}
						€<br>
					</div>
					<div class="col-md-6" >
						<a href="<c:url value="/desserts/edit?code=${dessert.code}"/>" class="btn btn-primary">Editer</a>
						<br>
						<form method="POST">
							<input type="hidden" name="code" value="${dessert.code}">
							<input type="hidden" name="action" value="supprimer">
							<button type="submit" class="btn btn-danger">Supprimer</button>
						</form>
					</div>
				</div>
			</td>
		</tr>
		</c:forEach>

	

	</table>
</body>
</html>