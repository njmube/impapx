

<%@ page import="com.luxsoft.impapx.EmbarqueDet" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'embarqueDet.label', default: 'EmbarqueDet')}" />
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
							
							<th class="header"><g:message code="embarqueDet.producto.label" default="Producto" /></th>
						
							<th class="header"><g:message code="embarqueDet.compraDet.label" default="Compra Det" /></th>
						
							<g:sortableColumn property="contenedor" title="${message(code: 'embarqueDet.contenedor.label', default: 'Contenedor')}" />
						
							<g:sortableColumn property="tarimas" title="${message(code: 'embarqueDet.tarimas.label', default: 'Tarimas')}" />
						
							<g:sortableColumn property="cantidad" title="${message(code: 'embarqueDet.cantidad.label', default: 'Cantidad')}" />
						
							<g:sortableColumn property="costoBruto" title="${message(code: 'embarqueDet.costoBruto.label', default: 'Costo Bruto')}" />
						
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${embarqueDetInstanceList}" var="embarqueDetInstance">
							<tr>
								
						<td><g:link action="show" id="${embarqueDetInstance.id}">${fieldValue(bean: embarqueDetInstance, field: "producto")}</g:link></td>
					
						<td>${fieldValue(bean: embarqueDetInstance, field: "compraDet")}</td>
					
						<td>${fieldValue(bean: embarqueDetInstance, field: "contenedor")}</td>
					
						<td>${fieldValue(bean: embarqueDetInstance, field: "tarimas")}</td>
					
						<td>${fieldValue(bean: embarqueDetInstance, field: "cantidad")}</td>
					
						<td>${fieldValue(bean: embarqueDetInstance, field: "costoBruto")}</td>
					
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${embarqueDetInstanceTotal}" />
				</div>
			</div>

		</div>
	</div>
</body>
</html>
