
<%@ page import="com.luxsoft.impapx.EmbarqueDet" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'embarqueDet.label', default: 'EmbarqueDet')}" />
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
				
					<g:if test="${embarqueDetInstance?.producto}">
						<dt><g:message code="embarqueDet.producto.label" default="Producto" /></dt>
						
							<dd><g:link controller="producto" action="show" id="${embarqueDetInstance?.producto?.id}">${embarqueDetInstance?.producto?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${embarqueDetInstance?.compraDet}">
						<dt><g:message code="embarqueDet.compraDet.label" default="Compra Det" /></dt>
						
							<dd><g:link controller="compraDet" action="show" id="${embarqueDetInstance?.compraDet?.id}">${embarqueDetInstance?.compraDet?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${embarqueDetInstance?.contenedor}">
						<dt><g:message code="embarqueDet.contenedor.label" default="Contenedor" /></dt>
						
							<dd><g:fieldValue bean="${embarqueDetInstance}" field="contenedor"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueDetInstance?.tarimas}">
						<dt><g:message code="embarqueDet.tarimas.label" default="Tarimas" /></dt>
						
							<dd><g:fieldValue bean="${embarqueDetInstance}" field="tarimas"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueDetInstance?.cantidad}">
						<dt><g:message code="embarqueDet.cantidad.label" default="Cantidad" /></dt>
						
							<dd><g:fieldValue bean="${embarqueDetInstance}" field="cantidad"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueDetInstance?.costoBruto}">
						<dt><g:message code="embarqueDet.costoBruto.label" default="Costo Bruto" /></dt>
						
							<dd><g:fieldValue bean="${embarqueDetInstance}" field="costoBruto"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueDetInstance?.costoNeto}">
						<dt><g:message code="embarqueDet.costoNeto.label" default="Costo Neto" /></dt>
						
							<dd><g:fieldValue bean="${embarqueDetInstance}" field="costoNeto"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueDetInstance?.costoUnitario}">
						<dt><g:message code="embarqueDet.costoUnitario.label" default="Costo Unitario" /></dt>
						
							<dd><g:fieldValue bean="${embarqueDetInstance}" field="costoUnitario"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueDetInstance?.embarque}">
						<dt><g:message code="embarqueDet.embarque.label" default="Embarque" /></dt>
						
							<dd><g:link controller="embarque" action="show" id="${embarqueDetInstance?.embarque?.id}">${embarqueDetInstance?.embarque?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${embarqueDetInstance?.factura}">
						<dt><g:message code="embarqueDet.factura.label" default="Factura" /></dt>
						
							<dd><g:fieldValue bean="${embarqueDetInstance}" field="factura"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueDetInstance?.gastos}">
						<dt><g:message code="embarqueDet.gastos.label" default="Gastos" /></dt>
						
							<dd><g:fieldValue bean="${embarqueDetInstance}" field="gastos"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueDetInstance?.kilosEstimados}">
						<dt><g:message code="embarqueDet.kilosEstimados.label" default="Kilos Estimados" /></dt>
						
							<dd><g:fieldValue bean="${embarqueDetInstance}" field="kilosEstimados"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueDetInstance?.kilosNetos}">
						<dt><g:message code="embarqueDet.kilosNetos.label" default="Kilos Netos" /></dt>
						
							<dd><g:fieldValue bean="${embarqueDetInstance}" field="kilosNetos"/></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${embarqueDetInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${embarqueDetInstance?.id}">
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
