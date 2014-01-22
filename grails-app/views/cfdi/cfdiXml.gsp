<%@ page import="com.luxsoft.cfdi.Cfdi" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<title>CFDI (XML)</title>
	</head>
	<body>
	
		<div class="row">
			<div class="span9 offset2">
				<h1>Comprobante Fiscal : ${cfdi.id}</h1>
				<div>
					${xml}
				</div>
			</div>
		</div>
						
		
		
	</body>
</html>
