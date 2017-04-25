<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Lister Pizza" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>



<div class="container">

	<h2>Utilisateur</h2>

	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<form action="<c:url value='/utilisateur/nouveau'></c:url>"
		method="post">


		<div class="form-group">
			<label for="InputCode">Nom </label> <input class="form-control"
				type='text' name='nom' value="${donnees.nom}" required>
		</div>
		<div class="form-group">
			<label for="InputNom">Prenom </label> <input class="form-control"
				type='text' name='prenom' value="${donnees.prenom}" required>
		</div>
		<div class="form-group">
			<label for="InputPrix">Email </label> <input class="form-control"
				type='email' name='email' value="${donnees.email}" required>
		</div>
		<div class="form-group">
			<label for="InputPrix">Mot de passe </label> <input
				class="form-control" type='password' name='motDePasse' required>
		</div>
		<div class="form-group">
			<label for="InputPrix">Adresse </label> <input class="form-control"
				name='adresse' value="${donnees.adresse}" required>
		</div>
		<input type='submit' class='btn btn-success' value='VALIDER !!!!'>

	</form>


</div>



<jsp:include page="../layout/footer.html"/>