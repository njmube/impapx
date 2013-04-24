<%@ page import="com.luxsoft.impapx.ProveedorProducto" %>
<%@ page defaultCodec="html" %>

<div class="btn-toolbar">
	<div class="btn-group" id="toolbar">
		<%-- <a href="#agregarProd" role="botton" class="btn btn-primary" data-toggle="modal">Agregar</a>--%>
		
		
		<g:link action="selectorDeProductos" id="${proveedorInstance.id }" class="btn btn-primary">Agregar</g:link>
		<button  id="#eliminarBtn" class="btn btn-danger" onclick="eliminarProductos()">
			<i class="icon-trash icon-white "></i> 
  					Eliminar
  		</button>
  		
  		<a href="#asignarPrecioDialog" role="botton" class="btn " data-toggle="modal">Asignar precio</a>
  		
	</div>
</div>

<table id="grid" class="table table-striped table-hover table-bordered table-condensed">
	<thead>
		<tr>
			<g:sortableColumn property="producto.clave" title="Clave"/>
			<g:sortableColumn property="producto.descripcion" title="Descripción"/>
			<g:sortableColumn property="producto.gramos" title="Gramos"/>
			<g:sortableColumn property="codigo" title="Código Prov"/>
			<g:sortableColumn property="descripcion" title="Desc Prov"/>
			<g:sortableColumn property="costoUnitario" title="Costo U"/>
		</tr>
	</thead>
	<tbody>
		<g:each in="${proveedorInstance?.productos.sort{it.codigo}}" var="det">
		<tr id="${det.id}" class="${det.costoUnitario<=0?'textError':''}">
			<td><g:link controller="proveedorProducto" action="edit" id="${det.id}">${fieldValue(bean:det,field:"producto.clave") }</g:link></td>
			<td>${fieldValue(bean:det,field:"producto.descripcion") }</td>
			<td>${fieldValue(bean:det,field:"gramos") }</td>
			<td>
				<g:link controller="proveedorProducto" action="edit" id="${det.id }">
					${fieldValue(bean:det,field:"codigo") }
				</g:link>
			</td>
			<td>${fieldValue(bean:det,field:"descripcion") }</td>
			<td name="costoUnitario">${fieldValue(bean:det,field:"costoUnitario") }</td>
		</tr>
		</g:each>
	</tbody>
</table>

<div class="modal hide fade" id="asignarPrecioDialog" tabindex=-1 role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="myModalLabel">Asignacion de facturas</h4>
	</div>
	<div class="modal-body">
		<input id="precioAsignado" class="numericField" type="number" name="precio" value="" placeholder="Defina el precio a asignar">
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal">Cancelar</a>
    	<%-- <a href="#" class="btn btn-primary">Aplicar</a> --%>
    	<button id="asignarCosto" class="btn btn-primary" >Aplicar</button>
	</div>
</div>

<div id="agregarProd" class="modal hide fade" tabindex="-1" role="dialog" >
  <div class="modal-header">
  	 <h3>Alta de producto</h3>
  </div>
  <g:set var="proveedorProductoInstance" value="${new ProveedorProducto(proveedor:proveedorInstance)}"></g:set>
  <g:form class="form-horizontal" controller="proveedorProducto" action="create" >
  	<div class="modal-body">
    	
    		<fieldset>
    			<f:with bean="proveedorProductoInstance">
					<g:hiddenField name="proveedorId" value="${proveedorProductoInstance?.proveedor?.id}"/>
					<g:hiddenField name="productoId"  value="${proveedorProductoInstance?.producto?.id}"/>
					<f:field property="producto">
						<g:field type="text" name="producto" id="productoAuto" class="input-xxlarge"/>
					</f:field>
					<f:field property="codigo" input-id="codigo" value="${proveedorProductoInstance?.codigo}"/>
					<f:field property="descripcion" input-id="descripcion" value="${proveedorProductoInstance?.descripcion}"/>
					<f:field property="costoUnitario" input-id="costoUnitario" value="${proveedorProductoInstance?.costoUnitario}"/>
				</f:with>
    			<div class="model-fotter form-actions">
					<a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Cancelar</a>
					<button type="submit" class="btn btn-primary">
						<i class="icon-ok icon-white"></i>
						<g:message code="default.button.create.label" default="Create" />
					</button>
				</div>
    		</fieldset>
  	</div>
  </g:form>
  
