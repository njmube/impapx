

<%@ page import="com.luxsoft.impapx.CuentaDeGastos" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'cuentaDeGastos.label', default: 'CuentaDeGastos')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<r:require module="dataTables"/>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">

			<div class="span2">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">Cuentas de Gastos</li>
						<li class="active"><g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
								Todas 
							</g:link></li>
						<li><g:link class="create" action="create">
								<i class="icon-plus"></i>
								Alta
							</g:link></li>
					</ul>
				</div>
			</div>

			<div class="span10">

				<div class="page-header">
					<h3>
						<g:message code="cuentaDeGastos.list.label" default="Cuentas de gastos"/>
					</h3>
				</div>

				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<table id="grid" class="table table-striped table-hover table-bordered table-condensed">
					<thead>
						<tr>
							<th>Folio</th>
							<th>Embarque</th>
							<th>BL</th>
							<th>Agente</th>
							<th>Referencia</th>
							<g:sortableColumn property="fecha" title="${message(code: 'cuentaDeGastos.fecha.label', default: 'Fecha')}" />
							<th><g:message code="cuentaDeGastos.total" default="Total"/></th>
							<th><g:message code="cuentaDeGastos.comentario" default="Comentario"/></th>
							
						</tr>
					</thead>
					<tbody>
						<g:each in="${cuentaDeGastosInstanceList}" var="cuentaDeGastosInstance">
							<tr>
								<td>${fieldValue(bean: cuentaDeGastosInstance, field: "id")}</td>
								<td><g:link action="edit" id="${cuentaDeGastosInstance.id}">
									${fieldValue(bean: cuentaDeGastosInstance, field: "embarque.id")}
									</g:link>
								</td>
								<td><g:link action="edit" id="${cuentaDeGastosInstance.id}">
									${fieldValue(bean: cuentaDeGastosInstance, field: "embarque.bl")}
									</g:link>
								</td>
								<td>${fieldValue(bean: cuentaDeGastosInstance, field: "proveedor.nombre")}</td>
								<td>${fieldValue(bean: cuentaDeGastosInstance, field: "referencia")}</td>
								<td><lx:shortDate date="${cuentaDeGastosInstance.fecha}" /></td>
								<td><lx:moneyFormat number="${cuentaDeGastosInstance.total }"/></td>
								<td><g:link action="edit" id="${cuentaDeGastosInstance.id}">${fieldValue(bean: cuentaDeGastosInstance, field: "comentario")}</g:link></td>
								
							</tr>
						</g:each>
					</tbody>
				</table>
				
			</div>

		</div>
	</div>
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
