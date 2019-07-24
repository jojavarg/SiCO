<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page import="co.movilidadbogota.bl.BLEstudiosPrevios"%>
<%@ page import="co.movilidadbogota.model.LineaPlan"%>
<%@ page import="co.movilidadbogota.model.Modalidad"%>
<%@ page import="co.movilidadbogota.model.Dependencia"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<%@ include file="/templates/plantilla.jsp" %> 

<%

BLEstudiosPrevios bLEstudiosPrevios= new BLEstudiosPrevios();
String vigencia = bLEstudiosPrevios.obtenerVigencia(request);
List<LineaPlan> listaPlan = bLEstudiosPrevios.obtenerLineaPlan(request);
List<Modalidad> listaModalidad = bLEstudiosPrevios.obtenerModalidad(request);
//List<Dependencia> listaDependencia = bLEstudiosPrevios.obtenerDependencia(request);


%>

<h3>Estudios Previos</h3>

<form method="post" name="estudiosprevios" id="estudiosprevios" action=""  >
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="form-group">
				<div class="row input-icons" style="margin:0px" align="right">
					<!-- <div class="col-lg-4 col-md-4 col-sm-1 col-xs-1"></div>-->
					<!--<i class='far fa-file-alt iconY'></i>-->
					<button class="btn btn-primary" id="nuevo">Nuevo</button>&nbsp;&nbsp;
					<button class="btn btn-primary">Consultar </button>&nbsp;&nbsp;
				</div>
			</div>
		</div>
	</div>

	<div class="row" id="flechas" style="display:none">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="form-group" align="right">
				<button class="btn btn-default"><< 
				</button>
				&nbsp;&nbsp;
				<button class="btn btn-default"> >></button>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="form-group">
				<label for="linea">Linea Plan de Contratación</label>
				<select class="form-control" id="linea" >
					<option value="0">--Seleccione uno --</option>
				<%
				int x=1;
					if(listaPlan.size() > 0 && listaPlan != null){
					
						for(LineaPlan linea : listaPlan) {
							//if(x<6){
								String objeto = "";
								if(linea.getObjeto().length() > 100) objeto = linea.getObjeto().substring(0, 100);
								else objeto = linea.getObjeto();
						%>
							<option value="<%= linea.getNumeroOrden()%>"><%= objeto%></option>
						<%
							//}
						x++;
						}
					}
				%>

				</select>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="form-group">
				<label for="modalidad">Modalidad Selección</label>
				<select class="form-control" id="modalidad" >
					<option value="0">--Seleccione uno --</option>
					<%

					if(listaModalidad.size() > 0 && listaModalidad != null){

						for(Modalidad modalidad : listaModalidad) {
							String nombreModalidad = "";
							if(modalidad.getResultado().length() > 100) nombreModalidad = modalidad.getResultado().substring(0, 100);
							else nombreModalidad = modalidad.getResultado();
							%>
							<option value="<%= modalidad.getArgumento()%>"><%= nombreModalidad%></option>
							<%						
						}
					}
					%>
		
				</select>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			<div class="form-group">
				<label for="nosisco">Número Sisco</label>
				<input type="text" class="form-control" id="nosisco" placeholder="Número Sisco" />
			</div>
		</div>
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			<div class="form-group">

				<label for="vigencia">Vigencia</label>
				<input type="text" class="form-control" id="vigencia" placeholder="Vigencia" maxlength="4" value="<%= vigencia%>">
			</div>
		</div>
	</div>

	
	<div class="row">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			<div class="form-group">
				<label for="estado">Estado</label>
				<select class="form-control" id="estado">
					<option value="0">--Seleccione uno--</option>
					<option value="1">ELABORACIÓN</option>
					<option value="2">Anulada</option>
					<option value="3">Viable y conveniente </option>
				</select>
			</div>
		</div>
	</div>
	

	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="form-group">
				<label for="objetoestudios">Objeto</label>
				<textarea rows=3 class="form-control" id="objetoestudios" placeholder="Objeto"></textarea>
			</div>
		</div>
	</div>

	<br><br>

	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="form-group">
				<button class="btn btn-primary" id="buscar">Buscar   <i class="fas fa-search"></i></button>
			</div>
		</div>
	</div>

	<hr />

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#home">General</a></li>
		<li><a data-toggle="tab" href="#menu1">Conveniencia y Necesidad</a></li>
		<li><a data-toggle="tab" href="#menu2">Objeto</a></li>
		<li><a data-toggle="tab" href="#menu3">Condiciones Esenciales</a></li>
		<li><a data-toggle="tab" href="#menu4">Obligaciones</a></li>
		<li><a data-toggle="tab" href="#menu5">Productos</a></li>
		<li><a data-toggle="tab" href="#menu6">Garantía</a></li>
	</ul>
	
	<!-- 
	<div class="tab-content">
		<div id="home" class="tab-pane fade in active">aaaa
		</div>
		<div id="menu1" class="tab-pane fade">dddd
		</div>
	</div>
	-->

	<div class="tab-content">
		<div id="home" class="tab-pane fade in active">
			<br><br>

			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					&nbsp;
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					<div class="form-group">
						<div class='input-group date' id='datetimepicker8'>
							<input type='text' class="form-control" placeholder="Fecha de Elaboración" id="fechaelaboracion"/>
							<span class="input-group-addon">
								<span class="fa fa-calendar">
								</span>
							</span>
						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				$(function () {
					$('#datetimepicker8').datetimepicker({
						format: 'L',
						icons: {
							time: "fa fa-clock-o",
							date: "fa fa-calendar",
							up: "fa fa-arrow-up",
							down: "fa fa-arrow-down"
						}
					});
				});
			</script>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="entidad">Entidad</label>
						<input type="text" class="form-control" id="entidad" placeholder="Entidad">
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">	
					<div class="form-group">
						<label for="unidad">Unidad Ejecutora Presupuesto</label>
						<input type="text" class="form-control" id="unidad" placeholder="Unidad Ejecutora Presupuesto">
					</div>
				</div>
			</div>
					
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="direccion">Dirección Oficina</label>
						<input type="text" class="form-control" id="direccion" placeholder="Dirección Oficina">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="director">Director(a)</label> <input type="text"
						class="form-control" id="director" placeholder="Director(a)">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
					<div class="form-group">
						<button class="btn  btn-default">Director Firmas (Adicional)</button>
					</div>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
					<div class="form-group">
						<button class="btn btn-default">Ordenador del gasto</button>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
					<div class="form-group">
						<button class="btn  btn-default">Replicar firmas</button>
					</div>
				</div>
			</div>

			<H4>Lónea o renglón del plan</H4>
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="renglon">Línea</label> 
						<select class="form-control" id="renglon">
							<%
							if(listaPlan.size() > 0 && listaPlan != null){

							for(LineaPlan linea : listaPlan) {

							String objeto = "";
							if(linea.getObjeto().length() > 100) objeto = linea.getObjeto().substring(0, 100);
							else objeto = linea.getObjeto();
							%>
							<option value="<%= linea.getNumeroOrden()%>"><%= linea.getNumeroOrden()%> - <%= objeto%></option>
							<%

							x++;
						}
					}
					%>
				</select>
					</div>
				</div>
			</div>
		</div>

		<div id="menu1" class="tab-pane fade">
			<br><br>
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="conveniencia">1. Análisis sobre la conveniencia de realizar la contratación</label>
						<textarea rows=3 class="form-control" id="conveniencia" placeholder="Análisis sobre la conveniencia de realizar la contratación"></textarea>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="continuacion">Continuación</label>
						<textarea rows=3 class="form-control" id="continuacion" placeholder="Continuación"></textarea>
					</div>
				</div>
			</div>
		</div>

		<div id="menu2" class="tab-pane fade">
			<br><br>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="alcance">Alcance del objeto</label>
						<textarea rows=3 class="form-control" id="alcance" placeholder="Alcance del objeto"></textarea>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<button class="btn btn-default" onclick="window.open('bienes.jsp');">Detalle Bienes y Servicios</button>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="especificaciontecnica">Especificación técnica</label>
						<textarea rows=3 class="form-control" id="especificaciontecnica" placeholder="Especificación técnica"></textarea>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="otrosaspectos">Otros aspectos del objeto contractual</label>
						<textarea rows=3 class="form-control" id="otrosaspectos" placeholder="Otros aspectos del objeto contractual"></textarea>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="nopluralidad">Constancia sobre no existencia de pluralidad de oferentes</label>
						<textarea rows=3 class="form-control" id="nopluralidad" placeholder="Constancia sobre no existencia de pluralidad de oferentes"></textarea>
					</div>
				</div>
			</div>
		</div>

		<div id="menu3" class="tab-pane fade">
			<br><br>
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					<div class="form-group">
						<label for="numeroplazo">Plazo</label> <input type="text"
						class="form-control" id="numeroplazo" placeholder="Plazo">
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					<div class="form-group">
						<label for="descripcionplazo">&nbsp;</label> 
						<select class="form-control" id="descripcionplazo">
							<option value="0">--Seleccion uno--</option>
							<option value="D">día(s)</option>
							<option value="M">mes(es)</option>
							<option value="A">año(s)</option>
						</select>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="observacionesPlazo">Observaciones al plazo</label>
						<textarea rows=3 class="form-control" id="observacionesPlazo" placeholder="Observaciones al plazo"></textarea>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					<div class="form-group">
						<label for="valormensual">Valor mensual de servicios</label> <input type="text"
						class="form-control" id="valormensual" placeholder="Valor mensual de servicios">
					</div>
					<div class="form-group">
						<label>SOLO APLICA PARA EL FORMATO 37-F_34</label>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					<div class="form-group">
						<label for="presupuesto">Presupuesto estimado</label> <input type="text"
						class="form-control" id="presupuesto" placeholder="Presupuesto estimado">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="calculopresupuesto">Cálculo del presupuesto</label>
						<textarea rows=3 class="form-control" id="calculopresupuesto" placeholder="Cálculo del presupuesto"></textarea>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="soportecalculo">Soporte cálculo de presupuesto P/U</label>
						<textarea rows=3 class="form-control" id="soportecalculo" placeholder="Soporte cálculo de presupuesto P/U"></textarea>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="tipopropuestatecnica">Tipo de propuesta técnica</label>
						<textarea rows=3 class="form-control" id="tipopropuestatecnica" placeholder="Tipo de propuesta tócnica"></textarea>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="formapago">Forma de pago</label>
						<textarea rows=3 class="form-control" id="formapago" placeholder="Forma de pago"></textarea>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="sitioentrega">Sitio de entrega</label>
						<textarea rows=3 class="form-control" id="sitioentrega" placeholder="Sitio de entrega"></textarea>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<div class="row" style="margin: 0px">
							<button class="btn btn-default">Ver Ejemplo pagos</button>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<div class="row" style="margin: 0px">
							<button class="btn btn-default">Factores de evaluación</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="menu4" class="tab-pane fade">
			<br><br>
			<label>Obligaciones especiales del contratista</label>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="right"></div>
				<button class="btn-success btn">+</button>
				<button class="btn-danger btn">-</button>
				></div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<textarea rows=3 class="form-control" id="obligacionesContratista" placeholder="Nueva obligación"></textarea>
					</div>
				</div>
			</div>
			
			<hr/>


			<label>Obligaciones del supervisor (por parte de la Secretaróa Distrital de Movilidad)</label>
			<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="right"></div>
					<button class="btn-success btn">+</button>
					<button class="btn-danger btn">-</button>
					></div>
				</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<textarea rows=3 class="form-control" id="obligacionesSupervisor" placeholder="Nueva obligación"></textarea>
					</div>
				</div>
			</div>

			<hr/>

			<label>Obligaciones especiales del interventor (Si las hay)</label>
			<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="right"></div>
					<button class="btn-success btn">+</button>
					<button class="btn-danger btn">-</button>
					></div>
				</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<textarea rows=3 class="form-control" id="obligacionesInterventor" placeholder="Nueva obligación"></textarea>
					</div>
				</div>
			</div>

			<hr/>
		</div>

		<div id="menu5" class="tab-pane fade">
			<br><br>
			<h4>Resultados esperados</h4>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<textarea rows=3 class="form-control" id="resultadoesperado" placeholder="Resultado"></textarea>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<textarea rows=3 class="form-control" id="volumen" placeholder="Volumen"></textarea>
					</div>
				</div>
			</div>
		</div>

		<div id="menu6" class="tab-pane fade">
			<br><br>

			<h4>Garantías</h4>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="garantia">Garantía</label>
						<select class="form-control" id="garantia" >
							<option value="0">--Seleccione uno --</option>
							<option value="1">No requiere</option>
						</select>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="observacionesgarantia">Observaciones a la garantía / Razones de no seguir</label>
						<textarea rows=3 class="form-control" id="observacionesgarantia" placeholder="Observaciones a la garantóa / Razones de no seguir"></textarea>
					</div>
				</div>
			</div>
			
			<hr/>

			<h4>Amparos que cubre la garantía</h4>
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="amparo">Amparo</label>
						<select class="form-control" id="amparo" >
							<option value="0">--Seleccione uno --</option>
						</select>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="porcentaje">Porcentaje</label> <input type="text"
						class="form-control" id="porcentaje" placeholder="Porcentaje">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="base">Base</label>
						<select class="form-control" id="base" >
							<option value="0">--Seleccione uno --</option>
						</select>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="vigenciagarantia">Vigencia</label> <input type="text"
						class="form-control" id="vigenciagarantia" placeholder="Vigencia">
					</div>
				</div>
			</div>

			<hr />

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="soporteriesgos">Soporte Tipificación Riesgos</label> <input type="text"
						class="form-control" id="soporteriesgos" placeholder="Soporte Tipificación Riesgos">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="exigenciasgarantia">Exigencia Garantías Ofrecimiento</label> <input type="text"
						class="form-control" id="exigenciasgarantia" placeholder="Exigencia Garantías Ofrecimiento">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="garantiasofrecimiento">Garantóas Ofrecimiento</label> <input type="text" class="form-control" id="garantiasofrecimiento" placeholder="Garantóas Ofrecimiento">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="analisisgarantias">Anólisis Garantóas Incumplimiento</label> <input type="text"
						class="form-control" id="analisisgarantias" placeholder="Anólisis Garantóas Incumplimiento">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="pertinenciagarantias">Pertinencia División Garantóas</label> <input type="text"
						class="form-control" id="pertinenciagarantias" placeholder="Pertinencia División Garantóas">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="form-group">
						<label for="documentogarantias">Revisión Documento Garantías</label> <input type="text"
						class="form-control" id="documentogarantias" placeholder="Revisión Documento Garantías">
					</div>
				</div>
			</div>
		</div>

	</div>
	<br /><br >
	<div class="form-group">
		<div class="row" style="margin:0px">
		<!-- <div class="col-lg-4 col-md-4 col-sm-1 col-xs-1"></div>-->
			<button class="btn btn-success" id="actualizarFormulario">Guardar</button>
		</div>
	</div>

