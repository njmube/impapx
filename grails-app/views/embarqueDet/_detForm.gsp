<fieldset>
	<div class="row">
	<g:form class="form-horizontal" action="create">
		<fieldset>
			<g:hiddenField name="kilosPorMillar" value="0"/>
			<g:hiddenField name="factorUnitario" value="1000"/>
			<f:with bean="embarqueDetInstance">
				
				<g:hiddenField name="proveedorId" type="text" value="${proveedorId}"/>
				<g:hiddenField id="embarqueId" name="embarqueId" value="${embarqueDetInstance?.embarque?.id}"/>
				<div class="span10">
					<!-- 
					<f:field property="embarque">
						<g:link  controller="embarque" action="edit" id="${embarqueDetInstance?.embarque?.id}">
							<g:fieldValue bean="${embarqueDetInstance}" field="embarque"/>
						</g:link>
					</f:field> -->
					
					
				</div>
				
				<div class="span10">
					<g:hiddenField id="compraDetId" name="compraDetId"/>
					<g:hiddenField name="productoId" value="10"/>
					<f:field property="compraDet" >
						<g:field type="text" id="compraDetAuto" name="compraDet" class="span10" required="true" value="${embarqueDetInstance?.compraDet}" autofocus="true"/>
					</f:field>
				</div>
				
				
				<div class="span4">
					<f:field property="cantidad" input-class="numericField" input-value="${embarqueDetInstance.cantidad}"/>
				</div>
				<div class="span6">
					<f:field property="costoUnitario">
						<g:field name="costoUnitario" type="number" required="true" class="numericField"/>
					</f:field>
				</div>

				<div class="span4">
					<f:field property="kilosNetos" input-class="numericField"/>
				</div>

				<div class="span6">
					<f:field property="kilosEstimados"  input-class="numericField"/>
				</div>
				
				<div class="span4">
					<f:field property="costoBruto" input-type="number" input-required="true" input-class="numericField"/>
				</div>
				<div class="span6">
					<f:field property="tarimas" input-class="numericField"/>
				</div>
				
				
				
			</f:with>
			<div class="span10">
				<div class="form-actions">
				<button type="submit" class="btn btn-primary">
					<i class="icon-ok icon-white"></i>
					<g:message code="default.button.create.label" default="Create" />
				</button>
			</div>
			</div>
			
		</fieldset>
	</g:form>		
	</div>
	
</fieldset>


<r:script>
	$(function(){

		
			
		$("#compraDetAuto").autocomplete(
			{
				source:'${createLink(controller:'embarque',action:'comprasDetDisponiblesJSON',params:[proveedorId:proveedorId]) }',
					minLength:3,
				select:function(e,ui){
					//console.log('Valor seleccionado: '+ui.item.id);
		   			$("#compraDetId").val(ui.item.id);
		   			$("#kilosPorMillar").val(ui.item.kilosPorMillar);
		   			$("#factorUnitario").val(ui.item.factor);
		   			$("#productoId").val(ui.item.productoId);
				}
			});

	});
			
			$("#cantidad").focusout(function(){
				var cantidad=$("#cantidad").val();

				var kilosPorMillar=$("#kilosPorMillar").val();
				var factor=$("#factorUnitario").val();
				if(isNaN(factor))
					factor=1;
				var kilosEstimados=(cantidad/factor)*kilosPorMillar;

				//console.log('Kilos estimados:' +cantidad+'/'+factor+' * '+kilosPorMillar);
				$("#kilosEstimados").val(kilosEstimados);


		$(".numericField").forceNumeric();
		
	});
			
			
</r:script>

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
					$("#costoUnitario").val(data.costoUnitario);
				},
				error:function(request,status,error){
					console.log('Error: '+error);
				},
				complete:function(){
					console.log('Completado...');
				}
			});
		});
		$("#kilosNetos").focusout(function(){
			//Actualizamos el 
			var costo=$("#costoUnitario").val();
			var kilos=$(this).val();
			var costoBruto=kilos*costo;
			console.log('Costo Bruto:'+costoBruto);
			$("#costoBruto").val(costoBruto);
		});
	});
</r:script>

