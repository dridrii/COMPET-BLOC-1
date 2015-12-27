<%@ page pageEncoding="UTF-8"%>

<link type="text/css" rel="stylsheet"
	href=" <c:url value="inc/css/tuto.css"/>" />


<label for="NumBlocTX">Numéro du bloc<span class="requis">*</span></label>
<input type="number" id="NumBlocTX" value="<c:out value="${bloc.NumBloc}"/>" name="NumBlocTX" size="30"
	maxlength="30" />
<span class="erreur">${form.erreurs['NumBloc']}</span>
<br />

<label for="CouleurDiffTX">Couleur de la difficulté<span
	class="requis">*</span></label>
<input type="text" id="CouleurDiffTX" name="CouleurDiffTX" size="30"
	maxlength="30" value="<c:out value="${bloc.CouleurDiff}"/>" />

<span class="erreur">${form.erreurs['CouleurDiff']}</span>
<br />

<label for="CouleurVoieTX">Couleur de la voie<span class="requis">*</span></label>
<input type="text" id="CouleurVoieTX" name="CouleurVoieTX" value="<c:out value="${bloc.CouleurVoie}"/>"size="30"
	maxlength="30" />
<span class="erreur">${form.erreurs['CouleurVoie']}</span>
<br />

<label for="OuvreurTX">Ouvreur<span class="requis">*</span></label>
<input type="text" id="OuvreurTX" name="OuvreurTX" value="<c:out value="${bloc.Ouvreur}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['Ouvreur']}</span>
<br />

<label for="ValeurInitTX">Valeur initiale<span class="requis">*</span></label>
<input type="number" id="ValeurInitTX" value="<c:out value="${bloc.ValeurInit}"/>" name="ValeurInitTX" />
<br />
