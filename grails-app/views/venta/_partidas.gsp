<div>
	<g:if test="${flash.gridMessage}">
		<bootstrap:alert class="alert-error">${flash.gridMessage}</bootstrap:alert>
	</g:if>
	
	<g:hasErrors bean="${ventaInstance}">
		<bootstrap:alert class="alert-error">
		<ul>
			<g:eachError bean="${ventaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
	</bootstrap:alert>
	
	</g:hasErrors>
	
	<table id="partidasGrid" class=" simpleGrid table table-striped table-bordered table-condensed" cellpadding="0" cellspacing="0" border="0">
		<thead>
			<tr>
				<th>Clave</th>
				<th>Descripcion</th>
				<th>Contenedor</th>
				<th>BL</th>
				<th>Kg </th>
				<th>Cantidad </th>
				<th>Precio </th>
				<th>Importe</th>
				<th>Desc</th>
				<th>SubTotal</th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${partidas}" var="row">
				<tr id="${fieldValue(bean:row, field:"id")}" >
					
					<td>
						<g:link controller='ventaDet' action='edit' id="${row.embarque.embarque.id}" target="_blank"> 
						${fieldValue(bean:row, field:"producto.clave")}
						</g:link>
					</td>
					
					<td>${fieldValue(bean:row, field:"producto.descripcion")}</td>
					<td>${fieldValue(bean:row, field:"contenedor")}</td>
					<td>
						<g:link controller='embarque' action='edit' id="${row.embarque.embarque.id}" target="_blank"> 
						${fieldValue(bean:row, field:"embarque.embarque.bl")}
						</g:link>
					</td>
					<td><g:formatNumber number="${row.kilos}" format="###,###,###.###"/></td>
					<td><g:formatNumber number="${row.cantidad}" format="###,###,###.###"/></td>
					<td><lx:moneyFormat number="${row.precio}"/> </td>
					<td><lx:moneyFormat number="${row.importe}"/> </td>
					<td><lx:moneyFormat number="${row.descuentos}"/> </td>
					<td><lx:moneyFormat number="${row.subtotal}"/> </td>
				</tr>
			</g:each>
		</tbody>
	</table>
</div>
