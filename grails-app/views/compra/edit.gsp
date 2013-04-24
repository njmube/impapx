<%@ page import="com.luxsoft.impapx.Compra" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="taskView">
		<g:set var="entityName" value="${message(code: 'compra.label', default: 'Compra')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		<r:require module="luxorForms"/>
	</head>
	<body>
	<content tag="header">
		
 	</content>
 	
 	<content tag="consultas">
 		<g:link action="list">
			Compras
		</g:link>
 	</content>
 	<content tag="operaciones">
 		<li>
 			<g:link class="" action="create">
				<i class="icon-plus "></i>
				Alta de compra
			</g:link>
		</li>
 	</content>
 	
 	<content tag="document">
 		
 		<g:render template="/shared/messagePanel" model="[beanInstance:compraInstance]"/>
 		<div class="alert">
 			<h5>Compra:${compraInstance.id} ${compraInstance.proveedor.nombre}</h5>
 		</div>
 		<ul class="nav nav-tabs" id="myTab">
			<li class=""><a href="#compraPanel" data-toggle="tab">Compra</a></li>
			<li class="active"><a href="#partidasPanel" data-toggle="tab">Partidas</a></li>
		</ul>
		
		<div class="tab-content">
			
			<div class="tab-pane" id="compraPanel">
				<g:render template="form" bean="${compraInstance}"/>
			</div>
			
			<div class="tab-pane active" id="partidasPanel">
				<div id="comprasDetGrid">
					<g:render template="comprasDetGrid" bean="${compraInstance}" model="[partidas:partidas]"/>
				</div>
				
				
				
				
				<%-- 
				<div class="btn-group">
					<g:link id="agregarPartidas"
							class="btn btn-success"
							controller="compraDet"
							action="create"
							params="[compraId:compraInstance.id]">
						<i class="icon-plus icon-white"></i>
  							<g:message code="default.button.add.label" default="Agregar" />
					</g:link>
				</div>
				--%>
			</div>
		</div>
		
 	</content>
 	
		
	</body>

</html>

