<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="co.movilidadbogota.bl.UsuarioBean"%>


    <%
        	UsuarioBean usuario=(UsuarioBean)session.getAttribute("usuario");
        	if (usuario != null) {
        		session.removeAttribute("usuario");
        		session.invalidate();
        		        	
	        %>
			<script language="Javascript">
				alert("Ha sido desconectado con exito");
			</script>
			<%
				response.sendRedirect("login.jsp");
				}
			%>
			
			
			
