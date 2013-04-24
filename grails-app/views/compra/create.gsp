<%@ page import="com.luxsoft.impapx.Compra" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="taskView">
		<g:set var="entityName" value="${message(code: 'compra.label', default: 'Compra')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		<r:require module="luxorForms"/>
	</head>
	<body>
		<content tag="header">
		<h3>Alta de compra</h3>
 	</content>
 	
 	<content tag="consultas">
 		<%-- <g:render template="/cuentaPorPagar/actions"/>--%>
 	</content>
 	<content tag="operaciones">
 		<li>
 			<g:link class="list" action="list">
				<i class="icon-list"></i>
				Lista de compras
			</g:link>
 			<g:link class="" action="create">
				<i class="icon-plus "></i>
				Alta de compra
			</g:link>
		</li>
 	</content>
 	
 	<content tag="document">
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
		<fieldset>
			<g:form class="form-horizontal" action="create" >
				<fieldset>
					<f:with bean="compraInstance">
						<f:field property="proveedor"/>
						<f:field property="fecha"/>
						<f:field property="entrega"/>
						<f:field property="moneda"/>
						<f:field property="tc"/>
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
<r:script>
$(function(){
	$('#moneda').bind('change',function(e){
		var selected=$(this).val();
		if(selected=="MXN"){
			$("#tc").attr("disabled",true);//.val(1.0);
			$("#tc").autoNumericSet(1.0);
		}else
			$("#tc").attr("disabled",false);
		
	});
	$(".moneyField").autoNumeric({vMin:'0.00',wEmpty:'zero',mRound:'B'});
 	$("#tc").autoNumeric({vMin:'0.0000'});
	$("#fecha").mask("99/99/9999");
	
});
</r:script>
</html>