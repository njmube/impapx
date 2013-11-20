<%-- Parcial template para input field de tipo proveedor --%>
<div class="input-prepend">
<%
	def sucursales=['CALLE 4','TACUBA','BOLIVAR','ANDRADE','QUERETARO','TRANSITO','CF5FEBRERO','VERTIZ 176','ALESA','PROMASA NORTE','PROMASA SUR','GACELA','PROGRESO','INTERCARTON+']
	def attrs = [name: property, value: value,from:sucursales]
	if (required) 
		attrs.required = ''
	out << g.select(attrs) 
	
%>
</div>