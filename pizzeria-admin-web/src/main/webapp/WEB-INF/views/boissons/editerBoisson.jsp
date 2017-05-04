<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Editer Boisson ${boisson.code} " />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />

<div class="container">
	<div class="row">
		<div class="col-md-9">
			<div class="container">
				<h1>Editer boisson ${boisson.nom}</h1>

				<c:if test="${msg != null}">
					<div class="alert alert-danger" role="alert">${msg}</div>
				</c:if>

				<form method="post">
					<div class="form-group">
						<label>Code :</label> <input class="form-control" name="code"
							type="text" value="${boisson.code}" required>
					</div>
					<div class="form-group">
						<label>Nom :</label> <input name="nom" class="form-control"
							type="text" value="${boisson.nom}" required>
					</div>
					<div class="form-group">
						<label>Prix : </label> <input step="0.01" id="number" name="prix"
							class="form-control" type="number" value="${boisson.prix}"
							required>
					</div>
					<div class="form-group">
						<label for="image"> Image :</label> <input id="urlImage"
							name="urlImage" class="form-control" type="text"
							placeholder="Lien vers image" value="${boisson.urlImage}"
							required>
					</div>
					<input class="btn btn-success" type="submit" value="Valider">
					<a href=<c:url value='/boissons/liste'/>>
						<button type="button" class="btn btn-primary">Retour</button>
					</a> <a href=<c:url value=''/>>
						<button type="button" class="btn btn-primary">Reset</button>
					</a>
				</form>
			</div>

			<jsp:include page="../layout/footer.jsp" />