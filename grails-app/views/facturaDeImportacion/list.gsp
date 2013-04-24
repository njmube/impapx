<%@ page import="com.luxsoft.impapx.FacturaDeImportacion" %>
<!doctype html>
<html>
<head>
<meta name="layout" content="taskView">
<g:set var="entityName"
	value="${message(code: 'facturaDeImportacion.label', default: 'Facturas')}" />
<title><g:message code="cuentasPorPagar.list.label" default="Cuentas por pagar (CxP)" /></title>
<r:require module="dataTables"/>
</head>
<body>

	<content tag="header">
		<h4>Facturas de importación Periodo: ${periodo}</h4>
	</content>
	
 	<content tag="consultas">
 		
 		<li><g:link class="list" action="list" controller="cuentaPorPagar">
 			<i class="icon-list"></i> 
			<g:message code="cuentaPorPagar.list.label" default="C X P"/>
			</g:link>
		</li>
 		
 		<li><g:link class="list" action="programacionDePagos" >
			<i class="icon-list"></i> 
			<g:message code="programacionDePagos.list.label" default="Programación"/>
			</g:link>
		</li>
 		
 	</content>
 	
 	
 	<content tag="operaciones">
 		<li>
 			<g:link class="" action="create">
				<i class="icon-plus "></i>
				<g:message code="cuentaPorPagar.create.label"  />
			</g:link>
		</li>
		<li class="nav-header">Filtros</li>
		<li>
		<div class="accordion-group">
 				<div class="accordion-heading">
 					<a class="accordion-toggle alert" data-toggle="collapse" data-parent="#saldoDeCuentaAccordion" href="#collapseOne">
 						Periodo
 					</a>
 				</div>
 				<div id="collapseOne" class="accordion-body collapse ">
 					<div class="accordion-inner ">
 						<g:form class="" action="list">
 							<fieldset>
 								<label>Fecha Inicial</label>
 								<g:field type="string" class="dateField"  name="fechaInicial" value="${periodo.fechaInicial.text()}"/>
 								<label>Fecha Final</label>
 								<g:field type="string" class="dateField"  name="fechaFinal" value="${periodo.fechaFinal.text() }"/>
 								<label>Total</label>
 								<label><lx:moneyFormat number="${facturaDeImportacionInstanceList.sum{it.total} }"/></label>
 								<label>Por pagar</label>
 								<label><lx:moneyFormat number="${facturaDeImportacionInstanceList.sum{it.saldoActual} }"/></label>
 							</fieldset>
 							<div class="form-actions">
 							<button type="submit" class="btn btn-primary">
								<i class="icon-ok icon-white"></i>
								Filtrar
							</button>
 						</div>
 						</g:form>
 						
				
 					</div>
 				</div>
 			</div>
 			</li>
 	</content>
 	<content tag="document">
 		
 		<g:if test="${flash.message}">
			<bootstrap:alert class="alert-info">
				${flash.message}
			</bootstrap:alert>
		</g:if>
		<g:render template="facturasPanel" 
			model="['cuentaPorPagarInstance':facturaDeImportacionInstance
					,'facturasList':facturaDeImportacionInstanceList
					,'cuentaPorPagarInstanceTotal':facturaDeImportacionInstanceTotal]"
					/>
 	</content>

	<r:script>
 $(function(){
 	$.datepicker.setDefaults( $.datepicker.regional[ "es" ] );
 	$(".dateField").datepicker({
    	 dateFormat: 'dd/mm/yy',
         showOtherMonths: true,
         selectOtherMonths: true,
         showOn:'focus',
         showAnim:'fold',
         minDate:'01/10/2010',
         maxDate:'31/12/2015',
         navigationAsDateFormat:false,
         showButtonPanel: true,
         changeMonth:true,
         changeYear:true,
         closeText:'Cerrar'
      });
 });
 </r:script>
	
</body>
</html>
