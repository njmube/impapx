<div >
	<h4>${embarqueInstance}</h4>
</div>
<div>
	<g:if test="${embarqueInstance}">
		Cuenta de gastos:
		<g:link controller='gastosDeImportacion' action="edit" id="${facturaGastos?.id}" target="_blank">
			 ${facturaGastos?.documento}
		</g:link>
	</g:if>
</div>
<g:if test="${embarqueInstance.facturado<=0}">
<div class="btn-toolbar">

	<div class="btn-group">
		<g:remoteLink class="btn btn-success" id="${embarqueInstance.id}"
			action="cargarPartidas" controller="embarque"
			update="costosGrid">
			<i class="icon-refresh icon-white"></i>
			<g:message code="default.button.load.label" default="Refrescar" />
		</g:remoteLink>
		<g:remoteLink class="btn btn-info" id="${embarqueInstance.id}"
			action="actualizarPrecios" controller="embarque"
			update="costosGrid">
			<i class="icon-check icon-white"></i>
			<g:message code="default.button.load.label" default="Actualizar precios" />
		</g:remoteLink>
		
	</div>
</div>
</g:if>
<div id="costosGrid">
	
	<g:jasperReport jasper="AnalisisDeCosteoDeImportacion" format="PDF,HTML,XLS" name="Imprimir Análisis">
			<g:hiddenField name="ID" value="${embarqueInstance.id}"/>
	</g:jasperReport>
	
	<g:render template="costosGrid" bean="${embarqueInstance}"/>
	
</div>
