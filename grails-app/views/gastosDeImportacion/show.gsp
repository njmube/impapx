
<%@ page import="com.luxsoft.impapx.GastosDeImportacion" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'gastosDeImportacion.label', default: 'GastosDeImportacion')}" />
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
				
					<g:if test="${gastosDeImportacionInstance?.proveedor}">
						<dt><g:message code="gastosDeImportacion.proveedor.label" default="Proveedor" /></dt>
						
							<dd><g:link controller="proveedor" action="show" id="${gastosDeImportacionInstance?.proveedor?.id}">${gastosDeImportacionInstance?.proveedor?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.documento}">
						<dt><g:message code="gastosDeImportacion.documento.label" default="Documento" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="documento"/></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.fecha}">
						<dt><g:message code="gastosDeImportacion.fecha.label" default="Fecha" /></dt>
						
							<dd><g:formatDate date="${gastosDeImportacionInstance?.fecha}" /></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.vencimiento}">
						<dt><g:message code="gastosDeImportacion.vencimiento.label" default="Vencimiento" /></dt>
						
							<dd><g:formatDate date="${gastosDeImportacionInstance?.vencimiento}" /></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.comentario}">
						<dt><g:message code="gastosDeImportacion.comentario.label" default="Comentario" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="comentario"/></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.moneda}">
						<dt><g:message code="gastosDeImportacion.moneda.label" default="Moneda" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="moneda"/></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.tc}">
						<dt><g:message code="gastosDeImportacion.tc.label" default="Tc" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="tc"/></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.importe}">
						<dt><g:message code="gastosDeImportacion.importe.label" default="Importe" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="importe"/></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.descuentos}">
						<dt><g:message code="gastosDeImportacion.descuentos.label" default="Descuentos" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="descuentos"/></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.subTotal}">
						<dt><g:message code="gastosDeImportacion.subTotal.label" default="Sub Total" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="subTotal"/></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.impuestos}">
						<dt><g:message code="gastosDeImportacion.impuestos.label" default="Impuestos" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="impuestos"/></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.total}">
						<dt><g:message code="gastosDeImportacion.total.label" default="Total" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="total"/></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.analisisCosto}">
						<dt><g:message code="gastosDeImportacion.analisisCosto.label" default="Analisis Costo" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="analisisCosto"/></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.tasaDeImpuesto}">
						<dt><g:message code="gastosDeImportacion.tasaDeImpuesto.label" default="Tasa De Impuesto" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="tasaDeImpuesto"/></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.embarque}">
						<dt><g:message code="gastosDeImportacion.embarque.label" default="Embarque" /></dt>
						
							<dd><g:link controller="embarque" action="show" id="${gastosDeImportacionInstance?.embarque?.id}">${gastosDeImportacionInstance?.embarque?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.dateCreated}">
						<dt><g:message code="gastosDeImportacion.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${gastosDeImportacionInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.lastUpdated}">
						<dt><g:message code="gastosDeImportacion.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${gastosDeImportacionInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.saldo}">
						<dt><g:message code="gastosDeImportacion.saldo.label" default="Saldo" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="saldo"/></dd>
						
					</g:if>
				
					<g:if test="${gastosDeImportacionInstance?.totalMN}">
						<dt><g:message code="gastosDeImportacion.totalMN.label" default="Total MN" /></dt>
						
							<dd><g:fieldValue bean="${gastosDeImportacionInstance}" field="totalMN"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${gastosDeImportacionInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${gastosDeImportacionInstance?.id}">
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
