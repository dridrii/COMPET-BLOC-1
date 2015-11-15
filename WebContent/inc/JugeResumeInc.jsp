<%@ page pageEncoding="UTF-8"%>

<form>
	<div id="corps">
		<p class="info">${ form.resultat }</p>
		<p>
			Pseudo :
			<c:out value="${ juge.pseudo }" />
		</p>
		<p>
			Nom :
			<c:out value="${ juge.nom }" />
		</p>
		<p>
			Prénom :
			<c:out value="${ juge.prenom }" />
		</p>
		<p>
			Mots de passe :
			<c:out value="${ juge.mdp }" />
		</p>
	</div>
</form>