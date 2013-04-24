<%@ page import="com.luxsoft.impapx.Pedimento" %>
<%@ page contentType="text/html;charset=ISO-8859-1" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'pedimento.label', default: 'Pedimento')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		<r:require modules="luxorTableUtils,autoNumeric"/>
	</head>
	<body>
		<div class="container-fluid">
		<div class="row-fluid">

			<div class="span2">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
								<g:message code="pedimento.list.label"  />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="pedimento.create.label" default="Nuevo" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span10">

				<div class="alert">
					<h4><strong>
					Pedimento: ${pedimentoInstance.pedimento} ( Id:${pedimentoInstance.id})
					</strong></h4>
				</div>
				
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${pedimentoInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${pedimentoInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>
				<ul class="nav nav-tabs" id="myTab">
					<li class="active"><a href="#pedimento" data-toggle="tab">Pedimento</a></li>
<%--					<li class=""><a href="#facturas" data-toggle="tab">Contenedores</a></li>--%>
					<li class=""><a href="#embarques" data-toggle="tab">Embarques</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="pedimento">
						<g:render template="editForm" bean="${pedimentoInstance}"/>
					</div>
					<%--
					<div class="tab-pane" id="facturas">
						<g:render template="facturasPanel" bean="${pedimentoInstance}"/>
					</div>
					 --%>
					<div class="tab-pane" id="embarques">
						<g:render template="embarquesPanel" bean="${pedimentoInstance}"/>
					</div>
				</div>
			</div>
		</div>
		</div>
	</body>
</html>
