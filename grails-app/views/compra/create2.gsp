<%@ page import="com.luxsoft.impapx.Compra" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'compra.label', default: 'Compra')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		<r:require module="datepicker"/>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="header">
						<h3>Alta de Compra</h3>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="span9">
					<g:form class="form-horizontal" action="save" >
						<fieldset>
							<f:with bean="compraInstance">
								<f:field property="proveedor"/>
								<f:field property="fecha">
									<input class="datepicker" type="text" id="fecha" name="fecha" value="${formatDate(format:'dd/MM/yyyy',date:compraInstance?.fecha)}"/>
									<a href="#" class="btn btn-medium datepicker-btn"><i class="icon-calendar"></i></a> 
								</f:field> 
								<f:field property="moneda"/>
								<f:field property="comentario" input-class="comentario">
									<g:textArea name="comentario" />
								</f:field>
								
							</f:with>
							<div class="form-actions">
								<g:link class="btn btn-success" action="agregarPartida">
									<i class="icon-plus icon-white"></i>Agregar partida
								</g:link>
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.create.label" default="Create" />
								</button>
							</div>
						</fieldset>
					</g:form>
				</div>
				<div class="span3">
					<g:if test="${flash.message}">
						<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
					</g:if>
					<g:hasErrors bean="${compraInstance}">
						<bootstrap:alert class="alert-error">
						<ul>
							<g:eachError bean="${compraInstance}" var="error">
								<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
							</g:eachError>
						</ul>
						</bootstrap:alert>
					</g:hasErrors>
				</div>
				
				<div class="row">
				<div class="span9">
					<f:field bean="compraInstance" property="partidas"/>
				</div>
			</div>
				
			</div>
			
		</div>
		
	</body>
</html>
