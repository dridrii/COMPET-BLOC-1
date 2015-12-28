<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Liste des blocs existants</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/style.css"/>" />
</head>
<body>
	<c:import url="/inc/Header-juge.jsp" />
	<div class="container">

		<legend class="legend" id="l01">LISTE DES BLOCS</legend>
		<c:choose>
			<%-- Si aucun client n'existe en session, affichage d'un message par défaut. --%>
			<c:when test="${ empty sessionScope.blocs }">
				<p class="erreur">Aucun blocs enregistré.</p>
			</c:when>
			<%-- Sinon, affichage du tableau. --%>
			<c:otherwise>
				<table class="table-wight" id="t01">
					<tr>
						<th>Numéro de bloc</th>
						<th>Couleur Difficulté</th>
						<th>Couleur de la voie</th>
						<th>Ouvreur</th>
						<th>Valeur initale</th>
						<th class="action">Action</th>

					</tr>
					<%-- Parcours de la Map des clients en session, et utilisation de l'objet varStatus. --%>
					<c:forEach items="${ sessionScope.blocs }"
						var="mapBlocs" varStatus="boucle">
						<%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
						<tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
							<%-- Affichage des propriétés du bean Client, qui est stocké en tant que valeur de l'entrée courante de la map --%>
							<td><c:out value="${mapBlocs.value.numBloc}" /></td>
							<td><c:out value="${mapBlocs.value.couleurDiff}" /></td>
							<td><c:out value="${mapBlocs.value.couleurVoie}" /></td>
							<td><c:out value="${mapBlocs.value.ouvreur}" /></td>
							<td><c:out value="${mapBlocs.value.valeurInit}" /></td>

							<%-- Lien vers la servlet de suppression, avec passage du nom du client - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>

							<td class="action"><span class="glyphicon glyphicon-pencil"></span><a
								href="<c:url value="/JUGE/modificationBloc"><c:param name="idBloc" value="${ mapClients.key }" /></c:url>">
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