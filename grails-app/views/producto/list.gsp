

<%@ page import="com.luxsoft.impapx.Producto" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'producto.label', default: 'Producto')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<r:require module="dataTables"/>
</head>
<body>
	<div class="container">

		<div class="row">

			<div class="span12">

				<div class="navbar">
					<div class="navbar-inner">
						<a class="brand" href="#">Catálogo de ${entityName}</a>
						<ul class="nav nav-pills">
							<li class="active">
								<g:link  action="list">
									<i class="icon-list icon-white"></i>
								<g:message code="default.list.label" args="[entityName]" />
								</g:link>
							</li>
							<li><g:link  action="create">
									<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
								</g:link>
							</li>
							<li><g:link action="importarProductos" controller="importador" 
								onclick="return myConfirm2(this,'Importar productos de SiipapEx','Importación de información');">
									<i class="icon-download"></i> Importar
								</g:link>
							</li>
							<li><g:link action="importarProducto" controller="importador"
								data-target="#importarDialog" data-toggle="modal">
								<i class="icon-download"></i> Importar por clave
							</g:link></li>
						</ul>
					</div>
				</div>
			</div>
		
		<div class="row">
			<div class="span12">
				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<table id="grid" class="display table table-striped table-hover table-bordered table-condensed">
					<thead>
						<tr>
							
							<th class="header"><g:message code="producto.descripcion.label" default="Clave"/></th>
							<th class="header"><g:message code="producto.descripcion.label" default="Descripción"/></th>
							<th class="header"><g:message code="producto.unidad.label" default="U"/></th>
							<th class="header"><g:message code="producto.kilos.label" default="K" /></th>
							<th class="header"><g:message code="producto.gramos.label" default="g" /></th>
							<th class="header"><g:message code="producto.m2.label" default="M2" /></th>
							<th class="header"><g:message code="producto.linea.label" default="Linea" /></th>
							<th class="header"><g:message code="producto.marca.label" default="Marca" /></th>
							<th class="header"><g:message code="producto.clase.label" default="Clase" /></th>
						</tr>
					</thead>
					<tbody>
						<%--
						<g:each in="${productoInstanceList}" var="productoInstance">
							<tr>
								
						<td><g:link action="show" id="${productoInstance.id}">${fieldValue(bean: productoInstance, field: "clave")}</g:link></td>
					
						<td>${fieldValue(bean: productoInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: productoInstance, field: "unidad.clave")}</td>
						<td>${fieldValue(bean: productoInstance, field: "kilos")}</td>
						<td>${fieldValue(bean: productoInstance, field: "gramos")}</td>
						<td>${fieldValue(bean: productoInstance, field: "m2")}</td>
						<td>${fieldValue(bean: productoInstance, field: "linea")}</td>
					
						<td>${fieldValue(bean: productoInstance, field: "marca")}</td>
					
						<td>${fieldValue(bean: productoInstance, field: "clase")}</td>
					
							</tr>
						</g:each> --%>
					</tbody>
				</table>
				<%-- <div class="pagination">
					<bootstrap:paginate total="${productoInstanceTotal}" />
 				</div>
 				--%> 
			</div>
		</div>
		
		<div class="modal hide fade" id="importarDialog" tabindex=-1
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="myModalLabel">Importar Producto desde Siipap</h4>
		</div>
		<div class="">
			<g:form controller="importador" action="importarProducto"
				name="importarForm">
				<input id="clave" class="span5" type="text" name="clave" value=""
					placeholder="Digite la clave a importar" autofocus="autofocus" required="true">
				<div class="form-actions">
					<a href="#" class="btn" data-dismiss="modal">Cancelar</a>
					<button type="submit" class="btn btn-primary">
						<i class="icon-download icon-white"></i> Importar
					</button>
				</div>
			</g:form>
		</div>
	</div>
		
		
	</div>

	<r:script>
		$(function(){
			$("#grid").dataTable({
			"sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
			bProcessing: true,
			bServerSide:false,
			sAjaxSource: '${createLink(controller:'producto',action:'listAsJSON')}', 
			aLengthMenu: [[100, 150, 200, 250, -1], [100, 150, 200, 250, "Todos"]],
          	iDisplayLength: 100,
          	"oLanguage": {
      			"oPaginate": {
        			"sFirst": "Inicio",
        			"sNext": "Siguiente",
        			"sPrevious": "Página anterior"
      				}
    			},
    			"sEmptyTable": "No data available in table",
    			 "sLoadingRecords": "Please wait - loading...",
    			 "sProcessing": "DataTables is currently busy",
    			 "sSearch": "Filtrar:",
    			 "sInfo": "Got a total of _TOTAL_ entries to show (_START_ to _END_)"

			});
		});
	</r:script>

</body>
</html>
