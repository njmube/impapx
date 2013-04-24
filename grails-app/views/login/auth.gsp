<!doctype html>
<html>
<head>
	<meta name="layout" content="luxor"/>
	<title>ImpapEx</title>
	<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
</head>
<body>
	<header class="jumbotron subhead">
		<div class="container">
			<h1> IMPAP-EX</h1>
			<p class="lead">Sistema de administración, importación y comerciaización de la inustria del papel</p>
		</div>
	</header>
	
	<div class="container">
		<div class="row">
			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
			</g:if>
			<form action='${postUrl}' method='POST' id='loginForm'  autocomplete='off' class="form-signin">
        		<h2 class="form-signin-heading">Acceso al sistema</h2>
        		<input type="text" class="input-block-level" placeholder="Usuadio" name='j_username' id='username'>
        		<input type="password" class="input-block-level" placeholder="Password" name='j_password' id='password'>
        		<label class="checkbox" >
        			<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
          			<input type="checkbox" value="remember-me"> Recrodar
        		</label>
        		<button class="btn btn-large btn-primary" type="submit">Login</button>
      		</form>
		</div> 
    </div> <!-- /container -->
    <r:script>
    	$(function(){
    		$("#username").focus();
    	});
    </r:script>
</body>
</html>