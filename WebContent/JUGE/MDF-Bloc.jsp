<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Modification Bloc</title>
<link type="text/css"
	href=" <c:url value="bootstrap/dist/css/bootstrap.css"/>" />

<link type="text/css" rel="stylsheet"
	href=" <c:url value="inc/css/tuto.css"/>" />

</head>
<body>
	<c:import url="/inc/Header-juge.jsp" />

	<div class="container">
		<form method="post" action="<c:url value="/JUGE/ModifierBloc"/>">
			<legend>Modification d'un bloc</legend>
			<fieldset>

				<c:import url="/inc/BlocFormInc.jsp" />
			</fieldset>
			<br />

			<p class="info">${ form.resultat}</p>
			<input type="submit" value="Soumettre" /> <input type="reset"
				value="Remettre à zéro" />  <input type="button" value="Annuler" onclick='location.href="Liste-Bloc"'/><br />
		</form>

	</div>


</body>
</html>