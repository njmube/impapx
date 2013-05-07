<!doctype html>
<html>
<head>
<meta name="layout" content="taskView">
<title>Comprobantes fiscales</title>
<r:require module="dataTables"/>
</head>
<body>
	
	<content tag="header">
		<h3>Reporte mensual de CFDs : ${session.periodoContable}</h3>
 	</content>
 	
 	<content tag="consultas">
 		<li>
 			
		</li>
			
 	</content>
 	
 	<content tag="operaciones">
 		<li>
			<g:render template="cambiarPeriodo" bean="${session.periodoContable}"/>
 		</li>
		<li>
			<g:link class="list" action="actualizar">
				<i class="icon-list"></i>
				Actualizar CFD's
			</g:link>
		</li>
		<li>
			<g:link class="list" action="generarArchivo">
				<i class="icon-list"></i>
				Generar archivo
			</g:link>
		</li>
 	</content>
 	
 	<content tag="document">
 		<g:if test="${flash.message}">
			<bootstrap:alert class="alert-info">
				${flash.message}
			</bootstrap:alert>
		</g:if>
		<table id="grid"
			class="table table-striped table-hover table-bordered table-condensed">
			<thead>
				<tr>
					<th>RFC</th>
					<th>SERIE</th>
					<th>FOLIO</th>
					<th>AÑO/NumAp</th>
					<th>Fecha</th>
					<th>Total</th>
					<th>Impuesto</th>
					<th>E</th>
					<th>T</th>
					<th>Pedimento</th>
					<th>FechaPedimento</th>
					<th>Aduana</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${cfdList}" var="row">
					<tr>
						<td>${fieldValue(bean: row, field: "rfc")}</td>
						<td>${fieldValue(bean: row, field: "serie")}</td>
						<td><g:formatNumber number="${row.folio}"/></td>
						<td><g:formatNumber number="${row.anoAprobacion}"/><g:formatNumber number="${row.numeroDeAprobacion}"/></td>
						<td><g:formatDate date="${row.fecha }" format="dd/MM/yyyy hh:mm:ss"/></td>
						<td><g:formatNumber number="${row.total}" format="#########.##"/></td>
						<td><g:formatNumber number="${row.impuesto}" format="#########.##"/></td>
						<td>${fieldValue(bean: row, field: "estado")}</td>
						<td>${fieldValue(bean: row, field: "tipoCfd")}</td>
						<td>${fieldValue(bean: row, field: "pedimento")}</td>
						<td><g:formatDate date="${row.fechaPedimento }" format="dd/MM/yyyy"/></td>
						<td>${fieldValue(bean: row, field: "aduana")}</td>
					</tr>
				</g:each>
			</tbody>
		</table>
		<div class="pagination">
			<bootstrap:paginate total="${cfdTotal}" />
		</div>
	</content>
<r:script>
$(function(){
	$("#grid").dataTable({
		aLengthMenu: [[100, 150, 200, 250, -1], [100, 150, 200, 250, "Todos"]],
        iDisplayLength: 50,
        "oLanguage": {
      		"sUrl":"<g:resource dir="js" file="dataTables.spanish.txt" />"
	    },
    	"aoColumnDefs": [
        	{ "sType": "numeric","bSortable": true,"aTargets":[0] }
         ],
         "bPaginate": false  
	});
});
</r:script>			
</body>
</html>



