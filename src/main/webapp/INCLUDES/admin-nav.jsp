<%
HttpSession sesion = request.getSession(false);
int nivelAcceso = 0;
String usuario=null;
if(sesion.getAttribute("nivelAcceso")== null){
	nivelAcceso = 1;
}else{
	nivelAcceso = (int)sesion.getAttribute("nivelAcceso");
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
		case "detalleCompra":
		case "mensaje":
		case "error":
		 nivelPagina = 2;
		break;

		case "admin":
		case "altaArticulo":
		case "altaCategoria":
		case "articulo2":
		case "listaUsuarios":
		case "listadoArticulos":
		case "listadoOrdenes":
		case "tipoUsuario":
			nivelPagina = 3;
		break;

		default:
			nivelPagina = 1;

		}

		if(nivelPagina > nivelAcceso){
			response.sendRedirect("index.jsp");
					
		}

		
		%>
<nav class=menuPpal>
	
	<input class="botonNav" type="button" onclick="history.back()" name="volver atrás" value="VOLVER"> 
	<a class="botonNav" href="index.jsp">INDEX</a> 
	<%if(nivelAcceso==3){ %>
	<a class="botonNav" href="Sv_Usuarios?opcion=2">USUARIOS</a> 
	<a class="botonNav" href="Sv_CategoriasArticulos?opcion=4">STOCK</a>
	 <%} %>
	<a class="botonNav" href="Sv_Login?opcion=2">SALIR</a>
	<div id="nombre"><p>Usuario: <%=usuario %> || Administrador</p></div>
</nav>
