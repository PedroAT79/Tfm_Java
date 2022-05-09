<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "controlador.Sv_CategoriasArticulos" %>
<%@ page import = "controlador.Sv_Filtros" %>
<%@ page import = "dao.*" %>
<%@ page import = "modelo.*" %>
<%@ page import = "java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="estilos.css" rel="stylesheet" type="text/css" />
<script src="JS/buscador.js"  type="text/javascript"></script>
<script src="JS/confirmador.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>

<body>
	<div class="header">
		<%@ include file="/INCLUDES/admin-header.jsp"%>
	
	</div>
	
			
	<div class="cuerpo">
		<section class="listado">
			<h2>Listado de Articulos en Stock</h2>
				<div class="buscador">	
				<label>Buscar:</label>
				<input id="terminoBuscado" type="text" onkeyup="buscador()"/>
				</div>	
				
				
				<table class="formularioTabla" id="formularioTabla">
				<thead>
					<th>Id.</th>
					<th>Imagen</th>
					<th>Subcategoria</th>
					<th>Marca</th>
					<th>Modelo</th>
					<th>Estado Conserv.</th>
					<th>Stock</th>
					<th>Precio</th>
					<th>Ver / Editar</th>
					<th>Eliminar</th>
				
				</thead>
				<%
					ArrayList<Articulo> listaArticulos = (ArrayList<Articulo>) request.getAttribute("listaArticulos");
				Categoria cat = null;
				Imagen img = null;
				for(int i=0; i<listaArticulos.size();i++){
						img = new Imagen();
						int idArticulo = listaArticulos.get(i).getIdArticulo();
						img.obtenerUltimaImagen(idArticulo);
						cat = new Categoria(listaArticulos.get(i).getIdSubcategoria());
						cat.obtenerCategoria(listaArticulos.get(i).getIdSubcategoria());
						Articulo art = new Articulo(listaArticulos.get(i).getIdArticulo(),
								listaArticulos.get(i).getNombreArticulo(),listaArticulos.get(i).getPrecioArticulo(),
								listaArticulos.get(i).getMarca(),listaArticulos.get(i).getModelo(),listaArticulos.get(i).getCantidad(),
								listaArticulos.get(i).getEstadoConservacion(),listaArticulos.get(i).getDescripcion(),listaArticulos.get(i).getIdSubcategoria());
								
				
				%>	
				<tbody>
					<tr>
					<td><%=art.getIdArticulo()%></td>
					<td><a class="ver" href="Sv_Filtros?opcion=5&idArticulo=<%=art.getIdArticulo()%>"><%if(img.getRuta()!= null){ %><img src="<%=img.getRuta()%>"><%}else{ %><img src="fotos/sinImagen.jpg"><%} %></a></td>
					<td><%=cat.getNombreCategoria()%></td>
					<td><%=art.getMarca()%></td>
					<td><%=art.getModelo()%></td>
					<td><%=art.getEstadoConservacion()%></td>
					<td><%=art.getCantidad()%></td>
					<td><%=art.getPrecioArticulo()%></td>
					
				
					
					<td><a href="Sv_CategoriasArticulos?idArticulo=<%=art.getIdArticulo()%>&opcion=6">
					<img src="IMG/ICONOS/icono-editar.png"/></a></td><!-- obtener articulo para editar -->
					
					<td><a href="Sv_CategoriasArticulos?opcion=5&idArticulo=<%=art.getIdArticulo()%>"  onclick="return confirmDelete()">
					<img src="IMG/ICONOS/icono-eliminar.png"/></a></td><!-- eliminar -->
					
					</tr>
					<%} %>
					<%listaArticulos.clear(); %>
	
				</tbody>	
			
				</table>
				<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>
	 
	
</body>
</html>