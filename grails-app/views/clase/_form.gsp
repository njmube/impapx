<%@ page import="com.luxsoft.impapx.Clase" %>



<div class="fieldcontain ${hasErrors(bean: claseInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="clase.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="20" required="" value="${claseInstance?.nombre}"/>
</div>

