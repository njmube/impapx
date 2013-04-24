<%@ page defaultCodec="html"%>
<div class="control-group ${invalid ? 'error' : ''}">
	
	<div >
		<table class="table table-striped table-bordered table-hovere table-codensed">
			<thead>
				<tr class="partidas-header">
					<th><g:message code="producto.clave.label" default="Clave" /></th>
					<th><g:message code="producto.descripcion.label" default="Descripción" /></th>
					<th><g:message code="producto.unidad.label" default="U" /></th>
					<th><g:message code="compraDet.solicitado" default="Solicitado" /></th>
					<th><g:message code="compraDet.entregado.label" default="Entregado" /></th>
					<th><g:message code="compraDet.pendiente.label" default="Pendiente" /></th>
			</thead>
			<tbody>
				<g:each in="${value}" status="i" var="compraDetInstance">
					<tr >
						<td>
							${fieldValue(bean: compraDetInstance, field: "producto.clave")}
						<td>
							${fieldValue(bean: compraDetInstance, field: "producto.descripcion")}
						</td>
						<td>
							${fieldValue(bean: compraDetDinstance, field: "producto.unidad.clave")}
						</td>
						<td>
							${fieldValue(bean: compraDetDinstance, field: "solicitado")}
						</td>
						<td>
							${fieldValue(bean: compraDetDinstance, field: "entregado")}
						</td>
						<td>
							${fieldValue(bean: compraDetDinstance, field: "pendiente")}
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</div>
</div>