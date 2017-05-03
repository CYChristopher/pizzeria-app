<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Nouvelle Boisson" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />

<div class="container">
	<div class="row">
		<div class="col-md-9">
			<div class="container">

				<h1>Nouvelle Boisson</h1>
				<c:if test="${msg != null}">
					<div class="alert alert-danger" role="alert">${msg}</div>
				</c:if>

				<form method="post">
					<label for="newcode">Code :</label> <input
						style="border-color:${erreur[0]}" class="form-control" name="code"
						type="text" placeholder="Code" value="${code}"> <label
						for="ref">Nom :</label> <input style="border-color:${erreur[1]}"
						class="form-control" name="nom" type="text" placeholder="Nom"
						value="${nom}"> <label for="prix">Prix : </label> <input
						style="border-color:${erreur[2]}" step="0.01" class="form-control"
						name="prix" type="number" placeholder="prix" value="${prix}">

					<div class="form-group">
						<label for="urlImage">Image :</label> <input id="image"
							name="urlImage" class="form-control" type="text"
							placeholder="Lien vers image" value="${urlImage}" required>
					</div>

					<input class="btn btn-success" type="submit" value="Valider">
					<a href=<c:url value='/boissons/liste'/>>
						<button type="button" class="btn btn-primary">Retour</button>
					</a>
				</form>


			</div>
		</div>
		<jsp:include page="../layout/footer.jsp" />