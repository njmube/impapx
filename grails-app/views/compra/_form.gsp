<fieldset>
	<g:form class="form-horizontal" action="edit"
		id="${compraInstance?.id}">
		<g:hiddenField name="version" value="${compraInstance?.version}" />
		<fieldset>
			<f:with bean="compraInstance">
				<f:field property="proveedor" />
				<f:field property="fecha">
					<input class="datepicker" type="text" id="fecha" name="fecha"
						value="${formatDate(format:'dd/MM/yyyy',date:compraInstance?.fecha)}" />
					<a href="#" class="btn btn-medium datepicker-btn"><i
						class="icon-calendar"></i></a>
				</f:field>
				<f:field property="entrega">
					<input class="datepicker" type="text" id="entrega" name="entrega"
						value="${formatDate(format:'dd/MM/yyyy',date:compraInstance?.entrega)}" />
					<a href="#" class="btn btn-medium datepicker-btn"><i
						class="icon-calendar"></i></a>
				</f:field>
				<f:field property="moneda" />
				<f:field property="tc" />
				<f:field property="comentario" input-class="comentario">
					<g:textArea class="comentario" name="comentario"
						value="${compraInstance?.comentario }" />
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