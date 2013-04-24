
<%@ page import="com.luxsoft.impapx.FacturaDeGastos" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'facturaDeGastos.label', default: 'FacturaDeGastos')}" />
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
				
					<g:if test="${facturaDeGastosInstance?.proveedor}">
						<dt><g:message code="facturaDeGastos.proveedor.label" default="Proveedor" /></dt>
						
							<dd><g:link controller="proveedor" action="show" id="${facturaDeGastosInstance?.proveedor?.id}">${facturaDeGastosInstance?.proveedor?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.documento}">
						<dt><g:message code="facturaDeGastos.documento.label" default="Documento" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="documento"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.fecha}">
						<dt><g:message code="facturaDeGastos.fecha.label" default="Fecha" /></dt>
						
							<dd><g:formatDate date="${facturaDeGastosInstance?.fecha}" /></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.vencimiento}">
						<dt><g:message code="facturaDeGastos.vencimiento.label" default="Vencimiento" /></dt>
						
							<dd><g:formatDate date="${facturaDeGastosInstance?.vencimiento}" /></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.comentario}">
						<dt><g:message code="facturaDeGastos.comentario.label" default="Comentario" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="comentario"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.moneda}">
						<dt><g:message code="facturaDeGastos.moneda.label" default="Moneda" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="moneda"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.tc}">
						<dt><g:message code="facturaDeGastos.tc.label" default="Tc" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="tc"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.importe}">
						<dt><g:message code="facturaDeGastos.importe.label" default="Importe" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="importe"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.descuentos}">
						<dt><g:message code="facturaDeGastos.descuentos.label" default="Descuentos" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="descuentos"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.subTotal}">
						<dt><g:message code="facturaDeGastos.subTotal.label" default="Sub Total" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="subTotal"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.impuestos}">
						<dt><g:message code="facturaDeGastos.impuestos.label" default="Impuestos" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="impuestos"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.total}">
						<dt><g:message code="facturaDeGastos.total.label" default="Total" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="total"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.analisisCosto}">
						<dt><g:message code="facturaDeGastos.analisisCosto.label" default="Analisis Costo" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="analisisCosto"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.tasaDeImpuesto}">
						<dt><g:message code="facturaDeGastos.tasaDeImpuesto.label" default="Tasa De Impuesto" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="tasaDeImpuesto"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.requisitado}">
						<dt><g:message code="facturaDeGastos.requisitado.label" default="Requisitado" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="requisitado"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.dateCreated}">
						<dt><g:message code="facturaDeGastos.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${facturaDeGastosInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.lastUpdated}">
						<dt><g:message code="facturaDeGastos.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${facturaDeGastosInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.pagosAplicados}">
						<dt><g:message code="facturaDeGastos.pagosAplicados.label" default="Pagos Aplicados" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="pagosAplicados"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.saldo}">
						<dt><g:message code="facturaDeGastos.saldo.label" default="Saldo" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="saldo"/></dd>
						
					</g:if>
				
					<g:if test="${facturaDeGastosInstance?.totalMN}">
						<dt><g:message code="facturaDeGastos.totalMN.label" default="Total MN" /></dt>
						
							<dd><g:fieldValue bean="${facturaDeGastosInstance}" field="totalMN"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${facturaDeGastosInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${facturaDeGastosInstance?.id}">
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
