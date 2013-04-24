<g:form class="form-horizontal" action="edit"
	id="${embarqueInstance?.id}">
	<g:hiddenField name="version" value="${embarqueInstance?.version}" />
	<fieldset>
		<f:with bean="embarqueInstance">
			<f:field property="proveedor">
				<g:field type="text" name="proveedor"
					value="${embarqueInstance?.proveedor?.nombre}" class="span5" />
			</f:field>
			<f:field property="bl" input-class="mayusculas"></f:field>
			<f:field property="nombre" input-class="mayusculas"></f:field>
			<f:field property="fechaEmbarque">
				<input class="datepicker" type="text" id="fechaEmbarque"
					name="fechaEmbarque"
					value="${formatDate(format:'dd/MM/yyyy',date:embarqueInstance?.fechaEmbarque)}" />
				<a href="#" class="btn btn-medium datepicker-btn"><i
					class="icon-calendar"></i></a>
			</f:field>
			<f:field property="aduana"></f:field>
			<f:field property="ingresoAduana" label="ETA">
				<input class="datepicker" type="text" id="ingresoAduana"
					name="ingresoAduana"
					value="${formatDate(format:'dd/MM/yyyy',date:embarqueInstance?.ingresoAduana)}" />
				<a href="#" class="btn btn-medium datepicker-btn"><i
					class="icon-calendar"></i></a>
			</f:field>
			<f:field property="contenedores" />
			<f:field property="comentario">
				<g:textArea name="comentario" class="comentario"
					value="${embarqueInstance?.comentario}" />
			</f:field>

		</f:with>
		<g:if test="${embarqueInstance.facturado<=0}">
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">
				<i class="icon-ok icon-white"></i>
				<g:message code="default.button.update.label" default="Update" />
			</button>
			<button type="submit" class="btn btn-danger" name="_action_delete"
				formnovalidate>
				<i class="icon-trash icon-white"></i>
				<g:message code="default.button.delete.label" default="Delete" />
			</button>
		</div>
		</g:if>
	</fieldset>
</g:form>