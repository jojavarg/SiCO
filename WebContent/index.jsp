<%@page import="javax.faces.context.FacesContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
</body>
<%
	RequestDispatcher rd = null;
	if (session.getAttribute("UsuarioBean") == null) {
		rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	} else {
		rd = request.getRequestDispatcher("/pages/main.xhtml");
		rd.forward(request, response);
	}
%>
</html>