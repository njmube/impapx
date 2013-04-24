
<%@ page import="com.luxsoft.impapx.Direccion" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'direccion.label', default: 'Direccion')}" />
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
				
					<g:if test="${direccionInstance?.calle}">
						<dt><g:message code="direccion.calle.label" default="Calle" /></dt>
						
							<dd><g:fieldValue bean="${direccionInstance}" field="calle"/></dd>
						
					</g:if>
				
					<g:if test="${direccionInstance?.numeroInterior}">
						<dt><g:message code="direccion.numeroInterior.label" default="Numero Interior" /></dt>
						
							<dd><g:fieldValue bean="${direccionInstance}" field="numeroInterior"/></dd>
						
					</g:if>
				
					<g:if test="${direccionInstance?.numeroExterior}">
						<dt><g:message code="direccion.numeroExterior.label" default="Numero Exterior" /></dt>
						
							<dd><g:fieldValue bean="${direccionInstance}" field="numeroExterior"/></dd>
						
					</g:if>
				
					<g:if test="${direccionInstance?.colonia}">
						<dt><g:message code="direccion.colonia.label" default="Colonia" /></dt>
						
							<dd><g:fieldValue bean="${direccionInstance}" field="colonia"/></dd>
						
					</g:if>
				
					<g:if test="${direccionInstance?.municipio}">
						<dt><g:message code="direccion.municipio.label" default="Municipio" /></dt>
						
							<dd><g:fieldValue bean="${direccionInstance}" field="municipio"/></dd>
						
					</g:if>
				
					<g:if test="${direccionInstance?.codigoPostal}">
						<dt><g:message code="direccion.codigoPostal.label" default="Codigo Postal" /></dt>
						
							<dd><g:fieldValue bean="${direccionInstance}" field="codigoPostal"/></dd>
						
					</g:if>
				
					<g:if test="${direccionInstance?.estado}">
						<dt><g:message code="direccion.estado.label" default="Estado" /></dt>
						
							<dd><g:fieldValue bean="${direccionInstance}" field="estado"/></dd>
						
					</g:if>
				
					<g:if test="${direccionInstance?.pais}">
						<dt><g:message code="direccion.pais.label" default="Pais" /></dt>
						
							<dd><g:fieldValue bean="${direccionInstance}" field="pais"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${direccionInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${direccionInstance?.id}">
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
