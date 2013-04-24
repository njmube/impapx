

<%@ page import="com.luxsoft.impapx.Clase" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'clase.label', default: 'Clase')}" />
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
							</g:link>
						</li>
						<li><g:link action="importarClases" controller="importador" 
								onclick="return myConfirm2(this,'Importar clases de SiipapEx','Importación de información');">
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
							
							<g:sortableColumn property="nombre" title="${message(code: 'clase.nombre.label', default: 'Nombre')}" />
						
							<g:sortableColumn property="dateCreated" title="${message(code: 'clase.dateCreated.label', default: 'Date Created')}" />
						
							<g:sortableColumn property="lastUpdated" title="${message(code: 'clase.lastUpdated.label', default: 'Last Updated')}" />
						
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${claseInstanceList}" var="claseInstance">
							<tr>
								
						<td><g:link action="show" id="${claseInstance.id}">${fieldValue(bean: claseInstance, field: "nombre")}</g:link></td>
					
						<td><g:formatDate date="${claseInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${claseInstance.lastUpdated}" /></td>
					
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${claseInstanceTotal}" />
				</div>
			</div>

		</div>
	</div>
</body>
</html>
