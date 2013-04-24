<%@ page import="com.luxsoft.impapx.EmbarqueDet" %>



<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'producto', 'error')} ">
	<label for="producto">
		<g:message code="embarqueDet.producto.label" default="Producto" />
		
	</label>
	<g:select id="producto" name="producto.id" from="${com.luxsoft.impapx.Producto.list()}" optionKey="id" value="${embarqueDetInstance?.producto?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'compraDet', 'error')} ">
	<label for="compraDet">
		<g:message code="embarqueDet.compraDet.label" default="Compra Det" />
		
	</label>
	<g:select id="compraDet" name="compraDet.id" from="${com.luxsoft.impapx.CompraDet.list()}" optionKey="id" value="${embarqueDetInstance?.compraDet?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'contenedor', 'error')} ">
	<label for="contenedor">
		<g:message code="embarqueDet.contenedor.label" default="Contenedor" />
		
	</label>
	<g:textField name="contenedor" maxlength="30" value="${embarqueDetInstance?.contenedor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'tarimas', 'error')} required">
	<label for="tarimas">
		<g:message code="embarqueDet.tarimas.label" default="Tarimas" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tarimas" type="number" value="${embarqueDetInstance.tarimas}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'cantidad', 'error')} required">
	<label for="cantidad">
		<g:message code="embarqueDet.cantidad.label" default="Cantidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cantidad" value="${fieldValue(bean: embarqueDetInstance, field: 'cantidad')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'costoBruto', 'error')} required">
	<label for="costoBruto">
		<g:message code="embarqueDet.costoBruto.label" default="Costo Bruto" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="costoBruto" value="${fieldValue(bean: embarqueDetInstance, field: 'costoBruto')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'costoNeto', 'error')} required">
	<label for="costoNeto">
		<g:message code="embarqueDet.costoNeto.label" default="Costo Neto" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="costoNeto" value="${fieldValue(bean: embarqueDetInstance, field: 'costoNeto')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'costoUnitario', 'error')} required">
	<label for="costoUnitario">
		<g:message code="embarqueDet.costoUnitario.label" default="Costo Unitario" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="costoUnitario" value="${fieldValue(bean: embarqueDetInstance, field: 'costoUnitario')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'embarque', 'error')} required">
	<label for="embarque">
		<g:message code="embarqueDet.embarque.label" default="Embarque" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="embarque" name="embarque.id" from="${com.luxsoft.impapx.Embarque.list()}" optionKey="id" required="" value="${embarqueDetInstance?.embarque?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'factura', 'error')} ">
	<label for="factura">
		<g:message code="embarqueDet.factura.label" default="Factura" />
		
	</label>
	<g:textField name="factura" value="${embarqueDetInstance?.factura}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'gastos', 'error')} required">
	<label for="gastos">
		<g:message code="embarqueDet.gastos.label" default="Gastos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="gastos" value="${fieldValue(bean: embarqueDetInstance, field: 'gastos')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'kilosEstimados', 'error')} required">
	<label for="kilosEstimados">
		<g:message code="embarqueDet.kilosEstimados.label" default="Kilos Estimados" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="kilosEstimados" value="${fieldValue(bean: embarqueDetInstance, field: 'kilosEstimados')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: embarqueDetInstance, field: 'kilosNetos', 'error')} required">
	<label for="kilosNetos">
		<g:message code="embarqueDet.kilosNetos.label" default="Kilos Netos" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="kilosNetos" value="${fieldValue(bean: embarqueDetInstance, field: 'kilosNetos')}" required=""/>
</div>

