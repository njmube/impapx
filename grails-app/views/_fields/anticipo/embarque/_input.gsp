<%-- Parcial template para input field de tipo proveedor --%>
<div class="input-prepend">
	<g:hiddenField id="embarqueId" name="${property+'.id'}"  value="${value?.id }"/>
<%
	def attrs = [type:'text',name: property+'nombre', value: value,id:"embarqueAuto",placeholder:"Seleccione un embarque",class:'input-xxlarge']
	if (required) attrs.required = ''
	out << g.field(attrs)
%>
<r:script>  
	$(function(){
			
		$("#embarqueAuto").autocomplete(
		{			
			source:'<g:createLink controller="anticipo" action="embarquesDisponiblesJSONList"/>',
			minLength:1,
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
					$("#embarqueId").val(ui.item.id);
			}
		});
	});
</r:script>
</div>