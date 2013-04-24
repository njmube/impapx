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
		<div class="container">
		<div class="row">
		<div class="span12">

			<ul class="nav nav-tabs">
					<li class=""><a href="#embarque" data-toggle="tab">Embarque</a></li>
					<li class="active">
						<a data-target="#plist" data-toggle="tab">P.List</a> 
						<%--
						<g:remoteLink action="pickingList" update="plist" data-toggle="tab" data-target="#plist" params="[embarqueId:embarqueInstance?.id]">P List</g:remoteLink> 
						--%>
					</li>
			</ul>			

			<div class="tab-content">

			
			
			<div class="tab-pane " id="embarque">

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
				
					<g:form class="form-horizontal" action="edit" id="${embarqueInstance?.id}" >
						<g:hiddenField name="version" value="${embarqueInstance?.version}" />
						<fieldset>
							<f:with bean="embarqueInstance">
								<f:field property="proveedor">
									<g:field type="text" name="proveedor" value="${embarqueInstance?.proveedor?.nombre}" class="span5"/>
								</f:field>
								<f:field property="bl" input-class="mayusculas" ></f:field>
								<f:field property="nombre" input-class="mayusculas" ></f:field>
								<f:field property="fechaEmbarque">
									<input class="datepicker" type="text" id="fechaEmbarque" name="fechaEmbarque" value="${formatDate(format:'dd/MM/yyyy',date:embarqueInstance?.fechaEmbarque)}"/>
									<a href="#" class="btn btn-medium datepicker-btn"><i class="icon-calendar"></i></a> 
								</f:field>
								 <f:field property="ingresoAduana" label="ETA">
									<input class="datepicker" type="text" id="ingresoAduana" name="ingresoAduana" value="${formatDate(format:'dd/MM/yyyy',date:embarqueInstance?.ingresoAduana)}"/>
									<a href="#" class="btn btn-medium datepicker-btn"><i class="icon-calendar"></i></a> 
								</f:field>
								<f:field property="contenedores"/>
								<f:field property="comentario" >
									<g:textArea name="comentario" class="comentario" value="${embarqueInstance?.comentario}"/>
								</f:field>
								
							</f:with>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">
									<i class="icon-ok icon-white"></i>
									<g:message code="default.button.update.label" default="Update" />
								</button>
								<button type="submit" class="btn btn-danger" name="_action_delete" formnovalidate>
									<i class="icon-trash icon-white"></i>
									<g:message code="default.button.delete.label" default="Delete" />
								</button>
								<g:link class="btn btn-success" controller="embarqueDet" action="create" params="['embarque.id':embarqueInstance?.id,'proveedorId':embarqueInstance?.proveedor?.id]">
									<i class="icon-plus icon-white"></i>Agregar partida
								</g:link>
							</div>
						</fieldset>
					</g:form>
				
			</div>

			<div class="tab-pane active" id="plist">
				
				<g:set var="partidas" value="${embarqueInstance.partidas}"/>
				<g:render template="partidas" model="['embarqueInstance':embarqueInstance]"/>
			</div>

			</div>
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
	