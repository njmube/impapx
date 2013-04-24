<%@ page import="com.luxsoft.impapx.Proveedor" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'proveedor.label', default: 'Proveedor')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li class="active">
							<g:link class="create" action="create">
								<i class="icon-plus icon-white"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span9">

				<div class="page-header">
					<h3><g:message code="default.create.label" args="[entityName]" /></h3>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${proveedorInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${proveedorInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="form-horizontal" action="create" >
						<fieldset>
							<f:with bean="proveedorInstance">
				<f:field property="nombre" value="${proveedorInstance?.nombre}" input-class="span7"/>
				<f:field property="factorDeUtilidad" input-class="numericEditField"/>
				<f:field property="tipoDeCosteo" />
				<f:field property="plazo" />
				<f:field property="vencimentoBl" />
				<f:field property="lineaDeCredito" label="Línea de Crédito"/>
				<f:field property="nacional"/>
				<f:field property="correoElectronico"/>
				<f:field property="www" label="Página WEB"/>
				
				<f:field property="direccion" value="${proveedorInstance?.direccion}"/>
			</f:with>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.create.label" default="Create" />
								</button>
							</div>
						</fieldset>
					</g:form>
				</fieldset>
				
			</div>

		</div>
	</body>
</html>