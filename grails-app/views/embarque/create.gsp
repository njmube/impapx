<%@ page import="com.luxsoft.impapx.Embarque" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'embarque.label', default: 'Embarque')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		<r:require module="datepicker"/>
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
						<li class="active">
							<g:link class="create" action="create">
								<i class="icon-plus icon-white"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span9">

				<div class="page-header">
					<h3><g:message code="default.create.label" args="[entityName]" /></h3>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${embarqueInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${embarqueInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="form-horizontal" action="create" >
						<fieldset>
							<f:with bean="embarqueInstance">
								<f:field property="bl" input-class="mayusculas" ></f:field>
								<f:field property="nombre" input-class="mayusculas" ></f:field>
								<f:field property="fechaEmbarque">
									<input class="datepicker" type="text" id="fechaEmbarque" name="fechaEmbarque" value="${formatDate(format:'dd/MM/yyyy',date:compraInstance?.fecha)}"/>
									<a href="#" class="btn btn-medium datepicker-btn"><i class="icon-calendar"></i></a> 
								</f:field>
								 <f:field property="proveedor" ></f:field>
								 <f:field property="aduana"></f:field>
								 <f:field property="ingresoAduana" label="ETA">
									<input class="datepicker" type="text" id="ingresoAduana" name="ingresoAduana" value="${formatDate(format:'dd/MM/yyyy',date:compraInstance?.fecha)}"/>
									<a href="#" class="btn btn-medium datepicker-btn"><i class="icon-calendar"></i></a> 
								</f:field>
								<f:field property="contenedores"/>
								<f:field property="comentario" >
									<g:textArea name="comentario" class="comentario"/>
								</f:field>
								
							</f:with>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.create.label" default="Create" />
								</button>
							</div>
						</fieldset>
					</g:form>
				</fieldset>
			</div>
		</div>
		
	</body>
</html>
