<r:require module="autoNumeric"/>
<div class="input-prepend">
	<g:textField id="${property}" name="${property}" value="${value}"/>
	<g:hiddenField id="_hidden" name="${property}+Id" value="${value}"/>
</div>
<r:script>
$(function{
 	$("#${property}").autoNumeric({vMin:'0.00'});
 });
</r:script>