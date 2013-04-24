<%-- Parcial template para input field de tipo cliente --%>
<div class="input-prepend">
	<g:hiddenField id="clienteId" name="${property+'.id'}" value="${value?.id }"/>
<%
	def attrs = [type:'text',name: property+'nombre'
		, value: value,id:"clienteAuto"
		,class:"input-xxlarge"
		, placeholder:"Seleccione el cliente "]
	if (required) attrs.required = ''
	if(bean.id) attrs.readOnly='readOnly'
	out << g.field(attrs)
%>
<r:script>
	$(function(){
			
		$("#clienteAuto").autocomplete(
			{
				source:'<g:createLink controller="cliente" action="clientesJSONList"/>',
				minLength:2,
				select:function(e,ui){
					$("#clienteId").val(ui.item.id);
				}
			}
		);
	});
</r:script>
</div>