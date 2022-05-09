<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="controlador.Sv_CategoriasArticulos"%>
<%@ page import="modelo.*"%>
<%@ page import="modelo.Imagen"%>
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
		<%@ include file="/INCLUDES/admin-header.jsp"%>
	

	</div>
	<%
	Articulo artic = (Articulo) request.getAttribute("articulo");
	Imagen img = (Imagen) request.getAttribute("imagenObtenida");
	Categoria cat = new Categoria(artic.getIdSubcategoria());
	cat.obtenerCategoria(artic.getIdSubcategoria());
	%>
	<div class="tituloCard">
		<h2>FICHA DE ARTICULO:</h2>
		<h3>
			Id.Articulo:
			<%=artic.getIdArticulo()%> - <%=cat.getNombreCategoria()%></h3>
	</div>

	<div class=tablaEdicion>
	
		<form action="Sv_CategoriasArticulos?opcion=1" method="post">
			<table class="">
		<!--  	<h2>EDICION DE ARTICULO:# Id. <%=artic.getIdArticulo()%> -- <%=artic.getNombreArticulo()%> -- <%=artic.getPrecioArticulo()%>(e.) #</h2>-->
				<tr>
					<td>Marca:</td>
					<td><input type="text" name="marca" value="<%=artic.getMarca()%>"></td>
					<td>Modelo:</td>
					<td><input  type="text" name="modelo" value="<%=artic.getModelo()%>"></td>
					<td>Precio</td>
					<td><input type="text"  name="precioArticulo" value="<%=artic.getPrecioArticulo()%>"></td>
				</tr>
				<tr>
					<td>Id:</td><td><input type="text" name="idArticulo" value="<%=artic.getIdArticulo()%>"></td>
					<td>Nombre:</td><td><input type="text" name="nombreArticulo" value="<%=artic.getNombreArticulo()%>"></td>
					<td>Categoria:</td><td><input type="text" name="idSubcategoria" value="<%=artic.getIdSubcategoria()%>"></td>
					
				</tr>
				<tr>
					<td>Descripcion:</td><td><input type="text" name="descripcion" value="<%=artic.getDescripcion()%>"></td>
					<td>Estado:</td><td><input  type="text" name="estadoConservacion" value="<%=artic.getEstadoConservacion()%>"></td>
					<td>Stock:</td><td><input type="text" name="cantidad" value="<%=artic.getCantidad()%>"></td>
				
				</tr>

			</table>
				<input class="botonLogin" type="submit" value="Editar"></input><br>
		</form>
			

	</div>
	<div class=tablaEdicion>
	<div>
		<form action="Sv_CategoriasArticulos?opcion=9" method="post"  enctype="multipart/form-data" >
			<table class="">
			
			<h2>Insertar Fotos:(Max. 4)</h2>
				<tr>
					
					<tr><td><input type="hidden" name="idArticulo" value="<%=artic.getIdArticulo()%>"></td></tr>
					<tr><td><input type="hidden" name="idImagen" value="<%=img.getIdImagen()%>"></td></tr>
					<tr><td>Nombre del archivo:</td><td><input type="text" name="nombreImagen"></td>
					<td><input type="file" name="foto"><input type="submit" value="Enviar" >  </td>
					
				
				</tr>
				

			</table>
				
		</form>
		</div>	
	
				
	<div class="fotosArticulo grid-container">
	
	<%try{
		List<Imagen> listado = (ArrayList<Imagen>)request.getAttribute("listadoImagenes");
		for(int i=0; i<listado.size();i++){%>
		<div class="foto grid-item">
		<img src="<%=listado.get(i).getRuta()%>">
			<div><a href="Sv_CategoriasArticulos?opcion=10&idImagen=<%=listado.get(i).getIdImagen()%>&idArticulo=<%=listado.get(i).getIdArticulo()%>"><div>Borrar</div>
			</a>
			</div>
		
</div>			
	<%}}catch(Exception e1){ %>
		<div class="foto grid-item"><img src="fotos/sinImagen.jpg"></div>
			</a>
			</div>
	
	<%} %>	
	</div>
	
	
	
	<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>



</body>
</html>