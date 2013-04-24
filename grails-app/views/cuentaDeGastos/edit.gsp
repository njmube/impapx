<%@ page import="com.luxsoft.impapx.CuentaDeGastos" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'cuentaDeGastos.label', default: 'Cuenta De Gastos')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		<r:require modules="luxorTableUtils,dataTables"/>
	</head>
	<body>
		<div class="container-fluid">
		<div class="row-fluid">

			<div class="span2">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span10">

				<div class="page-header">
					<h3><g:message code="default.edit.label" args="[entityName]" /></h3>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${cuentaDeGastosInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${cuentaDeGastosInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>
				<ul class="nav nav-tabs" id="myTab">
					<li class=""><a href="#cuenta" data-toggle="tab">Cuenta</a></li>
					<li class="active"><a href="#facturas" data-toggle="tab">Facturas</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane " id="cuenta">
						<g:render template="editForm" bean="${cuentaDeGastosInstance}"/>
					</div>
					
					<div class="tab-pane active" id="facturas">
						<g:render template="facturasPanel" bean="${cuentaDeGastosInstance}"/>
					</div>
					<%--
					<div class="tab-pane" id="embarques">
						<g:render template="embarquesPanel" bean="${cuentaDeGastosInstance}"/>
					</div>
					 --%>
				</div>
				

			</div>

		</div>
		</div>
	</body>
</html>
