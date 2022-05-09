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
	
	<div class="cajaArticulo">
	<div class="articuloDetalle">
		<% Articulo art = (Articulo) request.getAttribute("articulo");
		Imagen img = (Imagen) request.getAttribute("imagenObtenida");
		
		%>
		<div class="detallePrincipal">
		<h2><%=art.getNombreArticulo() %></h2>
		<div class=imagenPrincipal>
		<%if(img.getRuta()!=null){ %><img src="<%=img.getRuta()%>"><%}else{ %><img src="fotos/sinImagen.jpg"><%} %>
		<div class="detalles">
		<table >
		<thead><h3>Caracteristicas de Producto.</h3></thead>
		<tr><td>Marca: <%=art.getMarca() %></td><td>Modelo: <%=art.getModelo() %></td></tr>
		<tr><td>Estado: <%=art.getEstadoConservacion()%></td><td> P.V.P: <%=art.getPrecioArticulo() %>0 Euros </td></tr>
		
		<tr><td colspan=2>Descripcion:</td></tr>
		<tr><td colspan=2 ><%=art.getDescripcion() %></td></tr>
		<%if(nivelAcceso<3){ %>
		<tr><td>Cantidad:</td><td><input id="valorInput" type="number" name="cantidadCompra" min="1" max="<%=art.getCantidad() %>" value="1"></td></tr> 
		<%}else{ %><tr><td>Stock en Almacen</td></tr><%} %>
		</table>
			<div class="botones">
					<%if(art.getCantidad()>0){ %>
						<div class="boton" ><%if(nivelAcceso<3){ %><a id="inputAñadir" "class="anadir" href="Sv_CarritoCompra?opcion=2&idArticulo=<%=art.getIdArticulo()%>&cantidadCompra=">Añadir al carrito</a><%}else{ %><%=art.getCantidad() %> ud.en Stock<%} %></div>
						<%} %><div class="boton" ><a class="ver" href="Sv_Filtros?opcion=4&filtro=<%=art.getIdSubcategoria()%>">Otros Modelos</a></div>
						
						</div>
				
		</div>
		</div>
		</div>
		</div>
		
		<div class="fotosMostrarArticulo grid-container2">
		
		
		<% 
		try{
			List<Imagen> listado = (ArrayList<Imagen>)request.getAttribute("listadoImagenes");
			for(int i=0; i<listado.size();i++){ %>
		
		<div class="fotosMostrarArticulo grid-item2">
		
		<img src="<%=listado.get(i).getRuta()%>">
	
		</div><%} %>
		
		
			<%}catch(Exception e1){ %>
		<div class="fotosMostrarArticulo grid-item2">
		
		<img src="fotos/sinImagen.jpg">
	
		</div>
			<%} %>
		</div>
		
		
			
				
	
				
			
			</div>	
			
		<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>
	<script>
		window.onload = function(){
			link = document.querySelector('#inputAñadir');
			link.addEventListener('click', function(e){
				 var valor = document.querySelector('#valorInput').value;
				this.href = this.href+valor;
			})
		}
	
	
	</script>
	
</body>
</html>