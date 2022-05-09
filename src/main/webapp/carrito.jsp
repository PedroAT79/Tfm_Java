 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import = "modelo.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "controlador.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="estilos.css" rel="stylesheet" type="text/css" />
<title>Formulario de Contacto</title>
<!--<script type="text/javascript" src="JS/validarFormularios.js"></script>  -->
</head>
<body>
	<div class="header">
			<%@ include file="/INCLUDES/user-nav.jsp"%>
	</div>
	<div class="cuerpo">
		<section class="listado">
		<h4 class="tituloAlta">CESTA DE COMPRA</h4>
		<%	sesion = request.getSession(false);
			
			if(sesion.getAttribute("carrito")!=null){
					double totalPagar;
			
			
			%>
		<a class="botonNav"  href="index.jsp">Seguir Comprando</a>			
		<table class="formularioTabla" id="formularioTabla">
		<form action="Sv_CarritoCompra" method="POST">
		<input name="opcion" type="hidden" value="1">
		
				<thead>
					<th>Item</th>
					<th>Imagen</th>
					<th>Descripcion</th>
					<th>Estado</th>
					<th>Precio</th>
					<th>Cantidad</th>
					<th>Subtotal</th>
					<th>Añadir</th>
					<th>Quitar</th>
					<th>Eliminar</th>
							
				</thead> 
				<%
				
				ArrayList<DetalleCompra> lista =(ArrayList<DetalleCompra>) sesion.getAttribute("carrito");
				int idDetalle = 0;
				Articulo art = null;
				Imagen img =null;
				double totalCompra =0;
				for(int i=0; i<lista.size();i++){
					
				art = new Articulo(lista.get(i).getIdArticulo());
				img = new Imagen();
				art.obtenerArticulo(lista.get(i).getIdArticulo());
				img.obtenerUltimaImagen(art.getIdArticulo());
				idDetalle = idDetalle+1;
				%>	
				
				<tbody>
				<tr>
					<td><%=idDetalle%></td>
					<td><a href="Sv_Filtros?opcion=5&idArticulo=<%=art.getIdArticulo()%>"><img src="<%= img.getRuta() %>"></a></td>
					<td><%=lista.get(i).getNombreArticulo()%></td>
					<td><%=art.getEstadoConservacion() %></td>
					<td><%=lista.get(i).getPrecioArticulo()%></td>
					<td><%=lista.get(i).getCantidad() %></td>
					<td><%=lista.get(i).getPrecioCompra()%></td>
					
					<input type="hidden" name="totalPagar" value="<%=lista.get(i).getPrecioCompra() %>">
					<input type="hidden" name="idArticulo" value="<%=art.getIdArticulo() %>	">
					<input type="hidden" name="cantidadCompra" value="<%=lista.get(i).getCantidad()%>">
					<input type="hidden" name="nombreArticulo" value="<%=art.getNombreArticulo() %>">
					
					
					
					<td><a href="Sv_CarritoCompra?opcion=2&cantidadCompra=1&idArticulo=<%=art.getIdArticulo() %>"><img src="IMG/ICONOS/icons8-añadir-30.png"/></a></td><!-- obtener articulo para editar -->
					<td><a href="Sv_CarritoCompra?opcion=4&idDetalle=<%=lista.get(i).getIdDetalle() %>" ><img src="IMG/ICONOS/icons8-quitar-30.png"/></a></td><!-- quitar 1 ud -->
					<td><a href="Sv_CarritoCompra?opcion=5&idArticulo=<%=art.getIdArticulo()%>&idDetalle=<%=idDetalle%>" ><img src="IMG/ICONOS/icons8-eliminar-30.png"/></a></td> <!-- eliminar -->
					
					</tr>
					<% totalCompra = totalCompra + lista.get(i).getPrecioCompra(); %>
					<%} %>
					<th colspan=10>TOTAL COMPRA: <%=totalCompra %> Euros.
					<input type="hidden" name="totalCompra" value="<%=totalCompra%>"><%} %>
					
				</tbody>
				<input class="botonNav" type="submit" value="CONFIRMAR">
			</table>
			
		</form>
				
		</section>

	</div>
	<div class="footer">
		<%@ include file="/INCLUDES/footer.jsp"%>

	</div>

</body>
</html>