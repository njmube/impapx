
<%@ page import="com.luxsoft.cfdi.Cfdi" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="taskView">
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<content tag="header">
			<h3>Lista de CFDI's : ${session.periodoContable}</h3>
 		</content>
 		
 		<content tag="document">
 			<g:if test="${flash.message}">
			<bootstrap:alert class="alert-info">
				${flash.message}
			</bootstrap:alert>
			</g:if>
			
			<table class="table table-striped table-hover table-bordered table-condensed">
				<thead>
					<tr>
						<g:sortableColumn property="serie" title="${message(code: 'cfdi.serie.label', default: 'Serie')}" />
						<g:sortableColumn property="folio" title="${message(code: 'cfdi.folio.label', default: 'Folio')}" />
						<g:sortableColumn property="fecha" title="${message(code: 'cfdi.fecha.label', default: 'Fecha')}" />
						<g:sortableColumn property="receptor" title="${message(code: 'cfdi.serie.label', default: 'Receptor')}" />
						<g:sortableColumn property="uuid" title="${message(code: 'cfdi.uuid.label', default: 'Uuid')}" />
						<g:sortableColumn property="timbrado" title="${message(code: 'cfdi.timbrado.label', default: 'Timbrado')}" />
					</tr>
				</thead>
			<tbody>
				<g:each in="${cfdiInstanceList}" status="i" var="cfdiInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: cfdiInstance, field: "serie")}</td>
						<td><g:link action="show" id="${cfdiInstance.id}">${fieldValue(bean: cfdiInstance, field: "folio")}</g:link></td>
						<td><g:formatDate date="${cfdiInstance.fecha}" /></td>
						<td>${fieldValue(bean: cfdiInstance, field: "receptor")}</td>
						<td>${fieldValue(bean: cfdiInstance, field: "uuid")}</td>
						<td><g:formatDate date="${cfdiInstance.timbrado}" /></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${cfdiInstanceCount ?: 0}" />
			</div>
		
 		</content>
 		
		
	</body>
</html>
