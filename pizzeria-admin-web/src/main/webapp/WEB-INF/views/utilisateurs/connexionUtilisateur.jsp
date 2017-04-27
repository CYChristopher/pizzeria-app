<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Lister Pizza" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />

<c:if test="${msg != null}">
	<div class="alert alert-danger" role="alert">${msg}</div>
</c:if>

<div class="col-sm-offset-4 col-sm-4">
	<div class="panel panel-default">
		<div class="panel-heading text-center">
			<img alt="dta" src='<c:url value="/static/img/dta.png"/>'>
		</div>
		<div class="panel-body">
		<h3 class="text-center">Connexion</h3>
			<form method="POST" action="<c:url value='/login'/>">
				<div class="form-group">
					<label for="email">Email</label> <input type="email"
						class="form-control" name="email" id="email" placeholder="Email">
				</div>
				<div class="form-group">
					<label for="motDePasse">Mot de passe <a
						href="<c:url value='/utilisateurs/password'/>">(Mot
							de passe oublié)</a></label> <input type="password" class="form-control"
						name="motDePasse" id="motDePasse" placeholder="Mot de passe">
				</div>
				<button type="submit" class="btn btn-sm btn-default">Se
					connecter</button>
			</form>
		</div>
	</div>
</div>

<jsp:include page="../layout/footer.jsp" />
