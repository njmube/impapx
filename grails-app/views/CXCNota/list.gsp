<%@ page import="com.luxsoft.impapx.cxc.CXCNota" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="taskView">
<title><g:message code="CXPNota.list.label" default="CxC Notas de Crédito" /></title>
<r:require module="dataTables"/>
</head>
<body>
	<content tag="header">
		<h3>Notas de crédito</h3>
 	</content>
	<content tag="consultas">
 		<li><g:link class="list" action="list">
			<i class="icon-list"></i>
			Notas registradas
			</g:link>
		</li>
 	</content>
 	<content tag="operaciones">
 		<li><g:link  action="create">Alta de nota</g:link></li>
 			
 	</content>
 	
 	<content tag="document">
 		<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
		</g:if>
		
		<table id="notasGrid" class="table table-striped table-hover table-bordered table-condensed">
			<thead>
				<tr>
					<td>Id</td>
					<td>Cliente</td>
					<td>Fecha</td>
					<td>Tipo</td>
					<td>Mon</td>
					<td>T.C</td>
					<td>Total</td>
					<td>Disponible</td>		
				</tr>
			</thead>
			<tbody>
				<g:each in="${CXCNotaInstanceList}" var="row">
					<tr>
						<td><g:link action="show" id="${row.id}">
							${fieldValue(bean: row, field: "id")}
							</g:link>
						</td>
						<td><g:link action="edit" id="${row.id}">
							${fieldValue(bean: row, field: "cliente.nombre")}
							</g:link>
						</td>
						<td><lx:shortDate date="${row.fecha }"/></td>
						<td>${fieldValue(bean: row, field: "tipo")}</td>
						<td>${fieldValue(bean: row, field: "moneda")}</td>
						<td>${fieldValue(bean: row, field: "tc")}</td>
						<td><lx:moneyFormat number="${row.total }"/></td>
						<td><lx:moneyFormat number="${row.disponible }"/></td>	
					
					</tr>
				</g:each>
			</tbody>
			</table>
				
	</content>
 
 <r:script>
 $(function(){
 	var oTable=$("#notasGrid").dataTable({
		"sDom": "<'row'<'span4'f>r>t<'row'<'span6'i><'span6'p>>",
        "oLanguage": {
      		"sUrl":"<g:resource dir="js" file="dataTables.spanish.txt" />"
    	},
    	bProcessing: true,
		bServerSide:false,
		/*sAjaxSource: '${createLink(controller:'CXCPago',action:'pagosAsJSON')}', 
		"aoColumns":[
			{"sName": "id", "sTitle": "Folio",sWidth:"5%", "bSortable": "true"}
			,{"sName": "cliente", "sTitle": "Cliente", "bSortable": "true"}
			,{"sName": "fecha", "sTitle": "Fecha", "bSortable": "true"}
			,{"sName": "formaDePago", "sTitle": "F.P", "bSortable": "true"}
			,{"sName": "moneda", "sTitle": "Mon", "bSortable": "false"}
			,{"sName": "tc", "sTitle": "TC", "bSortable": "false"}
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

