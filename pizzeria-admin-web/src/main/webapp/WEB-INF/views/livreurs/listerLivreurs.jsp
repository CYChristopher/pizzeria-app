<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Liste Livreurs"/>
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>

<div class="container">
	<div class="row">
		<div class="col-md-9">

			<h1>Liste des livreurs</h1>
			<a class="btn btn-primary" href="./ajouter">Nouveau livreur</a>
			<br>
				<c:if test="${msg != null}">
					<div class="alert alert-danger" role="alert">${msg}</div>
				</c:if>

				<table class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>Nom</th>
							<th>Prenom</th>
							<th></th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="livreur" items="${listeLivreurs}">
							<tr>
								<td>${livreur.id}</td>
								<td>${livreur.nom}</td>
								<td>${livreur.prenom}</td>
								<td>
									<a href="<c:url value='./editer?id=${livreur.id}'/>" role="button" class="btn btn-warning">Editer</a>
									<a href="<c:url value='./editer?id=${livreur.id}&del=true'/>" role="button" class="btn btn-danger">Supprimer</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<jsp:include page="../layout/footer.jsp"/>
