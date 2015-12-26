<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Liste des juges existants</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css"/>" />
</head>
<body>
	<c:import url="/inc/Header-juge.jsp" />
	<div class="container">

		<legend class="legend" id="l01">LISTE DES PARTICIPANTS</legend>
		<c:choose>
			<%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
			<c:when test="${ empty sessionScope.participants }">
				<p class="erreur">Aucun participant enregistré.</p>
			</c:when>
			<%-- Sinon, affichage du tableau. --%>
			<c:otherwise>
				<table class="table-wight" id="t01">
					<tr>
						<th>Dossards</th>
						<th>Nom</th>
						<th>Prénom</th>
						<th>Age</th>
						<th>Sex</th>
						<th>Catégorie</th>
						<th>Resultat</th>
						<th class="action">Action</th>

					</tr>
					<%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
					<c:forEach items="${ sessionScope.participants }"
						var="mapParticipants" varStatus="boucle">
						<%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
						<tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
							<%-- Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map --%>
							<td><c:out value="${ mapParticipants.value.dossard }" /></td>
							<td><c:out value="${ mapParticipants.value.nom }" /></td>
							<td><c:out value="${ mapParticipants.value.prenom }" /></td>
							<td><c:out value="${ mapParticipants.value.age }" /></td>
							<td><c:out value="${ mapParticipants.value.sex }" /></td>
							<td><c:out value="${ mapParticipants.value.categorieparti }" /></td>
							<td><c:out value="${ mapParticipants.value.resultat }" /></td>

							<%-- Lien vers la servlet de suppression, avec passage du nom du client - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>

							<td class="action"><span class="glyphicon glyphicon-pencil"></span><a
								href="<c:url value="/JUGE/modificationParticipant"><c:param name="idParticipant" value="${ mapClients.key }" /></c:url>">
									<img src="<c:url value="/inc/modifier.png"/>" alt="Modifier" />
							</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>