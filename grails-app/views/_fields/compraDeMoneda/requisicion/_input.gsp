<div class="input-prepend">
	<g:hiddenField id="requisicionId" name="${property+'.id'}" value="${value?.id}" />
<%
	def attrs = [type:'text',id:property,name: property+'.descripcion'
		, value: value,placeholder:"Seleccione una requisición "
		,class:'input-xxlarge']
	if (required) attrs.required = ''
	out << g.field(attrs)
%>
<r:script>
$(function(){
	$("#${property}").autocomplete({
		source:'<g:createLink controller="compraDeMoneda" action="requisicionesDisponiblesJSONList"/>',
		minLength:3,
		select:function(e,ui){
			console.log('Valor seleccionado: '+ui.item.id);
			$("#requisicionId").val(ui.item.id);
		}
	});
});
</r:script>
</div>