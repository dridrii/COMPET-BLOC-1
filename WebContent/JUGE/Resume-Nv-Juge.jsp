<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Log-in</title>
<link type="text/css"
	href=" <c:url value="boostrap/dist/css/bootstrap.css"/>" />

<link type="text/css" href=" <c:url value="inc/css/tuto.css"/>" />

</head>
<body>
	<c:import url="/inc/Header-juge.jsp" />
	
 	<div class="container">
		<form  method="get" action="<c:url value="/JUGE/Index-Juge"/>">
			<legend>Recap Nouveau Juge</legend>
			<fieldset >
				<c:import url="/inc/JugeResumeInc.jsp"/>
				<input type="submit" value="Acceuil"/>
			</fieldset>
		</form>
		
	</div>


</body>
</html>