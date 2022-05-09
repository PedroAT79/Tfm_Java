<%HttpSession sesion = request.getSession(false);
int idUsuario = 0;
int nivelAcceso = 0;
String usuario =null;

if(sesion.getAttribute("nivelAcceso")== null){
	nivelAcceso = 1;
}else{
	nivelAcceso = (int)sesion.getAttribute("nivelAcceso");
	 idUsuario = (int)sesion.getAttribute("idUsuario");
	 usuario = (String)sesion.getAttribute("usuario");

	
}
%>
<header class="encabezado">
<nav class=menuPpal>
<%switch(nivelAcceso){
case 1:%>
	<input class="botonNav" type="button" onclick="history.back()" name="volver atrás" value="VOLVER">
	<a class="botonNav" href="altaUsuario.jsp">REGISTRO</a> 
	<a class="botonNav" href="login.jsp">LOGIN</a> 
	<a class="botonNav" href="contactar.jsp">CONTACTO</a>
	
	

<% break;
case 2: %>
	<input class="botonNav" type="button" onclick="history.back()" name="volver atrás" value="VOLVER">
	<a class="botonNav" href="contactar.jsp">CONTACTO</a> 
	<a class="botonNav" href="carrito.jsp">CARRITO</a>
	<a class="botonNav" href="Sv_Filtros?opcion=8&idUsuario=<%=idUsuario%>">HISTORIAL</a>
	<a class="botonNav" href="Sv_Login?opcion=2">SALIR</a>
	<div id="nombre"><p>Usuario: <%=usuario %></p></div>
<% break;
case 3: %>
	<input class="botonNav" type="button" onclick="history.back()" name="volver atrás" value="VOLVER">
	<a class="botonNav" href="login.jsp">LOGIN</a> 
	<a class="botonNav" href="admin.jsp">GESTION</a>
	<a class="botonNav" href="altaUsuario.jsp">USUARIO</a> 
	<a class="botonNav" href="altaArticulo.jsp">ARTICULO</a>
	<a class="botonNav" href="Sv_Login?opcion=2">SALIR</a>
	<div id="nombre"><p>Usuario: <%=usuario %></div>
<%break;
	default:%>
		<input class="botonNav" type="button" onclick="history.back()" name="volver atrás" value="VOLVER">
		<a class="botonNav" href="altaUsuario.jsp">REGISTRO</a> 
		<a class="botonNav" href="login.jsp">LOGIN</a> 
		<a class="botonNav" href="contactar.jsp">CONTACTO</a> 
		<div id="nombre"><p>Usuario: <%=usuario %></div>
	
<% }%>





</header>
