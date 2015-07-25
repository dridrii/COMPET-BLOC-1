<%@ page pageEncoding="UTF-8" %>

<label for="pseudo">Pseudo <span class="requis">*</span></label>
<input type="text" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" size="20" maxlength="60" />
<span class="erreur">${form.erreurs['pseudo']}</span>
<br />

<label for="mdp">Mot de passe <span class="requis">*</span></label>
<input type="password" id="mdp" name="mdp" value="" size="20" maxlength="20" />
<span class="erreur">${form.erreurs['mdp']}</span>
<br />

