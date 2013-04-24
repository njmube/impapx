<%@ page import="com.luxsoft.impapx.CompraDet" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<title>Selector de compras a detalle</title>
<r:require module="luxorSimpleTable"/>
</head>
<body>
	<div class="container">
		<div class="well alert">
		<strong> Compras disponibles</strong>
			<g:link action='edit' id="${embarque.id}" class="commit">
				 Embarque: ${embarque.id} BL:${embarque.bl} (${embarque.proveedor.nombre })  
			</g:link>
			
		</div>

		<div class="row">
			
			<div class="span12">
				<a id="asignar" href="#" class="btn">Asignar</a>
				<table id="grid" class="grid table table-striped table-hover table-bordered table-condensed">
					<thead>
						<tr>
							<g:sortableColumn property="id"    title="Id"/>
							<g:sortableColumn property="compra.folio" title="Compra"/>
							<th class="header">Fecha</th>
							<g:sortableColumn property="compra.proveedor.nombre" title="Proveedor"/>
							<g:sortableColumn property="producto.clave" title="Producto"/>
							<g:sortableColumn property="producto.descripcion" title="Descripción"/>
							<th class="header">Solicitado</th>
							<th class="header">Entregado</th>
							<th class="header">Pendiente</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${compraDetInstanceList}" var="row">
						<tr id="${row.id}">	
							<td>${row.id}</td>
							<td>${fieldValue(bean: row, field: "compra.folio")}</td>
							<td><lx:shortDate date="${row.compra.fecha}"/></td>
							<td>${fieldValue(bean: row, field: "compra.proveedor.nombre")}</td>
							<td>${fieldValue(bean: row, field: "producto.clave")}</td>
							<td>${fieldValue(bean: row, field: "producto.descripcion")}</td>
							<td>${fieldValue(bean: row, field: "solicitado")}</td>
							<td>${fieldValue(bean: row, field: "entregado")}</td>
							<td>${fieldValue(bean: row, field: "pendiente")}</td>
						</tr>
						</g:each> 
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${compraDetInstanceTotal}" id="${embarque.id}" max="100"/>
				</div>
			</div>
		</div>
	</div>
	<r:script>
		$(function(){
		
			$("#asignar").click(function(){
				var res=selectedRows();
				if(res==""){
					alert("Debe seleccionar al menos una compra unitaria");
					return
				}
				//var ok=confirm("Asignar partidas seleccionadas? "+res.length);
				//if(!ok)
					//return;
				$.ajax({
					url:"${createLink(controller:'embarque',action:'asignarComprasUnitarias')}",
					dataType:"json",
					data:{
						embarqueId:${embarque.id},partidas:JSON.stringify(res)
					},
					success:function(data,textStatus,jqXHR){
						console.log('Rres: '+data.documento);
						//alert('Compras unitarias asignadas exitosamente');
						window.location.href='${createLink(controller:'embarque',action:'edit',params:[id:embarque.id])}';

					},
					error:function(request,status,error){
						console.log(error);
						alert("Error asignando compras: "+error);
					},
					complete:function(){
						console.log('OK ');
					}
				});
			});
			
		});
	</r:script>
	
</body>
</html>
