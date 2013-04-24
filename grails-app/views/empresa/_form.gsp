<%@ page import="com.luxsoft.impapx.Empresa" %>



<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="empresa.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="nombre" cols="40" rows="5" maxlength="300" required="" value="${empresaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'rfc', 'error')} ">
	<label for="rfc">
		<g:message code="empresa.rfc.label" default="Rfc" />
		
	</label>
	<g:textField name="rfc" value="${empresaInstance?.rfc}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'regimen', 'error')} ">
	<label for="regimen">
		<g:message code="empresa.regimen.label" default="Regimen" />
		
	</label>
	<g:textField name="regimen" value="${empresaInstance?.regimen}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: empresaInstance, field: 'direccion', 'error')} required">
	<label for="direccion">
		<g:message code="empresa.direccion.label" default="Direccion" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

