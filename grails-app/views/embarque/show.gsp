
<%@ page import="com.luxsoft.impapx.Embarque" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'embarque.label', default: 'Embarque')}" />
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
				
					<g:if test="${embarqueInstance?.bl}">
						<dt><g:message code="embarque.bl.label" default="Bl" /></dt>
						
							<dd><g:fieldValue bean="${embarqueInstance}" field="bl"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueInstance?.nombre}">
						<dt><g:message code="embarque.nombre.label" default="Nombre" /></dt>
						
							<dd><g:fieldValue bean="${embarqueInstance}" field="nombre"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueInstance?.fechaEmbarque}">
						<dt><g:message code="embarque.fechaEmbarque.label" default="Fecha Embarque" /></dt>
						
							<dd><g:formatDate date="${embarqueInstance?.fechaEmbarque}" /></dd>
						
					</g:if>
				
					<g:if test="${embarqueInstance?.proveedor}">
						<dt><g:message code="embarque.proveedor.label" default="Proveedor" /></dt>
						
							<dd><g:link controller="proveedor" action="show" id="${embarqueInstance?.proveedor?.id}">${embarqueInstance?.proveedor?.encodeAsHTML()}</g:link></dd>
						
					</g:if>
				
					<g:if test="${embarqueInstance?.ingresoAduana}">
						<dt><g:message code="embarque.ingresoAduana.label" default="Ingreso Aduana" /></dt>
						
							<dd><g:formatDate date="${embarqueInstance?.ingresoAduana}" /></dd>
						
					</g:if>
				
					<g:if test="${embarqueInstance?.contenedores}">
						<dt><g:message code="embarque.contenedores.label" default="Contenedores" /></dt>
						
							<dd><g:fieldValue bean="${embarqueInstance}" field="contenedores"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueInstance?.comentario}">
						<dt><g:message code="embarque.comentario.label" default="Comentario" /></dt>
						
							<dd><g:fieldValue bean="${embarqueInstance}" field="comentario"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueInstance?.moneda}">
						<dt><g:message code="embarque.moneda.label" default="Moneda" /></dt>
						
							<dd><g:fieldValue bean="${embarqueInstance}" field="moneda"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueInstance?.tc}">
						<dt><g:message code="embarque.tc.label" default="Tc" /></dt>
						
							<dd><g:fieldValue bean="${embarqueInstance}" field="tc"/></dd>
						
					</g:if>
				
					<g:if test="${embarqueInstance?.dateCreated}">
						<dt><g:message code="embarque.dateCreated.label" default="Date Created" /></dt>
						
							<dd><g:formatDate date="${embarqueInstance?.dateCreated}" /></dd>
						
					</g:if>
				
					<g:if test="${embarqueInstance?.lastUpdated}">
						<dt><g:message code="embarque.lastUpdated.label" default="Last Updated" /></dt>
						
							<dd><g:formatDate date="${embarqueInstance?.lastUpdated}" /></dd>
						
					</g:if>
				
					<g:if test="${embarqueInstance?.partidas}">
						<dt><g:message code="embarque.partidas.label" default="Partidas" /></dt>
						
							<g:each in="${embarqueInstance.partidas}" var="p">
							<dd><g:link controller="embarqueDet" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${embarqueInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${embarqueInstance?.id}">
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
