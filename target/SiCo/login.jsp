<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ include file="/templates/plantilla_login.jsp" %> 

<body>
 
<%@ include file="/templates/header.jsp" %>  



<form action="processLogin" class="form" method="post" onsubmit="return validarLogin(this)">

    
    <div class="row">
    	<div class="col-lg-4 col-md-4 col-sm-1 col-xs-1"></div>
    	<div class="col-lg-4 col-md-4 col-sm-10 col-xs-10">
    		<div class="form-group">
    			<div class="alert alert-danger" role="alert" id="alerta"  style="display:none"></div>
    		</div>
    	</div>
    	<div class="col-lg-4 col-md-4 col-sm-1 col-xs-1"></div>
    </div>

	<div class="row">
		<div class="col-lg-4 col-md-4 col-sm-1 col-xs-1"></div>
		<div class="col-lg-4 col-md-4 col-sm-10 col-xs-10">
			<div class="form-group">
				<label for="usuario">Usuario</label> <input type="text"
					class="form-control" id="usuario" name="usuario" placeholder="Usuario">
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-1 col-xs-1"></div>
	</div>
	<div class="row">
		<div class="col-lg-4 col-md-4 col-sm-1 col-xs-1"></div>
		<div class="col-lg-4 col-md-4 col-sm-10 col-xs-10">
			<div class="form-group">
				<label for="password">Contrase&ntilde;a</label> <input type="password"
					class="form-control" id="password" name="password" placeholder="Contrase&ntilde;a">
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-1 col-xs-1"></div>
	</div>

	<div class="row">
		<div class="col-lg-4 col-md-4 col-sm-1 col-xs-1"></div>
		<div class="col-lg-4 col-md-4 col-sm-10 col-xs-10">
			<div class="form-group">
				<label for="database">Base de datos</label> 
				<input type="text"
					class="form-control" id="database" name="database" placeholder="Base de datos" value="sicapitalpru">
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-1 col-xs-1"></div>
	</div>


	<div class="row">
		<div class="col-lg-4 col-md-4 col-sm-1 col-xs-1"></div>
		<div class="col-lg-4 col-md-4 col-sm-10 col-xs-10">
			<div class="form-group">
				<button class="btn btn-primary" id="login" type="submit">Ingresar</button>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-1 col-xs-1"></div>
	</div>

</form>
	<%@ include file="/templates/footer.jsp" %> 
