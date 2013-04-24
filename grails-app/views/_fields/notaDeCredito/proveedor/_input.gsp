<div class="input-prepend">
	<g:hiddenField id="proveedorId" name="proveedor.id" value="${value?.id}" />
<%
	def attrs = [type:'text',name: property+'.nombre', value: value,class:"proveedorAuto",placeholder:"Seleccione un proveedor "]
	if (required) attrs.required = ''
	out << g.field(attrs)
%>
<r:script>
$(function(){
	$(".proveedorAuto").autocomplete({
			source:'<g:createLink controller="proveedor" action="proveedoresJSONList"/>',
			minLength:3,
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
				$("#proveedorId").val(ui.item.id);
			}
	});
});
</r:script>
</div>
<%-- UPDATED--%>