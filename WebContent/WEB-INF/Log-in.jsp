<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Log-in</title>
<link type="text/css"
	href=" <c:url value="bootstrap/dist/css/bootstrap.css"/>" />

<link type="text/css" rel="stylsheet"
	href=" <c:url value="inc/css/tuto.css"/>" />

</head>
<body>
	<c:import url="/inc/Header-public.jsp" />

	<div class="container">
		<form method="post" action="<c:url value="/Log-in"/>">
			<legend>Inscription d'un nouveau juge</legend>
			<fieldset>

				<c:import url="/inc/JugeFormInc.jsp" />
			</fieldset>
			<br />

			<p class="info">${ form.resultat}</p>
			<input type="submit" value="Valider" /> <input type="reset"
				value="Remettre à zéro" /> <br />
		</form>

	</div>


</body>
</html>