
<%@ page import="com.luxsoft.cfdi.Cfdi" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'cfdi.label', default: 'Cfdi')}" />
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
					</ul>
				</div>
			</div>
			
			<div class="span9">
				<div class="page-header">
					<h3>Comprobante fiscal digital CFDI id: ${cfdiInstance.id }</h3>
					<g:if test="${flash.message}">
						<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
					</g:if>
				</div>
				
				<dl>
					<g:if test="${cfdiInstance?.tipo}">
						<dt>Tipo</dt>
						<dd><g:fieldValue bean="${cfdiInstance}" field="tipo"/></dd>
					</g:if>
					
					<g:if test="${cfdiInstance?.serie}">
						<dt>Serie</dt>
						<dd><g:fieldValue bean="${cfdiInstance}" field="serie"/></dd>
					</g:if>
					<g:if test="${cfdiInstance?.folio}">
						<dt>Folio</dt>
						<dd><g:fieldValue bean="${cfdiInstance}" field="folio"/></dd>
					</g:if>
					
					<g:if test="${cfdiInstance?.fecha}">
						<dt>Fecha</dt>
						<dd><lx:shortDate date="${cfdiInstance.fecha }"/></dd>
					</g:if>
					<g:if test="${cfdiInstance?.receptor}">
						<dt>Receptor</dt>
						<dd><g:fieldValue bean="${cfdiInstance}" field="receptor"/> (<g:fieldValue bean="${cfdiInstance}" field="rfc"/>)</dd>
					</g:if>
					<g:if test="${cfdiInstance?.xmlName}">
						<dt>XML</dt>
						<dd><g:fieldValue bean="${cfdiInstance}" field="xmlName"/></dd>
					</g:if>
					<g:if test="${cfdiInstance?.importe}">
						<dt>Importe</dt>
						<dd><lx:moneyFormat number="${cfdiInstance.importe}"/></dd>
					</g:if>
					<g:if test="${cfdiInstance?.impuesto}">
						<dt>Impuesto</dt>
						<dd><lx:moneyFormat number="${cfdiInstance.impuesto}"/></dd>
					</g:if>
					<g:if test="${cfdiInstance?.total}">
						<dt>Total</dt>
						<dd><lx:moneyFormat number="${cfdiInstance.total}"/></dd>
					</g:if>
					<g:if test="${cfdiInstance?.uuid}">
						<dt>UUID</dt>
						<dd><g:fieldValue bean="${cfdiInstance}" field="uuid"/></dd>
					</g:if>
					<g:if test="${cfdiInstance?.dateCreated}">
						<dt>Creado</dt>
						<dd><g:formatDate date="${cfdiInstance.dateCreated }" format="dd/MM/yyyy hh:mm"/></dd>
					</g:if>
					<g:if test="${cfdiInstance?.lastUpdated}">
						<dt>Modificado</dt>
						<dd><g:formatDate date="${cfdiInstance.lastUpdated }" format="dd/MM/yyyy hh:mm"/></dd>
					</g:if>
				</dl>
				
				<g:if test="${cfdiInstance.uuid }">
					<g:jasperReport
						controller="cfdi"
						action="imprimirCfdi"
						jasper="CFDI" 
						format="PDF" 
						name="">
				<g:hiddenField name="id" value="${cfdiInstance.id}"/>
			</g:jasperReport>
				</g:if>
		
		<g:form action="delete" id="${cfdiInstance.id }" method="POST">
			<fieldset>
			<div class="">
				
				<g:link class="btn btn-info" action="mostrarXml" id="${cfdiInstance.id}">Ver XML</g:link>
				<g:if test="${cfdiInstance.uuid }">
					
					<g:link class="btn btn-success" action="descargarXml" resource="${cfdiInstance}"><i class="icon-download icon-white"></i> Descargar XML</g:link>
					<g:actionSubmit class="btn btn-danger" action="cancelar" value="${message(code: 'default.button.cancel.label', default: 'Cancelar')}" 
						onclick="return confirm('${message(code: 'default.button.cancel.confirm.message', default: 'Seguro que desa cancelar?')}');" />
				</g:if>
				<g:else>
					<g:link class="btn btn-primary" action="timbrar" id="${cfdiInstance.id}"><i class="icon-qrcode icon-white"></i> Timbrar</g:link>
					<g:actionSubmit class="btn btn-danger" action="delete" value="Eliminar" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</g:else>
			</div>
			</fieldset>
		</g:form>				
				
				
			</div>
			
		</div>
		
		
		
		
		
	</body>
</html>
