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

<div class="btn-toolbar">

	<div class="btn-group">
		<g:remoteLink class="btn btn-success" id="${embarqueInstance.id}"
			action="contenedoresDeEmbarque" controller="embarque"
			update="contenedoresGrid">
			<i class="icon-refresh icon-white"></i>
			<g:message code="default.button.load.label" default="Refrescar" />
		</g:remoteLink>
	</div>
</div>

<div id="contenedoresGrid">
	<g:render template="contenedoresGrid" bean="${embarqueInstance}"/>
</div>
