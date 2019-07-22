<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<%@ include file="/templates/resources.jsp" %>  



<body>
<%@ include file="/templates/header.jsp" %>  
<main class="detalle">
	<%@ include file="/templates/logged.jsp" %> 
	
	
	<%@ include file="/templates/menu.jsp" %>  
	
	
	<!-- 
	<ol class="breadcrumb">
	  <li><a href="#">Home</a></li>
	  <li><a href="#">Library</a></li>
	  <li class="active">Data</li>
	</ol>-->
	



