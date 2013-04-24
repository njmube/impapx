<%@ page import="com.luxsoft.impapx.FacturaDeImportacion" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'gastosDeImportacion.label', default: 'Facturas')}" />
<title><g:message code="cuentasPorPagar.list.label" default="Cuentas por pagar" /></title>
<r:require module="dataTables"/>
</head>
<body>
	<div class="container">
		<ul class="nav nav-tabs" >
			<li >
				<g:link controller="facturaDeImportacion" action="list"><i class="icon-list"></i>
					Facturas de importación
				</g:link>
			</li>
			
			<li class="active">
				<g:link  action="list">
				<i class="icon-list "></i>
					Gastos de Importación
				</g:link>
			</li>
		
		</ul>

		<div class="row">
			<div class="span12">
				<div class="btn-toolbar">
					<div class="btn-group">
						<g:link class="btn btn-primary" action="create">
						<i class="icon-plus icon-white"></i>
						<g:message code="cuentaPorPagar.create.label"  />
						</g:link>
					</div>
				</div>
				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">
						${flash.message}
					</bootstrap:alert>
				</g:if>
				<table id="grid" class="display table table-striped table-hover table-bordered table-condensed">
					<thead>
						<tr>
							
							<g:sortableColumn property="id" title="Id"/>
							<g:sortableColumn property="proveedor.nombre" title="Proveedor"/>
							
							<th class="header">Dcto</th>
							<th class="header">Fecha</th>
							<th class="header">Vto</th>
							<th class="header">Moneda</th>
							<th class="header">Total</th>
							<th class="header">Saldo</th>
							<th class="header">Comentario</th>
							<th class="header">Creada</th>
							<th class="header">Modificada</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${gastosDeImportacionInstanceList}" var="row">
						<tr>	
							<td>
								<g:link action="edit" id="${row.id}">
									${fieldValue(bean: row, field: "id")}
								</g:link>
							</td>
							<td>
								<g:link action="edit" id="${row.id}">
									${fieldValue(bean: row, field: "proveedor.nombre")}
								</g:link>
							</td>
							<td>${fieldValue(bean: row, field: "documento")}</td>
							<td><g:formatDate date="${row.fecha}" format="dd/MM/yyyy"/></td>
							<td><g:formatDate date="${row.vencimiento}" format="dd/MM/yyyy"/></td>
							<td>${fieldValue(bean: row, field: "moneda")}</td>
							<td>${fieldValue(bean: row, field: "total")}</td>
							<td>${fieldValue(bean: row, field: "saldo")}</td>
							<td>${fieldValue(bean: row, field: "comentario")}</td>
							<td><g:formatDate date="${row.dateCreated}"/></td>
							<td><g:formatDate date="${row.lastUpdated}"/></td>
						</tr>
						</g:each> 
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${gastosDeImportacionInstanceTotal}" />
				</div>
			</div>
		</div>
	</div>
	<r:script>
		$(function(){
			$("#grid2").dataTable({
			"sDom": "<'row'<'span6'l><'span12'f>r>t<'row'<'span6'i><'span6'p>>",
			aLengthMenu: [[100, 150, 200, 250, -1], [100, 150, 200, 250, "Todos"]],
          	iDisplayLength: 100,
          	"oLanguage": {
      			"oPaginate": {
        			"sFirst": "Inicio",
        			"sNext": "Siguiente",
        			"sPrevious": "Página anterior",

      				},
      				"sSearch": "Filtrar:",
    			},
    			"sEmptyTable": "No data available in table",
    			"sLoadingRecords": "Please wait - loading...",
    			"sProcessing": "DataTables is currently busy",
    			"bPaginate": false,
    			"bInfo": false,
    			"sSearch": "Filtrar:"
    			//"oSearch": {"sSearch": "Filtrar"}

			});
		});
		$("tr").hover(function(){
			$(this).toggleClass("info");
		});
		$("tr").click(function(){
			$(this).toggleClass("success selected");
		});
	</r:script>
</body>
</html>
