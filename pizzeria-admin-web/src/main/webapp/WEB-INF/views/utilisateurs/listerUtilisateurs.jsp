<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Liste utilisateurs" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />


<div class="container">
	<div class="row">
		<div class="col-md-9">
	<h1>Utilisateurs</h1>

	<a href="./ajouter"> <input
		type="button" value="Nouvel Utilisateur" class='btn btn-primary' />
	</a>

	<table class="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Email</th>
				<th>Adresse</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="utilisateur" items="${listeUtilisateurs}">
				<tr>
					<td>${utilisateur.id}</td>
					<td>${utilisateur.nom}</td>
					<td>${utilisateur.prenom}</td>
					<td>${utilisateur.email}</td>
					<td>${utilisateur.adresse}</td>
					<td><a
						href=<c:url value='./editer?id=${utilisateur.id}'></c:url>><button
								class='btn btn-warning'>Editer</button></a>
						<c:if test="${utilisateur.id != utilisateurCourant.id}">
							<form action="<c:url value='./supprimer?id=${utilisateur.id}'></c:url>"
							method="post">
							<input class='btn btn-danger' type='submit' value='Supprimer'>
							</form>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>

<jsp:include page="../layout/footer.jsp"/>
