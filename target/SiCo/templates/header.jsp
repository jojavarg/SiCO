<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
String path2 = request.getContextPath();
String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path2+"/";
%>

<header class="clear " id="header">
			<div id="hgroup">
				<div id="logo">
					<a href="#"><img alt="" src="<% out.println(basePath2);%>/images/logo.png"></a>
				</div>

				<div class="titulo">
					Secretar&iacute;a Distrital de Movilidad<span style="margin-left:20px; font-weight:bold">SISTEMA DE INFORMACI&Oacute;N HACENDARIO</span>
				</div>

				<div id="logoAlcaldia">
					<img alt="" src="<% out.println(basePath2); %>/images/logoAlcaldia.png">
				</div>
			</div>
		</header>
		<div class="wrapper row1">
		<div class="gradGrey shadow" style="min-height:7px; margin-bottom:15px"></div>
	</div>