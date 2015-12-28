<%@ page pageEncoding="UTF-8"%>

<form>
	<div id="corps">
		<p class="info">${ form.resultat }</p>
		<p>
			Numéro du Bloc :
			<c:out value="${bloc.numBloc}" />
		</p>
		<p>
			Couleur de la difficulté :
			<c:out value="${bloc.couleurDiff}" />
		</p>
		<p>
			Couleur de la voie :
			<c:out value="${bloc.couleurVoie}" />
		</p>
		<p>
			Ouvreur :
			<c:out value="${bloc.ouvreur}" />
		</p>
		<p>
			Valeur Initiale :
			<c:out value="${bloc.valeurInit}" />
		</p>

	</div>
</form>