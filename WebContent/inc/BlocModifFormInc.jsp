<%@ page pageEncoding="UTF-8"%>

<link type="text/css" rel="stylsheet"
	href=" <c:url value="inc/css/tuto.css"/>" />

<input type="hidden" id="idParticipant" name="idParticipant"
	value="<c:out value="${participant.idParticipant}"/>" size="30"
	maxlength="30" />

<label for="dossardParticipant">Dossard<span class="requis">*</span></label>
<input type="number" id="dossardParticipant" name="dossardParticipant"
	value="<c:out value="${participant.dossard}"/>" size="30"
	maxlength="30" />
<span class="erreur">${form.erreurs['dossardParticipant']}</span>
<br />

<label for="nomParticipant">Nom<span class="requis">*</span></label>
<input type="text" id="nomParticipant" name="nomParticipant"
	value="<c:out value="${participant.nom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['nomPartcipant']}</span>
<br />

<label for="prenomParticipant">Prénom<span class="requis">*</span></label>
<input type="text" id="prenomParticipant" name="prenomParticipant"
	value="<c:out value="${participant.prenom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['prenomParticipant']}</span>
<br />

<label for="ageParticipant">Age<span class="requis">*</span></label>
<input type="number" id="ageParticipant" name="ageParticipant"
	value="<c:out value="${participant.age}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['ageParticipant']}</span>
<br />



<c:choose>

	<c:when test="${(participant.sex).equalsIgnoreCase('Homme')}">

		<label for="sexParticipant">Sex<span class="requis">*</span></label>
		<input type="radio" id="sexParticipant" name="sexParticipant" checked
			value="Homme" />Homme
		<br />
		<label for="sexParticipant"><span class="requis"></span></label>
		<input type="radio" id="sexParticipant" name="sexParticipant"
			value="Femme" />Femme


	</c:when>

	<c:otherwise>
		<label for="sexParticipant">Sex<span class="requis">*</span></label>
		<input type="radio" id="sexParticipant" name="sexParticipant"
			value="Homme" />Homme
		<br />
		<label for="sexParticipant"><span class="requis"></span></label>
		<input type="radio" id="sexParticipant" name="sexParticipant" checked
			value="Femme" />Femme

<br />
	</c:otherwise>

</c:choose>

<span class="erreur">${form.erreurs['numParticipant']}</span>
<br />
