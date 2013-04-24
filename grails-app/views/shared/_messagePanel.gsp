<g:if test="${flash.message}">
	<bootstrap:alert class="alert-info">
		${flash.message}
	</bootstrap:alert>
</g:if>
<g:hasErrors bean="${beanInstance}">
	<bootstrap:alert class="alert-error">
		<ul>
			<g:eachError bean="${beanInstance}" var="error">
				<li
					<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"
					</g:if> >
					<g:message error="${error}" />
				</li>
			</g:eachError>
		</ul>
	</bootstrap:alert>
</g:hasErrors>