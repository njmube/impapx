<%@ page import="com.luxsoft.impapx.Embarque" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'embarque.label', default: 'Embarque')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<r:require modules="dataTables,filterpane"/>

</head>
<body>
	<div class="container-fluid">
		
		<div class="row-fluid">

			<div class="span12">
				<h3>Lista de embarques</h3>
				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<div class="btn-toolbar">
					<div class="btn-group">
						<g:link class="btn btn-primary" action="create">
							<i class="icon-plus icon-white"></i>
							<g:message code="embarque.create.label"  />
						</g:link>
						
						<filterpane:filterButton text="Filter Me" appliedText="Change Filter" class="btn"/>
						
					</div>
				</div>
				<table id="grid" class=" table table-striped table-hover table-bordered table-condensed">
					<thead>
						<tr >
							<th>Id .</th>
							<th>BL</th>
							<th>Nombre</th>
							<th>F Emb</th>
							<th>Proveedor</th>
							<th>Aduana</th>
							<th>Ingreso Aduana</th>
							<th>C.Gastos</th>
							<th>Valor</th>
							<th>Facturado</th>
							<th>Pendiente</th>
							<th>Cont</th>
							
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${embarqueInstanceList}" var="embarqueInstance">
						<tr class="${embarqueInstance.cuentaDeGastos ?'':'error' }">

						<td><g:link action="edit" id="${embarqueInstance.id}">${fieldValue(bean: embarqueInstance, field: "id")}</g:link></td>

						<td><g:link action="edit" id="${embarqueInstance.id}">${fieldValue(bean: embarqueInstance, field: "bl")}</g:link></td>
					
						<td>${fieldValue(bean: embarqueInstance, field: "nombre")}</td>
					
						<td><g:formatDate date="${embarqueInstance.fechaEmbarque}" format="dd/MM/yyyy"/></td>
					
						<td>${fieldValue(bean: embarqueInstance, field: "proveedor.nombre")}</td>
					
						<td>${fieldValue(bean: embarqueInstance, field: "aduana.nombre")}</td>
						<td><g:formatDate date="${embarqueInstance.ingresoAduana}" format="dd/MM/yyyy" /></td>
					
						
						
						<td>${fieldValue(bean: embarqueInstance, field: "cuentaDeGastos")}</td>
						<td><lx:moneyFormat number="${embarqueInstance.valor}"/></td>
						 <td><lx:moneyFormat number="${embarqueInstance.facturado}"/></td>
						 <td><lx:moneyFormat number="${embarqueInstance.porFacturar()}"/></td>
						<td><g:link action="relacionDeContenedores" 
							id="${embarqueInstance.id}" target="_blank"><i class="icon-print"></i></g:link></td>
						
						<%-- 
						<td><g:jasperReport jasper="RelacionDeContenedoresPorEmbarque" format="PDF" >
								<g:hiddenField name="ID" value="${embarqueInstance.id }"/>
							</g:jasperReport>
						</td>
						--%>
						</tr>
						
						</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${embarqueInstanceTotal}" />
				</div>
			</div>
		
		</div> 
	</div>
	<filterpane:filterPane	domain="com.luxsoft.impapx.Embarque"
		dialog="true"
		title="Filtrar"
		filterProperties="id,fechaEmbarque,nombre,ingresoAduana"
		associatedProperties="proveedor.nombre"
		additionalProperties="identifier"
		
	/>
	<r:script>
	$(function(){
		$("#grid2").dataTable({
			//"sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
			//"sDom": "<'row'<'span3'l><'span9'f>r>t<'row'<'span6'i><'span6'p>>",
			"sDom": "<'row'<'span4'f>r>t<'row'<'span6'i><'span6'p>>",
        	"oLanguage": {
      			"sUrl":"<g:resource dir="js" file="dataTables.spanish.txt" />"
    		},
    		bProcessing: true,
			bServerSide:false,
			aLengthMenu: [[100, 150, 200, 250, -1], [100, 150, 200, 250, "Todos"]],
          	iDisplayLength: 100,
    		"bPaginate": true,
    		"bInfo": true,
    		
    		"aoColumnDefs": [
        						{ "sType": "numeric","bSortable": true,"aTargets":[0] }
         					]
			});
		
		var oTable=$("#grid").dataTable({
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
