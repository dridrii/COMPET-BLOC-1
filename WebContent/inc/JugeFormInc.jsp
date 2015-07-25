<%@ page pageEncoding="UTF-8" %>
<link type="text/css" rel="stylsheet" href=" <c:url value="inc/css/tuto.css"/>" />
<label for="pseudoJuge">Pseudo<span class="requis">*</span></label>
<input type="text" id="pseudoJuge" name="pseudoJuge" value="<c:out value="${juge.pseudo}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['pseudoJuge']}</span>
<br />

<label for="nomJuge">Nom<span class="requis">*</span></label>
<input type="text" id="nomJuge" name="nomJuge" value="<c:out value="${juge.nom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['nomJuge']}</span>
<br />

<label for="prenomJuge">Prénom<span class="requis">*</span></label>
<input type="text" id="prenomJuge" name="prenomJuge" value="<c:out value="${juge.prenom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['prenomJuge']}</span>
<br />

<label for="mdpJuge">Mots de passe<span class="requis">*</span></label>
<input type="password" id="mdpJuge" name="mdpJuge" value="<c:out value="${juge.mdp}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['mdpJuge']}</span>
<br />

<label for="validmdpJuge">Confirmation du mots de passe<span class="requis">*</span></label>
<input type="password" id="validmdpJuge" name="validmdpJuge" value="" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['validmdpJuge']}</span>
<br />

<span class="erreur">${form.erreurs['numJuge']}</span>
<br />
