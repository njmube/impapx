<%@ page import="com.luxsoft.impapx.CompraDet" %>
<r:require modules="luxorTableUtils,dataTables"/>
<div class="btn-toolbar">
	<div class="btn-group ">
						
		<button  class="btn btn-primary" data-target="#asignarContendorDialog" data-toggle="modal">
  			<i class="icon-plus icon-white"></i>Agregar
  		</button>
						
  		<button id="eliminarBtn" class="btn btn-danger">
  			<i class="icon-trash icon-white"></i>Eliminar
  		</button>
  						
	</div>
</div>
<div>
	<table id="compraDetTable" 
		class="simpleGrid table table-striped table-bordered table-condensed" cellpadding="0" cellspacing="0" border="0">
		<thead>
			<tr>
				<th><g:message code="producto.clave.label" default="Clave" /></th>
				<th><g:message code="producto.descripcion.label" default="Descripción" /></th>
				<th><g:message code="producto.unidad.label" default="Unidad" /></th>
				<th><g:message code="compraDet.solicitado" default="Solicitado" /></th>
				<th><g:message code="compraDet.entregado.label" default="Entregado" /></th>
				<th><g:message code="compraDet.pendiente.label" default="Pendiente" /></th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${partidas}" var="row">
			<tr id="${row.id }">
				<td><g:link controller="compraDet" action="edit" id="${row.id}">
					${fieldValue(bean:row, field:"producto.clave")}
					</g:link>
				</td>
				<td>${fieldValue(bean:row, field:"producto.descripcion")}</td>
				<td>${fieldValue(bean:row, field:"producto.unidad.clave")}</td>
				<td><g:formatNumber number="${row.solicitado}" format="###,####,###"/></td>
				<td><g:formatNumber number="${row.entregado}" format="###,####,###"/></td>
				<td><g:formatNumber number="${row.pendiente}" format="###,####,###"/></td>
			</tr>
			</g:each>
		</tbody>
	</table>
</div>


 <div class="modal hide fade modal-large" id="asignarContendorDialog" tabindex=-1 role="dialog" 
	aria-labelledby="myModalLabel" >
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h4 class="myModalLabel">Partida de compra</h4>
	</div>
	<div class="modal-body " >
		<fieldset>
		<g:form class="form-horizontal" controller="compra" action="agregarPartida" id="${compraInstance.id}">
			
			<div class="control-group">
    			<label class="control-label" for="productoAuto">Producto</label>
    			<div class="controls">
    				<g:hiddenField id="productoId" name="producto.id"/>
      				<g:field id="productoAuto" name="producto.descripcion" type="text" 
					required="true" class="input-xxlarge" placeholder="Seleccione un producto"/>
    			</div>
    			
    			
  			</div>
			<div class="control-group">
    			<label class="control-label" for="cantidad">Cantidad</label>
    			<div class="controls">
      				<g:field id="cantidad" name="cantidad" type="text" 
					required="true"/>
    			</div>
  			</div>
			
			
				<%-- 
				<f:with bean="${new CompraDet(solicitado:0,entregado:0)}">
					<f:field property="producto"/>
					<f:field property="solicitado"/>
					<f:field property="entregado" input-readonly="true"/>
				</f:with>
				--%>
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">
					<i class="icon-ok icon-white"></i>
					<g:message code="default.button.update.label" default="Update" />
				</button>
				
				<a href="#" class="btn" data-dismiss="modal">Cancelar</a>
			</div>
		
		</g:form>
		</fieldset>
	</div>
	
</div>
<r:script>
$(function(){
	$("#productoAuto").autocomplete({
			source:'<g:createLink controller="producto" action="productosJSONList"/>',
			minLength:3,
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
				$("#productoId").val(ui.item.id);
			}
	});
	$("#cantidad").autoNumeric({vMin:'0.000'});
	
	$("#eliminarBtn").click(function(e){
		eliminar();
	});
	
	function eliminar(){
		var res=selectedRows();
		if(res.length==0){
			alert('Debe seleccionar al menos un registro');
			return;
		}
		var ok=confirm('Eliminar  ' + res.length+' partida(s)?');
		if(!ok)
			return;
		console.log('Eliminando partidas: '+res);
		
		$.ajax({
			url:"${createLink(action:'eliminarPartida')}",
			data:{
				compraId:${compraInstance.id},partidas:JSON.stringify(res)
			},
			success:function(response){
				
				location.reload();
			},
			error:function(request,status,error){
				alert("Error: "+status);
			}
		});
	}
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
