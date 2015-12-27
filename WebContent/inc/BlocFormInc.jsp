<%@ page pageEncoding="UTF-8"%>

<link type="text/css" rel="stylsheet"
	href=" <c:url value="inc/css/tuto.css"/>" />


<label for="NumBloc">Numéro du Bloc<span class="requis">*</span></label>
<input type="number" id="NumBloc" value="<c:out value="${bloc.NumBloc}"/>" name="NumBloc" size="30"
	maxlength="30" />
<span class="erreur">${form.erreurs['NumBloc']}</span>
<br />

<label for="CouleurDiff">Couleur de la difficulté<span
	class="requis">*</span></label>
<input type="text" id="CouleurDiff" name="CouleurDiff" size="30"
	maxlength="30" value="<c:out value="${bloc.CouleurDiff}"/>" />

<span class="erreur">${form.erreurs['CouleurDiff']}</span>
<br />

<label for="CouleurVoie">Couleur de la voie<span class="requis">*</span></label>
<input type="text" id="CouleurVoie" name="CouleurVoie" value="<c:out value="${bloc.CouleurVoie}"/>"size="30"
	maxlength="30" />
<span class="erreur">${form.erreurs['CouleurVoie']}</span>
<br />

<label for="Ouvreur">Ouvreur<span class="requis">*</span></label>
<input type="text" id="Ouvreur" name="Ouvreur" value="<c:out value="${bloc.Ouvreur}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['Ouvreur']}</span>
<br />

<label for="ValeurInit">Valeur initiale<span class="requis">*</span></label>
<input type="number" id="ValeurInit" value="<c:out value="${bloc.ValeurInit}"/>" name="ValeurInit" />
<br />
