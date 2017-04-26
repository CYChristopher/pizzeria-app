<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Ajouter Livreur" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>

<div class="container">
	<h1>Ajouter un livreur</h1>
	<br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<form method="post">
		<div class="form-group">
			<label for="nom">Nom</label>
			<input type="text" class="form-control" id="nom" name="nom"
				placeholder="Nom">
		</div>
		<div class="form-group">
			<label for="prenom">Prenom</label>
			<input type="text" class="form-control" id="prenom" name="prenom"
				placeholder="Prenom">
		</div>
		<button type="submit" class="btn btn-default">Ajouter</button>
	</form>

</div>

<jsp:include page="../layout/footer.html"/>