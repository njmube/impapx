<div class="span6">
	<h4>${embarqueInstance}</h4>
</div>
<div>
	<g:if test="${embarqueInstance.cuentaDeGastos}">
		
		<g:link controller='cuentaDeGastos' action="edit" id="${embarqueInstance.cuentaDeGastos}" target="_blank">
			Cuenta de gastos:  ${embarqueInstance.cuentaDeGastos}
		</g:link>
	</g:if>
</div>

<div class="row-fluid">
<div class="span12">
<g:if test="${embarqueInstance.facturado<=0}">
<div class="btn-toolbar">
		<div class="btn-group">
			<g:link class="btn btn-success" action="comprasPendientes" id="${embarqueInstance.id}">
				<i class="icon-plus icon-white"></i>Agregar 
			</g:link>
			<button  id="#eliminarBtn" class="btn btn-danger" onclick="eliminarPartidas()">
				<i class="icon-trash icon-white "></i> 
  					Eliminar
  			</button>
			
			<g:link class="btn " controller="embarque" action="eliminarFaltantes" id="${embarqueInstance.id}">
				<i class="icon-plus icon-white"></i>Eliminar faltantes
			</g:link>
  			<button  id="#asignarFacturaBtn" class="btn" data-target="#asignarFacturaDialog" data-toggle="modal">
  				Asignar Factura
  			</button>
  			<button  id="#cancelarFacturaBtn"  onclick="cancelarFacturas()" class="btn">
  				Cancelar asignación
  			</button>
  			<button  class="btn" data-target="#asignarContendorDialog" data-toggle="modal">
  				Asignar Contenedor
  			</button>
  			
		</div>
</div>
</g:if>
</div>
</div>
	<div>
		
		<table id="grid" class="table table-striped table-bordered table-condensed" cellpadding="0" cellspacing="0" border="0">
			<thead>
				<tr>
					<th>Clave</th>
					<th>Descripcion</th>
					<th>Compra</th>
					<th>Cantidad</th>
					<th>Kg Net</th>
					<th>Kg Est</th>
					<th>Precio</th>
					<th>Importe</th>
					<th>Factura</th>
					<th>Contenedor</th>
					<th>Tarimas</th>
					
				</tr>
			</thead>
			<tbody>
				<g:each in="${partidas}" var="row">
				<tr id="${ formatNumber(number:row.id,format:'##########') }" name="embarqueDetId"
					class="${( row?.kilosNetos<=0 || row?.tarimas<=0 )?'textError':'' }">
					<td>
						<g:link action="edit" controller="embarqueDet" id="${row?.id}" params="[proveedorId:embarqueInstance.proveedor.id]">
							${fieldValue(bean:row, field:"producto.clave")}
						</g:link>
					</td>
					<td>
						<g:link action="edit" controller="embarqueDet" id="${row?.id}">
							${fieldValue(bean:row, field: "producto.descripcion")}
						</g:link>
					</td>
					<td>${fieldValue(bean: row, field: "compraDet.compra.folio")}</td>
					<td>${fieldValue(bean: row, field: "cantidad")}</td>
					<td>${fieldValue(bean: row, field: "kilosNetos")}</td>
					<td>${fieldValue(bean: row, field: "kilosEstimados")}</td>
					<td><lx:moneyFormat number="${row.precio }"/></td>
					<td><lx:moneyFormat number="${row.importe }"/></td>
					<td name="factura">
						<g:link controller="facturaDeImportacion" action="edit" id="${row?.factura?.id} " target="_blank">
							${fieldValue(bean: row, field: "factura.documento")}
						</g:link>
					</td>
					<td name="contenedor">${fieldValue(bean: row, field: "contenedor")}</td>
					<td name="tarimas">${fieldValue(bean: row, field: "tarimas")}</td>
				</tr>
				</g:each>
			</tbody>
			<tfoot>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th id="totalCantidad"><g:formatNumber number="${embarqueInstance?.getTotal('cantidad')}" format="###,###,###.##"/></th>
					<th id="totalKilos"><g:formatNumber number="${embarqueInstance?.getTotal('kilosNetos')}" format="###,###,###.##"/></th>
					<th id="totalKilsoEstimados"><g:formatNumber number="${embarqueInstance?.getTotal('kilosEstimados')}" format="###,###,###.##"/></th>
					<th></th>
					<th id="totalImporte"><lx:moneyFormat number="${embarqueInstance?.getTotal('importe')}" /></th>
					<th></th>
					<th></th>
					<th></th>
					
				</tr>
			</tfoot>
		</table>
	</div>

