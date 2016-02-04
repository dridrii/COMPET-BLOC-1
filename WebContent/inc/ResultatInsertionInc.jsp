<%@ page pageEncoding="UTF-8"%>

<link type="text/css" rel="stylsheet"
	href=" <c:url value="inc/css/tuto.css"/>" />

<label for="dossardParticipant">Dossard<span class="requis">*</span></label>
<input type="number" id="dossardParticipant" name="dossardParticipant"
	value="<c:out value="${participant.dossard}"/>" size="30"
	maxlength="30" />
<span class="erreur">${form.erreurs['dossardParticipant']}</span>

<br />

<label for="NumBlocTX">Numéro du bloc<span class="requis">*</span></label>
<input type="number" id="NumBlocTX"
	value="<c:out value="${bloc.numBloc}"/>" name="NumBlocTX" size="30"
	maxlength="30" />
<br />
