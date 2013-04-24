
<%@ page import="com.luxsoft.impapx.Proveedor" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'proveedor.label', default: 'Proveedor')}" />
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
				
					<g:if test="${proveedorInstance?.nombre}">
						<dt><g:message code="proveedor.nombre.label" default="Nombre" /></dt>
						
							<dd><g:fieldValue bean="${proveedorInstance}" field="nombre"/></dd>
						
					</g:if>
				
					<g:if test="${proveedorInstance?.dateCreated}">
						<dt><g:message code="proveedor.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${proveedorInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${proveedorInstance?.lastUpdated}">
						<dt><g:message code="proveedor.lastUpdated.label" default="Last Updated" /></dt>
							<dd><g:formatDate date="${proveedorInstance?.lastUpdated}" /></dd>
						
					</g:if>	
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${proveedorInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${proveedorInstance?.id}">
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


