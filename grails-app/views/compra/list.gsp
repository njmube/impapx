<%@ page import="com.luxsoft.impapx.Compra"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="taskView">
<g:set var="entityName"
	value="${message(code: 'compra.label', default: 'Compra')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<r:require module="dataTables"/>
</head>
<body>
	
	<content tag="header">
		<h3><g:message code="compra.listView.header" default='Ordenes de compra'/></h3>
 	</content>
 	
 	<content tag="consultas">
		<li><g:link class="list" action="list">
			<i class="icon-list"></i>
			Compras registradas
			</g:link>
		</li>	
 	</content>
 	<content tag="operaciones">
 		<li>
 			<g:link class="" action="create">
				<i class="icon-plus "></i>
				Alta de compra
			</g:link>
			<li>
				<a href="#importarCompraDialog" data-toggle="modal"><i class="icon-download"></i>Importar compra</a>
			</li>
			
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
					<td>  Id</td>
					<td>Folio</td>
					<g:sortableColumn property="proveedor"
						title="${message(code: 'compra.fecha.label', default: 'Proveedor')}" />
					<g:sortableColumn property="fecha"
						title="${message(code: 'compra.fecha.label', default: 'Fecha')}" />
					<g:sortableColumn property="entrega"
						title="${message(code: 'compra.entrega.label', default: 'Entrega')}" />
					<g:sortableColumn property="depuracion"
						title="${message(code: 'compra.depuracion.label', default: 'Depuracion')}" />
					<g:sortableColumn property="comentario"
						title="${message(code: 'compra.comentario.label', default: 'Comentario')}" />
					<td>Creado</td>
					
				</tr>
			</thead>
			<tbody>
				<g:each in="${compraInstanceList}" var="row">
					<tr>
						<td><g:link action="edit" id="${row.id}">
							${fieldValue(bean: row, field: "id")}
							</g:link>
						</td>
						<td><g:link action="edit" id="${row.id}">
							${fieldValue(bean: row, field: "folio")}
							</g:link>
						</td>
						<td>${fieldValue(bean: row, field: "proveedor.nombre")}</td>
						<td><lx:shortDate date="${row.fecha }"/></td>
						<td><lx:shortDate date="${row.entrega }"/></td>
						<td><lx:shortDate date="${row.depuracion }"/></td>
						<td>${fieldValue(bean: row, field: "comentario")}</td>
						<td><a href="#" rel="tooltip" 
							title="Creado: ${g.formatDate(date:row.dateCreated)}  Modificado:${g.formatDate(date:row.dateCreated)}" 
							data-animation="false"
							data-html='true'>...</a>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
		<div class="pagination">
			<bootstrap:paginate total="${compraInstanceTotal}" />
		</div>
		
		
		<div class="modal hide fade" id="importarCompraDialog" tabindex=-1 role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="myModalLabel">Importar Compra desde Siipap</h4>
			</div>
			
			<div class="">
				<g:form controller="importador" action="importarCompra"	name="importarForm">
					<input id="folio" class="span5" type="text" name="folio" value="" placeholder="Digite el folio a importar" autofocus="autofocus" required="true">
					<div class="form-actions">
						<a href="#" class="btn" data-dismiss="modal">Cancelar</a>
						<button type="submit" class="btn btn-primary">
							<i class="icon-download icon-white"></i> Importar
						</button>
					</div>
				</g:form>
			</div>
			
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
        	{ "sType": "numeric","bSortable": true,"aTargets":[0] },
        	{ "sType": "numeric","bSortable": true,"aTargets":[1] }
         ],
         "bPaginate": false  
	});
});
</r:script>			
</body>
</html>