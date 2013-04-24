<%-- Parcial template para input field de tipo proveedor --%>
<%@page import="com.luxsoft.impapx.contabilidad.CuentaContable"%>
<div class="input-prepend">
<%
	def cuentas=CuentaContable.findAllByClaveLikeOrClaveLike('102-%','103-%')
	def attrs = [name: property+'.id', value: value,from:cuentas,optionKey:'id' ]
	if (required) 
		attrs.required = ''
	out << g.select(attrs) 
	
%>
</div>