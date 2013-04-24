<%@ page import="com.luxsoft.impapx.FacturaDeImportacion" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<title><g:message code="cuentaPorPagar.edit.label" default="Edición de factura" /></title>
		<r:require module="datepicker"/>
		<r:require module="luxorForms"/>
		<r:require module="dataTables"/>
	</head>
	
	<body>
		<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">
							Opciones
						</li>
						<li class="active">
							<g:link action="list">
							<i class="icon-list "></i>
							Facturas de importación
							</g:link>
						</li>
						<li>
							<g:link  action="create">
							<i class="icon-plus "></i>
							<g:message code="cuentaPorPagar.create.label"  />
							</g:link>
						</li>
					</ul>
				</div>
			</div>

			<div class="span9">
			
				<div class="alert">
					<h4><strong>
					Factura: ${cuentaPorPagarInstance.id} (${cuentaPorPagarInstance.documento}) ${cuentaPorPagarInstance.proveedor.nombre}
					</strong></h4>
				</div>
				
				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:hasErrors bean="${cuentaPorPagarInstance}">
					<bootstrap:alert class="alert-error">
						<ul>
							<g:eachError bean="${cuentaPorPagarInstance}" var="error">
								<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-	id="${error.field}"</g:if>><g:message error="${error}"/></li>
							</g:eachError>
						</ul>
					</bootstrap:alert>
				</g:hasErrors>
				<%-- Tabs --%>
				<ul class="nav nav-tabs" id="mainTab">
  					<li class="active" ><a href="#facturasPanel" data-toggle="tab">Factura</a></li>
  					<li><a href="#embarquesPanel" data-toggle="tab">Embarques</a></li>
  					<li><a href="#contenedoresPanel" data-toggle="tab">Contenedores</a></li>
				</ul>
				
				<%-- Tab Content --%>
				<div class="tab-content">
					<div class="tab-pane fade in active" id="facturasPanel">
						<g:render template="form" bean="${cuentaPorPagarInstance}" model="[action:'edit']"/>
					</div>
					<div class="tab-pane fade in" id="embarquesPanel">
						PENDIENTE
					</div>
					<div class="tab-pane fade in" id="contenedoresPanel">
						CONTENEDORES PENDIENTES
					</div>
				</div>			
			</div>

		</div>
	</div>

	
	</body>
</html>
