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
				<g:pageProperty name="page.header" default="FALTA HEADER"/>
			</div>
		</div>
			
		<div class="row-fluid">
			<div class="span12"> <!-- Document section -->
				<g:pageProperty name="page.document" />
			</div><!--  End document section -->
		</div>
		
	</div>
		
	</body>
</html>
</g:applyLayout>