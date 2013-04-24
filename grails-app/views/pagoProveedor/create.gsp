<%@ page import="com.luxsoft.impapx.tesoreria.PagoProveedor" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="taskView">
<title><g:message code="comision.list.label" default="Alta de pago"/></title>
<r:require module="autoNumeric"/>
</head>
<body>
	
	<content tag="header">
		<h3>Pago a proveedor</h3>
 	</content>
	<content tag="consultas">
	
		<li><g:link class="list" action="list">
			<i class="icon-list"></i>
			Pagos
			</g:link>
		</li>
	</content>
	
 	<content tag="operaciones">
 		<li><g:link  action="create"><i class="icon-plus "></i> Alta de pago</g:link></li>
 	</content>
 	
 	<content tag="document">
 		<g:render template="/shared/messagePanel" model="[beanInstance:pagoProveedorInstance]"/>

		<fieldset>
					<g:form class="form-horizontal" action="create" >
						<fieldset>
							<f:with bean="pagoProveedorInstance">
								<f:field property="fecha"/>
								<f:field property="cuenta"/>
								<f:field property="requisicion"/>
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
 	
 <r:script>
 $(function(){
 	$(".moneyField").autoNumeric({vMin:'0.00',wEmpty:'zero'});
 	$(".porcentField").autoNumeric({vMax:'100.00',vMin:'0.00'});
 	$(".tcField").autoNumeric({vMin:'0.0000'});
 	
 	$("#impuestoTasa").blur(function(){
		
		var comision=$("#comision").autoNumericGet();
		var tasa=$("#impuestoTasa").autoNumericGet();
		tasa=tasa/100;
		var impuesto=comision*tasa;
		//importe=Math.round(importe*100)/100;
		$("#impuesto").val(impuesto);
	});
 });
 </r:script>
	
</body>
</html>

