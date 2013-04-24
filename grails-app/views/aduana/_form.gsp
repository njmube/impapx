<%@ page import="com.luxsoft.impapx.Aduana" %>



<div class="fieldcontain ${hasErrors(bean: aduanaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="aduana.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="50" required="" value="${aduanaInstance?.nombre}"/>
</div>
<fieldset class="embedded"><legend><g:message code="aduana.direccion.label" default="Direccion" /></legend>
<div class="fieldcontain ${hasErrors(bean: aduanaInstance, field: 'direccion.calle', 'error')} ">
	<label for="direccion.calle">
		<g:message code="aduana.direccion.calle.label" default="Calle" />
		
	</label>
	<g:textField name="calle" maxlength="200" value="${direccionInstance?.calle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: aduanaInstance, field: 'direccion.codigoPostal', 'error')} ">
	<label for="direccion.codigoPostal">
		<g:message code="aduana.direccion.codigoPostal.label" default="Codigo Postal" />
		
	</label>
	<g:textField name="codigoPostal" value="${direccionInstance?.codigoPostal}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: aduanaInstance, field: 'direccion.colonia', 'error')} ">
	<label for="direccion.colonia">
		<g:message code="aduana.direccion.colonia.label" default="Colonia" />
		
	</label>
	<g:textField name="colonia" value="${direccionInstance?.colonia}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: aduanaInstance, field: 'direccion.estado', 'error')} ">
	<label for="direccion.estado">
		<g:message code="aduana.direccion.estado.label" default="Estado" />
		
	</label>
	<g:textField name="estado" value="${direccionInstance?.estado}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: aduanaInstance, field: 'direccion.id', 'error')} required">
	<label for="direccion.id">
		<g:message code="aduana.direccion.id.label" default="Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="id" type="number" value="${direccionInstance.id}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: aduanaInstance, field: 'direccion.municipio', 'error')} ">
	<label for="direccion.municipio">
		<g:message code="aduana.direccion.municipio.label" default="Municipio" />
		
	</label>
	<g:textField name="municipio" value="${direccionInstance?.municipio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: aduanaInstance, field: 'direccion.numeroExterior', 'error')} ">
	<label for="direccion.numeroExterior">
		<g:message code="aduana.direccion.numeroExterior.label" default="Numero Exterior" />
		
	</label>
	<g:textField name="numeroExterior" maxlength="50" value="${direccionInstance?.numeroExterior}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: aduanaInstance, field: 'direccion.numeroInterior', 'error')} ">
	<label for="direccion.numeroInterior">
		<g:message code="aduana.direccion.numeroInterior.label" default="Numero Interior" />
		
	</label>
	<g:textField name="numeroInterior" maxlength="50" value="${direccionInstance?.numeroInterior}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: aduanaInstance, field: 'direccion.pais', 'error')} ">
	<label for="direccion.pais">
		<g:message code="aduana.direccion.pais.label" default="Pais" />
		
	</label>
	<g:select name="pais" from="${direccionInstance.constraints.pais.inList}" value="${direccionInstance?.pais}" valueMessagePrefix="direccion.pais" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: aduanaInstance, field: 'direccion.version', 'error')} required">
	<label for="direccion.version">
		<g:message code="aduana.direccion.version.label" default="Version" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="version" type="number" value="${direccionInstance.version}" required=""/>
</div>
</fieldset>
