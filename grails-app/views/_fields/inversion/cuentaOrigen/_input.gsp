<%--Input field para campos de tipo fecha --%>
<%@ page import="com.luxsoft.impapx.CuentaBancaria" %>
<div class="input-prepend ">
	<g:select id="${property}" name="${property}.id"  from="${CuentaBancaria.findAllByActivoAndTipo('true','INVERSION')}" value="${value}" optionKey="id" class="cuentaDeBanco" style="width:500px;"/>
</div>