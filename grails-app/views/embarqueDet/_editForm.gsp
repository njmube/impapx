<fieldset>
	<div class="row">
	<g:form class="form-horizontal" action="edit" id="${embarqueDetInstance.id}">
		<fieldset>
			
			<g:hiddenField name="kilosPorMillar" value="${embarqueDetInstance.producto.kilos}"/>
			<g:hiddenField name="factorUnitario" value="1000"/>
			
			<f:with bean="embarqueDetInstance">
				<g:hiddenField name="proveedorId" value="${embarqueDetInstance.embarque.proveedor.id}"/>
				
				<g:hiddenField id="embarqueId" name="embarqueId" value="${embarqueDetInstance?.embarque?.id}"/>
				
				
				<div class="span10">
					<g:hiddenField id="compraDetId" name="compraDetId" value="${embarqueDetInstance?.compraDet?.id}"/>
					<g:hiddenField name="productoId" value="${embarqueDetInstance?.compraDet?.producto?.id}"/>
					<f:field property="compraDet" >
						<g:field type="text" id="compraDetAuto" name="compraDet" class="span10" required="true" value="${embarqueDetInstance?.compraDet}" />
					</f:field>
				</div>
				
				
				<div class="span4">
					<f:field property="cantidad" input-type="string" input-class="autonumeric" 
					input-value="${embarqueDetInstance.cantidad}" autofocus="true"/>
				</div>
				<div class="span6">
					<f:field property="precio" input-class="numericField"/>
				</div>

				<div class="span4">
					<f:field property="kilosNetos" input-class="numericField" />
				</div>

				<div class="span6">
					<f:field property="kilosEstimados"  input-class="numericField" value="${embarqueDetInstance.kilosEstimados }"/>
				</div>
				
				<div class="span4">
					<f:field property="importe" input-type="number" input-required="true" input-class="numericField"/>
				</div>
				<div class="span6">
					<f:field property="tarimas" input-class="numericField" value="${embarqueDetInstance.tarimas}"/>
				</div>
				<div class="span4">
					<f:field property="contenedor" input-type="number" input-required="true"/>
				</div>
				
				
			</f:with>
			<div class="span10">
				<div class="form-actions">
				<button type="submit" class="btn btn-primary">
					<i class="icon-ok icon-white"></i>
					<g:message code="default.button.update.label" default="Create" />
				</button>
			</div>
			</div>
			
		</fieldset>
	</g:form>		
	</div>
	
</fieldset>




<r:script>
	$(function(){
		$("#compraDetAuto").focusout(function(){
			console.log('Localizando precio...');
			$.ajax({
				url:"${createLink(controller:'proveedor',action:'localisarPrecio')}",
				data:{
					proveedorId:$("#proveedorId").val(),productoId:$("#productoId").val()
				},
				success:function(data){
					console.log('Exitosamente obtenemos: '+data.costoUnitario);
					$("#precio").val(data.costoUnitario);
				},
				error:function(request,status,error){
					console.log('Error: '+error);
				},
				complete:function(){
					console.log('Completado...');
				}
			});
		});
		$("#kilosNetos").blur(function(){
			//Actualizamos el 
			var costo=$("#precio").val();
			var kilos=$(this).val();
			kilos=kilos/1000 // Precio por tonelada
			var importe=kilos*costo;
			importe=Math.round(importe*100)/100;
			$("#importe").val(importe);
		});
		
		$(".numericField").forceNumeric();
		$(".autonumeric").autoNumeric({vMin:'0.000'});
		
		$("#cantidad").focusout(function(){
				var cantidad=$("#cantidad").autoNumericGet();

				var kilosPorMillar=$("#kilosPorMillar").val();
				var factor=$("#factorUnitario").val();
				if(isNaN(factor))
					factor=1;
				var kilosEstimados=(cantidad/factor)*kilosPorMillar;
				kilosEstimados=Math.round(kilosEstimados*100)/100;

				//console.log('Kilos estimados:' +cantidad+'/'+factor+' * '+kilosPorMillar);
				$("#kilosEstimados").val(kilosEstimados);	
			});
	});
</r:script>

