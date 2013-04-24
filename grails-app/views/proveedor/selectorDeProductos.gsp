<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<title>Selector de productos</title>

<r:require modules="dataTables,luxorTableUtils"/>
</head>
<body>
	<div class="container">
	
		<div class="row">
			<div class="well alert">
			<strong>Productos disponibles de asignar</strong>
				<g:link action='edit' id="${proveedor.id}" 	>
				 Proveedor: (${proveedor.id}) ${proveedor.nombre} 
				</g:link>
			
			</div>
		</div>
		
		<div class="row">
			<div class="span12">
				<a id="asignar" href="#" class="btn">Agregar</a>
				Productos: ${productos.size() }
				<table id="grid" class="simpleGrid table table-striped table-hover table-bordered table-condensed">
					
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
						<g:each in="${productos}" var="row">
						<tr id="${row.id}">
							<td>${fieldValue(bean: row, field: "clave")}</td>
							<td>${fieldValue(bean: row, field: "descripcion")}</td>
							<td>${fieldValue(bean: row, field: "unidad.clave")}</td>
							<td>${fieldValue(bean: row, field: "kilos")}</td>
							<td>${fieldValue(bean: row, field: "gramos")}</td>
							<td>${fieldValue(bean: row, field: "m2")}</td>
							<td>${fieldValue(bean: row, field: "linea")}</td>
							<td>${fieldValue(bean: row, field: "marca")}</td>
							<td>${fieldValue(bean: row, field: "clase")}</td>
						</tr>
						</g:each> 
					</tbody>
				</table>
				<%-- <div class="pagination">
					<bootstrap:paginate total="${productosTotal}" id="${proveedor.id}" max="100"/>
					</div>
				--%>
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
    	
        	 	"bPaginate": true  
			});
		
			$("#asignar").click(function(){
				var res=selectedRows();
				if(res==""){
					alert("Debe seleccionar al menos una factura");
					return
				}
				$.ajax({
					url:"${createLink(action:'registrarProductos')}",
					dataType:"json",
					data:{
						proveedorId:${proveedor.id},partidas:JSON.stringify(res)
					},
					success:function(data,textStatus,jqXHR){
						console.log('Rres: '+data.documento);
						window.location.href='${createLink(action:'edit',params:[id:proveedor.id])}';

					},
					error:function(request,status,error){
						console.log(error);
						alert("Error asignando facturas: "+error);
					},
					complete:function(){
						console.log('OK ');
					}
				});
			});
			
		});
	
	function selectedRows(){
		var res=[];
		var data=$("tbody tr.selected").each(function(){
		var tr=$(this);
		res.push(tr.attr("id"));
		});
		return res;
	}
	
	</r:script>
	
</body>
</html>
