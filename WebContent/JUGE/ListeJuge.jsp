<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Liste des juges existants</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/tuto.css"/>" />
</head>
<body>
	<c:import url="/inc/Header-juge.jsp" />
	<div class="container">
	
	<legend class="legend" id="l01" >LISTE DES JUGES</legend>
	
		<c:choose>
			<%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
			<c:when test="${ empty sessionScope.juges }">
				<p class="erreur">Aucun juge enregistré.</p>
			</c:when>
			<%-- Sinon, affichage du tableau. --%>
			<c:otherwise>
				<table class="table-wight" id="t01">
					<tr>
						<th>Pseudo</th>
						<th>Nom</th>
						<th>Prénom</th>
						<th class="action">Action</th>
					</tr>
					<%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
					<c:forEach items="${ sessionScope.juges }" var="mapJuges"
						varStatus="boucle">
						<%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
						<tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
							<%-- Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map --%>
							<td><c:out value="${ mapJuges.value.pseudo }" /></td>
							<td><c:out value="${ mapJuges.value.nom }" /></td>
							<td><c:out value="${ mapJuges.value.prenom }" /></td>
							<td>
								<%-- Lien vers la servlet de suppression, avec passage du nom du client - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>
							<td class="action"><a
								href="<c:url value="/suppressionClient"><c:param name="idClient" value="${ mapJuges.key }" /></c:url>">
									<img src="<c:url value="/inc/supprimer.png"/>" alt="Supprimer" />
							</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>