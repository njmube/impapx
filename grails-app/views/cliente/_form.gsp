<%@ page import="com.luxsoft.impapx.Cliente" %>



<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="cliente.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="nombre" cols="40" rows="5" maxlength="255" required="" value="${clienteInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'rfc', 'error')} ">
	<label for="rfc">
		<g:message code="cliente.rfc.label" default="Rfc" />
		
	</label>
	<g:textField name="rfc" value="${clienteInstance?.rfc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'email1', 'error')} ">
	<label for="email1">
		<g:message code="cliente.email1.label" default="Email1" />
		
	</label>
	<g:textField name="email1" value="${clienteInstance?.email1}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'formaDePago', 'error')} ">
	<label for="formaDePago">
		<g:message code="cliente.formaDePago.label" default="Forma De Pago" />
		
	</label>
	<g:textField name="formaDePago" value="${clienteInstance?.formaDePago}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'cuentaPago', 'error')} ">
	<label for="cuentaPago">
		<g:message code="cliente.cuentaPago.label" default="Cuenta Pago" />
		
	</label>
	<g:textField name="cuentaPago" value="${clienteInstance?.cuentaPago}"/>
</div>
<fieldset class="embedded"><legend><g:message code="cliente.direccion.label" default="Direccion" /></legend>
<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'direccion.calle', 'error')} ">
	<label for="direccion.calle">
		<g:message code="cliente.direccion.calle.label" default="Calle" />
		
	</label>
	<g:textField name="calle" maxlength="200" value="${direccionInstance?.calle}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'direccion.codigoPostal', 'error')} ">
	<label for="direccion.codigoPostal">
		<g:message code="cliente.direccion.codigoPostal.label" default="Codigo Postal" />
		
	</label>
	<g:textField name="codigoPostal" value="${direccionInstance?.codigoPostal}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'direccion.colonia', 'error')} ">
	<label for="direccion.colonia">
		<g:message code="cliente.direccion.colonia.label" default="Colonia" />
		
	</label>
	<g:textField name="colonia" value="${direccionInstance?.colonia}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'direccion.estado', 'error')} ">
	<label for="direccion.estado">
		<g:message code="cliente.direccion.estado.label" default="Estado" />
		
	</label>
	<g:textField name="estado" value="${direccionInstance?.estado}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'direccion.municipio', 'error')} ">
	<label for="direccion.municipio">
		<g:message code="cliente.direccion.municipio.label" default="Municipio" />
		
	</label>
	<g:textField name="municipio" value="${direccionInstance?.municipio}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'direccion.numeroExterior', 'error')} ">
	<label for="direccion.numeroExterior">
		<g:message code="cliente.direccion.numeroExterior.label" default="Numero Exterior" />
		
	</label>
	<g:textField name="numeroExterior" maxlength="50" value="${direccionInstance?.numeroExterior}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'direccion.numeroInterior', 'error')} ">
	<label for="direccion.numeroInterior">
		<g:message code="cliente.direccion.numeroInterior.label" default="Numero Interior" />
		
	</label>
	<g:textField name="numeroInterior" maxlength="50" value="${direccionInstance?.numeroInterior}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'direccion.pais', 'error')} ">
	<label for="direccion.pais">
		<g:message code="cliente.direccion.pais.label" default="Pais" />
		
	</label>
	<g:textField name="pais" maxlength="100" value="${direccionInstance?.pais}"/>
</div>
</fieldset>
<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'fisica', 'error')} ">
	<label for="fisica">
		<g:message code="cliente.fisica.label" default="Fisica" />
		
	</label>
	<g:checkBox name="fisica" value="${clienteInstance?.fisica}" />
</div>

<div class="fieldcontain ${hasErrors(bean: clienteInstance, field: 'suspendido', 'error')} ">
	<label for="suspendido">
		<g:message code="cliente.suspendido.label" default="Suspendido" />
		
	</label>
	<g:checkBox name="suspendido" value="${clienteInstance?.suspendido}" />
</div>

