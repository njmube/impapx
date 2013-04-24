<%@ page import="com.luxsoft.impapx.tesoreria.MovimientoDeCuenta" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="taskView">
		<g:set var="entityName" value="${message(code: 'movimientoDeCuenta.label', default: 'MovimientoDeCuenta')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<content tag="header">
			<h3><g:message code="movimientoDeCuenta.create.label" default="Alta de movimiento bancario" /></h2>
 		</content>
 		
 		
		
		<content tag="consultas">
			<li>
				<li><g:link  action="list">Movimientos</g:link></li>
			</li>
		</content>
		
		<content tag="operaciones">
 			<li><g:link  action="depositar">Depositar</g:link></li>
 			<li><g:link  action="retirar">Retirar</g:link></li>
 		</content>
		
		<content tag="document">
			<g:render template="/shared/messagePanel"  model="[beanInstance:movimientoDeCuentaInstance]"/>
			<fieldset>
				<g:form class="form-horizontal" action="create" >
					<fieldset>
							
						<f:with bean="movimientoDeCuentaInstance">
							<g:hiddenField name="ingreso" value="${movimientoDeCuentaInstance?.ingreso }"/>
							<f:field property="cuenta" input-class="span5"/>
							<f:field property="concepto" >
								<g:select name="concepto" from="${conceptos }" 
									value="${movimientoDeCuentaInstance?.concepto}"/>
							</f:field> 
							<f:field property="fecha"/>
							<%-- <f:field property="tipo"/>--%>
							<f:field property="referenciaBancaria"/>
							<f:field property="tc"/>
							<f:field property="importe"/>
							<f:field property="comentario" input-class="input-xxlarge"/>
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
		</content>
		
		
	</body>
</html>
