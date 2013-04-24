<%@ page import="com.luxsoft.impapx.Embarque" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="luxor">
		<g:set var="entityName" value="${message(code: 'embarque.label', default: 'Embarque')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		<r:require module="datepicker"/>
		<r:require module="dataTables"/>
		<r:require module="luxorForms"/>
	</head>
	<body>
	
		<div class="container-fluid">
		<div class="row-fluid">
		<div class="span12">
				
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${embarqueInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${embarqueInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>
				
				
				<ul class="nav nav-tabs" id="myTab">
					
					<li class=""><a href="#embarque" data-toggle="tab">Embarque</a></li>
					<li class="active">
						<a data-target="#plist" data-toggle="tab">P.List</a>
					</li>
					<li class=""><a href="#costos" data-toggle="tab">Costos</a></li>
					<li class=""><a href="#con" data-toggle="tab">Contenedores</a></li>
					
				</ul>
				
				<div class="tab-content"> <!-- Tab Content -->
				
					<div class="tab-pane " id="embarque">
						<g:render template="editForm" bean="${embarqueInstance}"/>
					</div> <!-- End Embarques form Panel -->
					
					<div class="tab-pane active" id="plist">
						<g:set var="partidas" value="${embarqueInstance.partidas}"/>
						<g:render template="partidas" model="['embarqueInstance':embarqueInstance,'facturaGastos':facturaGastos]"/>
					</div> <!-- End EmbarqueDet partidas Panel -->
					
					<div class="tab-pane" id="costos">
						<g:render template="costosPanel"/>
					</div>
					
					<div class="tab-pane" id="con">
						<g:render template="contenedoresPanel" bean="${embarqueInstance}"/>
					</div>
					
				</div>	<!-- End Tab Content -->
		
		</div>		
		</div>
		</div>	
		
	<r:script>
		$(function(){
			$('.fechaFormat').mask("99/99/9999");
			$('.numeric').forceNumeric();
			$('.currency').formatCurrency();
		});	
	</r:script>
	</body>
</html>
	