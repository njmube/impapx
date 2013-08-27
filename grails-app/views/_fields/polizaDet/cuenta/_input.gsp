<div class="input-prepend">
	<g:hiddenField id="cuentaId" name="cuenta.id" value="${value?.id}" />
<%
	def attrs = [type:'text',name: property+'Descripcion', value: value,class:"cuentaAuto",placeholder:"Seleccione una cuenta "]
	if (required) attrs.required = ''
	out << g.field(attrs)
%>
<r:script>
$(function(){
	$(".cuentaAuto").autocomplete({
			source:'<g:createLink controller="cuentaContable" action="cuentasDeDetalleJSONList"/>',
			minLength:3,
			appendTo:".input-prepend",
			select:function(e,ui){
				console.log('Valor seleccionado: '+ui.item.id);
				$("#cuentaId").val(ui.item.id);
			}
	});
});
</r:script>
</div>
