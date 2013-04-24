<%@ page import="com.luxsoft.impapx.Producto" %>



<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'clave', 'error')} required">
	<label for="clave">
		<g:message code="producto.clave.label" default="Clave" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="clave" maxlength="10" required="" value="${productoInstance?.clave}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="producto.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" maxlength="250" required="" value="${productoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'unidad', 'error')} required">
	<label for="unidad">
		<g:message code="producto.unidad.label" default="Unidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="unidad" name="unidad.id" from="${com.luxsoft.impapx.Unidad.list()}" optionKey="id" required="" value="${productoInstance?.unidad?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'linea', 'error')} required">
	<label for="linea">
		<g:message code="producto.linea.label" default="Linea" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="linea" name="linea.id" from="${com.luxsoft.impapx.Linea.list()}" optionKey="id" required="" value="${productoInstance?.linea?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'marca', 'error')} ">
	<label for="marca">
		<g:message code="producto.marca.label" default="Marca" />
		
	</label>
	<g:select id="marca" name="marca.id" from="${com.luxsoft.impapx.Marca.list()}" optionKey="id" value="${productoInstance?.marca?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'clase', 'error')} ">
	<label for="clase">
		<g:message code="producto.clase.label" default="Clase" />
		
	</label>
	<g:select id="clase" name="clase.id" from="${com.luxsoft.impapx.Clase.list()}" optionKey="id" value="${productoInstance?.clase?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'acabado', 'error')} ">
	<label for="acabado">
		<g:message code="producto.acabado.label" default="Acabado" />
		
	</label>
	<g:textField name="acabado" value="${productoInstance?.acabado}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'color', 'error')} ">
	<label for="color">
		<g:message code="producto.color.label" default="Color" />
		
	</label>
	<g:textField name="color" value="${productoInstance?.color}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'ancho', 'error')} required">
	<label for="ancho">
		<g:message code="producto.ancho.label" default="Ancho" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="ancho" value="${fieldValue(bean: productoInstance, field: 'ancho')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'calibre', 'error')} required">
	<label for="calibre">
		<g:message code="producto.calibre.label" default="Calibre" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="calibre" type="number" value="${productoInstance.calibre}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'caras', 'error')} required">
	<label for="caras">
		<g:message code="producto.caras.label" default="Caras" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="caras" type="number" value="${productoInstance.caras}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'gramos', 'error')} required">
	<label for="gramos">
		<g:message code="producto.gramos.label" default="Gramos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="gramos" value="${fieldValue(bean: productoInstance, field: 'gramos')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'kilos', 'error')} required">
	<label for="kilos">
		<g:message code="producto.kilos.label" default="Kilos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="kilos" value="${fieldValue(bean: productoInstance, field: 'kilos')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'largo', 'error')} required">
	<label for="largo">
		<g:message code="producto.largo.label" default="Largo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="largo" value="${fieldValue(bean: productoInstance, field: 'largo')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'm2', 'error')} required">
	<label for="m2">
		<g:message code="producto.m2.label" default="M2" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="m2" value="${fieldValue(bean: productoInstance, field: 'm2')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'precioContado', 'error')} required">
	<label for="precioContado">
		<g:message code="producto.precioContado.label" default="Precio Contado" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="precioContado" value="${fieldValue(bean: productoInstance, field: 'precioContado')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'precioCredito', 'error')} required">
	<label for="precioCredito">
		<g:message code="producto.precioCredito.label" default="Precio Credito" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="precioCredito" value="${fieldValue(bean: productoInstance, field: 'precioCredito')}" required=""/>
</div>

