

<%@ page import="com.luxsoft.impapx.Pedimento"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="luxor">
<g:set var="entityName"
	value="${message(code: 'pedimento.label', default: 'Pedimento')}" />
<title><g:message code="pedimento.list.label"
		default='Pedimentos de importación' /></title>
<r:require module="luxorSimpleTable"/>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">

			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">
							${entityName}
						</li>
						<li class="active"><g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
								<g:message code="pedimento.list.label" />
							</g:link></li>
						<li><g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link></li>
					</ul>
				</div>
			</div>

			<div class="span9">

				<div class="well-small">
					<h4>
						<g:message code="pedimento.list.label" default='Lista de pedimentos'/>
					</h4>
				</div>

				<g:if test="${flash.message}">
					<bootstrap:alert class="alert-info">
						${flash.message}
					</bootstrap:alert>
				</g:if>

				<table
					class=" grid table table-striped table-hover table-bordered table-condensed">
					<thead>
						<tr>
							<td>Folio</td>
							<g:sortableColumn property="pedimento"
								title="${message(code: 'pedimento.pedimento.label', default: 'Pedimento')}" />
							<g:sortableColumn property="proveedor.nombre"
								title="Agente" />
							

							<g:sortableColumn property="fecha"
								title="${message(code: 'pedimento.fecha.label', default: 'Fecha')}" />


							<g:sortableColumn property="dta"
								title="${message(code: 'pedimento.dta.label', default: 'Dta')}" />

							<g:sortableColumn property="prevalidacion"
								title="${message(code: 'pedimento.prevalidacion.label', default: 'Prevalidación')}" />

							<g:sortableColumn property="tipoDeCambio"
								title="${message(code: 'pedimento.tipoDeCambio.label', default: 'T.C.')}" />

							<g:sortableColumn property="impuesto"
								title="${message(code: 'pedimento.impuesto.label', default: 'Impuesto')}" />
							<td>Incrementables</td>

						</tr>
					</thead>
					<tbody>
						<g:each in="${pedimentoInstanceList}" var="pedimentoInstance">
							<tr>
								<td><g:link action="edit" id="${pedimentoInstance.id}">
										${fieldValue(bean: pedimentoInstance, field: "id")}
									</g:link>
								</td>
								<td><g:link action="edit" id="${pedimentoInstance.id}">
										${fieldValue(bean: pedimentoInstance, field: "pedimento")}
									</g:link>
								</td>
								<td>${fieldValue(bean:pedimentoInstance,field:"proveedor.nombre") }</td>
								<td>
									<lx:shortDate date="${pedimentoInstance.fecha}"/>
								</td>
								<td>
									<lx:moneyFormat number="${pedimentoInstance.dta}"/>
								</td>
								<td>
									<lx:moneyFormat number="${pedimentoInstance.prevalidacion}"/>
								</td>

								<td>
									<g:formatNumber number="${pedimentoInstance.tipoDeCambio}" format="###.####"/>
								</td>

								<td>
									<lx:moneyFormat number="${pedimentoInstance.impuesto}"/>
								</td>
								<td>
									<lx:moneyFormat number="${pedimentoInstance.incrementables}"/>
								</td>

							</tr>
						</g:each>
					</tbody>
				</table>
				
			</div>

		</div>
	</div>
</body>
</html>
