<%@ page import="com.luxsoft.impapx.EmbarqueDet" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'embarqueDet.label', default: 'EmbarqueDet')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="container">
			
			<div class="row">

				

				<div class="span10 offset1 well">
					Embarque: <g:link  controller="embarque" action="edit" id="${embarqueDetInstance?.embarque?.id}">
							<g:fieldValue bean="${embarqueDetInstance}" field="embarque"/>
					</g:link>
				

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${embarqueDetInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${embarqueDetInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>
				</div>
				
			</div>
			<g:render template="detForm" model="['proveedorId':proveedorId ]"/>

		</div>
	</body>
</html>
