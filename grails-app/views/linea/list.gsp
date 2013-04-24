

<%@ page import="com.luxsoft.impapx.Linea" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'linea.label', default: 'Linea')}" />
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
						<li><g:link action="importarLineas" controller="importador" 
								onclick="return myConfirm2(this,'Importar lineas de SiipapEx','Importación de información');">
								<i class="icon-download"></i> Importar
							</g:link>
						</li>
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
							
							<g:sortableColumn property="nombre" title="${message(code: 'linea.nombre.label', default: 'Nombre')}" />
						
							<g:sortableColumn property="dateCreated" title="${message(code: 'linea.dateCreated.label', default: 'Date Created')}" />
						
							<g:sortableColumn property="lastUpdated" title="${message(code: 'linea.lastUpdated.label', default: 'Last Updated')}" />
						
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${lineaInstanceList}" var="lineaInstance">
							<tr>
								
						<td><g:link action="show" id="${lineaInstance.id}">${fieldValue(bean: lineaInstance, field: "nombre")}</g:link></td>
					
						<td><g:formatDate date="${lineaInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${lineaInstance.lastUpdated}" /></td>
					
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${lineaInstanceTotal}" />
				</div>
			</div>

		</div>
	</div>
</body>
</html>
