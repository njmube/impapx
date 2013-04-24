<%--Template para una viasta con dos columnas una para tareas y otra para contenido --%>
<g:applyLayout name="luxor">
<html>
	<head>
		<meta name="layout" content="luxor">
		<title><g:layoutTitle/></title>
		<g:layoutHead/>
	</head>
	
	<body>
	<div class="container-fluid">
	
		<div class="row-fluid">
			<div class="span12">
				<div class="page-header">
					<g:pageProperty name="page.header" default="FALTA HEADER"/>
				</div>
			</div>
		</div>
			
		<div class="row-fluid">
			
			<div class="span2"><%-- Task Section --%>
				
				<div class="well well-small">
					<ul class="nav nav-list ">
						<li class="nav-header">
							<g:pageProperty name="page.consultasPanelTitle" default="Consultas"/>
						</li>
						<g:pageProperty name="page.consultas"/>
						<li class="nav-header">
							<g:pageProperty name="page.operacionesPanelTitle" default="Operaciones"/>
						</li>
						<g:pageProperty name="page.operaciones"/>
					</ul>
				</div>
				
				
			</div>  <%--End task section --%>
			
			<div class="span10"> <!-- Document section -->
				<g:pageProperty name="page.document" />
			</div><!--  End document section -->
			
		</div>
		
	</div>
		
	</body>
</html>
</g:applyLayout>