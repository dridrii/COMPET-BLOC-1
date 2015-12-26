<%@ page pageEncoding="UTF-8"%>

<form>
	<div id="corps">
		<p class="info">${ form.resultat }</p>
		<p>
			Dossard :
			<c:out value="${ participant.dossard }" />
		</p>
		<p>
			Nom :
			<c:out value="${ participant.nom }" />
		</p>
		<p>
			Prénom :
			<c:out value="${ participant.prenom }" />
		</p>
		<p>
			Age :
			<c:out value="${ participant.age }" />
		</p>
		<p>
			Sex :
			<c:out value="${ participant.sex }" />
		</p>
		<p>
			Catégorie :
			<c:out value="${ participant.categorieparti }" />
		</p>
	</div>
</form>