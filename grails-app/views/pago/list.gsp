<%@ page import="com.luxsoft.impapx.cxp.Pago" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="taskView">
<title><g:message code="Pago.list.label" default="Pagos registrados" /></title>
<r:require module="dataTables"/>
</head>
<body>
	<content tag="header">
		<h3>Pagos registrados</h3>
 	</content>
	<content tag="consultas">
		<li><g:link class="list" controller="cuentaPorPagar">
			<i class="icon-list"></i>
			CxP
			</g:link>
		</li>
 		<li><g:link class="list" action="list">
			<i class="icon-list"></i>
			Pagos
			</g:link>
		</li>
 	</content>
 	<content tag="operaciones">
 		<li><g:link  action="create" class="disabled">Alta de pago</g:link></li>
 	</content>
 	
 	<content tag="document">
 		<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
		</g:if>
		
		<table id="pagosGrid" class="table table-striped table-hover table-bordered table-condensed">
			<thead>
				<tr>
					<td>Id</td>
					<td>Proveedor</td>
					<td>Fecha</td>
					<td>Mon</td>
					<td>Total</td>
					<td>Disponible</td>		
				</tr>
			</thead>
			<tbody>
				<g:each in="${pagoInstanceList}" var="row">
					<tr>
						<td><g:link action="edit" id="${row.id}">
							${fieldValue(bean: row, field: "id")}
							</g:link>
						</td>
						<td>${fieldValue(bean: row, field: "proveedor.nombre")}</td>
						<td><lx:shortDate date="${row.fecha }"/></td>
						<td>${fieldValue(bean: row, field: "moneda")}</td>
						<td><lx:moneyFormat number="${row.total }"/></td>
						<td><lx:moneyFormat number="${row.disponible }"/></td>
					</tr>
				</g:each>
			</tbody>
			</table>
				
	</content>
 
 <r:script>
 $(function(){
 	var oTable=$("#pagosGrid").dataTable({
		"sDom": "<'row'<'span4'f>r>t<'row'<'span6'i><'span6'p>>",
        "oLanguage": {
      		"sUrl":"<g:resource dir="js" file="dataTables.spanish.txt" />"
    	},
    	bProcessing: true,
		bServerSide:false,
		/*sAjaxSource: '${createLink(controller:'pago',action:'pagosAsJSON')}', 
		"aoColumns":[
			{"sName": "id", "sTitle": "Folio",sWidth:"5%", "bSortable": "true"}
			,{"sName": "proveedor", "sTitle": "Proveedor", "bSortable": "true"}
			,{"sName": "fecha", "sTitle": "Fecha", "bSortable": "true"}
			,{"sName": "moneda", "sTitle": "Mon", "bSortable": "false"}
			,{"sName": "total", "sTitle": "Total", "bSortable": "false"}
			,{"sName": "disponible", "sTitle": "Disponible", "bSortable": "false"}
			],
		"fnCreatedRow":function(nRow,aData,iDataIndex){
			$(nRow).attr("id",aData[0]);
		},
		"fnRowCallback":function(nRow, aData, iDisplayIndex, iDisplayIndexFull){
			if(aData[0]){
				//$('td:eq(0)', nRow).replaceWith( '<td><g:link action="edit" id="">'+$('td:eq(0)', nRow).html()+'</g:link></td>' );
			}
		},*/
		//"bDeferRender": false,
    	//"bPaginate": false,
    	"bInfo": false,
    	iDisplayLength: 1000
	});
	
	$("#refreshBtn").live('click',function(e){
		oTable.fnReloadAjax();
	});
	
 });
 
 
 </r:script>
	
</body>
</html>