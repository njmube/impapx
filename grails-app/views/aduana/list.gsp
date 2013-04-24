

<%@ page import="com.luxsoft.impapx.Aduana" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'aduana.label', default: 'Aduana')}" />
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
							
							<g:sortableColumn property="nombre" title="${message(code: 'aduana.nombre.label', default: 'Nombre')}" />
						
							<th class="header"><g:message code="aduana.direccion.label" default="Direccion" /></th>
						
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${aduanaInstanceList}" var="aduanaInstance">
							<tr>
								
						<td><g:link action="show" id="${aduanaInstance.id}">${fieldValue(bean: aduanaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: aduanaInstance, field: "direccion")}</td>
					
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${aduanaInstanceTotal}" />
				</div>
			</div>

		</div>
	</div>
</body>
</html>
