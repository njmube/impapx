<%@ page import="com.luxsoft.cfd.Certificado" %>



<div class="fieldcontain ${hasErrors(bean: certificadoInstance, field: 'numeroDeCertificado', 'error')} required">
	<label for="numeroDeCertificado">
		<g:message code="certificado.numeroDeCertificado.label" default="Numero De Certificado" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numeroDeCertificado" required="" value="${certificadoInstance?.numeroDeCertificado}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: certificadoInstance, field: 'expedicion', 'error')} required">
	<label for="expedicion">
		<g:message code="certificado.expedicion.label" default="Expedicion" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="expedicion" precision="day"  value="${certificadoInstance?.expedicion}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: certificadoInstance, field: 'vencimiento', 'error')} required">
	<label for="vencimiento">
		<g:message code="certificado.vencimiento.label" default="Vencimiento" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="vencimiento" precision="day"  value="${certificadoInstance?.vencimiento}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: certificadoInstance, field: 'certificadoPath', 'error')} ">
	<label for="certificadoPath">
		<g:message code="certificado.certificadoPath.label" default="Certificado Path" />
		
	</label>
	<g:textField name="certificadoPath" value="${certificadoInstance?.certificadoPath}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: certificadoInstance, field: 'privateKeyPath', 'error')} ">
	<label for="privateKeyPath">
		<g:message code="certificado.privateKeyPath.label" default="Private Key Path" />
		
	</label>
	<g:textField name="privateKeyPath" value="${certificadoInstance?.privateKeyPath}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: certificadoInstance, field: 'algoritmo', 'error')} ">
	<label for="algoritmo">
		<g:message code="certificado.algoritmo.label" default="Algoritmo" />
		
	</label>
	<g:textField name="algoritmo" maxlength="40" value="${certificadoInstance?.algoritmo}"/>
</div>

