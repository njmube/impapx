<%-- Parcial template para input field de tipo proveedor --%>
<div class="input-prepend">
	<g:hiddenField id="depositoId" name="${property+'.id'}"  value="${value?.id }"/>
<%
	def attrs = [type:'text',name: property+'nombre', value: value,id:"movimientoDeCuentaAuto"
		,placeholder:"Seleccione un deposito de tesorería",class:'input-xxlarge']
	if (required) attrs.required = ''
	out << g.field(attrs)
%>
<r:script>  
	$(function(){
			
		$("#movimientoDeCuentaAuto").autocomplete(
		{			
			source:'<g:createLink controller="anticipo" action="depositosDeTesoreriaDisponiblesJSONList"/>',
			minLength:1,
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
					$("#depositoId").val(ui.item.id);
			}
		});
	});
</r:script>
</div>