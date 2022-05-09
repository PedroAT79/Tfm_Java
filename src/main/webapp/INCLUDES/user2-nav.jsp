<%
HttpSession sesion = request.getSession(false);
int nivelAcceso = 0;
int idUsuario=0;
String usuario = null;
if(sesion.getAttribute("nivelAcceso")== null){
	nivelAcceso = 1;
}else{
	nivelAcceso = (int)sesion.getAttribute("nivelAcceso");
	 idUsuario = (int)sesion.getAttribute("idUsuario");
	usuario = (String)sesion.getAttribute("usuario");
}
/*Codigo para evitar que entren por la URL directamente.*/
 String pagina = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1, request.getRequestURI().lastIndexOf("."));
		int nivelPagina = 0;
		switch(pagina){
		case "altaUsuario":
		case "index":
		case "mostrarArticulos":
		case "mostrarArticulo":
		case "login":
			nivelPagina = 1;
		break;

		case "carrito":
		case "fichaUsuario":
		 nivelPagina = 2;
		 
		break;

		case "admin":
		case "altaArticulo":
		case "altaCategoria":
		case "articulo2":
		case "listaUsuarios":
		case "listadoArticulos":
		case "tipoUsuario":
			nivelPagina = 3;
		break;

		default:
			nivelPagina = 1;

		}
		try{
			
		if(nivelPagina > nivelAcceso){
			response.sendRedirect("login.jsp");
		}
		}catch(Exception e1){
			response.sendRedirect("index.jsp");
		}
					
		

		
		%>
		
<header class="encabezado">
<nav class=menuPpal>
	<input class="botonNav" type="button" onclick="history.back()" name="volver atrás" value="VOLVER">
	<a class="botonNav" href="contactar.jsp">CONTACTO</a> 
	<a class="botonNav" href="carrito.jsp">CARRITO</a>
	<a class="botonNav" href="Sv_Filtros?opcion=8&idUsuario=<%=idUsuario%>">HISTORIAL</a>
	<a class="botonNav" href="Sv_Login?opcion=2">SALIR</a>
	<div id="nombre"><p>Usuario: <%=usuario %></div>
</nav>