</div>
<r:script>
	$(function(){
		
		/** Autocomplete para productos **/		
		$("#productoAuto").autocomplete(
			{
				source:'<g:createLink controller="proveedor" action="productosAsignablesJSONList" params="['proveedorId':proveedorProductoInstance?.proveedor?.id]"/>',
				minLength:3,
				select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
				$("#productoId").val(ui.item.id);
				$("#codigo").val(ui.item.id);
				$("#descripcion").val(ui.item.descripcion);
				$("#costoUnitario").val(0);
			}
		});
		
		$("#grid tbody tr").live('hover',function(){
			$(this).toggleClass("info");
		});
		$("#grid tbody tr").live('click',function(){
			$(this).toggleClass("success selected");
		});

		$("#grid").dataTable({
			//sDom: "<'row'<'span12'f>r>t<'row'<'span6'i><'span6'p>>",
			sDom: " <'span12'f>rt<'row'<'span6'i><'span6'p>>",
			bProcessing: true,
			bServerSide:false,
			aLengthMenu: [[100, 150, 200, 250, -1], [100, 150, 200, 250, "Todos"]],
			iDisplayLength: 100,
			oLanguage: {
			    "oPaginate": {
			        "sFirst": "Inicio",
			        "sNext": "Siguiente",
			        "sPrevious": "Página anterior"
			     },
			    sSearch: "Filtrar:"
			},
			bPaginate: false,
		    bInfo: false
		});
		
		$(".numericField").forceNumeric();
	});
	
	
	function selectedRows(){
		var res=[];
		var data=$(".selected").each(function(){
			var tr=$(this);
			res.push(tr.attr("id"));
		});
		return res;
	}
	
	$("#asignarCosto").click(function(e){
		asignarCosto();
		e.stopPropagation();
	});
	
	function asignarCosto(){
		var res=selectedRows();
		var costoUnitario=$("#precioAsignado").val();
		console.log('Asignando costo unitario: '+costoUnitario+' a partidas: '+res);
		$.ajax({
			url:"${createLink(controller:'proveedor',action:'actualizarCostoUnitarioEnProductos')}",
			dataType:"json",
			data:{
				costoUnitario:costoUnitario,partidas:JSON.stringify(res)
			},
			success:function(data,textStatus,jqXHR){
				console.log('Rres: '+data.costoUnitario);
				$('.selected td[name=costoUnitario]').text(data.costoUnitario);
				$("#asignarPrecioDialog").modal("hide");
				clearAllRows();

			},
			error:function(request,status,error){
				console.log(error);
				alert("Error: "+error);
			},
			complete:function(){
				clearAllRows();
				$("#asignarPrecioDialog").show('false');
			}
		});
	}
	
	function eliminarProductos(){
		var res=selectedRows();
		if(res=="")
			return;
		var ok=confirm("Eliminar "+res.length+" productos vinculados en este proveedor?");
		if(!ok)
			return;
		$.ajax({
			url:"${createLink(controller:'proveedor',action:'eliminarProductos')}",
			dataType:"json",
			data:{
				proveedorId:${proveedorInstance.id},partidas:JSON.stringify(res)
			},
			success:function(data,textStatus,jqXHR){
				console.log('Elminados: '+data.eliminados);
				location.reload();
			},
			error:function(request,status,error){
				console.log(error);
				alert("Error: "+error);
			},
			complete:function(){
				console.log('OK ');
				clearAllRows();
			}
		});
	}
	
	function selectAllRows(){
		$("#grid tbody tr").addClass("success selected");
	}
	function clearAllRows(){
		$("#grid tbody tr").removeClass("success selected");
	}
	$(document).on("keydown",function(event){
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(event.ctrlKey ){
			if(keycode==65){
				//console.log('detecting under ctrl');
				selectAllRows();
			}else if(keycode==67){
				clearAllRows();
			}
		}
	})
			
</r:script>
