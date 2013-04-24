

<%@ page import="com.luxsoft.cfd.Certificado" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'certificado.label', default: 'Certificado')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">

			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active"><g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link></li>
						<li><g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link></li>
					</ul>
				</div>
			</div>

			<div class="span9">

				<div class="page-header">
					<h2>
						<g:message code="default.list.label" args="[entityName]" />
					</h2>
				</div>

				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<table class="table table-striped table-hover table-bordered table-condensed">
					<thead>
						<tr>
							
							<g:sortableColumn property="numeroDeCertificado" title="${message(code: 'certificado.numeroDeCertificado.label', default: 'Numero De Certificado')}" />
						
							<g:sortableColumn property="expedicion" title="${message(code: 'certificado.expedicion.label', default: 'Expedicion')}" />
						
							<g:sortableColumn property="vencimiento" title="${message(code: 'certificado.vencimiento.label', default: 'Vencimiento')}" />
						
							<g:sortableColumn property="certificadoPath" title="${message(code: 'certificado.certificadoPath.label', default: 'Certificado Path')}" />
						
							<g:sortableColumn property="privateKeyPath" title="${message(code: 'certificado.privateKeyPath.label', default: 'Private Key Path')}" />
						
							<g:sortableColumn property="algoritmo" title="${message(code: 'certificado.algoritmo.label', default: 'Algoritmo')}" />
						
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${certificadoInstanceList}" var="certificadoInstance">
							<tr>
								
						<td><g:link action="show" id="${certificadoInstance.id}">${fieldValue(bean: certificadoInstance, field: "numeroDeCertificado")}</g:link></td>
					
						<td><g:formatDate date="${certificadoInstance.expedicion}" /></td>
					
						<td><g:formatDate date="${certificadoInstance.vencimiento}" /></td>
					
						<td>${fieldValue(bean: certificadoInstance, field: "certificadoPath")}</td>
					
						<td>${fieldValue(bean: certificadoInstance, field: "privateKeyPath")}</td>
					
						<td>${fieldValue(bean: certificadoInstance, field: "algoritmo")}</td>
					
							</tr>
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${certificadoInstanceTotal}" />
				</div>
			</div>

		</div>
	</div>
</body>
</html>