</form>

<script>
	$("#linea").on('change',function() {
//		alert("Actualizando campos");	
		var linea;
		var row = 0;
		linea = this.value;

		$.ajax({
			data : {
				"linea" : linea
			},
			type : "POST",
			async : false,
			url : "../LlenarCampos/estudiosPrevios",
			success : function(data) {
				var respCons = data.respuesta;
				console.log(respCons);
				
				
				$("#modalidad option[value='"+respCons.argumento+"']").prop('selected', true);
				$("#objetoestudios").val(respCons.objeto);
				
				//General
				$("#unidad").val(respCons.general.idUnidad+' - '+respCons.general.nombreUnidad);
				$("#entidad").val(respCons.general.nombreEntidad);
				$("#director").val(respCons.general.nombreContacto);
				$("#direccion").val(respCons.general.idAreaOrigen+' - '+respCons.general.areaOrigen);
				$("#renglon option[value='"+linea+"']").prop('selected', true);
				
				//Condiciones
				$("#descripcionplazo option[value='"+respCons.condiciones.tipoPlazoEjecucion+"']").prop('selected', true);
				$("#numeroplazo").val(respCons.condiciones.plazoEjecucion);
				$("#presupuesto").val(respCons.condiciones.valordisp);
				
				
			}
		})
	})


	$("#nuevo").on('click',function(){
		$('#estudiosprevios')[0].reset();
	});


	
	
	$("#buscar").on('click',function() {
		alert("Buscando...");	
		var linea;
		var row = 0;
		nosisco = $("#nosisco").val();
		vigencia = $("#vigencia").val();
		estado = $( "select#estado option:selected" ).val();
		objetoestudios = $("#objetoestudios").val();

		if(nosisco == null) nosisco='';
		if(vigencia == null) vigencia='';
		if(estado == null) estado='';
		if(objetoestudios == null) objetoestudios='';
		
		$.ajax({
			data : {
				"nosisco" : nosisco,
				"vigencia" : vigencia,
				"estado" : estado,
				"objetoestudios" : objetoestudios,
			},
			type : "POST",
			async : false,
			url : "../LlenarCampos/buscarestudiosPrevios",
			success : function(data) {
				var respCons = data.respuesta;
				if (respCons.length > 1){
					$("flechas").style.display='';
					
				}else{
					$("flechas").style.display='none';
					
					
				}
				
			}
		})
	})
</script>
<%@ include file="/templates/footer.jsp" %>  

