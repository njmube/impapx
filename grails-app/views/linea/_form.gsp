<%@ page import="com.luxsoft.impapx.Linea" %>



<div class="fieldcontain ${hasErrors(bean: lineaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="linea.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="20" required="" value="${lineaInstance?.nombre}"/>
</div>

