

<%@ page import="com.luxsoft.impapx.Empresa" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'empresa.label', default: 'Empresa')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">

			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active"><g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link></li>
						<li><g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link></li>
					</ul>
				</div>
			</div>

			<div class="span9">

				<div class="page-header">
					<h2>
						<g:message code="default.list.label" args="[entityName]" />
					</h2>
				</div>

				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<table class="table table-striped table-hover table-bordered table-condensed">
					<thead>
						<tr>
							
							<g:sortableColumn property="nombre" title="${message(code: 'empresa.nombre.label', default: 'Nombre')}" />
						
							<g:sortableColumn property="rfc" title="${message(code: 'empresa.rfc.label', default: 'Rfc')}" />
						
							<g:sortableColumn property="regimen" title="${message(code: 'empresa.regimen.label', default: 'Regimen')}" />
						
							<g:sortableColumn property="direccion" title="${message(code: 'empresa.direccion.label', default: 'Direccion')}" />
						
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${empresaInstanceList}" var="empresaInstance">
							<tr>
								
						<td><g:link action="show" id="${empresaInstance.id}">${fieldValue(bean: empresaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: empresaInstance, field: "rfc")}</td>
					
						<td>${fieldValue(bean: empresaInstance, field: "regimen")}</td>
					
						<td>${fieldValue(bean: empresaInstance, field: "direccion")}</td>
					
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${empresaInstanceTotal}" />
				</div>
			</div>

		</div>
	</div>
</body>
</html>
