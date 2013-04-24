<%@ page import="com.luxsoft.impapx.Cliente" %>
<%@ page import="com.luxsoft.impapx.contabilidad.CuentaContable" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'cliente.label', default: 'Cliente')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
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
					<h3><g:message code="default.edit.label" args="[entityName]" /></h3>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${clienteInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${clienteInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="form-horizontal" action="edit" id="${clienteInstance?.id}" >
						<g:hiddenField name="version" value="${clienteInstance?.version}" />
						<fieldset>
							<f:with bean="${clienteInstance }">
								<f:field property="nombre" input-class="input-xxlarge"/>
								<f:field property="rfc"/>
								<f:field property="email1"/>
								<f:field property="formaDePago"/>
								<f:field property="cuentaDePago"/>
								<f:field property="subCuentaOperativa"/>
								
								<%--  
								<f:field property="cuentaContable">								
									<g:select id="cuentaContable" name="cuentaContable.id" from="${CuentaContable.findAllByClaveLike('106-%')}"
										value="${clienteInstance?.cuentaContable?.id}"
										optionKey="id" 
										 noSelection="['':'-Seleccione la cuenta ']"/>
								</f:field>
								--%>
								<f:field property="fisica" label="Persona física"/>
								<f:field property="direccion"/>
								
							</f:with>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.update.label" default="Update" />
								</button>
								<button type="submit" class="btn btn-danger" name="_action_delete" formnovalidate>
									<i class="icon-trash icon-white"></i>
									<g:message code="default.button.delete.label" default="Delete" />
								</button>
							</div>
						</fieldset>
					</g:form>
				</fieldset>

			</div>

		</div>
	</body>
</html>
