

<%@ page import="com.luxsoft.impapx.Cliente" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'cliente.label', default: 'Cliente')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">

			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">Operaciones</li>
						<li class="active"><g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
								Catálogo
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
					<h3>
						<g:message code="cliente.list.label"  />
					</h3>
				</div>

				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<table class="table table-striped table-hover table-bordered table-condensed">
					<thead>
						<tr>
							
							<g:sortableColumn property="nombre" title="${message(code: 'cliente.nombre.label', default: 'Nombre')}" />
						
							<g:sortableColumn property="rfc" title="${message(code: 'cliente.rfc.label', default: 'Rfc')}" />
						
							<g:sortableColumn property="email1" title="${message(code: 'cliente.email1.label', default: 'Email1')}" />
						
							<g:sortableColumn property="formaDePago" title="${message(code: 'cliente.formaDePago.label', default: 'Forma De Pago')}" />
						
							<g:sortableColumn property="lastUpdated" title="${message(code: 'cliente.cuentaPago.label', default: 'Modificado')}" />
						
							<th class="header"><g:message code="cliente.direccion.label" default="Direccion" /></th>
						
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${clienteInstanceList}" var="clienteInstance">
							<tr>
								
						<td><g:link action="show" id="${clienteInstance.id}">${fieldValue(bean: clienteInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: clienteInstance, field: "rfc")}</td>
					
						<td>${fieldValue(bean: clienteInstance, field: "email1")}</td>
					
						<td>${fieldValue(bean: clienteInstance, field: "formaDePago")}</td>
					
						<td>${fieldValue(bean: clienteInstance, field: "cuentaDePago")}</td>
					
						<td>${fieldValue(bean: clienteInstance, field: "direccion")}</td>
					
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${clienteInstanceTotal}" />
				</div>
			</div>

		</div>
	</div>
</body>
</html>
