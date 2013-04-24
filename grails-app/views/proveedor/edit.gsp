<%@ page import="com.luxsoft.impapx.Proveedor" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'proveedor.label', default: 'Proveedor')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		<r:require module="dataTables"/>
		<r:require module="luxorForms"/>
		<r:require module="luxorTableUtils"/>
	</head>
	<body>
		<div class="row-fluid">

			<div class="span2">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="proveedor.list.label"/></li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
								Catálogo
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="proveedor.create.label" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span10">

				<div >
					<h4><g:message code="default.edit.label" args="[proveedorInstance]" /></h4>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${proveedorInstance}">
					<bootstrap:alert class="alert-error">
						<ul>
							<g:eachError bean="${proveedorInstance}" var="error">
							<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-	id="${error.field}"</g:if>><g:message error="${error}"/>
							</li>
							</g:eachError>
						</ul>
					</bootstrap:alert>
				</g:hasErrors>

				<ul class="nav nav-tabs">
					<li><a href="#generales" data-toggle="tab">Generales</a></li>
					<li><a href="#productos" data-toggle="tab">Productos</a></li>
					<li><a href="#contactos" data-toggle="tab">Contactos</a></li>
				</ul>

				<div class="tab-content">
					<div class="tab-pane " id="generales">
						<g:render template="form" model="['proveedorInstance':proveedorInstance]"/>
					</div>
					<div class="tab-pane active" id="productos">
						<g:render template="productos" model="['proveedorInstance':proveedorInstance]"/>
					</div>
					<div class="tab-pane" id="contactos">
						<h1>Contactos</h1>
					</div>
				</div>
			</div>

		</div>
		
		
		
	</body>
</html>
