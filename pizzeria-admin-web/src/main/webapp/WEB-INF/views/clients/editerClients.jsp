<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Editer un client" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />


<div class="container">
	<div class="row">
		<div class="col-md-9">

			<h1>Ajout d'un client</h1>
			<form class="form-horizontal" method="POST"
				action="<c:url value='/clients/edit?id=${client.id}'/>">
				<div class="form-group">
					<label for="nom" class="col-sm-2 control-label">Nom</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="nom" id="nom"
							placeholder="Nom" value="${client.nom}" required>
					</div>
				</div>
				<div class="form-group">
					<label for="prenom" class="col-sm-2 control-label">Prenom</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="prenom" id="prenom"
							placeholder="Prenom" value="${client.prenom}" required>
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" name="email" id="email"
							placeholder="Email" value="${client.email}" required>
					</div>
				</div>
				<div class="form-group">
					<label for="motDePasse" class="col-sm-2 control-label">Mot
						de passe <a
						href="<c:url value='/clients/password?id=${client.id}'/>">(Mot
							de passe oublié)</a>
					</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="motDePasse"
							id="motDePasse" placeholder="Mot de passe" minlength="6">
					</div>
				</div>
				<div class="form-group">
					<label for="adresse" class="col-sm-2 control-label">Adresse</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="adresse"
							id="adresse" placeholder="Adresse" value="${client.adresse}"
							required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Editer</button>
						<a href=<c:url value=''/>>
							<button type="button" class="btn btn-default">Reset</button>
						</a>
					</div>
				</div>
			</form>

		</div>

		<jsp:include page="../layout/footer.jsp" />