<div class="modal hide fade" id="asignarFacturaDialog" tabindex=-1 role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="myModalLabel">Asignacion de facturas</h4>
	</div>
	<div class="modal-body">
		<g:hiddenField name="facturaId"/>
		<input id="factura" type="text" name="factura" value="" placeholder="Seleccione una factura" class="input-xxlarge">
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal">Cancelar</a>
    	<button id="asignarFactura" class="btn btn-primary" onclick="asignarFactura()">Asignar</button>
	</div>
</div>

<div class="modal hide fade" id="asignarContendorDialog" tabindex=-1 role="dialog" 
	aria-labelledby="myModalLabel">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="myModalLabel">Asignacion de contenedor</h4>
	</div>
	<div class="modal-body">
		<input id="contenedor" type="text" name="contendor" value="" placeholder="Seleccione una contenedor" class="input-xxlarge">
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal">Cancelar</a>
    	<button id="asignarContenedor" class="btn btn-primary" onclick="asignarContenedor()">Aplicar</button>
	</div>
</div>

<div class="modal hide fade" id="asignarFacturaGastosDialog" tabindex=-1 role="dialog" 
	aria-labelledby="myModalLabel">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="myModalLabel">Gastos de importación</h4>
	</div>
	<div class="modal-body">
		<g:hiddenField name="facturaGastosId"/>
		<input id="facturaGastos"  type="text" name="facturaGastos" value="" placeholder="Seleccione una factura de gastos" class="input-xxlarge">
	</div>
	<div class="modal-footer">
		<a href="#" class="btn" data-dismiss="modal">Cancelar</a>
    	<button id="asignarFacturaGastos" class="btn btn-primary" onclick="asignarFacturaDeGastos()">Aplicar</button>
    	
	</div>
</div>


