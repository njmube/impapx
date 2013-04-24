<%-- Parcial template para input field de tipo proveedor --%>
<div class="input-prepend">
<%
	def sucursales=['CHEQUE','TRANSFERENCIA','EFECTIVO']
	def attrs = [name: property, value: value,from:sucursales]
	if (required) 
		attrs.required = ''
	out << g.select(attrs) 
	
%>
</div>