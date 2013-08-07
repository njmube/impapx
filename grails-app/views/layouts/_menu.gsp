<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
	  	<div class="container">
		  
		  <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
			  <span class="icon-bar"></span>
			  <span class="icon-bar"></span>
			  <span class="icon-bar"></span>
		  </a>
		  <a class="brand" href="#">${grailsApplication.config.impapx.empresa}</a>
		  
		  <div class="nav-collapse">
			  <ul class="nav">
				  <li class="active"><a class="home1" href="${createLink(uri: '/')}">
						<i class="icon-home icon-white"></i> Inicio</a></li>
				  
				  <sec:ifLoggedIn>
				  
				  <li class="dropdown">
				  	<a class="dropdown-toggle" data-toggle="dropdown" href="#">Catalogos<b class="caret"></b></a>
				  	<sec:ifAnyGranted roles="ROLE_COMPRAS,ROLE_ADMIN">
				  	<ul class="dropdown-menu">
				  		<li><g:link controller="linea" action="list">Lineas</g:link></li>
				  		<li><g:link controller="marca" action="list">Marcas</g:link></li>
				  		<li><g:link controller="clase" action="list">Clases</g:link></li>
				  		<li><g:link controller="unidad" action="list">Unidades</g:link></li>
				  		<li><g:link controller="producto" action="list">Productos</g:link></li>
				  		<li><g:link controller="proveedor" action="list">Proveedores</g:link></li>
				  		<li><g:link controller="cliente" action="list">Clientes</g:link></li>
				  		<li><g:link controller="aduana" action="list">Aduanas</g:link></li>
				  		
				  		
				  	</ul>
				  	</sec:ifAnyGranted>
				  </li>
				  
				  <li class="dropdown">
				  	<a class="dropdown-toggle" data-toggle="dropdown" href="#">Operaciones<b class="caret"></b></a>
				  	<sec:ifAnyGranted roles="ROLE_COMPRAS,ROLE_ADMIN">
				  	<ul class="dropdown-menu">
				  		
				  		<li><g:link controller="compra" action="list">Compras</g:link></li>
				  		<li><g:link controller="embarque" action="list">Embarques</g:link></li>
				  		<li><g:link controller="pedimento" action="list">Pedimentos</g:link></li>
				  		<li><g:link controller="cuentaDeGastos" action="list">Cuentas de Gastos</g:link></li>
				  		<li><g:link controller="cuentaPorPagar" action="index">C x P</g:link></li>
				  		<%-- <li><g:link controller="requisicion" action="list">Requisiciones</g:link></li> --%>
				  		<li><g:link controller="distribucion" action="list">Distribución</g:link></li>
				  		<li><g:link controller="venta" action="list">Ventas</g:link></li>
				  		<li><g:link controller="notaDeCargo" action="list">Notas de Cargo</g:link></li>
				  		<li><g:link controller="cuentasPorCobrar" action="index">C x C</g:link></li>
				  		<li><g:link controller="anticipo" action="index">Anticipos</g:link></li>
				  		
				  	</ul>
				  	</sec:ifAnyGranted>
				  </li>
				  
				  
				   <li class="dropdown">
				  	<a class="dropdown-toggle" data-toggle="dropdown" href="#">Contabilidad<b class="caret"></b></a>
				  	<sec:ifAnyGranted roles="ROLE_CONTABILIDAD,ROLE_ADMIN">
				  	<ul class="dropdown-menu">
				  		<li><g:link controller="cuentaContable" action="list">Cuentas</g:link></li>
				  		<li><g:link controller="saldoPorCuentaContable" action="list">Saldos</g:link></li>
				  		<li><g:link controller="certificado" action="list">Certificados</g:link></li>
				  		<li><g:link controller="folioFiscal" action="list">Folios fiscales</g:link></li>
				  		<li><g:link controller="empresa" action="list">Empresa</g:link></li>
				  		<li><g:link controller="comprobanteFiscal" action="list">Reporte mensual CFD</g:link></li>
				  		<li><g:link controller="diferenciaCambiaria" action="list">Diferencias cambiarias</g:link></li>
				  		<li><g:link controller="poliza" action="index">Pólizas</g:link></li>
				  		<li><g:link controller="poliza" action="index">Balanza</g:link></li>
				  		<li><g:link controller="diot" action="list">DIOT</g:link></li>
				  	</ul>
				  	</sec:ifAnyGranted>
				  </li>
				  
				  <li class="dropdown">
				  	<a class="dropdown-toggle" data-toggle="dropdown" href="#">Tesorería<b class="caret"></b></a>
				  	<sec:ifAllGranted roles="ROLE_TESORERIA">
				  	<ul class="dropdown-menu">
				  		<li><g:link controller="banco" action="list">Bancos</g:link></li>
				  		<li><g:link controller="proveedor" action="list">Proveedores</g:link></li>
				  		<li><g:link controller="cuentaPorPagar" action="index">C x P</g:link></li>
				  		<li><g:link controller="cuentaBancaria" action="list">Cuentas Bancarias</g:link></li>
				  		<li><g:link controller="movimientoDeCuenta" action="list">Operaciones</g:link></li>
				  	</ul>
				  	</sec:ifAllGranted>
				  </li>
				 
				  <li><a href="#"><i class="icon-list icon-white"></i> Consultas</a></li>
				  <li class="divider-vertical"></li>
				  <li><a href="#">Sistemas</a></li>
				  <li><g:link controller="home" action="info"><i class="icon-list icon-info-sign icon-white"> </i> Info</g:link></li>
				  <li class="navbar-text">Usuario: <sec:loggedInUserInfo field="username"/></li>
				 
				  <li><g:link controller="logout" action="index">Logout</g:link></li>
				  
				  </sec:ifLoggedIn>
				  
				  <li>
				  	<sec:ifNotLoggedIn>
				  		<g:link controller="login" action="auth">Login</g:link>
				  	</sec:ifNotLoggedIn>
				  </li>
				  
			  </ul>
			  
		  </div>  
		  
		</div>  
	  </div>
</div>