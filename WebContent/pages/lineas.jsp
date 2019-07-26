<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="co.movilidadbogota.bl.BLEstudiosPrevios"%>
<%@ page import="co.movilidadbogota.model.LineaPlan"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<%@ include file="/templates/plantilla.jsp" %> 
<%

BLEstudiosPrevios bLEstudiosPrevios= new BLEstudiosPrevios();
String vigencia = bLEstudiosPrevios.obtenerVigencia(request);
List<LineaPlan> listaPlan = bLEstudiosPrevios.obtenerLineaPlan(request);

%>

<h3>Lìneas Plan de contratación</h3>
<div class="container">
	<div class="form-group">
		<div class="row">
			<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
				<input type="text" class="form-control" id="lineaPlan"
					placeholder="Línea Plan" />
				<button type="button"">
					Buscar <i class="fas fa-search"></i>
				</button>
			</div>
		</div>
	</div>
		<div class="form-group">
		<div class="row">
			<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
				
				<table class="table table-responsive"    >
					<tr>
						<th>
						
						</th>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>

			</div>
		</div>
	</div>
	
	

	<%@ include file="/templates/footer.jsp" %> 