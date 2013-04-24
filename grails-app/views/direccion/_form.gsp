<%@ page import="com.luxsoft.impapx.Direccion" %>



<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'calle', 'error')} ">
	<label for="calle">
		<g:message code="direccion.calle.label" default="Calle" />
		
	</label>
	<g:textField name="calle" maxlength="200" value="${direccionInstance?.calle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'numeroInterior', 'error')} ">
	<label for="numeroInterior">
		<g:message code="direccion.numeroInterior.label" default="Numero Interior" />
		
	</label>
	<g:textField name="numeroInterior" maxlength="50" value="${direccionInstance?.numeroInterior}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'numeroExterior', 'error')} ">
	<label for="numeroExterior">
		<g:message code="direccion.numeroExterior.label" default="Numero Exterior" />
		
	</label>
	<g:textField name="numeroExterior" maxlength="50" value="${direccionInstance?.numeroExterior}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'colonia', 'error')} ">
	<label for="colonia">
		<g:message code="direccion.colonia.label" default="Colonia" />
		
	</label>
	<g:textField name="colonia" value="${direccionInstance?.colonia}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'municipio', 'error')} ">
	<label for="municipio">
		<g:message code="direccion.municipio.label" default="Municipio" />
		
	</label>
	<g:textField name="municipio" value="${direccionInstance?.municipio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'codigoPostal', 'error')} ">
	<label for="codigoPostal">
		<g:message code="direccion.codigoPostal.label" default="Codigo Postal" />
		
	</label>
	<g:textField name="codigoPostal" value="${direccionInstance?.codigoPostal}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'estado', 'error')} ">
	<label for="estado">
		<g:message code="direccion.estado.label" default="Estado" />
		
	</label>
	<g:textField name="estado" value="${direccionInstance?.estado}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: direccionInstance, field: 'pais', 'error')} ">
	<label for="pais">
		<g:message code="direccion.pais.label" default="Pais" />
		
	</label>
	<g:select name="pais" from="${direccionInstance.constraints.pais.inList}" value="${direccionInstance?.pais}" valueMessagePrefix="direccion.pais" noSelection="['': '']"/>
</div>

