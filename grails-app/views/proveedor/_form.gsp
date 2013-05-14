<%@ page import="com.luxsoft.impapx.Proveedor"%>
<%@ page import="com.luxsoft.impapx.contabilidad.CuentaContable"%>
<fieldset>
	<g:form class="form-horizontal" action="edit"
		id="${proveedorInstance?.id}">
		<g:hiddenField name="version" value="${proveedorInstance?.version}" />
		<fieldset>
			<f:with bean="proveedorInstance">
				<f:field property="nombre" value="${proveedorInstance?.nombre}" input-class="span7"/>
				<f:field property="rfc" label="RFC/ID Fiscal"/>
				<f:field property="factorDeUtilidad" input-class="numericEditField"/>
				<f:field property="tipoDeCosteo" />
				<f:field property="plazo" />
				<f:field property="vencimentoBl" />
				<f:field property="lineaDeCredito" label="Línea de Crédito"/>
				<f:field property="nacional"/>
				<f:field property="paisDeOrigen"/>
				<f:field property="nacionalidad"/>
				<f:field property="subCuentaOperativa"/>
				<%-- <f:field property="cuentaContable">
					<g:select id="cuentaContable" name="cuentaContable.id" from="${CuentaContable.findAllByClaveLike('20%')}"
										value="${proveedorInstance?.cuentaContable?.id}"
										optionKey="id" 
										 noSelection="['':'-Seleccione la cuenta ']"/>
				</f:field>
				--%>
				<f:field property="correoElectronico"/>
				<f:field property="www" label="Página WEB"/>
				
				<f:field property="direccion" value="${proveedorInstance?.direccion}"/>
			</f:with>
			
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">
					<i class="icon-ok icon-white"></i>
					<g:message code="default.button.update.label" default="Update" />
				</button>
				<button type="submit" class="btn btn-danger" name="_action_delete"
					formnovalidate>
					<i class="icon-trash icon-white"></i>
					<g:message code="default.button.delete.label" default="Delete" />
				</button>
			</div>
		</fieldset>
	</g:form>
</fieldset>

<r:script>
	$(function(){
		$(".numericEditField").forceNumeric();
	});
</r:script>