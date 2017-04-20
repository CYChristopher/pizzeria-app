<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Users" name="title" />
</jsp:include>

<body class="container">


<h1> Utilisateurs</h1>

<table class="table">
  <thead>
    <tr>
      <th>Nom </th>
      <th>Prénom</th>
      <th>Email</th>
      <th>Adresse</th>
    </tr>
  </thead>
  <tbody>
<c:forEach var="pizza" items="${listeUtilisateurs}">
		<tr>
			<td>
				${pizza.nom}   
			</td>
			<td>
			${pizza.prenom}
			</td>
			<td>
			${pizza.email}
			</td>
			<td>
			${pizza.adresse} 
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>






</body>
</html>