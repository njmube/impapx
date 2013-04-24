<%-- Parcial template para input field de tipo proveedor --%>
<div class="input-prepend">
	<g:hiddenField id="proveedorId" name="proveedorId"/>
<%
	//println 'Valor del campo para proveedor: '+value
	def attrs = [type:'text',name: property, value: value,class:"proveedorAuto",placeholder:"Seleccione el proveedor de gastos "]
	if (required) attrs.required = ''
	out << g.field(attrs)
%>
<a href="#" class="btn btn-medium"><i class="icon-search"></i></a>
<%-- <input class="proveedor" type="text" name="proveedor" placeholder="Seleccione el proveedor" style="width: 410px"> --%>
<r:script>
			$(function(){
			
				$(".proveedorAuto").autocomplete(
					{
						//source:'<g:createLink controller="proveedor" action="proveedoresGastosJSONList"/>',
						source:'<g:createLink controller="proveedor" action="proveedoresJSONList"/>',
						minLength:3,
						select:function(e,ui){
							console.log('Valor seleccionado: '+ui.item.id);
							$("#proveedorId").val(ui.item.id);
						}
					}
				);
			});
</r:script>
</div>