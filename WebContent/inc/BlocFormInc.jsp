<%@ page pageEncoding="UTF-8"%>

<link type="text/css" rel="stylsheet"
	href=" <c:url value="inc/css/tuto.css"/>" />


<label for="NumBlocTX">Numéro du bloc<span class="requis">*</span></label>
<input type="number" id="NumBlocTX" value="<c:out value="${bloc.numBloc}"/>" name="NumBlocTX" size="30"
	maxlength="30" />
<span class="erreur">${form.erreurs['NumBlocTX']}</span>
<br />

<label for="CouleurDiffTX">Couleur de la difficulté<span
	class="requis">*</span></label>
<input type="text" id="CouleurDiffTX" name="CouleurDiffTX" size="30"
	maxlength="30" value="<c:out value="${bloc.couleurDiff}"/>" />

<span class="erreur">${form.erreurs['couleurDiff']}</span>
<br />

<label for="CouleurVoieTX">Couleur de la voie<span class="requis">*</span></label>
<input type="text" id="CouleurVoieTX" name="CouleurVoieTX" value="<c:out value="${bloc.couleurVoie}"/>"size="30"
	maxlength="30" />
<span class="erreur">${form.erreurs['couleurVoie']}</span>
<br />

<label for="OuvreurTX">Ouvreur<span class="requis">*</span></label>
<input type="text" id="OuvreurTX" name="OuvreurTX" value="<c:out value="${bloc.ouvreur}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['ouvreur']}</span>
<br />

<label for="ValeurInitTX">Valeur initiale<span class="requis">*</span></label>
<input type="number" id="ValeurInitTX" value="<c:out value="${bloc.valeurInit}"/>" name="ValeurInitTX" />
<br />
