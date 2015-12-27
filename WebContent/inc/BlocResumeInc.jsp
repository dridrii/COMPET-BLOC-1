<%@ page pageEncoding="UTF-8"%>

<form>
	<div id="corps">
		<p class="info">${ form.resultat }</p>
		<p>
			Numéro du Bloc :
			<c:out value="${ bloc.NumBloc }" />
		</p>
		<p>
			Couleur de la difficulté :
			<c:out value="${ bloc.CouleurDiff }" />
		</p>
		<p>
			Couleur de la voie :
			<c:out value="${ bloc.CouleurVoie }" />
		</p>
		<p>
			Ouvreur :
			<c:out value="${ bloc.Ouvreur }" />
		</p>
		<p>
			Valeur Initiale :
			<c:out value="${ bloc.ValeurInit }" />
		</p>

	</div>
</form>