<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Lister Livreurs" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>

<div class="container">
	<h1>Liste des livreurs</h1>
	<a class="btn btn-primary" href="new">Nouveau livreur</a>
	<br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<tr>
			<td>Id</td>
			<td>Nom</td>
			<td>Prenom</td>
			<td></td>
			<td></td>
		</tr>

		<c:forEach var="livreur" items="${listeLivreurs}">
			<tr>
				<td>${livreur.id}</td>
				<td>${livreur.nom}</td>
				<td>${livreur.prenom}</td>
				<td><a href="<c:url value='/livreurs/edit?id=${livreur.id}'/>" role="button" class="btn btn-warning">Editer</a></td>
				<td><a href="<c:url value='/livreurs/edit?id=${livreur.id}&del=true'/>" role="button" class="btn btn-danger">Supprimer</a></td>
			</tr>
			
		</c:forEach>



	</table>
</div>

<jsp:include page="../layout/footer.html"/>