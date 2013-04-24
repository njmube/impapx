<fieldset>
	<g:form class="form-horizontal" action="create">
		<fieldset>

			<f:with bean="embarqueDetInstance">
				<f:field property="compraDet">
					<g:textField name="compraDet" />
				</f:field>
				<f:field property="factura" />
				<f:field property="contenedor" />
				<f:field property="tarimas" />
				<f:field property="cantidad" />
				<f:field property="costoUnitario">
					<g:field name="costoUnitario" type="number" required="true"/>
				</f:field>
				<f:field property="costoBruto" />
				<f:field property="costoNeto" />
				<f:field property="gastos" />
				<f:field property="kilosEstimados" />
				<f:field property="kilosNetos" />
			</f:with>
			<div class="form-actions">
				<button type="submit" class="btn btn-primary">
					<i class="icon-ok icon-white"></i>
					<g:message code="default.button.create.label" default="Create" />
				</button>
			</div>
		</fieldset>
	</g:form>
</fieldset>