<link href="bootstrap/dist/css/bootstrap.css" rel="stylesheet">
<link href="inc/css/tuto.css" rel="stylesheet">

<header>

	<a href="#"> <img class="img-logo" src="src/Logo-grimpe-bloc.png"
		alt="Grimpebloc" id="logo">
	</a>

	<div class="container">
		<nav class="navbar navbar-default">
			<div class="navbar-header">
				<a class="navbar-brand" href="/COMPET-BLOC-1/Index-Juge">COMPET-BLOC</a>
			</div>
			<div>
				<ul class="nav navbar-nav">

					<li><a href="#">News</a></li>

					<li class="dropdown">
						<%--Menu déroulant blocs --%> <a data-toggle="dropdown" href="#">Blocs
							<b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#">Résultat Blocs</a></li>
						</ul>
					</li>

				</ul>

				<ul class="nav navbar-default navbar-nav navbar-right">
					<%--Deuxième partie de la barre de navigation --%>
					<li class="dropdown">
						<%--Menu déroulant classement --%> <a data-toggle="dropdown"
						href="">Login <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<c:if test="${empty sessionScope.sessionUtilisateur}">
								<li><a class="glyphicon glyphicon-user"
									href="/COMPET-BLOC-1/Log-in"> Sign Up</a></li>
							</c:if>
							<c:if test="${empty sessionScope.sessionUtilisateur}">
								<li><a class="glyphicon glyphicon-log-in"
									href="/COMPET-BLOC-1/Sign-in"> Login</a></li>
							</c:if>
							<c:if test="${!empty sessionScope.sessionUtilisateur}">
								<li><a class="glyphicon glyphicon-log-out"
									href="/COMPET-BLOC-1/Index-Public"> Log-out</a></li>
							</c:if>
						</ul>
					</li>
				</ul>
			</div>
		</nav>
		<%--barre de navigation --%>
	</div>
	<%--container --%>



	<script src="<c:url value="/bootstrap/js/jquery.js"/>"></script>
	<script src="<c:url value="/bootstrap/dist/js/bootstrap.js"/>"></script>
</header>


