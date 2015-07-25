<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Log-in</title>
<link type="text/css"
	href=" <c:url value="boostrap/dist/css/bootstrap.css"/>" />

<link type="text/css" rel="stylsheet" href=" <c:url value="inc/css/tuto.css"/>" />

</head>
<body>
	<c:import url="/inc/Header-juge.jsp" />
	
 	<div class="container">
		<form  method="post" action="<c:url value="/Sign-in"/>">
				<legend>Connexion d'un juge</legend>
			<fieldset >
				<c:import url="/inc/JugeConnexionInc.jsp"/>				
			</fieldset><br/>
			
			<p class="info">${ form.resultat}</p>
			
            <c:if test="${!empty sessionScope.sessionUtilisateur}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
            <p class="succes">Vous êtes connecté(e) avec le pseuso : ${sessionScope.sessionUtilisateur.pseudo}</p>
            </c:if>
            
            <input type="submit" value="Connexion" class="" />
            <br/>

		</form>
		
	</div>


</body>
</html>