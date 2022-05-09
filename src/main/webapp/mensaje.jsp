<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="controlador.*"%>
<%@ page import="modelo.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="estilos.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<div class="header">

		<%@ include file="/INCLUDES/user-nav.jsp"%>
	
	</div>
	
	<div class="cuerpo">
		<main class="cuerpoPrincipal">
			<section>
				<h1 class="titulo">TU TIENDA DE CICLISMO CON ARTICULOS NUEVOS Y DE OCASION</h1>
				
				<%String tipoMensaje =(String)sesion.getAttribute("tipoMensaje");
					if (tipoMensaje !=""){
						switch(tipoMensaje){
						case "compra":%>
						<a class= "botonNav" href="Sv_Filtros?opcion=8&idUsuario=<%=idUsuario %>">VER PEDIDO</a>
						<div class="mensaje"><h3 class="titulo">COMPRA REALIZADA SATISFACTORIAMENTE</h3></div>
						<%break;
						
						case "altaUsuario":	%>
						<%if(nivelAcceso<3){ %>
						<a class= "botonNav" href="index.jsp" >VOLVER A PAGINA PRINCIPAL</a>
						<%}else{%>
							
							<a  class= "botonNav" href="Sv_Usuarios?opcion=2" >LISTADO DE USUARIOS</a>
						<% }%>
						<div class="mensaje"><h3 class="titulo">ALTA DE USUARIO REALIZADA</h3></div>
						<%break;
						
						case "altaArticulo":	%>
						<a class= "botonNav" href="Sv_CategoriasArticulos?opcion=4">VER LISTADO DE ARTICULOS</a>
						<div class="mensaje"><h3 class="titulo">ALTA DE ARTICULO REALIZADA</h3></div>
						<%break;
						
						case "bajaUsuario":	%>
						<%if(nivelAcceso==3){ %>
						<a  class= "botonNav" href="Sv_Usuarios?opcion=2" >LISTADO DE USUARIOS</a>
						<%}else{%>
							<a href="index.jsp" >VOLVER A PAGINA PRINCIPAL</a>
						<% }%>
						<div class="mensaje"><h3 class="titulo">BAJA DE USUARIO REALIZADA</h3></div>
						<%break;
						
						case "edicionUsuario":	%>
						
						<%if(nivelAcceso==3){ %>
						<a class= "botonNav" href="Sv_Usuarios?opcion=2" >LISTADO DE USUARIOS</a>
						<%}else{%>
							<a class= "botonNav" href="index.jsp" >VOLVER A PAGINA PRINCIPAL</a>
						<% }%>
						<div class="mensaje"><h3 class="titulo">EDICION DE USUARIO REALIZADA</h3></div>
						<%break;
						
						case "edicionArticulo":	%>
						<a class= "botonNav" href="Sv_CategoriasArticulos?opcion=4">VER LISTADO DE ARTICULOS</a>
						<div class="mensaje"><h3 class="titulo">	EDICION DE ARTICULO REALIZADA CORRECTAMENTE</h3></div>
						<%break;
						
						case "errorBorrado":	%>
						<a class= "botonNav" href="admin.jsp">VER LISTADO DE ARTICULOS</a>
						<div class="mensaje"><h3 class="titulo">ERROR AL INTENTAR BORRAR UN ITEM DE LA ORDEN DE  COMPRA</h3></div>
						<%break;
						
						case "borradoArticulo":	%>
						<a class= "botonNav" href="Sv_CategoriasArticulos?opcion=4">VER LISTADO DE ARTICULOS</a>
						<div class="mensaje"><h3 class="titulo">ARTICULO ELIMINADO CORRECTAMENTE</h3></div>
						<%break;
						
						default:%>
						<div class="mensaje"><h3 class="titulo">	ACCION REALIZADA CORRECTAMENTE</h3></div>
						<%}  }%>
						
					
						
				
			</section>
			
				
		</main>

	</div>
	
	<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>



</body>
</html>