
<div class="input-prepend">
	<%-- <g:hiddenField id="pedimento" name="pedimento" value="${value?.pedimento}" required="true"  autocomplete="false"/> --%>
	<g:field id="pedimento" type="text" name="pedimento" placeHolder="" required="true" autocomplete="false" class="input-large "/>
	<%-- 
	<g:field id="pedimento_year" type="text" name="pedimento_year" placeHolder="Año" required="true" autocomplete="false" class="input-small pedimentoField"/>
	<g:field id="pedimento_aduana" type="text" name="pedimento_aduana" placeHolder="Aduana" required="true" autocomplete="false" class="input-small pedimentoField"/>
	<g:field id="pedimento_patente" type="text" name="pedimento_patente" placeHolder="Patente" required="true" autocomplete="false" class="input-small pedimentoField"/>
	<g:field id="pedimento_folio" type="text" name="pedimento_folio" placeHolder="Folio" required="true" autocomplete="false" class="input-small pedimentoField"/>
	--%>
</div>

<r:script>
$(function(){
	//$("#pedimento_year").autoNumeric({vMin:'00',vMax:'99',aSep: ''});
	//$("#pedimento_aduana").autoNumeric({vMin:'00',vMax:'99'});
	$("#pedimento").mask("99-99-9999-9999999");
	//$("#pedimento_patente").autoNumeric({vMin:'0',vMax:'9999',aSep: ''});
	//$("#pedimento_folio").autoNumeric({vMin:'0',vMax:'9999999',aSep: ''});
	/*
	$(".pedimentoField").change(function(){
		var year=$("#pedimento_year").autoNumericGet();
		if(year<=0)
			year="";
		var aduana=$("#pedimento_aduana").autoNumericGet();
		if(aduana<=0)
			aduana="";
		var patente=$("#pedimento_patente").autoNumericGet();
		if(patente<=0)
			patente="";
		var folio=$("#pedimento_folio").autoNumericGet();
		if(folio<=0)
			folio="";
		var res=year+aduana+patente+folio;
		console.log('Pedimento: '+res);
		$("#pedimento").val(res);
		
	});*/
});
</r:script>