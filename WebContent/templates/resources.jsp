<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<head>
    <%
String path5 = request.getContextPath();
String basePath5 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path5+"/";
%>

<meta charset="UTF-8">
<link href="<% out.println(basePath5);%>css/all.css" rel="stylesheet" type="text/css">


<link href="<% out.println(basePath5);%>css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="<% out.println(basePath5);%>css/jquery.smartmenus.bootstrap.css" rel="stylesheet">
<link href="<% out.println(basePath5);%>css/bootstrap-datetimepicker.css" rel="stylesheet">


<link href="<% out.println(basePath5);%>css/layout.css" rel="stylesheet" type="text/css">
<link href="<% out.println(basePath5);%>css/stylesheet.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<% out.println(basePath5);%>js/moment-with-locales.js"></script>
<script type="text/javascript" src="<% out.println(basePath5);%>js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="<% out.println(basePath5);%>js/validacion.js"></script>


</head>