<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "controlador.Sv_Filtros" %>
<%@ page import = "dao.*" %>
<%@ page import = "modelo.*" %>
<%@ page import = "java.util.*" %>

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
	
	
	<div class="cajaListadoArticulos">
		
				
				<%	Imagen img= null;
				
					ArrayList<Articulo> listaFitro = (ArrayList<Articulo>) request.getAttribute("listaFiltro");
					for(int i=0; i<listaFitro.size();i++){
						img = new Imagen();
						int idArticulo = listaFitro.get(i).getIdArticulo();
						int cantidad = listaFitro.get(i).getCantidad();
						img.obtenerUltimaImagen(idArticulo);
						String imagenRuta = img.getRuta();
						String nombre= listaFitro.get(i).getNombreArticulo();
						double precio = listaFitro.get(i).getPrecioArticulo();
						String marca =	listaFitro.get(i).getMarca();
						String modelo = listaFitro.get(i).getModelo();
						String estado =	listaFitro.get(i).getEstadoConservacion();
						String descripcion = listaFitro.get(i).getDescripcion();
						
						
				%>		<div class="articuloCategoria">
					
						<%if(img.getRuta()!=null){ %><img src="<%=img.getRuta()%>"><%}else{ %><img src="fotos/sinImagen.jpg"><%} %>
						
						<table >
						<tr><td><%=marca %></td><td><%=modelo %></td><td> *<%=estado %></td></tr>
						<tr><td colspan=3><%=precio %> Euros *IVA incl.</td></tr>
						</table>
						<div class="botones">
						<%if(cantidad > 0){ %>
						<%if(nivelAcceso<3){ %><div class="boton" ><a class="anadir" href="Sv_CarritoCompra?opcion=2&idArticulo=<%=idArticulo%>&cantidadCompra=1">Anadir al Carrito</a></div><%}else{ %><div class="boton"><%=cantidad %> ud. en Stock</div><% }%>
						<div class="boton" ><a class="ver" href="Sv_Filtros?opcion=5&idArticulo=<%=idArticulo%>">Ver Detalles</a></div><%}else{ %>
							<div class="boton" ><a class="" href="Sv_Filtros?opcion=4&filtro=<%=listaFitro.get(i).getIdSubcategoria()%>">Otros modelos</a></div>
							<div class="boton" ><a class="ver" href="Sv_Filtros?opcion=5&idArticulo=<%=idArticulo%>">Ver Detalles</a></div>
						<%}%></div>
						
						</div> <%}%>
						
										
					
								
				
			
			</div>	
			<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>

</body>
</html>