<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Editer Livreurs"/>
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>

<div class="container">
	<div class="row">
		<div class="col-md-9">

			<h1>Ajouter un livreur</h1>
			<br>
				<c:if test="${msg != null}">
					<div class="alert alert-danger" role="alert">${msg}</div>
				</c:if>

				<form method="post">
					<div class="form-group">
						<label for="nom">Nom</label>
						<input type="text" class="form-control" id="nom" name="nom" placeholder="${livreur.nom}" value="${livreur.nom}"></div>

						<div class="form-group">
							<label for="prix">Prenom</label>
							<input type="text" class="form-control" id="prenom" name="prenom" placeholder="${livreur.prenom}" value="${livreur.prenom}"></div>

							<br>
								<button class="btn btn-success" type="submit">Valider</button>

								<a href='./liste'>
									<button type="button" class="btn btn-primary">Retour</button>
								</a>
							</form>

						</div>

						<jsp:include page="../layout/footer.jsp"/>
