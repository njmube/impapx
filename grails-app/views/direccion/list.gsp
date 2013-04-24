

<%@ page import="com.luxsoft.impapx.Direccion" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'direccion.label', default: 'Direccion')}" />
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
							
							<g:sortableColumn property="calle" title="${message(code: 'direccion.calle.label', default: 'Calle')}" />
						
							<g:sortableColumn property="numeroInterior" title="${message(code: 'direccion.numeroInterior.label', default: 'Numero Interior')}" />
						
							<g:sortableColumn property="numeroExterior" title="${message(code: 'direccion.numeroExterior.label', default: 'Numero Exterior')}" />
						
							<g:sortableColumn property="colonia" title="${message(code: 'direccion.colonia.label', default: 'Colonia')}" />
						
							<g:sortableColumn property="municipio" title="${message(code: 'direccion.municipio.label', default: 'Municipio')}" />
						
							<g:sortableColumn property="codigoPostal" title="${message(code: 'direccion.codigoPostal.label', default: 'Codigo Postal')}" />
						
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${direccionInstanceList}" var="direccionInstance">
							<tr>
								
						<td><g:link action="show" id="${direccionInstance.id}">${fieldValue(bean: direccionInstance, field: "calle")}</g:link></td>
					
						<td>${fieldValue(bean: direccionInstance, field: "numeroInterior")}</td>
					
						<td>${fieldValue(bean: direccionInstance, field: "numeroExterior")}</td>
					
						<td>${fieldValue(bean: direccionInstance, field: "colonia")}</td>
					
						<td>${fieldValue(bean: direccionInstance, field: "municipio")}</td>
					
						<td>${fieldValue(bean: direccionInstance, field: "codigoPostal")}</td>
					
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${direccionInstanceTotal}" />
				</div>
			</div>

		</div>
	</div>
</body>
</html>
