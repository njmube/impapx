<%@ page import="com.luxsoft.impapx.Pedimento"%> 
<fieldset>
	<g:form class="form-horizontal" action="edit" id="${pedimentoInstance.id }">
		<fieldset>
			<f:with bean="${pedimentoInstance}" >
				<g:if test="${pedimentoInstance.id}">
					<f:field property="id" input-disabled="true"/>
				</g:if>
				
				
				<f:field property="proveedor" input-required="true" label="Agente"/>
				<f:field property="fecha"/>
				<f:field property="tipoDeCambio"/>
				<f:field property="impuestoTasa"/>
				<f:field property="dta"/>
				<f:field property="arancel"/>
				<f:field property="impuestoMateriaPrima" input-readOnly="true"
					input-class="moneyField"
				/>
				<f:field property="incrementables" input-readOnly="true"/>
				<f:field property="ivaAcreditable" input-readOnly="true"
					input-class="moneyField"
				/>
				
				<f:field property="prevalidacion"/>
				<f:field property="impuestoPrevalidacion" input-readOnly="true"
					input-class="moneyField"
				/>
				
				
				
				
				<f:field property="impuesto" input-readOnly="true"/>
				<f:field property="comentario" input-class="input-xxlarge"/>
			</f:with>
			<div class="form-actions">
				
				<g:if test="${!pedimentoInstance.id}">
					<button type="submit" class="btn btn-primary">
						<i class="icon-ok icon-white"></i>
						<g:message code="default.button.create.label" default="Create" />
					</button>	
				</g:if>
				<g:else>
					<button type="submit" class="btn btn-primary">
						<i class="icon-ok icon-white"></i>
						<g:message code="default.button.update.label" default="Update" />
					</button>
					<button type="submit" class="btn btn-danger" name="_action_delete" formnovalidate>
						<i class="icon-trash icon-white"></i>
						<g:message code="default.button.delete.label" default="Delete" />
					</button>
					
				</g:else>
				
			</div>
		</fieldset>
	</g:form>
</fieldset>
<r:script>
 $(function(){
 	$(".moneyField").autoNumeric({vMin:'0.00',wEmpty:'zero'}); 	
 	
 });
 </r:script>