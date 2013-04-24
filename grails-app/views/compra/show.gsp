
<%@ page import="com.luxsoft.impapx.Compra" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'compra.label', default: 'Compra')}" />
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
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${compraInstance?.proveedor}">
						<dt><g:message code="compra.proveedor.label" default="Proveedor" /></dt>
						
							<dd><g:link controller="proveedor" action="show" id="${compraInstance?.proveedor?.id}">${compraInstance?.proveedor?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.fecha}">
						<dt><g:message code="compra.fecha.label" default="Fecha" /></dt>
						
							<dd><g:formatDate date="${compraInstance?.fecha}" /></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.entrega}">
						<dt><g:message code="compra.entrega.label" default="Entrega" /></dt>
						
							<dd><g:formatDate date="${compraInstance?.entrega}" /></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.depuracion}">
						<dt><g:message code="compra.depuracion.label" default="Depuracion" /></dt>
						
							<dd><g:formatDate date="${compraInstance?.depuracion}" /></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.comentario}">
						<dt><g:message code="compra.comentario.label" default="Comentario" /></dt>
						
							<dd><g:fieldValue bean="${compraInstance}" field="comentario"/></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.moneda}">
						<dt><g:message code="compra.moneda.label" default="Moneda" /></dt>
						
							<dd><g:fieldValue bean="${compraInstance}" field="moneda"/></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.tc}">
						<dt><g:message code="compra.tc.label" default="Tc" /></dt>
						
							<dd><g:fieldValue bean="${compraInstance}" field="tc"/></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.folio}">
						<dt><g:message code="compra.folio.label" default="Folio" /></dt>
						
							<dd><g:fieldValue bean="${compraInstance}" field="folio"/></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.origen}">
						<dt><g:message code="compra.origen.label" default="Origen" /></dt>
						
							<dd><g:fieldValue bean="${compraInstance}" field="origen"/></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.importe}">
						<dt><g:message code="compra.importe.label" default="Importe" /></dt>
						
							<dd><g:fieldValue bean="${compraInstance}" field="importe"/></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.descuentos}">
						<dt><g:message code="compra.descuentos.label" default="Descuentos" /></dt>
						
							<dd><g:fieldValue bean="${compraInstance}" field="descuentos"/></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.subtotal}">
						<dt><g:message code="compra.subtotal.label" default="Subtotal" /></dt>
						
							<dd><g:fieldValue bean="${compraInstance}" field="subtotal"/></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.impuestos}">
						<dt><g:message code="compra.impuestos.label" default="Impuestos" /></dt>
						
							<dd><g:fieldValue bean="${compraInstance}" field="impuestos"/></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.total}">
						<dt><g:message code="compra.total.label" default="Total" /></dt>
						
							<dd><g:fieldValue bean="${compraInstance}" field="total"/></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.dateCreated}">
						<dt><g:message code="compra.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${compraInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.lastUpdated}">
						<dt><g:message code="compra.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${compraInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${compraInstance?.partidas}">
						<dt><g:message code="compra.partidas.label" default="Partidas" /></dt>
						
							<g:each in="${compraInstance.partidas}" var="p">
							<dd><g:link controller="compraDet" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${compraInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${compraInstance?.id}">
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
