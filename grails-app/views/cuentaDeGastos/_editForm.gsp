<fieldset>
	<g:form class="form-horizontal" action="edit"
		id="${cuentaDeGastosInstance?.id}">
		<g:hiddenField name="version"
			value="${cuentaDeGastosInstance?.version}" />
		<fieldset>
			<f:with bean="cuentaDeGastosInstance">
				<f:field property="proveedor" label="Agente" input-required="true"/>
				<f:field property="fecha"/>
				<f:field property="embarque" input-class="span7"/>
				<f:field property="referencia"/>
				<f:field property="comentario">
					<g:textArea name="comentario" value="${cuentaDeGastos?.comentario }" cols="40" rows="4" class="span7"/>
				</f:field>
			</f:with>
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
		</fieldset>
	</g:form>
</fieldset>