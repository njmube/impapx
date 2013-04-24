<div class="input-prepend">
	<g:hiddenField id="productoId" name="producto.id" value="${value?.id}" />
<%
	def attrs = [type:'text',name: property+'.descripcion', value: value,class:"productoAuto input-xxlarge",placeholder:"Seleccione un producto "]
	if (required) attrs.required = ''
	out << g.field(attrs)
%>
<r:script>
$(function(){
	$(".productoAuto").autocomplete({
			source:'<g:createLink controller="producto" action="productosJSONList"/>',
			minLength:3,
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
				$("#productoId").val(ui.item.id);
			}
	});
});
</r:script>
</div>
<%-- UPDATED   --%>