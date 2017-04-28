
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Static navbar -->
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button"
              class="navbar-toggle collapsed"
              data-toggle="collapse"
              data-target="#navbar"
              aria-expanded="false"
              aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span> <span
          class="icon-bar"></span> <span class="icon-bar"></span> <span
          class="icon-bar"></span>
      </button>
      <a class="navbar-brand"
         href="#">Pizzeria Administration</a>
    </div>
    <div id="navbar"
         class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li><a href=<c:url value='/.'/>>Accueil</a></li>
        <li><a href="<c:url value='/utilisateurs/list'/>">Utilisateurs</a></li>
        <li><a href=<c:url value='/pizzas/list'/>>Pizzas</a></li>
        <li><a href="#">Menus</a></li>
        <li><a href=<c:url value='/desserts/list'/>>Desserts</a></li>
        <li><a href=<c:url value='/commandes/list'/>>Commandes</a></li>
        <li><a href=<c:url value='/boissons/list'/>>Boissons</a></li>
        <li><a href=<c:url value='/clients/list'/>>Clients</a></li>
        <li><a href=<c:url value='/livreurs/list'/>>Livreurs</a></li>
        <li><a href=<c:url value='/ingredients/liste'/>>Ingrédients</a></li>
        <li><a href="#">Statistiques</a></li>
        <li><a href="#">Promotions</a></li>
        <!--
        <li class="dropdown">
          <a href="#"
             class="dropdown-toggle"
             data-toggle="dropdown"
             role="button"
             aria-haspopup="true"
             aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator"
                class="divider"></li>
            <li class="dropdown-header">Nav header</li>
            <li><a href="#">Separated link</a></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      -->
      </ul>

      <!--/.nav-collapse -->
    </div>
    <!--/.container-fluid -->
  </div>
</nav>
