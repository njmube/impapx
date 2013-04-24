
<%@ page import="com.luxsoft.impapx.FacturaDeImportacion" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'facturaDeImportacion.label', default: 'FacturaDeImportacion')}" />
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
				
					
				
					<g:if test="${facturaDeImportacionInstance?.proveedor}">
						<dt><g:message code="facturaDeImportacion.proveedor.label" default="Proveedor" /></dt>
						
							<dd><g:link controller="proveedor" action="show" id="${facturaDeImportacionInstance?.proveedor?.id}">${facturaDeImportacionInstance?.proveedor?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.documento}">
						<dt><g:message code="facturaDeImportacion.documento.label" default="Documento" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="documento"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.fecha}">
						<dt><g:message code="facturaDeImportacion.fecha.label" default="Fecha" /></dt>
						
							<dd><g:formatDate date="${facturaDeImportacionInstance?.fecha}" /></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.vencimiento}">
						<dt><g:message code="facturaDeImportacion.vencimiento.label" default="Vencimiento" /></dt>
						
							<dd><g:formatDate date="${facturaDeImportacionInstance?.vencimiento}" /></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.comentario}">
						<dt><g:message code="facturaDeImportacion.comentario.label" default="Comentario" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="comentario"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.moneda}">
						<dt><g:message code="facturaDeImportacion.moneda.label" default="Moneda" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="moneda"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.tc}">
						<dt><g:message code="facturaDeImportacion.tc.label" default="Tc" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="tc"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.importe}">
						<dt><g:message code="facturaDeImportacion.importe.label" default="Importe" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="importe"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.descuentos}">
						<dt><g:message code="facturaDeImportacion.descuentos.label" default="Descuentos" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="descuentos"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.subTotal}">
						<dt><g:message code="facturaDeImportacion.subTotal.label" default="Sub Total" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="subTotal"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.impuestos}">
						<dt><g:message code="facturaDeImportacion.impuestos.label" default="Impuestos" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="impuestos"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.total}">
						<dt><g:message code="facturaDeImportacion.total.label" default="Total" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="total"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.analisisCosto}">
						<dt><g:message code="facturaDeImportacion.analisisCosto.label" default="Analisis Costo" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="analisisCosto"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.tasaDeImpuesto}">
						<dt><g:message code="facturaDeImportacion.tasaDeImpuesto.label" default="Tasa De Impuesto" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="tasaDeImpuesto"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.pedimento}">
						<dt><g:message code="facturaDeImportacion.pedimento.label" default="Pedimento" /></dt>
						
							<dd><g:link controller="pedimento" action="show" id="${facturaDeImportacionInstance?.pedimento?.id}">${facturaDeImportacionInstance?.pedimento?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.dateCreated}">
						<dt><g:message code="facturaDeImportacion.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${facturaDeImportacionInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.lastUpdated}">
						<dt><g:message code="facturaDeImportacion.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${facturaDeImportacionInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.saldo}">
						<dt><g:message code="facturaDeImportacion.saldo.label" default="Saldo" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="saldo"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeImportacionInstance?.totalMN}">
						<dt><g:message code="facturaDeImportacion.totalMN.label" default="Total MN" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeImportacionInstance}" field="totalMN"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${facturaDeImportacionInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${facturaDeImportacionInstance?.id}">
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
