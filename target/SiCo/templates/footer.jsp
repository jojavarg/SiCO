<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
String path6 = request.getContextPath();
String basePath6 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path6+"/";
%>

</main>
<footer> 
&nbsp;
</footer>
</body>

<!-- <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<% out.println(basePath);%>js/bootstrap.js"></script>-->




<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<% out.println(basePath6);%>js/moment.js"></script>

<script type="text/javascript" src="<% out.println(basePath6);%>js/jquery.smartmenus.js"></script>
<script type="text/javascript" src="<% out.println(basePath6);%>js/jquery.smartmenus.bootstrap.js"></script>
<script type="text/javascript" src="<% out.println(basePath6);%>js/bootstrap-datetimepicker.js"></script>
</html>