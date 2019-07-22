<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
String path4 = request.getContextPath();
String basePath4 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path4+"/";
%>

<!-- 
<div class="menu-header">
	<nav class="navbar navbar-default">
		<div class="container-fluid">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"></a>
			</div>


			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Contratación <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Plan anual</a></li>
							<li><a>Precontractual <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a
										href="<%	out.println(basePath);	%>pages/estudiosprevios.jsp"
										target="_self">Estudios Previos</a></li>
									<li><a href="#">Licitación</a></li>
								</ul></li>
							<li><a href="#">Hoja de Ruta</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
				</ul>
			</div>

		</div>

	</nav>
</div>
-->

<!-- Navbar fixed top -->
<div class="navbar navbar-default" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Sicapital</a>
    </div>
    <div class="navbar-collapse collapse">
  
      <!-- Left nav -->
      <ul class="nav navbar-nav">
         <li><a href="#">Contratación <span class="caret"></span></a>
          <ul class="dropdown-menu">
           <!-- <li class="divider"></li>
            <li class="dropdown-header">Nav header</li>-->
            <li><a href="<%	out.println(basePath4);%>pages/estudiosprevios.jsp">Estudios Previos</a></li>
            <li><a href="#">PreContratación <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="<%	out.println(basePath4);%>pages/estudiosprevios.jsp">Estudios Previos</a></li>
                <!-- <li><a href="#">A long sub menu <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="disabled"><a class="disabled" href="#">Disabled item</a></li>
                    <li><a href="#">One more link</a></li>
                    <li><a href="#">Menu item 1</a></li>
                    <li><a href="#">Menu item 2</a></li>
                    <li><a href="#">Menu item 3</a></li>
                    <li><a href="#">Menu item 4</a></li>
                    <li><a href="#">Menu item 5</a></li>
                    <li><a href="#">Menu item 6</a></li>
                    <li><a href="#">Menu item 7</a></li>
                    <li><a href="#">Menu item 8</a></li>
                    <li><a href="#">Menu item 9</a></li>
                    <li><a href="#">Menu item 10</a></li>
   
                  </ul>
                </li>
                <li><a href="#">Another link</a></li>-->

              </ul>
            </li>
          </ul>
        </li>
      </ul>
      
  
    </div><!--/.nav-collapse -->
  </div><!--/.container -->
</div>
