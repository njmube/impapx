<div class="input-prepend">
	<g:hiddenField id="pagoId" name="${property+'.id'}" value="${value?.id}" />
<%
	def attrs = [type:'text',id:property,name: bean.toString()
		, value: value,placeholder:"Seleccione el pago con cheque"
		,class:'input-xxlarge']
	if (required) attrs.required = ''
	out << g.field(attrs)
%>
<r:script>
$(function(){
	$("#${property}").autocomplete({
		source:'<g:createLink controller="cheque" action="pagosDisponiblesJSONList"/>',
		minLength:1,
		select:function(e,ui){
			console.log('Valor seleccionado: '+ui.item.id);
			$("#pagoId").val(ui.item.id);
		}
	});
});
</r:script>
</div>