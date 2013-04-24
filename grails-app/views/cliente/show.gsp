
<%@ page import="com.luxsoft.impapx.Cliente" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'cliente.label', default: 'Cliente')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
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
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span9">

				<div class="page-header">
					<h3><g:message code="default.show.label" args="[entityName]" /></h3>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${clienteInstance?.nombre}">
						<dt><g:message code="cliente.nombre.label" default="Nombre" /></dt>
						
							<dd><g:fieldValue bean="${clienteInstance}" field="nombre"/></dd>
						
					</g:if>
				
					<g:if test="${clienteInstance?.rfc}">
						<dt><g:message code="cliente.rfc.label" default="Rfc" /></dt>
						
							<dd><g:fieldValue bean="${clienteInstance}" field="rfc"/></dd>
						
					</g:if>
				
					<g:if test="${clienteInstance?.email1}">
						<dt><g:message code="cliente.email1.label" default="Email1" /></dt>
						
							<dd><g:fieldValue bean="${clienteInstance}" field="email1"/></dd>
						
					</g:if>
				
					<g:if test="${clienteInstance?.formaDePago}">
						<dt><g:message code="cliente.formaDePago.label" default="Forma De Pago" /></dt>
						
							<dd><g:fieldValue bean="${clienteInstance}" field="formaDePago"/></dd>
						
					</g:if>
				
					<g:if test="${clienteInstance?.cuentaDePago}">
						<dt><g:message code="cliente.cuentaDePago.label" default="Cuenta  de Pago" /></dt>
						
							<dd><g:fieldValue bean="${clienteInstance}" field="cuentaDePago"/></dd>
						
					</g:if>
				
					<g:if test="${clienteInstance?.direccion}">
						<dt><g:message code="cliente.direccion.label" default="Dirección" /></dt>
						
							<dd><g:render template="/layouts/direccion/showDireccion" model="[direccion:clienteInstance.direccion]" bean="${clienteInstance.direccion}"/>   </dd>
						
					</g:if>
				
					<g:if test="${clienteInstance?.dateCreated}">
						<dt><g:message code="cliente.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${clienteInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${clienteInstance?.fisica}">
						<dt><g:message code="cliente.fisica.label" default="Fisica" /></dt>
						
							<dd><g:formatBoolean boolean="${clienteInstance?.fisica}" /></dd>
						
					</g:if>
				
					<g:if test="${clienteInstance?.lastUpdated}">
						<dt><g:message code="cliente.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${clienteInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${clienteInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${clienteInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
