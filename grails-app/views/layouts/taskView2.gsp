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
			
			<div class="span3"><%-- Task Section --%>
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">
							<g:pageProperty name="page.taskTitle" default="Opciones"/>
						</li>
						<g:pageProperty name="page.actions"/>
					</ul>
				</div>
			</div>  <%--End task section --%>
			
			<div class="span9"> <!-- Document section -->
				<g:pageProperty name="page.document" />
			</div><!--  End document section -->
			
		</div>
		
	</div>
		
	</body>
</html>
</g:applyLayout>