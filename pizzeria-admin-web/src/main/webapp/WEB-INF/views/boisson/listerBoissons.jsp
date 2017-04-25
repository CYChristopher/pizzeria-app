<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Liste des Boissons" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>
	<div class="jumbotron">
		<div class="container">

	<h1>Liste des Boissons</h1>
	<a class="btn btn-primary" href=<c:url value="/boisson/nouvelle"/>>Nouvelle Boisson</a>
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

		<c:forEach var="boisson" items="${listeBoissons}">
		<tr>
			<td><img src="${boisson.urlImage}"></td>
			<td>
				<div class="row">
					<div class="col-md-6">
					
						id. ${boisson.id}<br>
						code : <b>${boisson.code}</b><br>
						${boisson.nom}<br>
						${boisson.prix}€<br>
					</div>
					<div class="col-md-6" >
						<a href="<c:url value="/boissons/edit?id=${boisson.id}"/>" class="btn btn-primary">Editer</a>
						<br>
				 		<form action="<c:url value='/boisson/supprimer?id=${boisson.id}'></c:url>" method="post">
				 			<input class='btn btn-danger' type='submit' value='supprimer !!!!'>
						</form>
					</div>
				</div>
			</td>
		</tr>
		</c:forEach>

	

	</table>
	
	
		</div>

</div>

<jsp:include page="../layout/footer.html"/>