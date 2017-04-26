<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Ajouter un client" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />

<div class="container">
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>
	<h1>Ajout d'un clients</h1>
	<form class="form-horizontal" method="POST"
		action="<c:url value='/clients/new'/>">
		<div class="form-group">
			<label for="nom" class="col-sm-2 control-label">Nom</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="nom" id="nom"
					placeholder="Nom" required>
			</div>
		</div>
		<div class="form-group">
			<label for="prenom" class="col-sm-2 control-label">Prenom</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="prenom" id="prenom"
					placeholder="Prenom" required>
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">Email</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" name="email" id="email"
					placeholder="Email" required>
			</div>
		</div>
		<div class="form-group">
			<label for="motDePasse" class="col-sm-2 control-label">Mot de
				passe</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" name="motDePasse"
					id="motDePasse" placeholder="Mot de passe" minlength="6" required>
			</div>
		</div>
		<div class="form-group">
			<label for="adresse" class="col-sm-2 control-label">Adresse</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="adresse" id="adresse"
					placeholder="Adresse" required>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">Ajouter</button>
			</div>
		</div>
	</form>

</div>

<jsp:include page="../layout/footer.html" />