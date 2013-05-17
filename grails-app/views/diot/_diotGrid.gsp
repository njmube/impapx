
<div class="gridPanel" id="diotGridPanel">
	<table id="diotGrid"
			class="table table-striped table-hover table-bordered table-condensed">
			<thead>
				<tr>
					<th>Tipo</th>
					<th>Proveedor</th>					
					<th>RFC</th>
					<th>Nal</th>
					<th>Pais</th>
					<th>Nacionalidad</th>
					<th>Base</th>
					<th>Excento</th>					
				</tr>
			</thead>
			<tbody>
				<g:each in="${diots}" var="row">
					<tr>
						<td>${row.getTipoModificado()}</td>
						<td>${row.proveedor}</td>					
						<td>${row.rfc}</td>
						<td>${row.nacional}</td>
						<td>${row.pais}</td>
						<td>${row.nacionalidad}</td>
						<td><lx:moneyFormat number="${row.base}"/></td>
						<td><lx:moneyFormat number="${row.excento}"/></td>					
					</tr>
				</g:each>
			</tbody>
		</table>
</div>