<r:script>
	
	$(function(){	
		
		$("#grid").dataTable({
			"sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
			//"sDom": "<'row'<'span6'l><'span12'f>r>t<'row'<'span6'i><'span6'p>>",
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
    			"sSearch": "Filtrar:",
    			"aaSorting": []
		});
		
		$("tbody tr").live('click',function(){
			$(this).toggleClass("success selected");
		});
		
		$(document).on("keydown",function(event){
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(event.ctrlKey ){
				if(keycode==65){
					$("#grid tr").addClass("success selected");
					return false;
				}else if(keycode==67){
					$("#grid tr").removeClass("success selected");
					return false;
				}
			}
		})
		
		$("#factura").autocomplete(
			{
				source:'${createLink(controller:'embarque',action:'facturasPorAsignarJSON',params:[proveedorId:embarqueInstance.proveedor.id]) }',
				minLength:3,
				select:function(e,ui){
		   			$("#facturaId").val(ui.item.id);
			}
		});
		
		$('#asignarFacturaDialog').on('show', function(){
			$("#facturaId").val("");
			$("#factura").val("")
		});
		
		$("#facturaGastos").autocomplete(
			{
				source:'${createLink(controller:'embarque',action:'facturasDeGastosPorAsignarJSON') }',
				minLength:3,
				select:function(e,ui){
		   			$("#facturaGastosId").val(ui.item.id);
			}
		});
		
		$('#asignarFacturaGastosDialog').on('show', function(){
			$("#facturaGastosId").val("");
			$("#facturaGastos").val("")
		});
	});
	

	function asignarFactura(){
		var facturaId=$('#facturaId').val();
		if(facturaId==""){
			alert("Debe selccionar una factura para asignar");
			return;
		}
		
		var res=selectedRows();
		if(res.length==0){
			alert("Debe seleccionar al menos una partida de embarque");
			return;
		}
		console.log('Asignando factura: '+facturaId);
		console.log('Asignando factura: '+facturaId+' a partidas: '+res);
		$.ajax({
			url:"${createLink(controller:'embarque',action:'asignarFactura')}",
			dataType:"json",
			data:{
				facturaId:facturaId,partidas:JSON.stringify(res)
			},
			success:function(data,textStatus,jqXHR){
				console.log('Rres: '+data.documento);
				$('.selected td[name=factura]').text(data.documento);
				$("#asignarFacturaDialog").modal("hide")

			},
			error:function(request,status,error){
				console.log(error);
				alert("Error: "+error);
			},
			complete:function(){
				console.log('OK ');
			}
		});
	}

	function cancelarFacturas(){
		
		var res=selectedRows();
		if(res.length==0){
			alert('Debe seleccionar al menos un registro');
			return;
		}
		var ok=confirm('Cancelar asignación de ' + res.length+' factura(s)?');
		if(!ok)
			return;
		console.log('Cancelando facturas');
		$.ajax({
			url:"${createLink(controller:'embarque',action:'cancelarAsignacionDeFacturas')}",
			dataType:"json",
			data:{
				partidas:JSON.stringify(res)
			},
			success:function(data,textStatus,jqXHR){
				$('.selected td[name=factura]').text("");
			},
			error:function(request,status,error){
				alert("Error: "+error);
			},
			complete:function(){
				console.log('OK ');
			}
		});
	}
	
	function selectedRows(){
		var res=[];
		var data=$(".selected").each(function(){
			var tr=$(this);
			res.push(tr.attr("id"));
		});
		return res;
	}


	function asignarContenedor(){
		var res=selectedRows();
		var contenedor=$("#contenedor").val();
		console.log('Asignando contenedor: '+contenedor+' a partidas: '+res);
		$.ajax({
			url:"${createLink(controller:'embarque',action:'actualizarContenedor')}",
			dataType:"json",
			data:{
				contenedor:contenedor,partidas:JSON.stringify(res)
			},
			success:function(data,textStatus,jqXHR){
				console.log('Rres: '+data.contenedor);
				$('.selected td[name=contenedor]').text(data.contenedor);
				$("#contenedor").val("");
				$("#asignarContendorDialog").modal("hide")
				

			},
			error:function(request,status,error){
				console.log(error);
				alert("Error: "+error);
			},
			complete:function(){
				console.log('OK ');
				$("#grid tr").removeClass("success selected");
			}
		});
	}
	
	
	function asignarFacturaDeGastos(){
		var facturaId=$('#facturaGastosId').val();
		if(facturaId==""){
			alert("Debe selccionar una factura de gastos");
			return;
		}
		
		console.log('Asignando factura de gastos: '+facturaId);
		$.ajax({
			url:"${createLink(controller:'embarque',action:'asignarFacturaDeGastos')}",
			dataType:"json",
			data:{
				embarqueId:${embarqueInstance.id},facturaId:facturaId
			},
			success:function(data,textStatus,jqXHR){
				//console.log('Rres: '+data.gasto);
				$("#asignarGastosDialog").modal("hide")
				location.reload();

			},
			error:function(request,status,error){
				console.log(error);
				alert("Error: "+error);
			},
			complete:function(){
				console.log('OK ');
			}
		});
	}
	
	function eliminarPartidas(){
		var res=selectedRows();
		if(res==""){
			return;
		}
		var ok=confirm("Eliminar "+res.length+" partidas? ");
		if(!ok)
			return;
		console.log('Eliminando partidas : '+res);
		$.ajax({
			url:"${createLink(controller:'embarque',action:'eliminarPartidas')}",
			dataType:"json",
			data:{
				embarqueId:${embarqueInstance?.id},partidas:JSON.stringify(res)
			},
			success:function(data,textStatus,jqXHR){
				console.log('Partidas eleiminadas: '+data.eliminadas);
				location.reload();
			},
			error:function(request,status,error){
				//alert("Error: "+error);
				console.log('Error aparente: '+status);
				//location.reload();
			},
			complete:function(){
				console.log('OK ');
				
			}
		});
	}

</r:script>

