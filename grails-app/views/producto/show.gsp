
<%@ page import="com.luxsoft.impapx.Producto" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'producto.label', default: 'Producto')}" />
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
				
					<g:if test="${productoInstance?.clave}">
						<dt><g:message code="producto.clave.label" default="Clave" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="clave"/></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.descripcion}">
						<dt><g:message code="producto.descripcion.label" default="Descripcion" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="descripcion"/></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.unidad}">
						<dt><g:message code="producto.unidad.label" default="Unidad" /></dt>
						
							<dd><g:link controller="unidad" action="show" id="${productoInstance?.unidad?.id}">${productoInstance?.unidad?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.linea}">
						<dt><g:message code="producto.linea.label" default="Linea" /></dt>
						
							<dd><g:link controller="linea" action="show" id="${productoInstance?.linea?.id}">${productoInstance?.linea?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.marca}">
						<dt><g:message code="producto.marca.label" default="Marca" /></dt>
						
							<dd><g:link controller="marca" action="show" id="${productoInstance?.marca?.id}">${productoInstance?.marca?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.clase}">
						<dt><g:message code="producto.clase.label" default="Clase" /></dt>
						
							<dd><g:link controller="clase" action="show" id="${productoInstance?.clase?.id}">${productoInstance?.clase?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.acabado}">
						<dt><g:message code="producto.acabado.label" default="Acabado" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="acabado"/></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.color}">
						<dt><g:message code="producto.color.label" default="Color" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="color"/></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.ancho}">
						<dt><g:message code="producto.ancho.label" default="Ancho" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="ancho"/></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.calibre}">
						<dt><g:message code="producto.calibre.label" default="Calibre" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="calibre"/></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.caras}">
						<dt><g:message code="producto.caras.label" default="Caras" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="caras"/></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.dateCreated}">
						<dt><g:message code="producto.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${productoInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.gramos}">
						<dt><g:message code="producto.gramos.label" default="Gramos" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="gramos"/></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.kilos}">
						<dt><g:message code="producto.kilos.label" default="Kilos" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="kilos"/></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.largo}">
						<dt><g:message code="producto.largo.label" default="Largo" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="largo"/></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.lastUpdated}">
						<dt><g:message code="producto.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${productoInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.m2}">
						<dt><g:message code="producto.m2.label" default="M2" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="m2"/></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.precioContado}">
						<dt><g:message code="producto.precioContado.label" default="Precio Contado" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="precioContado"/></dd>
						
					</g:if>
				
					<g:if test="${productoInstance?.precioCredito}">
						<dt><g:message code="producto.precioCredito.label" default="Precio Credito" /></dt>
						
							<dd><g:fieldValue bean="${productoInstance}" field="precioCredito"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${productoInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${productoInstance?.id}">
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
