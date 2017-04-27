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
		<h3 class="text-center">Demande de mot de passe</h3>
			<form method="POST" action="<c:url value='/utilisateurs/password'/>">
				<div class="form-group">
					<label for="email">Email</label> <input type="email"
						class="form-control" name="email" id="email" placeholder="Email">
				</div>
				<button type="submit" class="btn btn-sm btn-default">Demander un mot de passe</button>
			</form>
		</div>
	</div>
</div>
