<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="co.movilidadbogota.bl.UsuarioBean"%>
    <%
String path3 = request.getContextPath();
String basePath3 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path3+"/";
%>
<% //String username = request.getParameter("usuario");
	UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");
%>

<div class="login-header" align="right">
	<div class="page-header" style="padding-bottom: 0px; margin: 0px">
		<h2>
			<small>Bienvenido</small>
			<%
				out.println(user.getLogin());
			%>

			<small><a href="<% out.println(basePath3);%>logout.jsp"><i
					class="fas fa-sign-out-alt"></i></a></small>
		</h2>
		<!--  <a href="logout.jsp"><b>Logout</b></a>-->

	</div>
</div>