<%@ page import="com.luxsoft.impapx.Proveedor"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="taskView">
<title>Catálogo de proveedores</title>
<r:require module="dataTables"/>
</head>
<body>
	<content tag="header">
		<h3>Catálogo de proveedores</h3>
 	</content>
	<content tag="consultas">
 	</content>
 	<content tag="operaciones">
 		<li class="active">
 			<g:link class="list" action="list">
				<i class="icon-list icon-white"></i>
				Proveedores
			</g:link>
		</li>
		<li>
			<g:link class="create" action="create">
				<i class="icon-plus"></i>
				<g:message code="proveedor.create.label" default="Alta de proveedor"/>
			</g:link>
		</li>
		
 	</content>
 	<content tag="document">
 		<g:if test="${flash.message}">
			<bootstrap:alert class="alert-info">
				${flash.message}
			</bootstrap:alert>
		</g:if>
 			<table id="grid" class="table table-striped table-hover table-bordered table-condensed">
				<thead>
					<tr>
						<g:sortableColumn property="nombre"	title="${message(code: 'proveedor.nombre.label', default: 'Nombre')}" />

						<g:sortableColumn property="lastUpdated"
								title="${message(code: 'proveedor.lastUpdated.label', default: 'Last Updated')}" />
					</tr>
				</thead>
				<tbody>
					<g:each in="${proveedorInstanceList}" var="proveedorInstance">
						<tr>
							<td>
								<g:link action="edit" id="${proveedorInstance.id}">
										${fieldValue(bean: proveedorInstance, field: "nombre")}
								</g:link>
							</td>
							<td><lx:shortDate date="${proveedorInstance.dateCreated}"/></td>
						</tr>
					</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<bootstrap:paginate total="${proveedorInstanceTotal}" />
			</div>
	</content>
	<r:script>
		$(function(){
			$("#grid").dataTable({
				aLengthMenu: [[100, 150, 200, 250, -1], [100, 150, 200, 250, "Todos"]],
          		iDisplayLength: 50,
				"oLanguage": {"sUrl":"<g:resource dir="js" file="dataTables.spanish.txt" />"},
			});
		});
	</r:script>
</body>
</html>

