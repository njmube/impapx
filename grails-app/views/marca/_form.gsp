<%@ page import="com.luxsoft.impapx.Marca" %>



<div class="fieldcontain ${hasErrors(bean: marcaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="marca.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" maxlength="20" required="" value="${marcaInstance?.nombre}"/>
</div>

